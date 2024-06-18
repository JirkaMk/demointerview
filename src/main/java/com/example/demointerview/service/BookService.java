package com.example.demointerview.service;

import com.example.demointerview.dto.BookDto;

public interface BookService {

    BookDto saveBook(BookDto bookDto);

    BookDto getBook(Long id);

    void deleteBook(Long id);
}
