package com.elsevier.main;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Run the class as Java Application to start the server.
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.elsevier")
public class FileSearchMainApp {

	private static Logger LOGGER = Logger.getLogger(FileSearchMainApp.class.getName());

	/**
	 * The main method to start the Spring boot application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		LOGGER.info("Main method to run the application FileSearchMainApp: Begins()");
		SpringApplication.run(FileSearchMainApp.class, args);
	}
}
