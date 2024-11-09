package com.eh2.noframe.infrastructure.presentation.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eh2.noframe.infrastructure.presentation.handler.functionalinterface.ExceptionalBiFunction;
import com.eh2.noframe.infrastructure.presentation.handler.template.HttpHandlerTemplate;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BookHttpHandler extends HttpHandlerTemplate {

	private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

	private final Map<String, ExceptionalBiFunction<URI, String, String>> routeHandlers;

	public BookHttpHandler(Map<String, ExceptionalBiFunction<URI, String, String>> routeHandlers) {
		this.routeHandlers = routeHandlers;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		logger.info("Init handle(HttpExchange exchange)");
		var handler = routeHandlers.get(httpExchange.getRequestMethod());
		try {
			URI uri = httpExchange.getRequestURI();
			String body = readRequestBody(httpExchange);

			String result = handler.apply(uri, body);

			httpExchange.getResponseHeaders().set("Content-Type", "application/json");
			httpExchange.sendResponseHeaders(200, result.getBytes().length);
			OutputStream responseBody = httpExchange.getResponseBody();
			responseBody.write(result.getBytes());
			responseBody.close();
		} catch (Exception e) {
			httpExchange.sendResponseHeaders(500, 0);
			httpExchange.getResponseBody().close();
		}
	}

}
