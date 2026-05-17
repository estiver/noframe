package com.eh2.noframe

import com.eh2.noframe.infrastructure.Config
import com.eh2.noframe.infrastructure.app.builder.App
import com.eh2.noframe.infrastructure.db.H2Init
import java.io.IOException
import java.net.URISyntaxException

object Main {
    private const val APPLICATION_PROPERTIES_FILE = "application.properties"

    @JvmStatic
    @Throws(IOException::class, URISyntaxException::class)
    fun main(args: Array<String>) {
        H2Init.main(args)
        val config = Config(APPLICATION_PROPERTIES_FILE)

        val app = App.AppBuilder(config).httpServer().contexts().build()
        app.start()
    }
}
