package com.eh2.noframe.businessservice

import com.eh2.noframe.domain.BookEntity

interface BookBusinessService {
    fun retriveBook(id: String): BookEntity?

    @Throws(CloneNotSupportedException::class)
    fun createBook(book: BookEntity): BookEntity
}
