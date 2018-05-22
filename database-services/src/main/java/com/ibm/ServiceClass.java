package com.ibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ServiceClass {
	
	public static void main(String[] args) {
		
		System.out.println("Hello world");
		
		System.out.println("Hello world");
		SpringApplication.run(ServiceClass.class,args);
	}

}
