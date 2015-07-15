package com.inria.myriads;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// This annotation tells Spring to look for controllers, etc.
// starting in the current package
@ComponentScan
// This annotation tells Spring that this class contains configuration
// information
// for the application.
@Configuration
public class Application {

	// The entry point to the application.
	public static void main(String[] args) {
		// This call tells spring to launch the application and
		// use the configuration specified in LocalApplication to
		// configure the application's components.
		SpringApplication.run(Application.class, args);
	}
}
