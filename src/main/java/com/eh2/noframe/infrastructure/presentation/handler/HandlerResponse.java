package com.eh2.noframe.infrastructure.presentation.handler;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HandlerResponse {

	private Integer responseCode;
	
	private String body;

	private Map<String, String> headers;

}
