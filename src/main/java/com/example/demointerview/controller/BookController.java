package com.example.demointerview.controller;

import com.example.demointerview.api.book.BookDetailResponse;
import com.example.demointerview.api.book.BookResponse;
import com.example.demointerview.api.book.CreateBookRequest;
import com.example.demointerview.domain.BookDomain;
import com.example.demointerview.mapper.BookMapper;
import com.example.demointerview.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody CreateBookRequest createBookRequest) {
        BookDomain bookDomain = BookMapper.INSTANCE.bookToBookDomain(createBookRequest);
        BookDomain savedBook = bookService.createBook(bookDomain);

        return BookMapper.INSTANCE.bookDomainToBookResponse(savedBook);
    }

    @GetMapping("/{id}")
    public BookDetailResponse getBook(@PathVariable Long id) {
        BookDomain book = bookService.getBook(id);

        return BookMapper.INSTANCE.bookDomainToBookDetailResponse(book);
    }
}
