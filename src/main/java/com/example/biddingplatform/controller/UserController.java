package com.example.biddingplatform.controller;

import com.example.biddingplatform.dto.ApiResponse;
import com.example.biddingplatform.dto.BidResponseDto;
import com.example.biddingplatform.dto.WatchlistItemDto;
import com.example.biddingplatform.service.UserService;
import org.springframework.security.core.Authentication;
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
    public ApiResponse<List<WatchlistItemDto>> watchlist(Authentication authentication) {
        return new ApiResponse<>(true, userService.getWatchlist(authentication.getName()), null);
    }

    @GetMapping("/bids")
    public ApiResponse<List<BidResponseDto>> bids(Authentication authentication) {
        return new ApiResponse<>(true, userService.getBids(authentication.getName()), null);
    }
}
