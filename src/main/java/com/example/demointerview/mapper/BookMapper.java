package com.example.demointerview.mapper;

import com.example.demointerview.api.book.BookDetailResponse;
import com.example.demointerview.api.book.BookResponse;
import com.example.demointerview.api.book.CreateBookRequest;
import com.example.demointerview.domain.BookDomain;
import com.example.demointerview.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorMapper.class)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    BookDomain bookToBookDomain(CreateBookRequest createBookRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    Book bookDomainToBook(BookDomain bookDomain);

    @Mapping(target = "authorId", source = "author.id")
    BookDomain bookToBookDomain(Book book);

    BookResponse bookDomainToBookResponse(BookDomain bookDomain);

    BookDetailResponse bookDomainToBookDetailResponse(BookDomain bookDomain);
}
