package com.juan.estevez.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.juan.estevez.app.entities.Patient;

@DataJpaTest
public class PatientRepositoryTest {

	private TestEntityManager testEntityManager;
	
	@Autowired
	public PatientRepositoryTest(TestEntityManager testEntityManager) {
		this.testEntityManager = testEntityManager;
	}

	/**
	 * Se encarga de testear el método post de la entidad Patient
	 */
	@Test
	public void savePatient() {
		Patient patient = new Patient();
		patient.setIdPatient("1829");
		patient.setName("Name2");
		patient.setDateOfBirth("2001-05-08");
		patient.setIdType("CC");
		patient.setEps("Nueva EPS");
		patient.setClinicHistory("Ok");
		testEntityManager.persistAndFlush(patient);
		assertThat(patient.getIdPatient()).isNotNull();
	}
	
}
