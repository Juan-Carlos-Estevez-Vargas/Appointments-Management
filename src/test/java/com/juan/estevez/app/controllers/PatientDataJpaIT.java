package com.juan.estevez.app.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IDoctorService;
import com.juan.estevez.app.services.IPatientService;

@WebMvcTest
class PatientDataJpaIT {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPatientService patientService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	void testSavePatient() throws JsonProcessingException, Exception {
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
	}
	
}
