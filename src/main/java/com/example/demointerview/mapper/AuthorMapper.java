package com.example.demointerview.mapper;


import com.example.demointerview.api.author.AuthorResponse;
import com.example.demointerview.api.author.CreateAuthorRequest;
import com.example.demointerview.domain.AuthorDomain;
import com.example.demointerview.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "id", ignore = true)
    AuthorDomain authorToAuthorDomain(CreateAuthorRequest createAuthorRequest);

    @Mapping(target = "id", ignore = true)
    Author authorDomainToAuthor(AuthorDomain authorDomain);

    AuthorDomain authorToAuthorDomain(Author author);

    AuthorResponse authorDomainToAuthorResponse(AuthorDomain authorDomain);
}
