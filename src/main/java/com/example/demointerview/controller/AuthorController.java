package com.example.demointerview.controller;

import com.example.demointerview.api.author.AuthorResponse;
import com.example.demointerview.api.author.CreateAuthorRequest;
import com.example.demointerview.domain.AuthorDomain;
import com.example.demointerview.mapper.AuthorMapper;
import com.example.demointerview.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
        AuthorDomain authorDomain = AuthorMapper.INSTANCE.authorToAuthorDomain(createAuthorRequest);
        AuthorDomain savedAuthor = authorService.createAuthor(authorDomain);

        return AuthorMapper.INSTANCE.authorDomainToAuthorResponse(savedAuthor);
    }

    @GetMapping
    public List<AuthorResponse> getAuthors() {
        return authorService.getAuthors().stream()
                .map(AuthorMapper.INSTANCE::authorDomainToAuthorResponse)
                .toList();
    }
}
