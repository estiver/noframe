package com.eh2.noframe.businessservice.impl

import com.eh2.noframe.businessservice.BookBusinessService
import com.eh2.noframe.domain.BookEntity
import com.eh2.noframe.domain.BookRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

class BookBusinessServiceImpl(
    private val bookRepository: BookRepository
) : BookBusinessService {

    override fun retriveBook(id: String): BookEntity? {
        logger.info("init retriveBook(String id: {})", id)
        return bookRepository.findById(id)
    }

    override fun createBook(book: BookEntity): BookEntity {
        logger.info("init createBook(BookEntity book: {})", book)
        val uuid = UUID.randomUUID().toString()
        val toSave = book.generateWithId(uuid)
        return bookRepository.save(toSave)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BookBusinessServiceImpl::class.java)
    }
}
