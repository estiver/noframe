package com.eh2.noframe.infrastructure.presentation.handler

import com.eh2.noframe.infrastructure.presentation.handler.functionalinterface.ExceptionalBiFunction
import com.eh2.noframe.infrastructure.presentation.handler.template.HttpHandlerTemplate
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URI

class BookHttpHandler(
    private val routeHandlers: Map<String, ExceptionalBiFunction<URI, String, HandlerResponse>>
) : HttpHandlerTemplate() {

    @Throws(IOException::class)
    override fun handle(httpExchange: HttpExchange) {
        logger.info("Init handle(HttpExchange exchange)")
        val handler = routeHandlers[httpExchange.requestMethod]
        try {
            val uri = httpExchange.requestURI
            val body = readRequestBody(httpExchange)

            val result = handler!!.apply(uri, body)

            httpExchange.responseHeaders.set("Content-Type", "application/json")
            httpExchange.sendResponseHeaders(200, result.body.toByteArray().size.toLong())
            httpExchange.responseBody.use { responseBody ->
                responseBody.write(result.body.toByteArray())
            }
        } catch (e: Exception) {
            httpExchange.sendResponseHeaders(500, 0)
            httpExchange.responseBody.close()
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(HttpHandler::class.java)
    }
}
