package com.eh2.noframe.domain;

public interface BookRepository {
	BookEntity findById(String id);

	BookEntity save(BookEntity book);
}
