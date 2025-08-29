package com.example.biddingplatform.dto;

import lombok.Data;

@Data
public class SellerDto {
    private Long id;
    private String name;
    private double rating;
    private boolean verified;
    private String location;
    private String memberSince;
}
