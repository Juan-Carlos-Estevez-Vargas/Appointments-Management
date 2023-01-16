package com.juan.estevez.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.juan.estevez.app.entities.Doctor;

@SpringBootTest
public class DoctorRepositoryTests {

	@Autowired
	private IDoctorRepository doctorRepository;

	private Doctor doctor;

	@BeforeEach
	void setup() {
		doctor = new Doctor();
		doctor.setIdDoctor("101010");
		doctor.setDoctorsName("Doctor List Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(16.0);
	}

	@Test
	@DisplayName("Test to save a Doctor from Repository.")
	void testSaveDoctor() {
		// given - condición previa o configuración.

		// when - acción o comportamiento a probar.
		Doctor doctorGuardado = doctorRepository.save(doctor);

		// then - verificación de la salida esperada.
		assertThat(doctorGuardado).isNotNull();
	}

	@Test
	@DisplayName("Test to List doctors from Repository.")
	void testListDoctors() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);
		doctorRepository.save(new Doctor("101010101", "Doctor List Test", "CC", "1111", 4, "Cardiología", 8.0, 16.0));

		// when - acción o comportamiento a probar.
		List<Doctor> doctorsList = doctorRepository.findAll();

		// then - verificación de la salida esperada.
		assertThat(doctorsList).isNotNull();
		assertThat(doctorsList.size()).isEqualTo(3);
	}

	@Test
	@DisplayName("Test to get a doctor by id drom Repository.")
	void testGetDoctorById() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		Doctor doctorDB = doctorRepository.getById(doctor.getIdDoctor());

		// then - verificación de la salida esperada.
		assertThat(doctorDB).isNotNull();
	}

	@Test
	@DisplayName("Test to update a doctor from Repository.")
	void testUpdateDoctor() {
		// given - condición previa o configuración.
		doctor.setSpecialty("Neurología");
		doctor.setAttentionStartTime(8.0);

		// when - acción o comportamiento a probar.
		Doctor doctorUpdated = doctorRepository.save(doctor);

		// then - verificación de la salida esperada.
		assertThat(doctorUpdated).isNotNull();
		assertThat(doctorUpdated.getSpecialty()).isEqualTo("Neurología");
	}

	@Test
	@DisplayName("Test to delete a doctor by id from Repository.")
	void testDeleteDoctorById() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		doctorRepository.delete(doctor);
		Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getIdDoctor());

		// then - verificación de la salida esperada.
		assertThat(doctorOptional).isEmpty();
	}

}
