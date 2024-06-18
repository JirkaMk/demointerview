package com.example.demointerview.service;

import com.example.demointerview.dto.WordDtoReq;
import com.example.demointerview.dto.WordDtoRes;

public interface WordService {
    public WordDtoRes getTextAnalysis(String text);
}
