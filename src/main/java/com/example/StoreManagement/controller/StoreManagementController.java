package com.example.StoreManagement.controller;

import com.example.StoreManagement.dto.StoreDto;
import com.example.StoreManagement.model.Store;
import com.example.StoreManagement.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/store")
public class StoreManagementController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/save")
    public ResponseEntity<String> saveStore(@RequestParam("storeDto") String storeDtoJson,
                                            @RequestParam("uploadImage") MultipartFile uploadImage) {


        try {

//        BeanUtils.copyProperties(storeDto, store);
            Store store = new ObjectMapper().readValue(storeDtoJson, Store.class);

            if (uploadImage != null) {
                store.setImage(uploadImage.getBytes());
            }

            storeService.createOrUpdateStore(store);

            return ResponseEntity.ok(store.getId());

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid data: " + e.getMessage());
        }
    }

    @GetMapping("/getAllStore")
    public ResponseEntity<?> getAllStores() {
        try {

            return ResponseEntity.ok(storeService.getAllStores());

        } catch (Exception e) {

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid data: " + e.getMessage());

        }


    }

    @PutMapping("/editStore")
    public ResponseEntity<?> updateStore(@RequestParam("storeDto") String storeDtoJson,
                                         @RequestParam(value = "uploadImage", required = false) MultipartFile uploadImage) {


        try {

//        BeanUtils.copyProperties(storeDto, store);

            Store store = new ObjectMapper().readValue(storeDtoJson, Store.class);

            if (uploadImage == null) {

                Store currentStore = storeService.getStoreById(store.getId());
                store.setImage(currentStore.getImage());

            } else {
                store.setImage(uploadImage.getBytes());
            }

            storeService.createOrUpdateStore(store);

            return ResponseEntity.ok(store.getId());

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid data: " + e.getMessage());
        }


    }

    @DeleteMapping("/deleteStore/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") String id) {
        try {

            storeService.deleteStore(id);

            return ResponseEntity.ok("Successfully deleted " + id);

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid data: " + e.getMessage());
        }
    }


    @GetMapping("/searchByNameOrCategory")
    public ResponseEntity<?> getStoresByNameAndCategory(@RequestParam(value = "name", required = false) String name) {

        try {

            return ResponseEntity.ok(storeService.getStoreByNameAndCategory(name, name));

        } catch (Exception e) {

            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid data: " + e.getMessage());

        }

    }


}
