package com.eh2.noframe.presentation.mapper

import com.eh2.noframe.domain.BookEntity
import com.eh2.noframe.presentation.dto.book.BookResponseDTO
import com.eh2.noframe.presentation.dto.book.CreateBookRequestDTO

class BookMapper {
    fun toBookEntity(createBookRequestDTO: CreateBookRequestDTO): BookEntity {
        return BookEntity(createBookRequestDTO.title)
    }

    fun toBookResponseDTO(book: BookEntity): BookResponseDTO {
        return BookResponseDTO(book.id, book.title)
    }
}
