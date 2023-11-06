package com.mox.springfhiringestion;

import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;

@SpringBootApplication
@RestController
public class SpringFhirIngestionApplication {

	@RequestMapping("/")
    public String home() {
        return "Entry point for Springboot Fhir Ingestion";
    }

	@GetMapping("field/{fieldParam}")
		public String getParam(@PathVariable String fieldParam) {
			// Create a new Patient resource
			Patient patient = new Patient();

			if (fieldParam.equals("gender")) {
				patient.setGender(AdministrativeGenderEnum.MALE);
				// return """
				// 		Patient gender: g%
				// 		""".formatted(patient.getGender());
				return patient.getGender();
			}
			// will default to id
			// return patient.toString();
			return fieldParam;
		}

	public static void main(String[] args) {
		SpringApplication.run(SpringFhirIngestionApplication.class, args);
	}

}
