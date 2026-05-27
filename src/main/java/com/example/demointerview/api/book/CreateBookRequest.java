package com.example.demointerview.api.book;

public record CreateBookRequest(
        String name,
        Long authorId,
        Integer year
) {
}