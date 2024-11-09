package com.eh2.noframe.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eh2.noframe.businessservice.BookBusinessService;
import com.eh2.noframe.presentation.dto.book.BookResponseDTO;
import com.eh2.noframe.presentation.dto.book.CreateBookRequestDTO;
import com.eh2.noframe.presentation.mapper.BookMapper;

public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	private BookBusinessService bookBusinessService;

	private BookMapper bookMapper;

	public BookController(BookBusinessService bookBusinessService, BookMapper bookMapper) {
		super();
		this.bookBusinessService = bookBusinessService;
		this.bookMapper = bookMapper;
	}

	public BookResponseDTO createBook(CreateBookRequestDTO bookDTO) {
		logger.info("init createBook(CreateBookRequestDTO bookDTO: {})", bookDTO);
		try {
			return bookMapper.toBookResponseDTO(bookBusinessService.createBook(bookMapper.toBookEntity(bookDTO)));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BookResponseDTO retriveBook(String id) {
		logger.info("init retriveBook(String id: {})", id);
		return bookMapper.toBookResponseDTO(bookBusinessService.retriveBook(id));
	}

}
