package com.eh2.noframe.businessservice.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eh2.noframe.businessservice.BookBusinessService;
import com.eh2.noframe.domain.BookEntity;
import com.eh2.noframe.domain.BookRepository;

public class BookBusinessServiceImpl implements BookBusinessService {
	private static final Logger logger = LoggerFactory.getLogger(BookBusinessServiceImpl.class);

	private BookRepository bookRepository;

	public BookBusinessServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public BookEntity retriveBook(String id) {
		logger.info("init retriveBook(String id: {})", id);
		return bookRepository.findById(id);
	}

	@Override
	public BookEntity createBook(BookEntity book) throws CloneNotSupportedException {
		logger.info("init createBook(BookEntity book: {})", book);
		String uuid = UUID.randomUUID().toString();
		BookEntity toSave=book.generateWithId(uuid);
		return bookRepository.save(toSave);
	}

}
