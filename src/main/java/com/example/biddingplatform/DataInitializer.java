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
                Auction tomatoes = Auction.builder()
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
                auctionRepository.save(tomatoes);

                Auction apples = Auction.builder()
                        .title("Crisp Gala Apples")
                        .description("Sweet and crunchy Gala apples freshly picked")
                        .currentBid(new BigDecimal("15.00"))
                        .minimumBid(new BigDecimal("18.00"))
                        .imageUrl("https://your-cdn.com/images/apples.jpg")
                        .category("fruits")
                        .organic(true)
                        .weight("3 kg")
                        .location("Washington, USA")
                        .endTime(LocalDateTime.now().plusHours(4))
                        .createdAt(LocalDateTime.now())
                        .build();
                auctionRepository.save(apples);

                Auction lettuce = Auction.builder()
                        .title("Hydroponic Lettuce")
                        .description("Green, hydroponically grown lettuce heads")
                        .currentBid(new BigDecimal("10.00"))
                        .minimumBid(new BigDecimal("12.00"))
                        .imageUrl("https://your-cdn.com/images/lettuce.jpg")
                        .category("vegetables")
                        .organic(false)
                        .weight("2 kg")
                        .location("Florida, USA")
                        .endTime(LocalDateTime.now().plusHours(3))
                        .createdAt(LocalDateTime.now())
                        .build();
                auctionRepository.save(lettuce);

                Auction basil = Auction.builder()
                        .title("Fresh Basil Bunches")
                        .description("Aromatic basil leaves perfect for cooking")
                        .currentBid(new BigDecimal("5.00"))
                        .minimumBid(new BigDecimal("7.50"))
                        .imageUrl("https://your-cdn.com/images/basil.jpg")
                        .category("herbs")
                        .organic(true)
                        .weight("1 kg")
                        .location("Oregon, USA")
                        .endTime(LocalDateTime.now().plusHours(6))
                        .createdAt(LocalDateTime.now())
                        .build();
                auctionRepository.save(basil);
            }
        };
    }
}
