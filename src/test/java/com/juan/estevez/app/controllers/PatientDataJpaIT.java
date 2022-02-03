package com.juan.estevez.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class PatientDataJpaIT {

	private TestRestTemplate testRestTemplate;
	private IPatientService patientService;
	
	//@MockBean
//	private IPatientService patientService;

	@Autowired
	public PatientDataJpaIT(TestRestTemplate testRestTemplate, IPatientService patientService) {
		this.testRestTemplate = testRestTemplate;
		this.patientService = patientService;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertPatient.sql")
	void postPatient() {
		HttpEntity<Patient> request = new HttpEntity<>(createPatient());
		ResponseEntity<Patient> response = testRestTemplate.exchange(
				"http://localhost:8080/patient", HttpMethod.POST, request, Patient.class);
		Patient responseDatabase = patientService.get(response.getBody().getIdPatient());
		assertThat(responseDatabase.getIdPatient()).isNotEmpty();
		assertEquals(response.getBody().getIdPatient(), responseDatabase.getIdPatient());
		assertThat(response.getBody().getIdPatient()).isNotNull();
		assertEquals("1829", response.getBody().getIdPatient());
		assertThat(response.getBody().getName()).isNotEmpty();
		assertEquals("Patient Test", response.getBody().getName());
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals("CC", response.getBody().getIdType());
		assertThat(response.getBody().getDateOfBirth()).isNotEmpty();
		assertEquals("2001-05-08", response.getBody().getDateOfBirth());
		assertThat(response.getBody().getEps()).isNotEmpty();
		assertEquals("Nueva EPS Test", response.getBody().getEps());
		assertThat(response.getBody().getClinicHistory()).isNotNull();
		assertEquals("Ok Test", response.getBody().getClinicHistory());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToUpdate.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPatientToUpdate.sql")
	void putPatient() {
		HttpEntity<Patient> request = new HttpEntity<>(updatePatient());
		ResponseEntity<Patient> response = testRestTemplate.exchange(
				"http://localhost:8080/patient", HttpMethod.PUT, request, Patient.class);
		Patient responseDatabase = patientService.get(response.getBody().getIdPatient());
		assertThat(responseDatabase.getIdPatient()).isNotEmpty();
		assertEquals(response.getBody().getIdPatient(), responseDatabase.getIdPatient());
		assertThat(response.getBody().getIdPatient()).isNotNull();
		assertEquals("30", response.getBody().getIdPatient());
		assertThat(response.getBody().getName()).isNotEmpty();
		assertEquals("Name88", response.getBody().getName());
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals("CC", response.getBody().getIdType());
		assertThat(response.getBody().getDateOfBirth()).isNotEmpty();
		assertEquals("2000-10-11", response.getBody().getDateOfBirth());
		assertThat(response.getBody().getEps()).isNotEmpty();
		assertEquals("Nueva EPS Test", response.getBody().getEps());
		assertThat(response.getBody().getClinicHistory()).isNotNull();
		assertEquals("Ok", response.getBody().getClinicHistory());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatients.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPatients.sql")
	void getPatient() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Patient> request = new HttpEntity<Patient>(headers);
		ResponseEntity<Patient> response = testRestTemplate.exchange(
				"http://localhost:8080/patient/findById/30302",	HttpMethod.GET, request, Patient.class);
		Patient responseDatabase1 = patientService.get(response.getBody().getIdPatient());
		assertThat(responseDatabase1.getIdPatient()).isNotEmpty();
		assertEquals(response.getBody().getIdPatient(), responseDatabase1.getIdPatient());
		ResponseEntity<Patient> response2 = testRestTemplate.exchange(
				"http://localhost:8080/patient/findById/30303",	HttpMethod.GET, request, Patient.class);
		Patient responseDatabase2 = patientService.get(response2.getBody().getIdPatient());
		assertThat(responseDatabase2.getIdPatient()).isNotEmpty();
		assertEquals(response2.getBody().getIdPatient(), responseDatabase2.getIdPatient());
		assertThat(response.getBody().getIdPatient()).isNotNull();
		assertEquals("30302", response.getBody().getIdPatient());
		assertThat(response2.getBody().getIdPatient()).isNotNull();
		assertEquals("30303", response2.getBody().getIdPatient());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToDelete.sql")
	void deletePatient() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Patient> request = new HttpEntity<Patient>(headers);
		ResponseEntity<Patient> response = testRestTemplate.exchange(
				"http://localhost:8080/patient/30302", HttpMethod.DELETE, request, Patient.class);
		assertThat(response.getBody().getIdPatient()).isNotNull();
		assertEquals("30302", response.getBody().getIdPatient());
	}

	/**
	 * Crea un nuevo paciente para insertar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return paciente a insertar
	 */
	private Patient createPatient() {
		Patient patient = new Patient();
		patient.setIdPatient("1829");
		patient.setName("Patient Test");
		patient.setDateOfBirth("2001-05-08");
		patient.setIdType("CC");
		patient.setEps("Nueva EPS Test");
		patient.setClinicHistory("Ok Test");
		return patient;
	}

	/**
	 * Crea un nuevo paciente para actualizar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return paciente a actualizar
	 */
	private Patient updatePatient() {
		Patient patient = new Patient();
		patient.setIdPatient("30");
		patient.setName("Name88");
		patient.setDateOfBirth("2000-10-11");
		patient.setIdType("CC");
		patient.setEps("Nueva EPS Test");
		patient.setClinicHistory("Ok");
		return patient;
	}
}
