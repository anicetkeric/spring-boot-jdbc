package com.sample.bootlabs.model;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * Data model class for "Book"
 *
 * @author @bootteam
 */
@Builder
public record Book(
        Long id,
        String title,
        String isbn,
        String description,
        Integer page,
        BigDecimal price,
        Long authorId
) {
}