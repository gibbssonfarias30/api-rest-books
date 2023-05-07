package com.backfcdev.apirestbooks.service;


import com.backfcdev.apirestbooks.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(long id, Category category);
    void delete(long id);
}
