package com.juan.estevez.app.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

@SpringBootTest
@AutoConfigureMockMvc
class PatientDataJpaIT {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPatientService patientService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Test to Insert a Patient from Controller")
	void testSavePatient() throws Exception {
		// given
		Patient patient = new Patient();
		patient.setIdPatient("9000");
		patient.setName("Patient Unit Test Controller");
		patient.setIdType("CC");
		patient.setDateOfBirth("2000-10-09");
		patient.setEps("EPS test");
		patient.setClinicHistory("OK");

		given(patientService.save(any(Patient.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patient)));

		// then
		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name", is(patient.getName())))
				.andExpect(jsonPath("$.eps", is(patient.getEps())));
	}

	@Test
	@DisplayName("Test to get a Patients List from Controller")
	void testListPatients() throws Exception {
		// given
		List<Patient> patientList = new ArrayList<>();
		patientList.add(new Patient("9001", "Patient1", "CC", "1999-10-10", "EPS 1", "OK"));
		patientList.add(new Patient("9002", "Patient1", "CC", "1999-10-10", "EPS 1", "OK"));
		patientList.add(new Patient("9003", "Patient1", "CC", "1999-10-10", "EPS 1", "OK"));

		given(patientService.list()).willReturn(patientList);

		// when
		ResultActions response = mockMvc.perform(get("/patient/findAll"));

		// then
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(patientList.size())));
	}

	@Test
	@DisplayName("Test to get a Patient by ID from Controller")
	void testGetPatientById() throws Exception {
		// given
		Patient patient = new Patient();
		patient.setIdPatient("9000");
		patient.setName("Patient Unit Test Controller");
		patient.setIdType("CC");
		patient.setDateOfBirth("2000-10-09");
		patient.setEps("EPS test");
		patient.setClinicHistory("OK");

		given(patientService.get(patient.getIdPatient())).willReturn(patient);

		// when
		ResultActions response = mockMvc.perform(get("/patient/findById/{idPatient}", patient.getIdPatient()));

		// then
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.name", is(patient.getName())))
				.andExpect(jsonPath("$.eps", is(patient.getEps())));
	}

	@Test
	@DisplayName("Test to update a Patient from Controller")
	void testUpdatePatient() throws JsonProcessingException, Exception {
		// given
		Patient patientSaved = new Patient();
		patientSaved.setIdPatient("9000");
		patientSaved.setName("Patient Unit Test Controller");
		patientSaved.setIdType("CC");
		patientSaved.setDateOfBirth("2000-10-09");
		patientSaved.setEps("EPS test");
		patientSaved.setClinicHistory("OK");

		Patient patientUpdated = new Patient();
		patientUpdated.setIdPatient("9000");
		patientUpdated.setName("Patient Updated Unit Test Controller");
		patientUpdated.setIdType("CC");
		patientUpdated.setDateOfBirth("2000-10-09");
		patientUpdated.setEps("EPS test Update");
		patientUpdated.setClinicHistory("OK");

		given(patientService.get(patientSaved.getIdPatient())).willReturn(patientSaved);
		given(patientService.update(any(Patient.class))).willAnswer((invocation) -> invocation.getArgument(0));

		// when
		ResultActions response = mockMvc.perform(put("/patient").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patientUpdated)));

		// then
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.name", is(patientUpdated.getName())))
				.andExpect(jsonPath("$.eps", is(patientUpdated.getEps())));
	}
	
	@Test
	@DisplayName("Test to delete a Patient by ID from Controller")
	void testDeletePatient() throws Exception {
		// given
		String idPatient = "9000";
		willDoNothing().given(patientService).delete(idPatient);;
		
		// when
		ResultActions response = mockMvc.perform(delete("/patient/{idPatient}", idPatient));
		
		// then
		response.andExpect(status().isOk()).andDo(print());
	}

}
