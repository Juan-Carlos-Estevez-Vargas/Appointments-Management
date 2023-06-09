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

import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.entities.Patient;

@SpringBootTest
public class AppointmentRepositoryTest {

	@Autowired
	private IAppointmentRepository appointmentRepository;

	private Appointment appointment;
	private Doctor doctor;
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

		doctor = new Doctor();
		doctor.setIdDoctor("1010");
		doctor.setDoctorsName("Doctor List Test");
		doctor.setIdType("CC");
		doctor.setNumberProfessionalCard("1111");
		doctor.setYearsExperience(4);
		doctor.setSpecialty("Cardiología");
		doctor.setAttentionStartTime(8.0);
		doctor.setAttentionEndTime(16.0);

		appointment = new Appointment();
		appointment.setIdAppointment(13);
		appointment.setDate("2024-11-08");
		appointment.setDoctor(doctor.getIdDoctor());
		appointment.setPatient(patient.getIdPatient());
		appointment.setHour(14);
	}

	@Test
	@DisplayName("Test to save an Appointment from Repository.")
	void testSaveAppointment() {
		// given - condición previa o configuración. Crear el paciente

		// when - acción o comportamiento a probar.
		Appointment appointmentSaved = appointmentRepository.save(appointment);

		// then - verificación de la salida esperada.
		assertThat(appointmentSaved).isNotNull();
		assertThat(appointmentSaved.getDoctor()).isEqualTo(appointment.getDoctor());
		assertThat(appointmentSaved.getPatient()).isEqualTo(appointment.getPatient());
	}

	@Test
	@DisplayName("Test to List appointments from Repository.")
	void testListAppointments() {
		// given - condición previa o configuración. Crear el paciente
		Appointment appointment2 = new Appointment();
		appointment2.setIdAppointment(132);
		appointment2.setDate("2024-11-08");
		appointment2.setDoctor(doctor.getIdDoctor());
		appointment2.setPatient(patient.getIdPatient());
		appointment2.setHour(14);

		List<Appointment> lista = new ArrayList<>();
		lista.add(appointment2);
		lista.add(appointment);
		appointmentRepository.saveAll(lista);

		// when - acción o comportamiento a probar.
		List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();

		// then - verificación de la salida esperada.
		assertThat(appointmentList).isNotNull();
		assertThat(appointmentList.size()).isEqualTo(3);
	}

	@Test
	@DisplayName("Test to get an appointment by id from Repository")
	void testGetAppointmentById() {
		// given - condición previa o configuración.
		appointmentRepository.save(appointment);

		// when - acción o comportamiento a probar.
		Optional<Appointment> appointmentDB = appointmentRepository.findById(appointment.getIdAppointment());

		// then - verificación de la salida esperada.
		assertThat(appointmentDB).isNotNull();
	}

	@Test
	@DisplayName("Test to update an appointment from Repository.")
	void testUpdateAppointment() {
		// given - condición previa o configuración.
		appointment.setDate("2021-01-14");
		appointment.setHour(12);

		// when - acción o comportamiento a probar.
		Appointment appointmentUpdated = appointmentRepository.save(appointment);

		// then - verificación de la salida esperada.
		assertThat(appointmentUpdated).isNotNull();
		assertThat(appointmentUpdated.getDate()).isEqualTo(appointment.getDate());
	}

	@Test
	@DisplayName("Test to delete an appointment by id from Repository")
	void testDeleteAppointmentById() {
		// given - condición previa o configuración.
		appointmentRepository.save(appointment);

		// when - acción o comportamiento a probar.
		appointmentRepository.deleteById(appointment.getIdAppointment());
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getIdAppointment());

		// then - verificación de la salida esperada.
		assertThat(appointmentOptional).isEmpty();
	}

}
