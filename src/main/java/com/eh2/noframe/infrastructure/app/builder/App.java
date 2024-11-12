package com.eh2.noframe.infrastructure.app.builder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.Executors;

import com.eh2.noframe.infrastructure.Config;
import com.eh2.noframe.infrastructure.http.ContextHttpHandlers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {
	private HttpServer httpServer = null;

	private App(AppBuilder builder) {
		this.httpServer = builder.httpServer;
	}

	public static class AppBuilder {
		private HttpServer httpServer = null;
		private Config config;

		public AppBuilder(Config config) {
			super();
			this.config = config;
		}

		public AppBuilder httpServer() throws IOException {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(config.getHttpserverPort()), 0);
			httpServer.setExecutor(Executors.newFixedThreadPool(config.getHttpServerExecutorNThreads()));

			this.httpServer = httpServer;
			return this;
		}

		public AppBuilder contexts() {
			Map<String, HttpHandler> contextHandlersMap = new ContextHttpHandlers().buildContextHandlresMap()
					.getContextHandlresMap();
			for (Map.Entry<String, HttpHandler> entry : contextHandlersMap.entrySet()) {
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
