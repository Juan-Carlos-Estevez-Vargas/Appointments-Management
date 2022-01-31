package com.juan.estevez.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.juan.estevez.app.controllers.PatientRestController;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class InyectionTest {

	private PatientRestController patientRestController;
	private TestRestTemplate testRestTemplate;

	@Autowired
	public InyectionTest(PatientRestController patientRestController, TestRestTemplate testRestTemplate) {
		this.patientRestController = patientRestController;
		this.testRestTemplate = testRestTemplate;
	}

	@Test
	 void contextLoads() {
		assertThat(patientRestController).isNotNull();
		assertThat(testRestTemplate).isNotNull();
	}
}
