package com.mox.springfhiringestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;

@SpringBootApplication
@RestController
public class SpringFhirIngestionApplication {
	@RequestMapping("/")
		/** Hello world
			* @return      String java obj
		*/
    public String
		helloWorld() {
        return "Entry point for Springboot Fhir Ingestion";
    }

	@GetMapping("/test")
		/** Returns whatever is given in the request body with a status code
			*
			* @param  body ideally is a patient JSON obj
			* @return      String java obj
		*/
		public @ResponseBody ResponseEntity<?>
		getRequestBodyAndReturn(@RequestBody String body) throws Exception {
			return new ResponseEntity<String>(body, HttpStatus.OK);
		}

	@GetMapping("test/field/{fieldParam}")
		/** Returns gender data from a new patient object
		  * EX: gender, birthDate, deceasedBoolean, id, resourceType, etc... 
			*
			* @param  fieldParam  field parameter to search within a patient obj
			* @return      String of gender and status code
		*/
		public @ResponseBody ResponseEntity<?>
		getParamFromNewPatientObj(@PathVariable String fieldParam) throws Exception {
			Patient patient = new Patient();
			if (fieldParam.equals("gender")) {
				patient.setGender(AdministrativeGenderEnum.MALE);
				return new ResponseEntity<String>(patient.getGender(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(fieldParam, HttpStatus.I_AM_A_TEAPOT);
			}
		}

	@GetMapping("/test/parse")
		/** Parses a patient JSON object and returns it as a string
			*
			* @param  body ideally is a patient JSON obj
			* @return      String java obj
		*/
		public @ResponseBody ResponseEntity<?>
		convertPatientJSONToJavaObj(@RequestBody String body) throws Exception {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patientJsonRootNode = objectMapper.readTree(body);
			// TOSTRING WORKS, NOT ASTEXT, IDENTIFY WHAT KIND OF NODE IT IS AND PROPERLY RETURN OBJ
			String patientData = patientJsonRootNode.toString();
			if (!patientData.isBlank()) {
				return new ResponseEntity<String>(patientData, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(patientData, HttpStatus.BAD_REQUEST);
			}
		}

	@GetMapping("/test/parse/{fieldParam}")
		/** Parses a patient JSON object and returns a requested field via parameter
			*
			* @param  fieldParam  field parameter to search within a patient obj
			* @param  body ideally is a patient JSON obj
			* @return      String java obj of patient data
		*/
		public @ResponseBody ResponseEntity<?>
		getPatientJSONtoFieldData(
			@PathVariable String fieldParam,
			@RequestBody String body
		) throws Exception {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode patientJsonRootNode = objectMapper.readTree(body);
				String fieldData = patientJsonRootNode.get(fieldParam).toString();
				if (fieldData != null && !fieldData.isBlank()) {
					return new ResponseEntity<String>(fieldData, HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
				}
			}

	// @GetMapping("/test/parse/gender")
	// 	/** Parses provided patient JSON and statically returns gender data, unless above method
	// 		*
	// 		* @param  fieldParam  field parameter to search within a patient obj
	// 		* @param  body ideally is a patient JSON obj
	// 		* @return      String java obj
	// 	*/
	// 	public @ResponseBody ResponseEntity<?>
	// 	getGenderFromPatientJSON(@RequestBody String body)
	// 		throws Exception {
	// 			ObjectMapper objectMapper = new ObjectMapper();
	// 			JsonNode patientJsonRootNode = objectMapper.readTree(body);
	// 			String fieldData = patientJsonRootNode.get("gender").asText();
	// 			if (fieldData != null) {
	// 				return new ResponseEntity<String>(fieldData, HttpStatus.OK);
	// 			} else {
	// 				return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
	// 			}
	// 		}

	public static void main(String[] args) {
		SpringApplication.run(SpringFhirIngestionApplication.class, args);
	}

}
