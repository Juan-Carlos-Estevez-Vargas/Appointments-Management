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
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.services.IDoctorService;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class DoctorDataJpaIT {
	
	private TestRestTemplate testRestTemplate;
	private IDoctorService doctorService;

	@Autowired
	public DoctorDataJpaIT(TestRestTemplate testRestTemplate, IDoctorService doctorService) {
		this.testRestTemplate = testRestTemplate;
		this.doctorService = doctorService;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertDoctor.sql")
	void postDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(createDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange(
				"http://localhost:8080/doctor", HttpMethod.POST, request, Doctor.class);
		Doctor responseDatabase = doctorService.get(response.getBody().getIdDoctor());
		assertThat(responseDatabase.getIdDoctor()).isNotEmpty();
		assertEquals(response.getBody().getIdDoctor(), responseDatabase.getIdDoctor());
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("1010", response.getBody().getIdDoctor());
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals("Doctor Test", response.getBody().getDoctorsName());
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals("CC", response.getBody().getIdType());
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals("1111", response.getBody().getNumberProfessionalCard());
		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(1, response.getBody().getYearsExperience());
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals("Neurología", response.getBody().getSpecialty());
		assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		assertEquals(8, response.getBody().getAttentionStartTime());
		assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		assertEquals(18, response.getBody().getAttentionEndTime());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToUpdate.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctorToUpdate.sql")
	void putDoctor() {
		HttpEntity<Doctor> request = new HttpEntity<>(updateDoctor());
		ResponseEntity<Doctor> response = testRestTemplate.exchange(
				"http://localhost:8080/doctor", HttpMethod.PUT, request, Doctor.class);
		Doctor responseDatabase = doctorService.get(response.getBody().getIdDoctor());
		assertThat(responseDatabase.getIdDoctor()).isNotEmpty();
		assertEquals(response.getBody().getIdDoctor(), responseDatabase.getIdDoctor());
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("101010", response.getBody().getIdDoctor());
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals("Doctor Test Update", response.getBody().getDoctorsName());
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals("CC", response.getBody().getIdType());
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals("1", response.getBody().getNumberProfessionalCard());
		assertThat(response.getBody().getYearsExperience()).isNotNegative();
		assertEquals(3, response.getBody().getYearsExperience());
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals("Neurología", response.getBody().getSpecialty());
		assertThat(response.getBody().getAttentionStartTime()).isNotNegative();
		assertEquals(6, response.getBody().getAttentionStartTime());
		assertThat(response.getBody().getAttentionEndTime()).isNotNegative();
		assertEquals(18, response.getBody().getAttentionEndTime());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctors.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanDoctors.sql")
	void getDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(headers);
		ResponseEntity<Doctor> response = testRestTemplate.exchange(
				"http://localhost:8080/doctor/findById/101011",	HttpMethod.GET, request, Doctor.class);
		Doctor responseDatabase1 = doctorService.get(response.getBody().getIdDoctor());
		assertThat(responseDatabase1.getIdDoctor()).isNotEmpty();
		assertEquals(response.getBody().getIdDoctor(), responseDatabase1.getIdDoctor());
		ResponseEntity<Doctor> response2 = testRestTemplate.exchange(
				"http://localhost:8080/doctor/findById/101012",	HttpMethod.GET, request, Doctor.class);
		Doctor responseDatabase2 = doctorService.get(response2.getBody().getIdDoctor());
		assertThat(responseDatabase2.getIdDoctor()).isNotEmpty();
		assertEquals(response2.getBody().getIdDoctor(), responseDatabase2.getIdDoctor());
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
		ResponseEntity<Doctor> response = testRestTemplate.exchange(
				"http://localhost:8080/doctor/112255", HttpMethod.DELETE, request, Doctor.class);
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("112255", response.getBody().getIdDoctor());
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
