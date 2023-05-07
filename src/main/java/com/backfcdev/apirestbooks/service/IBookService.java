package com.backfcdev.apirestbooks.service;

import com.backfcdev.apirestbooks.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book save(Book book);
    Book findById(Long id);
    Book update(long id, Book book);
    void delete(long id);
}
