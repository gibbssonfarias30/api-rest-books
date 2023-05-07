package com.backfcdev.apirestbooks.repository;

import com.backfcdev.apirestbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
