package com.example.biddingplatform.mapper;

import com.example.biddingplatform.dto.*;
import com.example.biddingplatform.entity.Auction;
import com.example.biddingplatform.entity.Bid;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.stream.Collectors;

@Component
public class AuctionMapper {

    public AuctionSummaryDto toSummaryDto(Auction auction, boolean watchlisted) {
        AuctionSummaryDto dto = new AuctionSummaryDto();
        dto.setId(auction.getId());
        dto.setTitle(auction.getTitle());
        dto.setDescription(auction.getDescription());
        dto.setCurrentBid(auction.getCurrentBid());
        dto.setMinimumBid(auction.getMinimumBid());
        dto.setTimeLeftMs(Duration.between(java.time.LocalDateTime.now(), auction.getEndTime()).toMillis());
        dto.setImageUrl(auction.getImageUrl());
        dto.setBidCount(auction.getBids().size());
        dto.setCategory(auction.getCategory());
        dto.setOrganic(auction.isOrganic());
        dto.setWeight(auction.getWeight());
        dto.setLocation(auction.getLocation());
        dto.setEndTime(auction.getEndTime());
        dto.setCreatedAt(auction.getCreatedAt());
        dto.setWatchlisted(watchlisted);
        return dto;
    }

    public AuctionDetailDto toDetailDto(Auction auction, boolean watchlisted) {
        AuctionDetailDto dto = new AuctionDetailDto();
        dto.setId(auction.getId());
        dto.setTitle(auction.getTitle());
        dto.setDescription(auction.getDescription());
        dto.setCurrentBid(auction.getCurrentBid());
        dto.setMinimumBid(auction.getMinimumBid());
        dto.setTimeLeftMs(Duration.between(java.time.LocalDateTime.now(), auction.getEndTime()).toMillis());
        dto.setImageUrl(auction.getImageUrl());
        dto.setAdditionalImages(java.util.List.of());
        dto.setSeller(new SellerDto());
        dto.setBidCount(auction.getBids().size());
        dto.setCategory(auction.getCategory());
        dto.setOrganic(auction.isOrganic());
        dto.setWeight(auction.getWeight());
        dto.setEndTime(auction.getEndTime());
        dto.setCreatedAt(auction.getCreatedAt());
        dto.setWatchlisted(watchlisted);
        dto.setBidHistory(auction.getBids().stream()
                .sorted((a,b)->b.getTimestamp().compareTo(a.getTimestamp()))
                .map(this::toBidHistoryDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public BidHistoryDto toBidHistoryDto(Bid bid) {
        BidHistoryDto dto = new BidHistoryDto();
        dto.setAmount(bid.getAmount());
        dto.setBidderName(bid.getBidderName());
        dto.setTimestamp(bid.getTimestamp());
        return dto;
    }

    public WatchlistItemDto toWatchlistDto(Auction auction, WatchlistItemDto dto) {
        dto.setId(auction.getId());
        dto.setTitle(auction.getTitle());
        dto.setCurrentBid(auction.getCurrentBid());
        dto.setTimeLeftMs(Duration.between(java.time.LocalDateTime.now(), auction.getEndTime()).toMillis());
        dto.setImageUrl(auction.getImageUrl());
        return dto;
    }
}
