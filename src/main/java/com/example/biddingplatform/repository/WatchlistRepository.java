package com.example.biddingplatform.repository;

import com.example.biddingplatform.entity.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<WatchlistItem, Long> {
    List<WatchlistItem> findByUserId(Long userId);
    boolean existsByUserIdAndAuctionId(Long userId, Long auctionId);
    void deleteByUserIdAndAuctionId(Long userId, Long auctionId);
}
