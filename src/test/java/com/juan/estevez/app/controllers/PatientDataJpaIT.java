package com.juan.estevez.app.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
		ResultActions response = mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patient)));
		
		// then
		response.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.name",is(patient.getName())))
		.andExpect(jsonPath("eps", is(patient.getEps())));
	}
	
}
