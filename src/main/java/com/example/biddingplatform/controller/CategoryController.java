package com.example.biddingplatform.controller;

import com.example.biddingplatform.dto.ApiResponse;
import com.example.biddingplatform.dto.CategoryDto;
import com.example.biddingplatform.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ApiResponse<List<CategoryDto>> categories() {
        return new ApiResponse<>(true, categoryService.getCategories(), null);
    }
}
