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
import com.juan.estevez.app.entities.Doctor;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class DoctorDataJpaIT {

	private TestRestTemplate testRestTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DoctorDataJpaIT(TestRestTemplate testRestTemplate, JdbcTemplate jdbcTemplate) {
		this.testRestTemplate = testRestTemplate;
		this.jdbcTemplate = jdbcTemplate;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertDoctor.sql")
	void postDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(createDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.POST,
				request, Doctor.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?",
				response.getBody().getIdDoctor());

		assertThat(responseDatabase).isNotNegative();
		assertEquals(1, responseDatabase);

		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals(createDoctor().getIdDoctor(), response.getBody().getIdDoctor());

		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals(createDoctor().getDoctorsName(), response.getBody().getDoctorsName());

		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(createDoctor().getIdType(), response.getBody().getIdType());

		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals(createDoctor().getNumberProfessionalCard(), response.getBody().getNumberProfessionalCard());

		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(createDoctor().getYearsExperience(), response.getBody().getYearsExperience());

		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals(createDoctor().getSpecialty(), response.getBody().getSpecialty());

		assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		assertEquals(createDoctor().getAttentionStartTime(), response.getBody().getAttentionStartTime());

		assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		assertEquals(createDoctor().getAttentionEndTime(), response.getBody().getAttentionEndTime());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToUpdate.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctorToUpdate.sql")
	void putDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(updateDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.PUT,
				request, Doctor.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?",
				response.getBody().getIdDoctor());

		assertThat(responseDatabase).isNotNegative().isNotNull();
		assertEquals(1, responseDatabase);

		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals(updateDoctor().getIdDoctor(), response.getBody().getIdDoctor());

		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals(updateDoctor().getDoctorsName(), response.getBody().getDoctorsName());

		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals(updateDoctor().getIdType(), response.getBody().getIdType());

		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals(updateDoctor().getNumberProfessionalCard(), response.getBody().getNumberProfessionalCard());

		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(updateDoctor().getYearsExperience(), response.getBody().getYearsExperience());

		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals(updateDoctor().getSpecialty(), response.getBody().getSpecialty());

		assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		assertEquals(updateDoctor().getAttentionStartTime(), response.getBody().getAttentionStartTime());

		assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		assertEquals(updateDoctor().getAttentionEndTime(), response.getBody().getAttentionEndTime());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctors.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctors.sql")
	void getDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(headers);

		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor/findById/101011",
				HttpMethod.GET, request, Doctor.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?",
				response.getBody().getIdDoctor());

		assertThat(responseDatabase).isNotNegative().isNotNull();
		assertEquals(1, responseDatabase);

		ResponseEntity<Doctor> response2 = testRestTemplate.exchange("http://localhost:8080/doctor/findById/101012",
				HttpMethod.GET, request, Doctor.class);

		int responseDatabase2 = jdbcTemplate.update("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?",
				response2.getBody().getIdDoctor());

		assertThat(responseDatabase2).isNotNegative().isNotNull();
		assertEquals(1, responseDatabase2);

		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("101011", response.getBody().getIdDoctor());

		assertThat(response2.getBody().getIdDoctor()).isNotNull();
		assertEquals("101012", response2.getBody().getIdDoctor());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToDelete.sql")
	void deleteDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(headers);

		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor/112255",
				HttpMethod.DELETE, request, Doctor.class);

		int responseDatabase = jdbcTemplate.update("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?",
				response.getBody().getIdDoctor());

		assertThat(responseDatabase).isNotNegative().isNotNull();
		assertEquals(0, responseDatabase);
	}

	/**
	 * Crea un nuevo doctor para insertar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return doctor a insertar
	 */
	private Doctor createDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("1010");
		doctor.setDoctorsName("Doctor Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(1);
		doctor.setSpecialty("Neurología");
		doctor.setAttentionStartTime(8);
		doctor.setAttentionEndTime(18);
		return doctor;
	}

	/**
	 * Crea un nuevo doctor para actualizar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return doctor a actualizar
	 */
	private Doctor updateDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("101010");
		doctor.setDoctorsName("Doctor Test Update");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1");
		doctor.setYearsExperience(3);
		doctor.setSpecialty("Neurología");
		doctor.setAttentionStartTime(6);
		doctor.setAttentionEndTime(18);
		return doctor;
	}
}
