package com.example.demointerview.controller;

import com.example.demointerview.api.book.BookDetailResponse;
import com.example.demointerview.api.book.BookResponse;
import com.example.demointerview.api.book.CreateBookRequest;
import com.example.demointerview.domain.BookDomain;
import com.example.demointerview.mapper.BookMapper;
import com.example.demointerview.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody CreateBookRequest createBookRequest) {
        BookDomain bookDomain = BookMapper.INSTANCE.bookToBookDomain(createBookRequest);
        BookDomain savedBook = bookService.createBook(bookDomain);
        BookResponse bookResponse = BookMapper.INSTANCE.bookDomainToBookResponse(savedBook);

        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailResponse> getBook(@PathVariable Long id) {
        BookDomain book = bookService.getBook(id);
        BookDetailResponse bookResponse = BookMapper.INSTANCE.bookDomainToBookDetailResponse(book);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
}
