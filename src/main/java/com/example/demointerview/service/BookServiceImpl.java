package com.example.demointerview.service;

import com.example.demointerview.dto.BookDto;
import com.example.demointerview.entity.Book;
import com.example.demointerview.mapper.BookMapper;
import com.example.demointerview.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return BookMapper.INSTANCE.bookToBookDto(savedBook);
    }

    @Override
    public BookDto getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(BookMapper.INSTANCE::bookToBookDto).orElse(null);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
