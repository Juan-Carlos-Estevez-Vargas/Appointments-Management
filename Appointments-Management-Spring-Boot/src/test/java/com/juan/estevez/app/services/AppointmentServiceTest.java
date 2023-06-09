package com.juan.estevez.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.repositories.IAppointmentRepository;
import com.juan.estevez.app.services.impl.AppointmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
	
	@Mock
	private IAppointmentRepository appointmentRepository;
	
	@InjectMocks
	private AppointmentServiceImpl appointmentService;
	
	private Appointment appointment;
	
	@BeforeEach
	void setup() {
		appointment = new Appointment();
		appointment.setIdAppointment(18);
		appointment.setDoctor("123");
		appointment.setPatient("876");
		appointment.setDate("2024-12-12");
		appointment.setHour(14);
	}
	
	@Test
	@DisplayName("Test to save an Appointment from Service")
	void testInsertAppointment() {
		// given		
		given(appointmentRepository.save(appointment)).willReturn(appointment);
		
		// when
		Appointment appointmentSaved = appointmentService.save(appointment);
		
		// then
		assertThat(appointmentSaved).isNotNull();
		
	}
	
	@Test
	@DisplayName("Test to list Appointments from Service")
	void testListAppointments() {
		// given
		Appointment appointment2 = new Appointment();
		appointment2.setIdAppointment(17);
		appointment2.setDoctor("123");
		appointment2.setPatient("876");
		appointment2.setDate("2024-11-12");
		appointment2.setHour(14);
		
		given(appointmentRepository.findAll()).willReturn(List.of(appointment, appointment2));
		
		// when
		List<Appointment> appointments = appointmentService.list();
		
		//then
		assertThat(appointments).isNotNull();
		assertThat(appointments.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Test to get a empty list of Appointments from Service")
	void testListAppointmentsEmpty() {
		// given		
		given(appointmentRepository.findAll()).willReturn(Collections.emptyList());
		
		// when
		List<Appointment> appointmentsList = appointmentService.list();
		
		// then
		assertThat(appointmentsList).isEmpty();
		assertThat(appointmentsList.size()).isEqualTo(0);
	}
	
	@Test
	@DisplayName("Test to get an Appointment by ID from Service")
	void testGetAppointmentById() {
		// given
		given(appointmentRepository.findById(appointment.getIdAppointment())).willReturn(Optional.of(appointment));
		
		// when
		Appointment appointmentSaved = appointmentService.get(appointment.getIdAppointment());
		
		// then
		assertThat(appointmentSaved).isNotNull();
	}
	
	@Test
	@DisplayName("Test to update an Appointment from Service")
	void testToUpdateAppointment() {
		// given
		given(appointmentRepository.save(appointment)).willReturn(appointment);
		appointment.setHour(14);
		
		// when
		Appointment appointmentUpdated = appointmentService.update(appointment);
		
		// then
		assertThat(appointmentUpdated).isNotNull();
	}
	
	@Test
	@DisplayName("Test to delete an Appointment by ID from Service")
	void testToDeleteAppointment() {
		// given
		Integer id = appointment.getIdAppointment();
		willDoNothing().given(appointmentRepository).deleteById(id);;
		
		// when
		appointmentService.delete(id);
		
		// then
		verify(appointmentRepository,times(1)).deleteById(id);;
	}
}
