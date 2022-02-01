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
public class AppointmentDataJpaIT {
	
	private TestRestTemplate testRestTemplate;

	@Autowired
	public AppointmentDataJpaIT(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertDoctorToPostAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertPatientToPostAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "sql/appointment/cleanInsertAppointment.sql")
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
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertDoctorToPutAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertPatientToPutAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertAppointmentToPut.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "sql/appointment/cleanPutAppointment.sql")
	void putAppointment() {
		HttpEntity<Appointment> request = new HttpEntity<>(updateAppointment());
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment", HttpMethod.PUT, request, Appointment.class);
		assertThat(response.getBody().getIdAppointment()).isNotNull();
		assertEquals(1, response.getBody().getIdAppointment());
		assertThat(response.getBody().getDoctor()).isNotNull();
		assertEquals("100001", response.getBody().getDoctor());
		assertThat(response.getBody().getPatient()).isNotNull();
		assertEquals("33", response.getBody().getPatient());
		assertThat(response.getBody().getDate()).isNotNull();
		assertEquals("2023-10-11", response.getBody().getDate());
		assertThat(response.getBody().getHour()).isNotNull();
		assertEquals(14, response.getBody().getHour());
	}
	
	
	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertDoctorsToGetAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertPatientsToGetAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertAppointmentsToGet.sql")
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "sql/appointment/cleanAppointments.sql")
	void getAppointments() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(headers);
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment/findById/9",	HttpMethod.GET, request, Appointment.class);
		ResponseEntity<Appointment> response2 = testRestTemplate.exchange(
				"http://localhost:8080/appointment/findById/8",	HttpMethod.GET, request, Appointment.class);
		assertThat(response.getBody().getIdAppointment()).isNotNull();
		assertEquals(9, response.getBody().getIdAppointment());
		assertThat(response2.getBody().getIdAppointment()).isNotNull();
		assertEquals(8, response2.getBody().getIdAppointment());
	}

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertDoctorToDeleteAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertPatientToDeleteAppointment.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/insertAppointmentToDelete.sql")
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "sql/appointment/cleanAppointmentToDelete.sql")
	void deleteAppointment() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Appointment> request = new HttpEntity<Appointment>(headers);
		ResponseEntity<Appointment> response = testRestTemplate.exchange(
				"http://localhost:8080/appointment/14", HttpMethod.DELETE, request, Appointment.class);
		assertThat(response.getBody().getIdAppointment()).isNotNull();
		assertEquals(14, response.getBody().getIdAppointment());
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
		appointment.setIdAppointment(1);
		appointment.setDoctor("100001");
		appointment.setPatient("33");
		appointment.setDate("2023-10-11");
		appointment.setHour(14);
		return appointment;
	}
}
