package com.example.demointerview.api.book;

import com.example.demointerview.api.author.AuthorResponse;

public record BookDetailResponse(
        Long id,
        String name,
        Integer year,
        AuthorResponse author
) {
}