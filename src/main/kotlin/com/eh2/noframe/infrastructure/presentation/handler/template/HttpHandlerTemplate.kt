package com.eh2.noframe.infrastructure.presentation.handler.template

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import java.io.IOException
import java.nio.charset.StandardCharsets

abstract class HttpHandlerTemplate : HttpHandler {
    @Throws(IOException::class)
    protected fun readRequestBody(exchange: HttpExchange): String {
        return exchange.requestBody.bufferedReader(StandardCharsets.UTF_8).use { reader ->
            buildString {
                var line = reader.readLine()
                while (line != null) {
                    append(line)
                    line = reader.readLine()
                }
            }
        }
    }
}
