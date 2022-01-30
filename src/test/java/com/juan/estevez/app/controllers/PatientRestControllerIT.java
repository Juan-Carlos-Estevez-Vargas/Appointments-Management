package com.juan.estevez.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.juan.estevez.app.dto.PatientDTO;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

@SpringBootTest
public class PatientRestControllerIT {

	private IPatientService patientService;
	private ModelMapper modelMapper;
	
	@Autowired
	public PatientRestControllerIT(IPatientService patientService, ModelMapper modelMapper) {
		this.patientService = patientService;
		this.modelMapper = modelMapper;
	}
	
	@Test
	void postPatient() {
		PatientRestController patientRestController = new PatientRestController(patientService, modelMapper);
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
		PatientRestController patientRestController = new PatientRestController(patientService, modelMapper);
		ResponseEntity<PatientDTO> response = patientRestController.update(createPatientDTO());
		assertEquals(createPatientDTO().getIdPatient(), response.getBody().getIdPatient());
		assertEquals(createPatientDTO().getName(), response.getBody().getName());
		assertEquals(createPatientDTO().getDateOfBirth(), response.getBody().getDateOfBirth());
		assertEquals(createPatientDTO().getIdType(), response.getBody().getIdType());
		assertEquals(createPatientDTO().getEps(), response.getBody().getEps());
		assertEquals(createPatientDTO().getClinicHistory(), response.getBody().getClinicHistory());
	}
	
	@Test 
	void deletePatient(){
		PatientRestController patientRestController = new PatientRestController(patientService, modelMapper);
		String idPatient = "1223";
		ResponseEntity<Patient> response = patientRestController.delete(idPatient);
		assertEquals(idPatient, response.getBody().getIdPatient());
	}
	
	@Test
	void getPatient() {
		PatientRestController patientRestController = new PatientRestController(patientService, modelMapper);
		String idPatient = "1223";
		PatientDTO response = patientRestController.searchPatient(idPatient);
		assertEquals(idPatient, response.getIdPatient());
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
