package com.example.biddingplatform.controller;

import com.example.biddingplatform.dto.ApiResponse;
import com.example.biddingplatform.dto.BidResponseDto;
import com.example.biddingplatform.dto.WatchlistItemDto;
import com.example.biddingplatform.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/watchlist")
    public ApiResponse<List<WatchlistItemDto>> watchlist() {
        Long userId = 1L;
        return new ApiResponse<>(true, userService.getWatchlist(userId), null);
    }

    @GetMapping("/bids")
    public ApiResponse<List<BidResponseDto>> bids() {
        String bidder = "John D.";
        return new ApiResponse<>(true, userService.getBids(bidder), null);
    }
}
