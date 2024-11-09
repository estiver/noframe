package com.eh2.noframe.infrastructure.db;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Init {
    public static void main(String[] args) {
        URL scriptUrl = H2Init.class.getClassLoader().getResource("script.sql");
        if (scriptUrl == null) {
            System.out.println("Script file not found!");
            return;
        }
        try (Connection conn = DBConnection.getConnection()) {
            try (Statement st = conn.createStatement()) {
                st.execute("RUNSCRIPT FROM '" + scriptUrl.getPath() + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
