package com.example.demointerview.service;

import com.example.demointerview.domain.BookDomain;

public interface BookService {

    BookDomain createBook(BookDomain bookDomain);

    BookDomain getBook(Long id);
}
