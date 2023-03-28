package com.springbootlab0.approach_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableSwagger2
public class SpringLibraryApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringLibraryApp.class, args);
	}

}
