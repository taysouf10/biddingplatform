package com.example.biddingplatform.service;

import com.example.biddingplatform.dto.StatsDto;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    public StatsDto getStats() {
        StatsDto dto = new StatsDto();
        dto.setActiveAuctions(150);
        dto.setTotalFarmers(1200);
        dto.setAuctionsEndingToday(45);
        dto.setTotalBidsThisWeek(2800);
        return dto;
    }
}
