package com.example.demointerview.repository;

import com.example.demointerview.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveAndGetAndDeleteBook() {
        // given
        Book book = new Book(null, "Test Book", "Test Author", 2022);
        entityManager.persist(book);
        entityManager.flush();

        // when
        Book found = entityManager.find(Book.class, book.getId());

        // then
        assertThat(found).isEqualTo(book);

        // when
        bookRepository.deleteById(book.getId());
        entityManager.flush();

        // then
        Book notFound = entityManager.find(Book.class, book.getId());
        assertThat(notFound).isNull();
    }
}
