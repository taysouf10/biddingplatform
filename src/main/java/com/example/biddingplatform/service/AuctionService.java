package com.example.biddingplatform.service;

import com.example.biddingplatform.dto.*;
import com.example.biddingplatform.entity.Auction;
import com.example.biddingplatform.entity.Bid;
import com.example.biddingplatform.entity.WatchlistItem;
import com.example.biddingplatform.mapper.AuctionMapper;
import com.example.biddingplatform.repository.AuctionRepository;
import com.example.biddingplatform.repository.BidRepository;
import com.example.biddingplatform.repository.WatchlistRepository;
import com.example.biddingplatform.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final WatchlistRepository watchlistRepository;
    private final AuctionMapper auctionMapper;
    private final UserRepository userRepository;

    public AuctionService(AuctionRepository auctionRepository,
                          BidRepository bidRepository,
                          WatchlistRepository watchlistRepository,
                          AuctionMapper auctionMapper,
                          UserRepository userRepository) {
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
        this.watchlistRepository = watchlistRepository;
        this.auctionMapper = auctionMapper;
        this.userRepository = userRepository;
    }

    public List<AuctionSummaryDto> getAuctions(String username) {
        Long userId = null;
        if (username != null) {
            userId = userRepository.findByUsername(username)
                    .map(u -> u.getId())
                    .orElse(null);
        }
        Long finalUserId = userId;
        return auctionRepository.findAll().stream()
                .map(a -> auctionMapper.toSummaryDto(a,
                        finalUserId != null && watchlistRepository.existsByUserIdAndAuctionId(finalUserId, a.getId())))
                .collect(Collectors.toList());
    }

    public Optional<AuctionDetailDto> getAuction(Long id, String username) {
        Long userId = null;
        if (username != null) {
            userId = userRepository.findByUsername(username)
                    .map(u -> u.getId())
                    .orElse(null);
        }
        Long finalUserId = userId;
        return auctionRepository.findById(id)
                .map(a -> auctionMapper.toDetailDto(a,
                        finalUserId != null && watchlistRepository.existsByUserIdAndAuctionId(finalUserId, a.getId())));
    }

    @Transactional
    public Optional<BidResponseDto> placeBid(Long auctionId, PlaceBidRequest request, String bidderName) {
        return auctionRepository.findById(auctionId).map(auction -> {
            BigDecimal min = auction.getCurrentBid().max(auction.getMinimumBid());
            if (request.amount().compareTo(min) < 0) {
                return null;
            }
            Bid bid = Bid.builder()
                    .auction(auction)
                    .amount(request.amount())
                    .bidderName(bidderName)
                    .timestamp(LocalDateTime.now())
                    .build();
            bidRepository.save(bid);
            auction.setCurrentBid(request.amount());
            BidResponseDto resp = new BidResponseDto();
            resp.setBidId(bid.getId());
            resp.setAuctionId(auctionId);
            resp.setAmount(request.amount());
            resp.setTimestamp(bid.getTimestamp());
            resp.setWinning(true);
            resp.setNextMinimumBid(request.amount().add(BigDecimal.valueOf(2))); // simple increment
            return resp;
        });
    }

    @Transactional
    public boolean updateWatchlist(Long auctionId, WatchlistActionRequest request, String username) {
        Long userId = userRepository.findByUsername(username).orElseThrow().getId();
        if ("add".equalsIgnoreCase(request.action())) {
            if (watchlistRepository.existsByUserIdAndAuctionId(userId, auctionId)) {
                return true;
            }
            Auction auction = auctionRepository.findById(auctionId).orElseThrow();
            WatchlistItem item = WatchlistItem.builder()
                    .auction(auction)
                    .userId(userId)
                    .addedAt(LocalDateTime.now())
                    .build();
            watchlistRepository.save(item);
            return true;
        } else if ("remove".equalsIgnoreCase(request.action())) {
            watchlistRepository.deleteByUserIdAndAuctionId(userId, auctionId);
            return false;
        }
        throw new IllegalArgumentException("Unknown action");
    }
}
