package com.example.biddingplatform.controller;

import com.example.biddingplatform.dto.ApiResponse;
import com.example.biddingplatform.dto.StatsDto;
import com.example.biddingplatform.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    public ApiResponse<StatsDto> stats() {
        return new ApiResponse<>(true, statsService.getStats(), null);
    }
}
