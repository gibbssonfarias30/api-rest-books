package com.backfcdev.apirestbooks.service.impl;

import com.backfcdev.apirestbooks.exception.EntityNotFoundException;
import com.backfcdev.apirestbooks.model.Book;
import com.backfcdev.apirestbooks.repository.IBookRepository;
import com.backfcdev.apirestbooks.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements IBookService {
    private final IBookRepository bookRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book update(long id, Book book) {
        bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return bookRepository.save(book);
    }

    @Override
    public void delete(long id) {
        bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        bookRepository.deleteById(id);
    }
}
