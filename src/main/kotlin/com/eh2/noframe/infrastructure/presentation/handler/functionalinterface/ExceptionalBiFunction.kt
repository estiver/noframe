package com.eh2.noframe.infrastructure.presentation.handler.functionalinterface

import java.io.IOException

fun interface ExceptionalBiFunction<T, U, R> {
    @Throws(IOException::class)
    fun apply(t: T, u: U): R
}
