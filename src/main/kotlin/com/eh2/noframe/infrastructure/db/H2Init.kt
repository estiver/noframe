package com.eh2.noframe.infrastructure.db

import java.net.URISyntaxException
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.sql.SQLException

object H2Init {

    @JvmStatic
    @Throws(URISyntaxException::class)
    fun main(args: Array<String>) {

        val scriptUrl: URL = H2Init::class.java
            .classLoader
            .getResource("script.sql")
            ?: throw IllegalArgumentException("script.sql not found")

        val path: Path = Paths.get(scriptUrl.toURI())

        try {
            DBConnection.getConnection().use { conn ->
                conn.createStatement().use { st ->
                    st.execute("RUNSCRIPT FROM '${path}'")
                }
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}