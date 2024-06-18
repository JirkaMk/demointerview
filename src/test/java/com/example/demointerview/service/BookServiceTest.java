package com.example.demointerview.service;

import com.example.demointerview.dto.BookDto;
import com.example.demointerview.entity.Book;
import com.example.demointerview.mapper.BookMapper;
import com.example.demointerview.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testSaveBook() {
        BookDto bookDto = new BookDto(null, "Test Book", "Test Author", 2022);
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        book.setId(1L);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDto savedBookDto = bookService.saveBook(bookDto);

        assertEquals("Test Book", savedBookDto.getName());
        assertEquals("Test Author", savedBookDto.getAuthor());
        assertEquals(2022, savedBookDto.getYear());
        assertEquals(1L, savedBookDto.getId());
    }

    @Test
    public void testGetBook() {
        Book book = new Book(1L, "Test Book", "Test Author", 2022);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDto bookDto = bookService.getBook(1L);

        assertEquals("Test Book", bookDto.getName());
        assertEquals("Test Author", bookDto.getAuthor());
        assertEquals(2022, bookDto.getYear());
        assertEquals(1L, bookDto.getId());
    }

    @Test
    public void testGetBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        BookDto bookDto = bookService.getBook(1L);

        assertNull(bookDto);
    }

    @Test
    public void testDeleteBook() {
        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}

