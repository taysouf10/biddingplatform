package com.example.biddingplatform.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PlaceBidRequest(@NotNull @DecimalMin("0.0") BigDecimal amount) {}
