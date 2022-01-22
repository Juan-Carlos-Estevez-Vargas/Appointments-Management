package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.repositories.IAppointmentRepository;
import com.juan.estevez.app.services.IAppointmentService;
import com.juan.estevez.app.services.IDoctorService;

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
	
	@Autowired
	private IDoctorService doctorService;

	@Override
	public CrudRepository<Appointment, Integer> getRepository() {
		return appointmentRepository;
	}

	@Override
	public Appointment save(Appointment entity) {
		String idDoctor = entity.getDoctor();
		Doctor doctor = doctorService.get(idDoctor);
		
			if (entity.getHour() >= doctor.getAttentionStartTime()
					&& entity.getHour() <= doctor.getAttentionEndTime()) {
				return super.save(entity);
			}
		
		return null;
	}
}
