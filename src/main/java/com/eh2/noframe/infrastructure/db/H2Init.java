package com.eh2.noframe.infrastructure.db;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Init {
	public static void main(String[] args) throws URISyntaxException {
		URL scriptUrl = H2Init.class
		        .getClassLoader()
		        .getResource("script.sql");

		Path path = Paths.get(scriptUrl.toURI());
		
		if (scriptUrl == null) {
			System.out.println("Script file not found!");
			return;
		}
		try (Connection conn = DBConnection.getConnection()) {
			try (Statement st = conn.createStatement()) {
				st.execute("RUNSCRIPT FROM '" +path.toString() + "'");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
