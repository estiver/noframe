package com.eh2.noframe.infrastructure.presentation.handler

data class HandlerResponse(
    val responseCode: Int,
    val body: String,
    val headers: Map<String, String>?
)
