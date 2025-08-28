package com.example.biddingplatform.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BidHistoryDto {
    private BigDecimal amount;
    private String bidderName;
    private LocalDateTime timestamp;
}
