package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.juan.estevez.app.commons.GenericServiceImp;
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
	public PatientServiceImpl(IPatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public CrudRepository<Patient, String> getRepository() {
		return patientRepository;
	}

}
