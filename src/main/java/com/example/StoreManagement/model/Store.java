package com.example.StoreManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Store {

    @Id
    private String id;

    private String name;
    private String description;

    @Indexed(unique = true)
    private String emailAddress;

    @Field("image")
    private byte[] image;
    private String category;
    private String address;
    private String latitude;
    private String longitude;

}
