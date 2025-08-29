package com.example.biddingplatform.dto;

import jakarta.validation.constraints.NotBlank;

public record WatchlistActionRequest(@NotBlank String action) {}
