package com.juan.estevez.app.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.services.IAppointmentService;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentMockMvcIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IAppointmentService appointmentService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Appointment appointment;
	
	@BeforeEach
	void init() {
		appointment = new Appointment();
		appointment.setIdAppointment(200);
		appointment.setDoctor("900");
		appointment.setPatient("3300");
		appointment.setDate("2023-10-12");
		appointment.setHour(12);
	}
	
	@Test
	@DisplayName("Test to Insert an Appointment from Controller")
	void testSavePatient() throws Exception {
		// given
		given(appointmentService.save(any(Appointment.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(post("/appointment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appointment)));

		// then
		response.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.doctor", is(appointment.getDoctor())))
				.andExpect(jsonPath("$.patient", is(appointment.getPatient())));
	}

	@Test
	@DisplayName("Test to get an Appointments List from Controller")
	void testListPatients() throws Exception {
		// given
		List<Appointment> appointmentList = new ArrayList<>();
		appointmentList.add(new Appointment(12, "800", "34", "1999-10-10", 12));
		appointmentList.add(new Appointment(13, "800", "34", "1999-10-10", 12));
		appointmentList.add(new Appointment(14, "800", "34", "1999-10-10", 12));

		given(appointmentService.list()).willReturn(appointmentList);

		// when
		ResultActions response = mockMvc.perform(get("/appointment/findAll"));

		// then
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()", is(appointmentList.size())));
	}

	@Test
	@DisplayName("Test to get an Appointment by ID from Controller")
	void testGetPatientById() throws Exception {
		// given
		given(appointmentService.get(appointment.getIdAppointment())).willReturn(appointment);

		// when
		ResultActions response = mockMvc.perform(get("/appointment/findById/{idAppointment}", appointment.getIdAppointment()));

		// then
		response.andExpect(status().isOk())
				.andDo(print()).andExpect(jsonPath("$.doctor", is(appointment.getDoctor())))
				.andExpect(jsonPath("$.patient", is(appointment.getPatient())));
	}

	@Test
	@DisplayName("Test to update an Appointment from Controller")
	void testUpdatePatient() throws JsonProcessingException, Exception {
		// given
		Appointment appointmentUpdated = new Appointment();
		appointmentUpdated.setIdAppointment(200);
		appointmentUpdated.setDoctor("900");
		appointmentUpdated.setPatient("3300");
		appointmentUpdated.setDate("2024-10-12");
		appointmentUpdated.setHour(14);

		given(appointmentService.get(appointmentUpdated.getIdAppointment()))
				.willReturn(appointmentUpdated);
		given(appointmentService.update(any(Appointment.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(put("/appointment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appointmentUpdated)));

		// then
		response.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.doctor", is(appointmentUpdated.getDoctor())))
				.andExpect(jsonPath("$.patient", is(appointmentUpdated.getPatient())));
	}

	@Test
	@DisplayName("Test to delete an Appointment by ID from Controller")
	void testDeletePatient() throws Exception {
		// given
		given(appointmentService.get(appointment.getIdAppointment())).willReturn(appointment);

		// when
		ResultActions response = mockMvc.perform(delete("/appointment/{idAppointment}", appointment.getIdAppointment()));

		// then
		response.andExpect(status().isOk()).andDo(print());
	}
}

