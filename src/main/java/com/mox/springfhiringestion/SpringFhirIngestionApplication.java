package com.mox.springfhiringestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;

@SpringBootApplication
@RestController
public class SpringFhirIngestionApplication {

	// hello world
	@RequestMapping("/")
    public String home() {
        return "Entry point for Springboot Fhir Ingestion";
    }

	// test route: accept id and field parameters (ex. gender)
	@GetMapping("test/{idParam}/{fieldParam}")
		public String getParam(@PathVariable String idParam, String fieldParam) {
			// Create a new Patient resource
			Patient patient = new Patient();

			if (fieldParam.equals("gender")) {
				patient.setGender(AdministrativeGenderEnum.MALE);
				return patient.getGender();
			}
			return idParam + fieldParam;
		}

	@PostMapping("/test/buh")
			public @ResponseBody ResponseEntity<?> findBuh(@RequestBody String body) throws Exception {
				return new ResponseEntity<String>(body, HttpStatus.OK);
			}

	public static void main(String[] args) {
		SpringApplication.run(SpringFhirIngestionApplication.class, args);
	}

}
