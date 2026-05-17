package com.eh2.noframe.infrastructure.presentation.helper

import com.sun.net.httpserver.HttpExchange
import java.io.IOException
import java.nio.charset.StandardCharsets

class HttpExchangeHelper {
    @Throws(IOException::class)
    fun readRequestBody(exchange: HttpExchange): String {
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
