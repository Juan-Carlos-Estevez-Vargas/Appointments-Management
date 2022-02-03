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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AppointmentDataJpaIT {
	
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
		assertEquals(100, response.getBody().getIdAppointment());
		assertThat(response.getBody().getDoctor()).isNotNull();
		assertEquals("100000", response.getBody().getDoctor());
		assertThat(response.getBody().getPatient()).isNotNull();
		assertEquals("3030100", response.getBody().getPatient());
		assertThat(response.getBody().getDate()).isNotNull();
		assertEquals("2022-10-11", response.getBody().getDate());
		assertThat(response.getBody().getHour()).isNotNegative();
		assertEquals(12, response.getBody().getHour());
	}
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToPutAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToPutAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertAppointmentToPut.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanPutAppointment.sql")
	void putAppointment() {
		HttpEntity<Appointment> request = new HttpEntity<>(updateAppointment());
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment", HttpMethod.PUT, request, Appointment.class);
		assertEquals(1, response.getBody().getIdAppointment());
		assertThat(response.getBody().getDoctor()).isNotNull();
		assertEquals("100001", response.getBody().getDoctor());
		assertThat(response.getBody().getPatient()).isNotNull();
		assertEquals("33", response.getBody().getPatient());
		assertThat(response.getBody().getDate()).isNotNull();
		assertEquals("2023-10-11", response.getBody().getDate());
		assertThat(response.getBody().getHour()).isNotNegative();
		assertEquals(14, response.getBody().getHour());
	}
	
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorsToGetAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientsToGetAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertAppointmentsToGet.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "cleanAppointments.sql")
	void getAppointments() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(headers);
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment/findById/9",	HttpMethod.GET, request, Appointment.class);
		ResponseEntity<Appointment> response2 = testRestTemplate.exchange(
				"http://localhost:8080/appointment/findById/8",	HttpMethod.GET, request, Appointment.class);
		assertEquals(9, response.getBody().getIdAppointment());
		assertEquals(8, response2.getBody().getIdAppointment());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertDoctorToDeleteAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertPatientToDeleteAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "insertAppointmentToDelete.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "cleanAppointmentToDelete.sql")
	void deleteAppointment() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(headers);
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment/14", HttpMethod.DELETE, request, Appointment.class);
		assertEquals(14, response.getBody().getIdAppointment());
	}
	
	/**
	 * Crea una nueva cita para insertar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return cita a insertar
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
	 * Crea una nueva cita para actualizar en la base de datos como parte de la
	 * prueba de integración
	 * 
	 * @return cita a actualizar
	 */
	private Appointment updateAppointment() {
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(1);
		appointment.setDoctor("100001");
		appointment.setPatient("33");
		appointment.setDate("2023-10-11");
		appointment.setHour(14);
		return appointment;
	}
}
