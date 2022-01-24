package com.juan.estevez.app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.dto.PatientDTO;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.repositories.IPatientRepository;
import com.juan.estevez.app.services.IPatientService;

/**
 * Clase que hereda de la clase GenericServiceImp pasando como parámetro el tipo
 * de dato Patient y el tipo de dato de su llave primaria, la clase además
 * implementa la interface IPatientService la cual se encarga de generar los
 * métodos CRUD para un Paciente.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
@Service
public class PatientServiceImpl extends GenericServiceImp<Patient, String> implements IPatientService {

	private IPatientRepository patientRepository;
	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	public PatientServiceImpl(IPatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public CrudRepository<Patient, String> getRepository() {
		return patientRepository;
	}

	@Override
	public PatientDTO save(PatientDTO patientDto) {
		Patient pacient = modelMapper.map(patientDto, Patient.class);
		pacient = patientRepository.save(pacient);
		return modelMapper.map(pacient, PatientDTO.class);
	}

	@Override
	public PatientDTO update(PatientDTO patientDto) {
		Patient pacient = modelMapper.map(patientDto, Patient.class);
		pacient = patientRepository.save(pacient);
		return modelMapper.map(pacient, PatientDTO.class);
	}

}
