package com.example.biddingplatform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AuctionSummaryDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal currentBid;
    private BigDecimal minimumBid;
    private long timeLeftMs;
    private String imageUrl;
    private int bidCount;
    private String category;
    private boolean organic;
    private String weight;
    private String location;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private boolean watchlisted;
}
