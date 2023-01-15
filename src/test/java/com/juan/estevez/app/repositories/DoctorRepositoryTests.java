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
		doctor.setIdDoctor("1010");
		doctor.setDoctorsName("Doctor List Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(16.0);
	}

	@Test
	@DisplayName("Test to save a Doctor.")
	void testSaveDoctor() {
		// given - condición previa o configuración.
		Doctor doctor = new Doctor();
		doctor.setIdDoctor("101010");
		doctor.setDoctorsName("Doctor Insert Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(16.0);

		// when - acción o comportamiento a probar.
		Doctor doctorGuardado = doctorRepository.save(doctor);

		// then - verificación de la salida esperada.
		assertThat(doctorGuardado).isNotNull();
	}

	@Test
	@DisplayName("Test to List doctors.")
	void testListDoctors() {
		// given - condición previa o configuración.
		Doctor doctor1 = new Doctor();
		doctor1.setIdDoctor("101010");
		doctor1.setDoctorsName("Doctor List Test");
		doctor1.setIdType("CC");
		doctor1.setNumberProfessionalCard("1111");
		doctor1.setYearsExperience(4);
		doctor1.setSpecialty("Cardiología");
		doctor1.setAttentionStartTime(8.0);
		doctor1.setAttentionEndTime(16.0);

		doctorRepository.save(doctor1);
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		List<Doctor> doctorsList = doctorRepository.findAll();

		// then - verificación de la salida esperada.
		assertThat(doctorsList).isNotNull();
		assertThat(doctorsList.size()).isEqualTo(5);
	}

	@Test
	@DisplayName("Test to get a doctor by id")
	void testGetDoctorById() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		Doctor doctorDB = doctorRepository.getById(doctor.getIdDoctor());

		// then - verificación de la salida esperada.
		assertThat(doctorDB).isNotNull();
	}

	@Test
	@DisplayName("Test to update a doctor.")
	void testUpdateDoctor() {
		// given - condición previa o configuración.
		Doctor doctorToUpdate = new Doctor();
		doctorToUpdate.setIdDoctor("101010");
		doctorToUpdate.setDoctorsName("Doctor Update Test");
		doctorToUpdate.setIdType("CC");
		doctorToUpdate.setNumberProfessionalCard("1111");
		doctorToUpdate.setYearsExperience(4);
		doctorToUpdate.setSpecialty("Radiología");
		doctorToUpdate.setAttentionStartTime(8.0);
		doctorToUpdate.setAttentionEndTime(16.0);

		// when - acción o comportamiento a probar.
		Doctor doctorUpdated = doctorRepository.save(doctorToUpdate);

		// then - verificación de la salida esperada.
		assertThat(doctorUpdated).isNotNull();
		assertThat(doctorUpdated.getDoctorsName()).isEqualTo("Doctor Update Test");
		assertThat(doctorUpdated.getSpecialty()).isEqualTo("Radiología");
	}

	@Test
	@DisplayName("Test to delete a doctor by id")
	void testDeleteDoctorById() {
		// given - condición previa o configuración.
		doctorRepository.save(doctor);

		// when - acción o comportamiento a probar.
		doctorRepository.deleteById(doctor.getIdDoctor());
		Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getIdDoctor());

		// then - verificación de la salida esperada.
		assertThat(doctorOptional).isEmpty();
	}

}
