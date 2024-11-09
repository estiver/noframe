package com.eh2.noframe.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static String HTTP_SERVER_PORT_PROPERTY = "httpserver.port";
	private static String HTTP_SERVER_EXECUTOR_NTHREADS_PROPERTY = "httpServer.executor.nthreads";

	private Properties properties;

	public Config(String fileName) {
		properties = new Properties();

		try (InputStream is = Config.class.getClassLoader().getResourceAsStream(fileName)) {
			if (is == null) {
				throw new IOException("O arquivo não foi encontrado: " + fileName);
			}
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getHttpserverPort() {
		return Integer.parseInt(getProperty(HTTP_SERVER_PORT_PROPERTY));
	}

	public int getHttpServerExecutorNThreads() {
		return Integer.parseInt(getProperty(HTTP_SERVER_EXECUTOR_NTHREADS_PROPERTY));
	}
}
