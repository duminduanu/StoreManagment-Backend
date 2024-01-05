package com.example.StoreManagement.service;

import com.example.StoreManagement.dto.StoreDto;
import com.example.StoreManagement.model.Store;
import com.example.StoreManagement.repository.StoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createOrUpdateStore(Store store) {
        return storeRepository.save(store);
    }

    public List<StoreDto> getAllStores() {

        List<Store> storeList = storeRepository.findAll();

        return storeList.stream().map(store ->{
            StoreDto storeDto = new StoreDto();
            BeanUtils.copyProperties(store, storeDto);
            return storeDto;
        }).toList();
    }

    public Store getStoreById(String id) {
        return storeRepository.findById(id).orElse(null);
    }

    public List<StoreDto> getStoreByNameAndCategory(String name, String category) {
        return storeRepository.findByNameIsLikeIgnoreCaseOrCategoryIsLikeIgnoreCase(name, category).stream().map(store ->{
//        return storeRepository.findByNameIsLikeIgnoreCase(name).stream().map(store ->{
            StoreDto storeDto = new StoreDto();
            BeanUtils.copyProperties(store, storeDto);
            return storeDto;
        }).toList();
    }

    public void deleteStore(String id) {
        storeRepository.deleteById(id);
    }

}
