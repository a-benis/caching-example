package com.example.caching_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The type Caching example application.
 */
@SpringBootApplication
@EnableCaching
public class CachingExampleApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CachingExampleApplication.class, args);
	}

}
