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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.entities.Doctor;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AppointmentDataJpaIT {
	
	private TestRestTemplate testRestTemplate;

	@Autowired
	public AppointmentDataJpaIT(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToPostAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToPostAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanInsertAppointment.sql")
	void postAppointment() {
		HttpEntity<Appointment> request = new HttpEntity<>(createAppointment());
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment", HttpMethod.POST, request, Appointment.class);
		assertThat(response.getBody().getIdAppointment()).isNotNull();
		assertEquals(100, response.getBody().getIdAppointment());
		assertThat(response.getBody().getDoctor()).isNotNull();
		assertEquals("100000", response.getBody().getDoctor());
		assertThat(response.getBody().getPatient()).isNotNull();
		assertEquals("3030100", response.getBody().getPatient());
		assertThat(response.getBody().getDate()).isNotNull();
		assertEquals("2022-10-11", response.getBody().getDate());
		assertThat(response.getBody().getHour()).isNotNull();
		assertEquals(12, response.getBody().getHour());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToPutAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToPutAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPutAppointment.sql")
	void putAppointment() {
		HttpEntity<Appointment> request = new HttpEntity<>(updateAppointment());
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment", HttpMethod.PUT, request, Appointment.class);
		assertThat(response.getBody().getIdAppointment()).isNotNull();
		assertEquals(101, response.getBody().getIdAppointment());
		assertThat(response.getBody().getDoctor()).isNotNull();
		assertEquals("100001", response.getBody().getDoctor());
		assertThat(response.getBody().getPatient()).isNotNull();
		assertEquals("3030101", response.getBody().getPatient());
		assertThat(response.getBody().getDate()).isNotNull();
		assertEquals("2023-10-11", response.getBody().getDate());
		assertThat(response.getBody().getHour()).isNotNull();
		assertEquals(15, response.getBody().getHour());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorsToGetAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientsToGetAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanAppointments.sql")
	void getDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(headers);
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment/findById/10",	HttpMethod.GET, request, Appointment.class);
		ResponseEntity<Appointment> response2 = testRestTemplate.exchange(
				"http://localhost:8080/appointment/findById/11",	HttpMethod.GET, request, Appointment.class);
		assertThat(response.getBody().getIdAppointment()).isNotNull();
		assertEquals("10", response.getBody().getIdAppointment());
		assertThat(response2.getBody().getIdAppointment()).isNotNull();
		assertEquals("11", response2.getBody().getIdAppointment());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToDelete.sql")
	void deleteDoctor() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Doctor> request = new HttpEntity<Doctor>(headers);
		ResponseEntity<Doctor> response = testRestTemplate.exchange(
				"http://localhost:8080/patient/112255", HttpMethod.DELETE, request, Doctor.class);
		assertThat(response.getBody().getIdDoctor()).isNotNull();
		assertEquals("112255", response.getBody().getIdDoctor());
	}
	
	/**
	 * Crea un nuevo paciente para insertar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return paciente a insertar
	 */
	private Appointment createAppointment() {
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(100);
		appointment.setDoctor("100000");
		appointment.setPatient("3030100");
		appointment.setDate("2022-10-11");
		appointment.setHour(12);
		return appointment;
	}

	/**
	 * Crea un nuevo paciente para actualizar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return paciente a actualizar
	 */
	private Appointment updateAppointment() {
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(101);
		appointment.setDoctor("100001");
		appointment.setPatient("3030101");
		appointment.setDate("2023-10-11");
		appointment.setHour(15);
		return appointment;
	}
}
