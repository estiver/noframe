package com.eh2.noframe.infrastructure.presentation.handler

import com.eh2.noframe.presentation.controller.BookController
import com.eh2.noframe.presentation.dto.book.BookResponseDTO
import com.eh2.noframe.presentation.dto.book.CreateBookRequestDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URI

class BookController_HttpHandler_Link(
    private val bookController: BookController,
    context: String,
    private val objectMapper: ObjectMapper
) {
    private val contextToGET = "$context/"

    @Throws(IOException::class)
    fun createBook(uri: URI, body: String): HandlerResponse {
        logger.info("Init createBook(HttpExchange httpExchange)")
        val createBookRequestDTO = objectMapper.readValue(body, CreateBookRequestDTO::class.java)
        val bookResponseDTO: BookResponseDTO = bookController.createBook(createBookRequestDTO)
        val responseJson = objectMapper.writeValueAsString(bookResponseDTO)
        return HandlerResponse(200, responseJson, null)
    }

    @Throws(IOException::class)
    fun retrieveBook(uri: URI, body: String): HandlerResponse {
        logger.info("Init retrieveBook(HttpExchange httpExchange)")
        val id = uri.toString().replace(contextToGET.toRegex(), "")
        val bookResponseDTO: BookResponseDTO = bookController.retriveBook(id)
        val responseJson = objectMapper.writeValueAsString(bookResponseDTO)
        return HandlerResponse(200, responseJson, null)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BookController_HttpHandler_Link::class.java)
    }
}
