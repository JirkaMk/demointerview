package com.example.demointerview.service;

import com.example.demointerview.domain.AuthorDomain;
import com.example.demointerview.entity.Author;
import com.example.demointerview.mapper.AuthorMapper;
import com.example.demointerview.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDomain createAuthor(AuthorDomain authorDomain) {
        Author author = AuthorMapper.INSTANCE.authorDomainToAuthor(authorDomain);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.INSTANCE.authorToAuthorDomain(savedAuthor);
    }

    @Override
    public List<AuthorDomain> getAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .map(AuthorMapper.INSTANCE::authorToAuthorDomain)
                .toList();
    }
}
