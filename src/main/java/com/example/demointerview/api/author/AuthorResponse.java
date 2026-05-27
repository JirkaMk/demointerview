package com.example.demointerview.api.author;

import java.time.LocalDate;

public record AuthorResponse(
        Long id,
        String name,
        String surname,
        LocalDate birthDate
) {
}