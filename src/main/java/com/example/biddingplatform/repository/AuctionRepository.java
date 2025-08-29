package com.example.biddingplatform.repository;

import com.example.biddingplatform.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
