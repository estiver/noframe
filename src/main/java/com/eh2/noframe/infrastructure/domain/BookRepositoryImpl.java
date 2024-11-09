package com.eh2.noframe.infrastructure.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eh2.noframe.domain.BookEntity;
import com.eh2.noframe.domain.BookRepository;
import com.eh2.noframe.infrastructure.db.DBConnection;

public class BookRepositoryImpl implements BookRepository {
	private static final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

	@Override
	public BookEntity findById(String id) {
		logger.info("Init findById(String id: {}) ", id);
		String sql = "SELECT * FROM BOOK WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new BookEntity(rs.getString("id"), rs.getString("title"));
			}
		} catch (SQLException e) {
			logger.error("Error in findById", e);
		}
		return null;
	}

	@Override
	public BookEntity save(BookEntity book) {
		logger.info("Init save(BookEntity book: {}) ", book);

		String sql = "INSERT INTO BOOK (id, title) VALUES (?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, book.getId());
			stmt.setString(2, book.getTitle());
			stmt.executeUpdate();
			return book;
		} catch (SQLException e) {
			logger.error("Error in save", e);
			throw new RuntimeException("Failed to save the book: " + e.getMessage(), e);

		}
	}

}
