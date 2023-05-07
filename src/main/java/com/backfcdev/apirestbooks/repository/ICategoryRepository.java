package com.backfcdev.apirestbooks.repository;

import com.backfcdev.apirestbooks.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
