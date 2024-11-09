package com.eh2.noframe.infrastructure.presentation.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;

public class HttpExchangeHelper {
	public String readRequestBody(HttpExchange exchange) throws IOException {
		InputStream inputStream = exchange.getRequestBody();
		StringBuilder response = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		return response.toString();
	}
}
