package com.juan.estevez.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.juan.estevez.app.dto.PatientDTO;
import com.juan.estevez.app.entities.Patient;

@SpringBootTest
public class PatientRestControllerIT2 {
	
	private PatientRestController patientRestController;

	@Autowired
	public PatientRestControllerIT2(PatientRestController patientRestController) {
		this.patientRestController = patientRestController;
	}
	
	@Test
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
	void putPatient() {
		ResponseEntity<PatientDTO> response = patientRestController.update(createPatientDTO());
		assertEquals(createPatientDTO().getIdPatient(), response.getBody().getIdPatient());
		assertEquals(createPatientDTO().getName(), response.getBody().getName());
		assertEquals(createPatientDTO().getDateOfBirth(), response.getBody().getDateOfBirth());
		assertEquals(createPatientDTO().getIdType(), response.getBody().getIdType());
		assertEquals(createPatientDTO().getEps(), response.getBody().getEps());
		assertEquals(createPatientDTO().getClinicHistory(), response.getBody().getClinicHistory());
	}
	
	@Test
	void getPatient() {
		String idPatient = "1223";
		Patient response = patientRestController.searchPatient(idPatient);
		assertEquals(idPatient, response.getIdPatient());
	}
	
	@Test
	void deletePatient() {
		String idPatient = "1223";
		ResponseEntity<Patient> response = patientRestController.delete(idPatient);
		assertEquals(idPatient, response.getBody().getIdPatient());
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
