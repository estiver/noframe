package com.eh2.noframe.presentation.mapper;

import com.eh2.noframe.domain.BookEntity;
import com.eh2.noframe.presentation.dto.book.BookResponseDTO;
import com.eh2.noframe.presentation.dto.book.CreateBookRequestDTO;

public class BookMapper {

	public BookEntity toBookEntity(CreateBookRequestDTO createBookRequestDTO) {
		return new BookEntity(createBookRequestDTO.title());
	}

	public BookResponseDTO toBookResponseDTO(BookEntity book) {
		return new BookResponseDTO(book.getId(), book.getTitle());
	}
}
