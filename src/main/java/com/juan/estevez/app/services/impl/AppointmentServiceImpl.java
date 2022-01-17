package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.repositories.IAppointmentRepository;
import com.juan.estevez.app.services.IAppointmentService;

/**
 * Clase que hereda de la clase GenericServiceImp pasando como parámetro el tipo
 * de dato Appointment y el tipo de dato de su llave primaria, la clase además
 * implementa la interface IAppointmentService la cual se encarga de generar los
 * métodos CRUD para una Appointment
 * 
 * @author Juan Carlos Estevez Vargas
 */

@Service
public class AppointmentServiceImpl extends GenericServiceImp<Appointment, Integer> implements IAppointmentService {

	@Autowired
	private IAppointmentRepository appointmentRepository;

	@Override
	public CrudRepository<Appointment, Integer> getRepository() {
		return appointmentRepository;
	}

}
