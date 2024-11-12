package com.eh2.noframe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookEntity implements Cloneable {

	private String id;

	private String title;

	public BookEntity(String title) {
		this.title = title;
	}

	private void setId(String id) {
		this.id = id;
	}

	public BookEntity generateWithId(String id) throws CloneNotSupportedException {
		BookEntity clone = (BookEntity) this.clone();
		((BookEntity) clone).setId(id);
		return clone;
	}

}
