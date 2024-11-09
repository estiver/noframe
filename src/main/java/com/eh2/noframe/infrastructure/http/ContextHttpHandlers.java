package com.eh2.noframe.infrastructure.http;

import java.util.Map;

import com.eh2.noframe.businessservice.impl.BookBusinessServiceImpl;
import com.eh2.noframe.infrastructure.presentation.handler.BookHttpHandler;
import com.eh2.noframe.infrastructure.domain.BookRepositoryImpl;
import com.eh2.noframe.infrastructure.presentation.handler.BookController_HttpHandler_Link;
import com.eh2.noframe.presentation.controller.BookController;
import com.eh2.noframe.presentation.mapper.BookMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpHandler;

public class ContextHttpHandlers {

	private static final String API_BOOK_CONTEXT = "/api/book";

	public Map<String, HttpHandler> build() {
		BookController_HttpHandler_Link bookControllerLink = new BookController_HttpHandler_Link(
				new BookController(new BookBusinessServiceImpl(new BookRepositoryImpl()), new BookMapper()),
				API_BOOK_CONTEXT, new ObjectMapper());

		return Map.of(API_BOOK_CONTEXT, new BookHttpHandler(
				Map.of("POST", bookControllerLink::createBook, "GET", bookControllerLink::retrieveBook)));
	}

}
