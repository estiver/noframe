package com.eh2.noframe.infrastructure

import java.io.IOException
import java.util.Properties

class Config(fileName: String) {
    private val properties = Properties()

    init {
        try {
            Config::class.java.classLoader.getResourceAsStream(fileName).use { inputStream ->
                if (inputStream == null) {
                    throw IOException("O arquivo nao foi encontrado: $fileName")
                }
                properties.load(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun getProperty(key: String): String {
        return properties.getProperty(key)
    }

    fun getHttpserverPort(): Int {
        return getProperty(HTTP_SERVER_PORT_PROPERTY).toInt()
    }

    fun getHttpServerExecutorNThreads(): Int {
        return getProperty(HTTP_SERVER_EXECUTOR_NTHREADS_PROPERTY).toInt()
    }

    companion object {
        private const val HTTP_SERVER_PORT_PROPERTY = "httpserver.port"
        private const val HTTP_SERVER_EXECUTOR_NTHREADS_PROPERTY = "httpServer.executor.nthreads"
    }
}
