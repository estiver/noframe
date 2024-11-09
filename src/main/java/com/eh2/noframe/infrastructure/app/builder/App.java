package com.eh2.noframe.infrastructure.app.builder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

import com.eh2.noframe.infrastructure.Config;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {
	private HttpServer httpServer = null;

	private App(AppBuilder builder) {
		this.httpServer = builder.httpServer;
	}

	public static class AppBuilder {
		private HttpServer httpServer = null;

		public AppBuilder httpServer(Config config) throws IOException {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(config.getHttpserverPort()), 0);
			httpServer.setExecutor(Executors.newFixedThreadPool(config.getHttpServerExecutorNThreads()));

			this.httpServer = httpServer;
			return this;
		}

		public AppBuilder addContext(String path, HttpHandler httpHandler) {
			httpServer.createContext(path, httpHandler);
			return this;
		}

		public AppBuilder addContexts(Map<String, HttpHandler> contextHandlers) {
			for (Map.Entry<String, HttpHandler> entry : contextHandlers.entrySet()) {
				httpServer.createContext(entry.getKey(), entry.getValue());
			}
			return this;
		}

		public App build() {
			return new App(this);
		}
	}

	public void start() {
		httpServer.start();
	}

}
