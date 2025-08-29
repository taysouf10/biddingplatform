package com.example.biddingplatform;

import com.example.biddingplatform.entity.Auction;
import com.example.biddingplatform.entity.Category;
import com.example.biddingplatform.repository.AuctionRepository;
import com.example.biddingplatform.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category("fruits", "Fruits", "\uD83C\uDF4E", 25));
                categoryRepository.save(new Category("vegetables", "Vegetables", "\uD83E\uDD55", 32));
                categoryRepository.save(new Category("herbs", "Herbs", "\uD83C\uDF3F", 8));
            }

            if (auctionRepository.count() == 0) {
                Auction auction = Auction.builder()
                        .title("Premium Organic Tomatoes")
                        .description("Fresh, vine-ripened organic tomatoes from local farm")
                        .currentBid(new BigDecimal("25.50"))
                        .minimumBid(new BigDecimal("30.00"))
                        .imageUrl("https://your-cdn.com/images/tomatoes.jpg")
                        .category("vegetables")
                        .organic(true)
                        .weight("5 kg")
                        .location("California, USA")
                        .endTime(LocalDateTime.now().plusHours(2))
                        .createdAt(LocalDateTime.now())
                        .build();
                auctionRepository.save(auction);
            }
        };
    }
}
