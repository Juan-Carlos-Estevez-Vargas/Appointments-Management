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

import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.repositories.IDoctorRepository;
import com.juan.estevez.app.services.impl.DoctorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
	
	@Mock
	private IDoctorRepository doctorRepository;
	
	@InjectMocks
	private DoctorServiceImpl doctorService;
	
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
	@DisplayName("Test to save a Doctor from Service")
	void testInsertDoctor() {
		// given		
		given(doctorRepository.save(doctor)).willReturn(doctor);
		
		// when
		Doctor doctorSaved = doctorService.save(doctor);
		
		// then
		assertThat(doctorSaved).isNotNull();
		assertThat(doctorSaved.getDoctorsName()).isEqualTo(doctor.getDoctorsName());
		
	}
	
	@Test
	@DisplayName("Test to list Doctors from Service")
	void testListDoctors() {
		// given
		Doctor doctor1 = new Doctor();
		doctor1.setIdDoctor("11");
		doctor1.setDoctorsName("Doctor List Test Service");
		doctor1.setIdType("CC");
		doctor1.setNumberProfessionalCard("1111");
		doctor1.setYearsExperience(4);
		doctor1.setSpecialty("Cardiología");
		doctor1.setAttentionStartTime(8.0);
		doctor1.setAttentionEndTime(16.0);
		
		given(doctorRepository.findAll()).willReturn(List.of(doctor, doctor1));
		
		// when
		List<Doctor> doctors = doctorService.list();
		
		//then
		assertThat(doctors).isNotNull();
		assertThat(doctors.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Test to get a empty list of Doctors from Service")
	void testListDoctorsEmpty() {
		// given		
		given(doctorRepository.findAll()).willReturn(Collections.emptyList());
		
		// when
		List<Doctor> doctorsList = doctorService.list();
		
		// then
		assertThat(doctorsList).isEmpty();
		assertThat(doctorsList.size()).isEqualTo(0);
	}
	
	@Test
	@DisplayName("Test to get a Doctor by ID from Service")
	void testGetDoctorById() {
		// given
		given(doctorRepository.findById(doctor.getIdDoctor())).willReturn(Optional.of(doctor));
		
		// when
		Doctor doctorSaved = doctorService.get(doctor.getIdDoctor());
		
		// then
		assertThat(doctorSaved).isNotNull();
	}
	
	@Test
	@DisplayName("Test to update a Doctor from Service")
	void testToUpdateDoctor() {
		// given
		given(doctorRepository.save(doctor)).willReturn(doctor);
		doctor.setSpecialty("Specialty from unit test");
		
		// when
		Doctor doctorUpdated = doctorService.update(doctor);
		
		// then
		assertThat(doctorUpdated).isNotNull();
		assertThat(doctorUpdated.getSpecialty()).isEqualTo("Specialty from unit test");
	}
	
	/*@Test
	@DisplayName("Test to delete a Doctor by ID from Service")
	void testToDeleteDoctor() {
		// given
		String id = doctor.getIdDoctor();
		willDoNothing().given(doctorRepository).deleteById(id);;
		
		// when
		doctorService.delete(id);
		
		// then
		verify(doctorRepository,times(1)).deleteById(id);;
	}*/
}
