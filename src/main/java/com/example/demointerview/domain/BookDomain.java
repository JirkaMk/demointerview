package com.example.demointerview.domain;

public record BookDomain(
        Long id,
        String name,
        Long authorId,
        Integer year,
        AuthorDomain author
) {
    public BookDomain {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Book name must not be blank");
        }
    }
}