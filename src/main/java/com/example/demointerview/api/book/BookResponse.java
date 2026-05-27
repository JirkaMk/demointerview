package com.example.demointerview.api.book;

public record BookResponse(
        Long id,
        String name,
        Long authorId,
        Integer year
) {
}
