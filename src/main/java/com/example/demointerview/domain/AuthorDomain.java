package com.example.demointerview.domain;

import java.time.LocalDate;

public record AuthorDomain(
        Long id,
        String name,
        String surname,
        LocalDate birthDate
) {
    public AuthorDomain {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Author name must not be blank");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Author surname must not be blank");
        }
    }
}