package com.example.biddingplatform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BidResponseDto {
    private Long bidId;
    private Long auctionId;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private boolean winning;
    private BigDecimal nextMinimumBid;
}
