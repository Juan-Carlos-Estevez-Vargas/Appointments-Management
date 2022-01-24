package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.repositories.IDoctorRepository;
import com.juan.estevez.app.services.IDoctorService;

/**
 * Clase que hereda de la clase GenericServiceImp pasando como parámetro el tipo
 * de dato Doctor y el tipo de dato de su llave primaria, la clase además
 * implementa la interface IDoctorService la cual se encarga de generar los
 * métodos CRUD para un Doctor.
 * 
 * @author Juan Carlos Estevez Vargas.
 */

@Service
public class DoctorServiceImpl extends GenericServiceImp<Doctor, String> implements IDoctorService {

	private IDoctorRepository doctorRepository;
	
	@Autowired
	public DoctorServiceImpl(IDoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}

	@Override
	public CrudRepository<Doctor, String> getRepository() {
		return doctorRepository;
	}

}
