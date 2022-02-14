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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.juan.estevez.app.entities.Patient;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class PatientDataJpaIT {

	private TestRestTemplate testRestTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PatientDataJpaIT(TestRestTemplate testRestTemplate, JdbcTemplate jdbcTemplate) {
		this.testRestTemplate = testRestTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertPatient.sql")
	void postPatient() {

		HttpEntity<Patient> request = new HttpEntity<>(createPatient());
		ResponseEntity<Patient> response = testRestTemplate.exchange("http://localhost:8080/patient", HttpMethod.POST,
				request, Patient.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM PATIENT WHERE ID_PATIENT = ?",
				response.getBody().getIdPatient());

		assertThat(responseDatabase).isNotNegative();
		assertEquals(1, responseDatabase);

		assertThat(response.getBody().getIdPatient()).isNotNull();
		assertEquals(createPatient().getIdPatient(), response.getBody().getIdPatient());

		assertThat(response.getBody().getName()).isNotEmpty();
		assertEquals(createPatient().getName(), response.getBody().getName());

		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(createPatient().getIdType(), response.getBody().getIdType());

		assertThat(response.getBody().getDateOfBirth()).isNotEmpty();
		assertEquals(createPatient().getDateOfBirth(), response.getBody().getDateOfBirth());

		assertThat(response.getBody().getEps()).isNotEmpty();
		assertEquals(createPatient().getEps(), response.getBody().getEps());

		assertThat(response.getBody().getClinicHistory()).isNotNull();
		assertEquals(createPatient().getClinicHistory(), response.getBody().getClinicHistory());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToUpdate.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPatientToUpdate.sql")
	void putPatient() {

		HttpEntity<Patient> request = new HttpEntity<>(updatePatient());
		ResponseEntity<Patient> response = testRestTemplate.exchange("http://localhost:8080/patient", HttpMethod.PUT,
				request, Patient.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM PATIENT WHERE ID_PATIENT = ?",
				response.getBody().getIdPatient());

		assertThat(responseDatabase).isNotNegative().isNotNull();
		assertEquals(1, responseDatabase);

		assertThat(response.getBody().getIdPatient()).isNotNull();
		assertEquals(updatePatient().getIdPatient(), response.getBody().getIdPatient());

		assertThat(response.getBody().getName()).isNotEmpty();
		assertEquals(updatePatient().getName(), response.getBody().getName());

		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(updatePatient().getIdType(), response.getBody().getIdType());

		assertThat(response.getBody().getDateOfBirth()).isNotEmpty();
		assertEquals(updatePatient().getDateOfBirth(), response.getBody().getDateOfBirth());

		assertThat(response.getBody().getEps()).isNotEmpty();
		assertEquals(updatePatient().getEps(), response.getBody().getEps());

		assertThat(response.getBody().getClinicHistory()).isNotNull();
		assertEquals(updatePatient().getClinicHistory(), response.getBody().getClinicHistory());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatients.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPatients.sql")
	void getPatient() {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Patient> request = new HttpEntity<Patient>(headers);

		ResponseEntity<Patient> response = testRestTemplate.exchange("http://localhost:8080/patient/findById/30302",
				HttpMethod.GET, request, Patient.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM PATIENT WHERE ID_PATIENT = ?",
				response.getBody().getIdPatient());

		assertThat(responseDatabase).isNotNegative().isNotNull();
		assertEquals(1, responseDatabase);

		ResponseEntity<Patient> response2 = testRestTemplate.exchange("http://localhost:8080/patient/findById/30303",
				HttpMethod.GET, request, Patient.class);

		int responseDatabase2 = jdbcTemplate.update("SELECT * FROM PATIENT WHERE ID_PATIENT = ?",
				response2.getBody().getIdPatient());

		assertThat(responseDatabase2).isNotNegative().isNotNull();
		assertEquals(1, responseDatabase2);

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
		
		ResponseEntity<Patient> response = testRestTemplate.exchange("http://localhost:8080/patient/30302",
				HttpMethod.DELETE, request, Patient.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM PATIENT WHERE ID_PATIENT = ?",
				response.getBody().getIdPatient());

		assertThat(responseDatabase).isNotNegative().isNotNull();
		assertEquals(0, responseDatabase);
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
