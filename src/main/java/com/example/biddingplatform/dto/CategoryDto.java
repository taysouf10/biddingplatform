package com.example.biddingplatform.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String id;
    private String name;
    private String icon;
    private int activeAuctions;
}
