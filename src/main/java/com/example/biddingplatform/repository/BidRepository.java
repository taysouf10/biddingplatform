package com.example.biddingplatform.repository;

import com.example.biddingplatform.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByAuctionIdOrderByTimestampDesc(Long auctionId);
    List<Bid> findByBidderName(String bidderName);
}
