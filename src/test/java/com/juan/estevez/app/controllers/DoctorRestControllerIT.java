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
import com.juan.estevez.app.entities.Doctor;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class DoctorRestControllerIT {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void postDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("112233");
		doctor.setDoctorsName("Doctor Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1122 Test");
		doctor.setSpecialty("Specialty Test");
		doctor.setYearsExperience(5);
		doctor.setAttentionStartTime(7);
		doctor.setAttentionEndTime(17);
		HttpEntity<Doctor> request = new HttpEntity<>(doctor);
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.POST,
				request, Doctor.class);
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("112233", response.getBody().getIdDoctor());
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals("Doctor Test", response.getBody().getDoctorsName());
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals("CC", response.getBody().getIdType());
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals("1122 Test", response.getBody().getNumberProfessionalCard());
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals("Specialty Test", response.getBody().getSpecialty());
		assertThat(response.getBody().getYearsExperience()).isNotNull();
		assertEquals(5, response.getBody().getYearsExperience());
		assertThat(response.getBody().getAttentionStartTime()).isNotNull();
		assertEquals(7, response.getBody().getAttentionStartTime());
		assertThat(response.getBody().getAttentionEndTime()).isNotNull();
		assertEquals(17, response.getBody().getAttentionEndTime());
	}
	
	@Test
	void putDoctor() {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("10025990234");
		doctor.setDoctorsName("Doctor Test2");
		doctor.setIdType("CC2");
		doctor.setNumberProfessionalCard("1122 Test2");
		doctor.setSpecialty("Specialty Test2");
		doctor.setYearsExperience(5);
		doctor.setAttentionStartTime(7);
		doctor.setAttentionEndTime(17);
		HttpEntity<Doctor> request = new HttpEntity<>(doctor);
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor", HttpMethod.PUT, request, Doctor.class);
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("10025990234", response.getBody().getIdDoctor());
		assertThat(response.getBody().getDoctorsName()).isNotEmpty();
		assertEquals("Doctor Test2", response.getBody().getDoctorsName());
		assertThat(response.getBody().getIdType()).isNotEmpty();
		assertEquals("CC2", response.getBody().getIdType());
		assertThat(response.getBody().getNumberProfessionalCard()).isNotEmpty();
		assertEquals("1122 Test2", response.getBody().getNumberProfessionalCard());
		assertThat(response.getBody().getSpecialty()).isNotEmpty();
		assertEquals("Specialty Test2", response.getBody().getSpecialty());
		assertThat(response.getBody().getYearsExperience()).isNotNull();
		assertEquals(5, response.getBody().getYearsExperience());
		assertThat(response.getBody().getAttentionStartTime()).isNotNull();
		assertEquals(7, response.getBody().getAttentionStartTime());
		assertThat(response.getBody().getAttentionEndTime()).isNotNull();
		assertEquals(17, response.getBody().getAttentionEndTime());
	}
	
	@Test
	void getDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(headers);
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/doctor/findById/1235462388", HttpMethod.GET, request, Doctor.class);
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("1235462388", response.getBody().getIdDoctor());
	}
	
	@Test
	void deleteDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(headers);
		ResponseEntity<Doctor> response = testRestTemplate.exchange("http://localhost:8080/patient/1235462388", HttpMethod.DELETE, request, Doctor.class);
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("1235462388", response.getBody().getIdDoctor());
	}
}
