package com.example.biddingplatform.service;

import com.example.biddingplatform.dto.CategoryDto;
import com.example.biddingplatform.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(cat -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(cat.getId());
            dto.setName(cat.getName());
            dto.setIcon(cat.getIcon());
            dto.setActiveAuctions(cat.getActiveAuctions());
            return dto;
        }).collect(Collectors.toList());
    }
}
