package com.example.biddingplatform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WatchlistItemDto {
    private Long id;
    private boolean watchlisted;
    private String title;
    private BigDecimal currentBid;
    private long timeLeftMs;
    private String imageUrl;
    private LocalDateTime addedToWatchlist;
}
