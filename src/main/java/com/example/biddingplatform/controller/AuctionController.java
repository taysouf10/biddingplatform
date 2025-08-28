package com.example.biddingplatform.controller;

import com.example.biddingplatform.dto.*;
import com.example.biddingplatform.service.AuctionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    public ApiResponse<List<AuctionSummaryDto>> getAuctions() {
        Long userId = 1L; // demo
        return new ApiResponse<>(true, auctionService.getAuctions(userId), null);
    }

    @GetMapping("/auctions/{id}")
    public ResponseEntity<ApiResponse<AuctionDetailDto>> getAuction(@PathVariable Long id) {
        Long userId = 1L;
        return auctionService.getAuction(id, userId)
                .map(dto -> ResponseEntity.ok(new ApiResponse<>(true, dto, null)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/auctions/{id}/bids")
    public ResponseEntity<ApiResponse<BidResponseDto>> placeBid(@PathVariable Long id,
                                                                @Valid @RequestBody PlaceBidRequest request) {
        String bidder = "John D."; // demo user
        return auctionService.placeBid(id, request, bidder)
                .map(resp -> ResponseEntity.ok(new ApiResponse<>(true, resp, "Bid placed successfully")))
                .orElse(ResponseEntity.badRequest()
                        .body(new ApiResponse<>(false, null, "Bid amount must be at least minimum")));
    }

    @PostMapping("/auctions/{id}/watchlist")
    public ApiResponse<WatchlistItemDto> watchlist(@PathVariable Long id,
                                                   @Valid @RequestBody WatchlistActionRequest request) {
        Long userId = 1L;
        boolean watchlisted = auctionService.updateWatchlist(id, request, userId);
        WatchlistItemDto dto = new WatchlistItemDto();
        dto.setId(id);
        dto.setWatchlisted(watchlisted);
        return new ApiResponse<>(true, dto, watchlisted ? "Added to watchlist" : "Removed from watchlist");
    }
}
