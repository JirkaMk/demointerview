package com.example.demointerview.controller;

import com.example.demointerview.repository.AuthorRepository;
import com.example.demointerview.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void createBook() throws Exception {
        Long authorId = createAuthor();

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "R.U.R.",
                                  "authorId": %d,
                                  "year": 1920
                                }
                                """.formatted(authorId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name").value("R.U.R."))
                .andExpect(jsonPath("$.authorId").value(authorId))
                .andExpect(jsonPath("$.year").value(1920));
    }

    @Test
    void getBook() throws Exception {
        Long authorId = createAuthor();
        Long bookId = createBook(authorId);

        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.name").value("R.U.R."))
                .andExpect(jsonPath("$.year").value(1920))
                .andExpect(jsonPath("$.author.id").value(authorId))
                .andExpect(jsonPath("$.author.name").value("Karel"))
                .andExpect(jsonPath("$.author.surname").value("Capek"))
                .andExpect(jsonPath("$.author.birthDate").value("1990-01-09"));
    }

    private Long createBook(Long authorId) throws Exception {
        String response = mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "R.U.R.",
                                  "authorId": %d,
                                  "year": 1920
                                }
                                """.formatted(authorId)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = response.replaceAll(".*\"id\":(\\d+).*", "$1");
        return Long.valueOf(id);
    }

    private Long createAuthor() throws Exception {
        String response = mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Karel",
                                  "surname": "Capek",
                                  "birthDate": "1990-01-09"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = response.replaceAll(".*\"id\":(\\d+).*", "$1");
        return Long.valueOf(id);
    }
}
