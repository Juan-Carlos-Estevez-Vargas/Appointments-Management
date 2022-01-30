package com.juan.estevez.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.juan.estevez.app.dto.PatientDTO;

@SpringBootTest
class PatientRestControllerIT2 {
	
	//TestRestTemplate
	//TestWebClient
	
	private PatientRestController patientRestController;

	@Autowired
	public PatientRestControllerIT2(PatientRestController patientRestController) {
		this.patientRestController = patientRestController;
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertPatient.sql")
	void postPatient() {
		ResponseEntity<PatientDTO> response = patientRestController.save(createPatientDTO());
		assertEquals(createPatientDTO().getIdPatient(), response.getBody().getIdPatient());
		assertEquals(createPatientDTO().getName(), response.getBody().getName());
		assertEquals(createPatientDTO().getDateOfBirth(), response.getBody().getDateOfBirth());
		assertEquals(createPatientDTO().getIdType(), response.getBody().getIdType());
		assertEquals(createPatientDTO().getEps(), response.getBody().getEps());
		assertEquals(createPatientDTO().getClinicHistory(), response.getBody().getClinicHistory());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatient.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts =  "cleanUpdatePatient")
	void putPatient() {
		ResponseEntity<PatientDTO> response = patientRestController.update(createPatientDTO());
		assertEquals(createPatientDTO().getIdPatient(), response.getBody().getIdPatient());
		assertEquals(createPatientDTO().getName(), response.getBody().getName());
		assertEquals(createPatientDTO().getDateOfBirth(), response.getBody().getDateOfBirth());
		assertEquals(createPatientDTO().getIdType(), response.getBody().getIdType());
		assertEquals(createPatientDTO().getEps(), response.getBody().getEps());
		assertEquals(createPatientDTO().getClinicHistory(), response.getBody().getClinicHistory());
		//Ir a a la base de datos a verificar, ppor ejemplo usando JdbcTemplate.
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatient.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPatient.sql")
	void getPatient() {
		String idPatient = "1223";
		PatientDTO response = patientRestController.searchPatient(idPatient);
		assertEquals(idPatient, response.getIdPatient());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatient.sql")
	void deletePatient() {
		String idPatient = "1223";
		ResponseEntity<PatientDTO> response = patientRestController.delete(idPatient);
		assertEquals(idPatient, response.getBody().getIdPatient());
		//assert ir a la bae de datos a verificar que si se borró
	}
	
	private PatientDTO createPatientDTO() {
		PatientDTO patientDto = new PatientDTO();
		patientDto.setIdPatient("1223");
		patientDto.setName("test name");
		patientDto.setDateOfBirth("1999-01-02");
		patientDto.setIdType("CC");
		patientDto.setEps("test eps");
		patientDto.setClinicHistory("test ok");
		return patientDto;
	}
}
