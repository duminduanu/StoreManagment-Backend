package com.example.StoreManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreDto {
    private String id;
    private String name;
    private String description;
    private String emailAddress;
    private byte[] image;
    private String category;
    private String address;
    private String latitude;
    private String longitude;
}
