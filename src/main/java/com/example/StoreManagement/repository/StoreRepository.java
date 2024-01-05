package com.example.StoreManagement.repository;

import com.example.StoreManagement.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StoreRepository extends MongoRepository<Store, String> {
    List<Store> findByNameIsLikeIgnoreCaseOrCategoryIsLikeIgnoreCase(String name, String Category);
//    List<Store> findByNameIsLikeIgnoreCase(String name);
}
