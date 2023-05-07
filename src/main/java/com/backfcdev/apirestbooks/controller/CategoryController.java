package com.backfcdev.apirestbooks.controller;


import com.backfcdev.apirestbooks.dto.BookDTO;
import com.backfcdev.apirestbooks.dto.CategoryDTO;
import com.backfcdev.apirestbooks.model.Book;
import com.backfcdev.apirestbooks.model.Category;
import com.backfcdev.apirestbooks.service.IBookService;
import com.backfcdev.apirestbooks.service.ICategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    private final ModelMapper mapper;


    @GetMapping
    ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok(categoryService.findAll()
                .stream()
                .map(this::convertToDto)
                .toList());
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody CategoryDTO dto){
        Category category = categoryService.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId()).toUri();
        log.info("customer saved: {}", category);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(convertToDto(categoryService.findById(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryDTO> update(@PathVariable long id, @RequestBody CategoryDTO dto){
        Category category = categoryService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDto(category));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }



    private Category convertToEntity(CategoryDTO dto){
        return mapper.map(dto, Category.class);
    }

    private CategoryDTO convertToDto(Category obj){
        return mapper.map(obj, CategoryDTO.class);
    }
}
