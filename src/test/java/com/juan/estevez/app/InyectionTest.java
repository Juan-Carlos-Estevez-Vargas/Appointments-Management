package com.juan.estevez.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.juan.estevez.app.controllers.PatientRestController;
import com.juan.estevez.app.repositories.IDoctorRepository;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class InyectionTest {

	private PatientRestController patientRestController;
	private TestRestTemplate testRestTemplate;
	private IDoctorRepository doctorRepository;

	@Autowired
	public InyectionTest(PatientRestController patientRestController, TestRestTemplate testRestTemplate, IDoctorRepository doctorRepository) {
		this.patientRestController = patientRestController;
		this.testRestTemplate = testRestTemplate;
		this.doctorRepository = doctorRepository;
		
	}

	@Test
	 void contextLoads() {
		assertThat(patientRestController).isNotNull();
		assertThat(testRestTemplate).isNotNull();
		assertThat(doctorRepository).isNotNull();
	}
}
