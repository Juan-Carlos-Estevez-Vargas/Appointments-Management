package com.juan.estevez.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.juan.estevez.app.entities.Patient;

@SpringBootTest
public class PatientRepositoryTest {

	@Autowired
	private IPatientRepository patientRepository;

	private Patient patient;

	@BeforeEach
	void setup() {
		patient = new Patient();
		patient.setIdPatient("2080");
		patient.setName("Patient Repository Test");
		patient.setIdType("CC");
		patient.setDateOfBirth("1987-02-12");
		patient.setEps("Eps Test");
		patient.setClinicHistory("OK");
	}

	@Test
	@DisplayName("Test to save a Patient from Repository.")
	void testSavePatient() {
		// given - condición previa o configuración. Crear el paciente

		// when - acción o comportamiento a probar.
		Patient patientSaved = patientRepository.save(patient);

		// then - verificación de la salida esperada.
		assertThat(patientSaved).isNotNull();
		assertThat(patientSaved.getName()).isEqualTo(patient.getName());
		assertThat(patientSaved.getEps()).isEqualTo(patient.getEps());
	}

	@Test
	@DisplayName("Test to List patients from Repository.")
	void testListPatients() {
		// given - condición previa o configuración. Crear el paciente
		patientRepository.save(patient);

		// when - acción o comportamiento a probar.
		List<Patient> patientList = patientRepository.findAll();

		// then - verificación de la salida esperada.
		assertThat(patientList).isNotNull();
		assertThat(patientList.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Test to get a patient by id from Repository.")
	void testGetPatientById() {
		// given - condición previa o configuración.
		patientRepository.save(patient);

		// when - acción o comportamiento a probar.
		Patient patientDB = patientRepository.getById(patient.getIdPatient());

		// then - verificación de la salida esperada.
		assertThat(patientDB).isNotNull();
	}

	@Test
	@DisplayName("Test to update a patient from Repository.")
	void testUpdatePatient() {
		// given - condición previa o configuración.
		patient.setEps("Eps Test U");
		patient.setClinicHistory("OK U");

		// when - acción o comportamiento a probar.
		Patient patientUpdated = patientRepository.save(patient);

		// then - verificación de la salida esperada.
		assertThat(patientUpdated).isNotNull();
		assertThat(patientUpdated.getDateOfBirth()).isEqualTo(patient.getDateOfBirth());
	}

	@Test
	@DisplayName("Test to delete a patient by id from Repository.")
	void testDeletePatientById() {
		// given - condición previa o configuración.
		patientRepository.save(patient);

		// when - acción o comportamiento a probar.
		patientRepository.delete(patient);
		Optional<Patient> patientOptional = patientRepository.findById(patient.getIdPatient());

		// then - verificación de la salida esperada.
		assertThat(patientOptional).isEmpty();
	}

}
