package com.bharath.journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class E2EJournalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(E2EJournalAppApplication.class, args);
	}

}
//	The @SpringBootApplication annotation is a combination of three other annotations:
//
//@SpringBootConfiguration: This annotation indicates that the class is a Spring Boot configuration class. It is equivalent to using @Configuration in a standard Spring application.
//@EnableAutoConfiguration: This annotation enables Spring Boot’s auto-configuration mechanism. It automatically configures various components based on the dependencies and libraries present in your project.
//@ComponentScan: This annotation enables component scanning within the package where your application class is located. It allows Spring Boot to automatically discover and register beans (components, services, repositories, etc.) in that package.