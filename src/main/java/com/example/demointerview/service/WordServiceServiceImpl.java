package com.example.demointerview.service;

import com.example.demointerview.dto.WordDtoReq;
import com.example.demointerview.dto.WordDtoRes;
import org.springframework.stereotype.Service;

@Service
public class WordServiceServiceImpl implements WordService {

    public WordDtoRes getTextAnalysis(String text) {
        var wordDtoRes = new WordDtoRes();
        String[] words = text.split("\\s");



        wordDtoRes.setTotalWordCount((long) words.length);
        return wordDtoRes;
    }
}
