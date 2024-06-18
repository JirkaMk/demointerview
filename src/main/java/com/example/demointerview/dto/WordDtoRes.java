package com.example.demointerview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordDtoRes {

    private Long totalWordCount;
    private String topWord;
    private Long topWordCount;
}
