package com.backfcdev.apirestbooks.controller;

import com.backfcdev.apirestbooks.dto.BookDTO;
import com.backfcdev.apirestbooks.dto.CategoryDTO;
import com.backfcdev.apirestbooks.model.Book;
import com.backfcdev.apirestbooks.model.Category;
import com.backfcdev.apirestbooks.service.IBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    private final ModelMapper mapper;


    @GetMapping
    ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(bookService.findAll()
                .stream()
                .map(this::convertToDto)
                .toList());
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody BookDTO dto){
        Book book = bookService.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(book.getId()).toUri();
        log.info("customer saved: {}", book);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(convertToDto(bookService.findById(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<BookDTO> update(@PathVariable long id, @RequestBody BookDTO dto){
        Book book= bookService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDto(book));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private Book convertToEntity(BookDTO dto){
        return mapper.map(dto, Book.class);
    }

    private BookDTO convertToDto(Book obj){
        return mapper.map(obj, BookDTO.class);
    }
}
