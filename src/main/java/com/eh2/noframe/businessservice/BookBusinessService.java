package com.eh2.noframe.businessservice;

import com.eh2.noframe.domain.BookEntity;

public interface BookBusinessService {

	BookEntity retriveBook(String id);

	BookEntity createBook(BookEntity book) throws CloneNotSupportedException;

}
