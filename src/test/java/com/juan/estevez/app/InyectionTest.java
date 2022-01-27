package com.juan.estevez.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.juan.estevez.app.controllers.PatientRestController;

@SpringBootTest
class InyectionTest {

	private PatientRestController patientRestController;

	@Autowired
	public InyectionTest(PatientRestController patientRestController) {
		this.patientRestController = patientRestController;
	}

	/**
	 * Se encarga de probar si la inyección se está realizando correctamente.
	 */
	@Test
	 void contextLoads() {
		assertThat(patientRestController).isNotNull();
	}
}
