package com.example.demointerview.service;

import com.example.demointerview.domain.AuthorDomain;

import java.util.List;

public interface AuthorService {

    AuthorDomain createAuthor(AuthorDomain authorDomain);

    List<AuthorDomain> getAuthors();
}
