package com.example.demointerview.api.author;

import java.time.LocalDate;

public record CreateAuthorRequest(
        String name,
        String surname,
        LocalDate birthDate
) {
    public CreateAuthorRequest {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Author name must not be blank");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Author surname must not be blank");
        }
    }
}