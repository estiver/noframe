package com.eh2.noframe.infrastructure.presentation.handler.functionalinterface;

import java.io.IOException;

@FunctionalInterface
public interface ExceptionalBiFunction<T, U, R> {
	R apply(T t, U u) throws IOException;
}
