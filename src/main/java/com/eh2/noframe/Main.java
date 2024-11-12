package com.eh2.noframe;

import java.io.IOException;

import com.eh2.noframe.infrastructure.Config;
import com.eh2.noframe.infrastructure.app.builder.App;
import com.eh2.noframe.infrastructure.db.H2Init;

public class Main {
	private static String APPLICATION_PROPERTIES_FILE = "application.properties";

	public static void main(String[] args) throws IOException {
		H2Init.main(args);
		Config config = new Config(APPLICATION_PROPERTIES_FILE);

		App app = new App.AppBuilder(config).httpServer().contexts().build();
		app.start();
	}

}
