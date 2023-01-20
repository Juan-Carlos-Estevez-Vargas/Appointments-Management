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

import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.repositories.IPatientRepository;
import com.juan.estevez.app.services.impl.PatientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
	
	@Mock
	private IPatientRepository patientRepository;
	
	@InjectMocks
	private PatientServiceImpl patientService;
	
	private Patient patient;
	
	@BeforeEach
	void setup() {
		patient = new Patient();
		patient.setIdPatient("3456");
		patient.setName("Patient Service Test");
		patient.setIdType("CC");
		patient.setDateOfBirth("2000-09-13");
		patient.setEps("Eps test");
		patient.setClinicHistory("OK");
	}
	
	@Test
	@DisplayName("Test to save a Patient from Service")
	void testInsertPatient() {
		// given		
		given(patientRepository.save(patient)).willReturn(patient);
		
		// when
		Patient patientSaved = patientService.save(patient);
		
		// then
		assertThat(patientSaved).isNotNull();
		
	}
	
	@Test
	@DisplayName("Test to list to Patients from Service")
	void testListPatients() {
		// given
		Patient patient2 = new Patient();
		patient2.setIdPatient("34569");
		patient2.setName("Patient Service Test");
		patient2.setIdType("CC");
		patient2.setDateOfBirth("2000-09-13");
		patient2.setEps("Eps test");
		patient2.setClinicHistory("OK");
		
		given(patientRepository.findAll()).willReturn(List.of(patient, patient2));
		
		// when
		List<Patient> patients = patientService.list();
		
		//then
		assertThat(patients).isNotNull();
		assertThat(patients.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Test to get a empty list of Patients from Service")
	void testListPatientsEmpty() {
		// given		
		given(patientRepository.findAll()).willReturn(Collections.emptyList());
		
		// when
		List<Patient> patientsList = patientService.list();
		
		// then
		assertThat(patientsList).isEmpty();
		assertThat(patientsList.size()).isEqualTo(0);
	}
	
	@Test
	@DisplayName("Test to get a Patient by ID from Service")
	void testGetPatientById() {
		// given
		given(patientRepository.findById(patient.getIdPatient())).willReturn(Optional.of(patient));
		
		// when
		Patient patientSaved = patientService.get(patient.getIdPatient());
		
		// then
		assertThat(patientSaved).isNotNull();
	}
	
	@Test
	@DisplayName("Test to update a Patient from Service")
	void testToUpdatePatient() {
		// given
		given(patientRepository.save(patient)).willReturn(patient);
		patient.setEps("Eps test test");
		
		// when
		Patient patientUpdated = patientService.update(patient);
		
		// then
		assertThat(patientUpdated).isNotNull();
		assertThat(patientUpdated.getIdType()).isEqualTo("CC");
	}
	
	/*@Test
	@DisplayName("Test to delete a Patienr by ID from Service")
	void testToDeletePatient() {
		// given
		String id = patient.getIdPatient();
		willDoNothing().given(patientRepository).deleteById(id);;
		
		// when
		patientService.delete(id);
		
		// then
		verify(patientRepository,times(1)).deleteById(id);;
	}*/
}
