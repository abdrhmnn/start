package io.spring.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	// authentikasi -> proses menentukan siapa yg bisa masuk
	// authorisasi -> proses menentukan bagaimana dia melakukan suatu hal

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
