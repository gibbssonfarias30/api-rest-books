package com.backfcdev.apirestbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;

    private String name;

    private String description;

    private CategoryDTO category;
}
