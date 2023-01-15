package com.juan.estevez.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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
		patient.setIdPatient("2087");
		patient.setName("Patient Repository Test");
		patient.setIdType("CC");
		patient.setDateOfBirth("1987-02-12");
		patient.setEps("Eps Test");
		patient.setClinicHistory("OK");
	}

	@Test
	@DisplayName("Test to save a Doctor.")
	void testSaveDoctor() {
		// given - condición previa o configuración. Crear el paciente

		// when - acción o comportamiento a probar.
		Patient patientSaved = patientRepository.save(patient);

		// then - verificación de la salida esperada.
		assertThat(patientSaved).isNotNull();
		assertThat(patientSaved.getName()).isEqualTo(patient.getName());
		assertThat(patientSaved.getEps()).isEqualTo(patient.getEps());
	}

	@Test
	@DisplayName("Test to List doctors.")
	void testListDoctors() {
		// given - condición previa o configuración. Crear el paciente
		Patient patient2 = new Patient();
		patient2.setIdPatient("2087");
		patient2.setName("Patient Repository Test");
		patient2.setIdType("CC");
		patient2.setDateOfBirth("1987-02-12");
		patient2.setEps("Eps Test");
		patient2.setClinicHistory("OK");

		List<Patient> lista = new ArrayList<>();
		lista.add(patient2);
		lista.add(patient);
		patientRepository.saveAll(lista);

		// when - acción o comportamiento a probar.
		List<Patient> patientList = patientRepository.findAll();

		// then - verificación de la salida esperada.
		assertThat(patientList).isNotNull();
		assertThat(patientList.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("Test to get a doctor by id")
	void testGetDoctorById() {
		// given - condición previa o configuración.
		patientRepository.save(patient);

		// when - acción o comportamiento a probar.
		Patient patientDB = patientRepository.getById(patient.getIdPatient());

		// then - verificación de la salida esperada.
		assertThat(patientDB).isNotNull();
	}

	@Test
	@DisplayName("Test to update a doctor.")
	void testUpdateDoctor() {
		// given - condición previa o configuración.
		patient = new Patient();
		patient.setIdPatient("2088");
		patient.setName("Patient Repository Test U");
		patient.setIdType("CC");
		patient.setDateOfBirth("1987-02-12");
		patient.setEps("Eps Test");
		patient.setClinicHistory("OK");

		// when - acción o comportamiento a probar.
		Patient patientUpdated = patientRepository.save(patient);

		// then - verificación de la salida esperada.
		assertThat(patientUpdated).isNotNull();
		assertThat(patientUpdated.getDateOfBirth()).isEqualTo(patient.getDateOfBirth());
	}

	@Test
	@DisplayName("Test to delete a doctor by id")
	void testDeleteDoctorById() {
		// given - condición previa o configuración.
		patientRepository.save(patient);

		// when - acción o comportamiento a probar.
		patientRepository.deleteById(patient.getIdPatient());
		Optional<Patient> patientOptional = patientRepository.findById(patient.getIdPatient());

		// then - verificación de la salida esperada.
		assertThat(patientOptional).isEmpty();
	}

}
