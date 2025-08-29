package com.example.biddingplatform.service;

import com.example.biddingplatform.dto.WatchlistItemDto;
import com.example.biddingplatform.dto.BidResponseDto;
import com.example.biddingplatform.mapper.AuctionMapper;
import com.example.biddingplatform.repository.AuctionRepository;
import com.example.biddingplatform.repository.BidRepository;
import com.example.biddingplatform.repository.WatchlistRepository;
import com.example.biddingplatform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final WatchlistRepository watchlistRepository;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final AuctionMapper auctionMapper;
    private final UserRepository userRepository;

    public UserService(WatchlistRepository watchlistRepository,
                       AuctionRepository auctionRepository,
                       BidRepository bidRepository,
                       AuctionMapper auctionMapper,
                       UserRepository userRepository) {
        this.watchlistRepository = watchlistRepository;
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
        this.auctionMapper = auctionMapper;
        this.userRepository = userRepository;
    }

    public List<WatchlistItemDto> getWatchlist(String username) {
        Long userId = userRepository.findByUsername(username).orElseThrow().getId();
        return watchlistRepository.findByUserId(userId).stream().map(item -> {
            WatchlistItemDto dto = new WatchlistItemDto();
            dto.setAddedToWatchlist(item.getAddedAt());
            auctionRepository.findById(item.getAuction().getId())
                    .ifPresent(auction -> auctionMapper.toWatchlistDto(auction, dto));
            return dto;
        }).collect(Collectors.toList());
    }

    public List<BidResponseDto> getBids(String bidderName) {
        return bidRepository.findByBidderName(bidderName).stream().map(bid -> {
            BidResponseDto dto = new BidResponseDto();
            dto.setBidId(bid.getId());
            dto.setAuctionId(bid.getAuction().getId());
            dto.setAmount(bid.getAmount());
            dto.setTimestamp(bid.getTimestamp());
            dto.setWinning(bid.getAuction().getCurrentBid().equals(bid.getAmount()));
            dto.setNextMinimumBid(bid.getAmount().add(java.math.BigDecimal.valueOf(2)));
            return dto;
        }).collect(Collectors.toList());
    }
}
