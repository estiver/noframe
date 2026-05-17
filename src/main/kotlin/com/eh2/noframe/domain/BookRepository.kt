package com.eh2.noframe.domain

interface BookRepository {
    fun findById(id: String): BookEntity?

    fun save(book: BookEntity): BookEntity
}
