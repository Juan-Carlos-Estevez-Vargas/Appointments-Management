package com.juan.estevez.app.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.repositories.IAppointmentRepository;
import com.juan.estevez.app.services.IAppointmentService;
import com.juan.estevez.app.services.IDoctorService;
import com.juan.estevez.app.services.IPatientService;

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
		String idPatient = entity.getPatient();
		List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();

		for (Appointment app : appointments) {
			if (app.getDoctor().equals(idDoctor)&& app.getPatient().equals(idPatient)) {
				return null;
			}
		}
		return verify(entity);
	}

	@Override
	public Appointment update(Appointment entity) {
		return verify(entity);
	}

	public Appointment verify(Appointment entity) {
		String idDoctor = entity.getDoctor();
		Doctor doctor = doctorService.get(idDoctor);
		if (entity.getHour() >= doctor.getAttentionStartTime() && entity.getHour() <= doctor.getAttentionEndTime()) {
			return super.save(entity);
		}
		return null;
	}
}
