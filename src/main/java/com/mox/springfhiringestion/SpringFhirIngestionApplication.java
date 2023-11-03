package com.mox.springfhiringestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringFhirIngestionApplication {

	// @RequestMapping("/")
  //   public String home() {
  //       return "Entry point for Springboot Fhir Ingestion";
  //   }

	public static void main(String[] args) {
		System.out.print("Hello, Maud");
	}

	// @RequestMapping("/get-field")

	// public static void main(String[] args) {
	// 	SpringApplication.run(SpringFhirIngestionApplication.class, args);
	// }

}
