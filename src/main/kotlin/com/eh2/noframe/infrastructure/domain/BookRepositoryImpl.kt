package com.eh2.noframe.infrastructure.domain

import com.eh2.noframe.domain.BookEntity
import com.eh2.noframe.domain.BookRepository
import com.eh2.noframe.infrastructure.db.DBConnection
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.SQLException

class BookRepositoryImpl : BookRepository {
    override fun findById(id: String): BookEntity? {
        logger.info("Init findById(String id: {}) ", id)
        val sql = "SELECT * FROM BOOK WHERE id = ?"
        try {
            DBConnection.getConnection().use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id)
                    stmt.executeQuery().use { rs ->
                        if (rs.next()) {
                            return BookEntity(rs.getString("id"), rs.getString("title"))
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            logger.error("Error in findById", e)
        }
        return null
    }

    override fun save(book: BookEntity): BookEntity {
        logger.info("Init save(BookEntity book: {}) ", book)
        val sql = "INSERT INTO BOOK (id, title) VALUES (?, ?)"
        try {
            DBConnection.getConnection().use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.id)
                    stmt.setString(2, book.title)
                    stmt.executeUpdate()
                    return book
                }
            }
        } catch (e: SQLException) {
            logger.error("Error in save", e)
            throw RuntimeException("Failed to save the book: ${e.message}", e)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(BookRepositoryImpl::class.java)
    }
}
