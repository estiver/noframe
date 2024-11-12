package com.eh2.noframe.infrastructure.presentation.handler;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eh2.noframe.presentation.controller.BookController;
import com.eh2.noframe.presentation.dto.book.BookResponseDTO;
import com.eh2.noframe.presentation.dto.book.CreateBookRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookController_HttpHandler_Link {

	private static final Logger logger = LoggerFactory.getLogger(BookController_HttpHandler_Link.class);

	private final BookController bookController;
	private final String contextToGET;
	private final ObjectMapper objectMapper;

	public BookController_HttpHandler_Link(BookController bookController, String context, ObjectMapper objectMapper) {
		super();
		this.bookController = bookController;
		this.contextToGET = context + "/";
		this.objectMapper = objectMapper;
	}

	public String createBook(URI uri, String body) throws IOException {
		logger.info("Init createBook(HttpExchange httpExchange)");
		CreateBookRequestDTO createBookRequestDTO = objectMapper.readValue(body, CreateBookRequestDTO.class);
		BookResponseDTO bookResponseDTO = bookController.createBook(createBookRequestDTO);
		String responseJson = objectMapper.writeValueAsString(bookResponseDTO);
		return responseJson;
	}

	public String retrieveBook(URI uri, String body) throws IOException {
		logger.info("Init retrieveBook(HttpExchange httpExchange)");
		String id = uri.toString().replaceAll(contextToGET, "");
		BookResponseDTO bookResponseDTO = bookController.retriveBook(id);
		String responseJson = objectMapper.writeValueAsString(bookResponseDTO);
		return responseJson;

	}
}
