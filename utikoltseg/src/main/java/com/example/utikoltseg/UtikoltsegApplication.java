package com.example.utikoltseg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

#@SpringBootApplication
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class UtikoltsegApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtikoltsegApplication.class, args);
		System.out.println("---------- SERVER LOG: ----------");
	}

}
