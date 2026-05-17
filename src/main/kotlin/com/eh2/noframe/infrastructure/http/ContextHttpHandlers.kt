package com.eh2.noframe.infrastructure.http

import com.eh2.noframe.businessservice.impl.BookBusinessServiceImpl
import com.eh2.noframe.infrastructure.domain.BookRepositoryImpl
import com.eh2.noframe.infrastructure.presentation.handler.BookController_HttpHandler_Link
import com.eh2.noframe.infrastructure.presentation.handler.BookHttpHandler
import com.eh2.noframe.infrastructure.presentation.handler.HandlerResponse
import com.eh2.noframe.infrastructure.presentation.handler.functionalinterface.ExceptionalBiFunction
import com.eh2.noframe.presentation.controller.BookController
import com.eh2.noframe.presentation.mapper.BookMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.sun.net.httpserver.HttpHandler
import java.net.URI

class ContextHttpHandlers {
    var contextHandlresMap: Map<String, HttpHandler>? = null
        private set

    fun buildContextHandlresMap(): ContextHttpHandlers {
        val bookControllerLink = BookController_HttpHandler_Link(
            BookController(BookBusinessServiceImpl(BookRepositoryImpl()), BookMapper()),
            API_BOOK_CONTEXT,
            ObjectMapper()
        )

        contextHandlresMap = mapOf(
            API_BOOK_CONTEXT to BookHttpHandler(
                mapOf(
                    "POST" to ExceptionalBiFunction<URI, String, HandlerResponse> { uri, body ->
                        bookControllerLink.createBook(uri, body)
                    },
                    "GET" to ExceptionalBiFunction<URI, String, HandlerResponse> { uri, body ->
                        bookControllerLink.retrieveBook(uri, body)
                    }
                )
            )
        )
        return this
    }

    companion object {
        private const val API_BOOK_CONTEXT = "/api/book"
    }
}
