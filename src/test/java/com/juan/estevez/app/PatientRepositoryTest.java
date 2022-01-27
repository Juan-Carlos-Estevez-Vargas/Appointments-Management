package com.juan.estevez.app;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.juan.estevez.app.entities.Patient;

@DataJpaTest
class PatientRepositoryTest {

	private TestEntityManager testEntityManager;
	
	@Autowired
	public PatientRepositoryTest(TestEntityManager testEntityManager) {
		this.testEntityManager = testEntityManager;
	}

	/**
	 * Se encarga de testear el método post de la entidad Patient
	 */
	@Test
	void savePatient() {
		Patient patient = new Patient();
		patient.setIdPatient("1829");
		patient.setName("Name2");
		patient.setDateOfBirth("2001-05-08");
		patient.setIdType("CC");
		patient.setEps("Nueva EPS");
		patient.setClinicHistory("Ok");
		Patient response = new Patient();
		response = testEntityManager.persistAndFlush(patient);
		assertThat(response.getIdPatient()).isNotNull();
		assertThat(response.getName()).isNotEmpty();
		assertThat(response.getIdType()).isNotEmpty();
		assertThat(response.getDateOfBirth()).isNotEmpty();
		assertThat(response.getEps()).isNotEmpty();
		assertThat(response.getClinicHistory()).isNotEmpty();
	}
	
	@Test
	 void savePatient2() {
		Patient patient = new Patient();
		patient.setIdPatient("1829");
		patient.setName("Name2");
		patient.setDateOfBirth("2001-05-08");
		patient.setIdType("CC");
		patient.setEps("Nueva EPS");
		patient.setClinicHistory("Ok");
		Patient response = new Patient();
		response = testEntityManager.persistAndFlush(patient);
		assertThat(response.getIdPatient()).isNotNull();
		assertThat(response.getName()).isNotEmpty();
		assertThat(response.getIdType()).isNotEmpty();
		assertThat(response.getDateOfBirth()).isNotEmpty();
		assertThat(response.getEps()).isNotEmpty();
		assertThat(response.getClinicHistory()).isNotEmpty();
	}

}
