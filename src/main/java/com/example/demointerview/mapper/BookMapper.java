package com.example.demointerview.mapper;


import com.example.demointerview.dto.BookDto;
import com.example.demointerview.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book bookDtoToBook(BookDto bookDto);
}
