package com.juan.estevez.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.juan.estevez.app.entities.Appointment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppointmentControllerWebTestClientTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testSaveAppointment() {
		// given
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(32);
		appointment.setDoctor("9000");
		appointment.setPatient("1");
		appointment.setDate("2000-10-12");
		appointment.setHour(12);

		// when
		webTestClient.post().uri("http://localhost:8080/appointment").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(appointment).exchange()

				// then
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.idAppointment").isEqualTo(appointment.getIdAppointment()).jsonPath("$.doctor")
				.isEqualTo(appointment.getDoctor());

	}

	/*@Test
	void testGetAppointmentById() {
		webTestClient.get().uri("http://localhost:8080/appointment/findById/32").exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.idAppointment")
				.isEqualTo(32).jsonPath("$.doctor").isEqualTo("9000");
	}

	@Test
	void testListAppointments() {
		webTestClient.get().uri("http://localhost:8080/appointment/findAll").exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$[0].idAppointment")
				.isEqualTo(32).jsonPath("$[0].doctor").isEqualTo("9000").jsonPath("$").isArray();
	}

	@Test
	void tesGetListAppointments() {
		webTestClient.get().uri("http://localhost:8080/appointment/findAll").exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBodyList(Appointment.class)
				.consumeWith(response -> {
					List<Appointment> appointments = response.getResponseBody();
					assertEquals(1, appointments.size());
					assertNotNull(appointments);
				});
	}

	@Test
	void testUpdateAppointment() {
		Appointment appointmentUpdated = new Appointment();
		appointmentUpdated.setIdAppointment(9000);
		appointmentUpdated.setDoctor("101010");
		appointmentUpdated.setPatient("2020");
		appointmentUpdated.setDate("2023-11-12");
		appointmentUpdated.setHour(13);

		webTestClient.put().uri("http://localhost:8080/appointment/findById/32").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(appointmentUpdated).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON);
	}

	@Test
	void testDeleteAppointment() {
		webTestClient.get().uri("http://localhost:8080/appointment").exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBodyList(Appointment.class).hasSize(1);

		webTestClient.delete().uri("http://localhost:8080/appointment/32").exchange().expectStatus().isOk();

		webTestClient.get().uri("http://localhost:8080/appointment").exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBodyList(Appointment.class).hasSize(0);

		webTestClient.get().uri("http://localhost:8080/appointment/32").exchange().expectStatus().is4xxClientError();
	}*/

}
