package com.example.biddingplatform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuctionDetailDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal currentBid;
    private BigDecimal minimumBid;
    private long timeLeftMs;
    private String imageUrl;
    private List<String> additionalImages;
    private SellerDto seller;
    private int bidCount;
    private String category;
    private boolean organic;
    private String weight;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private boolean watchlisted;
    private List<BidHistoryDto> bidHistory;
}
