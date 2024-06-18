package com.example.demointerview.controller;

import com.example.demointerview.dto.BookDto;
import com.example.demointerview.dto.WordDtoReq;
import com.example.demointerview.dto.WordDtoRes;
import com.example.demointerview.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text")
public class TextAnalysisController {

    private final WordService wordService;

    public TextAnalysisController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public ResponseEntity<WordDtoRes> getTextAnalysis(@RequestBody WordDtoReq wordDtoReq) {

        WordDtoRes wordDtoRes = wordService.getTextAnalysis(wordDtoReq.getText());
        return new ResponseEntity<>(wordDtoRes, HttpStatus.OK);
    }
}
