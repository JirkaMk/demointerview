package com.example.demointerview.service;

import com.example.demointerview.domain.BookDomain;
import com.example.demointerview.entity.Author;
import com.example.demointerview.entity.Book;
import com.example.demointerview.mapper.BookMapper;
import com.example.demointerview.repository.AuthorRepository;
import com.example.demointerview.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDomain createBook(BookDomain bookDomain) {
        Author author = authorRepository.findById(bookDomain.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + bookDomain.authorId()));

        Book book = BookMapper.INSTANCE.bookDomainToBook(bookDomain);
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDomain(savedBook);
    }

    @Override
    public BookDomain getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));

        return BookMapper.INSTANCE.bookToBookDomain(book);
    }
}
