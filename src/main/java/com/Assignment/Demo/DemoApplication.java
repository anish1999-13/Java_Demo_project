package com.Assignment.Demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class DemoApplication {

//	@Autowired
//	private ApplicationContext context;
//	@Autowired
//	private CustomerRepository cus ;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
