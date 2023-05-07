package com.backfcdev.apirestbooks.service.impl;


import com.backfcdev.apirestbooks.exception.EntityNotFoundException;
import com.backfcdev.apirestbooks.model.Category;
import com.backfcdev.apirestbooks.repository.ICategoryRepository;

import com.backfcdev.apirestbooks.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Category update(long id, Category category) {
        categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(long id) {
        categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        categoryRepository.deleteById(id);
    }
}
