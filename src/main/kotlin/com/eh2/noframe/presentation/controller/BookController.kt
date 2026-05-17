package com.eh2.noframe.presentation.controller

import com.eh2.noframe.businessservice.BookBusinessService
import com.eh2.noframe.presentation.dto.book.BookResponseDTO
import com.eh2.noframe.presentation.dto.book.CreateBookRequestDTO
import com.eh2.noframe.presentation.mapper.BookMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BookController(
    private val bookBusinessService: BookBusinessService,
    private val bookMapper: BookMapper
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BookController::class.java)
    }

    fun createBook(bookDTO: CreateBookRequestDTO): BookResponseDTO {
        logger.info("init createBook(CreateBookRequestDTO bookDTO: {})", bookDTO)
        return try {
            bookMapper.toBookResponseDTO(bookBusinessService.createBook(bookMapper.toBookEntity(bookDTO)))
        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e)
        }
    }

    fun retriveBook(id: String): BookResponseDTO {
        logger.info("init retriveBook(String id: {})", id)
        return bookMapper.toBookResponseDTO(bookBusinessService.retriveBook(id)!!)
    }
}
