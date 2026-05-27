package com.example.demointerview.controller;

import com.example.demointerview.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAll();
    }

    @Test
    void createAuthor() throws Exception {
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Karel",
                                  "surname": "Capek",
                                  "birthDate": "1890-01-09"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name").value("Karel"))
                .andExpect(jsonPath("$.surname").value("Capek"))
                .andExpect(jsonPath("$.birthDate").value("1890-01-09"));
    }

    @Test
    void getAuthors() throws Exception {
        createAuthor("Jane", "Austen", "1975-12-16");
        createAuthor("George", "Orwell", "1903-06-25");

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].name").value("Jane"))
                .andExpect(jsonPath("$[0].surname").value("Austen"))
                .andExpect(jsonPath("$[0].birthDate").value("1975-12-16"))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].name").value("George"))
                .andExpect(jsonPath("$[1].surname").value("Orwell"))
                .andExpect(jsonPath("$[1].birthDate").value("1903-06-25"));
    }

    private void createAuthor(String name, String surname, String birthDate) throws Exception {
        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "%s",
                                  "surname": "%s",
                                  "birthDate": "%s"
                                }
                                """.formatted(name, surname, birthDate)))
                .andExpect(status().isCreated());
    }
}
