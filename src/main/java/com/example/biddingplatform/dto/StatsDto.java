package com.example.biddingplatform.dto;

import lombok.Data;

@Data
public class StatsDto {
    private int activeAuctions;
    private int totalFarmers;
    private int auctionsEndingToday;
    private int totalBidsThisWeek;
}
