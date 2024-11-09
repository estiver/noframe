module com.eh2.noframe {
	exports com.eh2.noframe.presentation.dto.book to com.fasterxml.jackson.databind;

	requires lombok;
	requires jdk.httpserver;
	requires com.fasterxml.jackson.databind;
	requires org.slf4j;
	requires java.sql;
}