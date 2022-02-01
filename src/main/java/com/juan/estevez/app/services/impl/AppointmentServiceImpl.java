package com.juan.estevez.app.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
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
 * métodos CRUD para una Appointment.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
@Service
public class AppointmentServiceImpl extends GenericServiceImp<Appointment, Integer> implements IAppointmentService {

	private IAppointmentRepository appointmentRepository;
	private IDoctorService doctorService;

	@Autowired
	public AppointmentServiceImpl(IAppointmentRepository appointmentRepository, IDoctorService doctorService) {
		this.appointmentRepository = appointmentRepository;
		this.doctorService = doctorService;
	}

	@Override
	public CrudRepository<Appointment, Integer> getRepository() {
		return appointmentRepository;
	}

	/**
	 * Se encarga de comprobar si la cita a insertar está dentro del rango de
	 * atención del médico.
	 * 
	 * @param entity a evaluar
	 * @return null en caso de que la cita a insertar este fuera del rango de
	 *         atención del médico, en caso contrario, se inserta el registro en la
	 *         base de datos.
	 */
	public Appointment verify(Appointment entity, String idDoctor) {
		Doctor doctor = doctorService.get(idDoctor);
		if (entity.getHour() >= doctor.getAttentionStartTime() && entity.getHour() <= doctor.getAttentionEndTime()) {
			return super.save(entity);
		}
		//BiPredicate<Appointment, Doctor> hourValidation = (appointment, doc) -> appointment.getHour() >= doc.getAttentionStartTime()
		//		&& appointment.getHour() <= doc.getAttentionStartTime();
		//if (hourValidation.test(entity, doctor)) {
			//return super.save(entity);
		//}
		return null;
	}

	/**
	 * Evalùa si ya existe una cita con el mismo doctor y paciente dentro de la base
	 * de datos.
	 * 
	 * @param entity a evaluar.
	 * @return null en caso de que ya exista la cita o llamado al métod verify() en
	 *         caso de que no exista la cita.
	 */
	@Override
	public Appointment save(Appointment appointment) {
		String idDoctor = appointment.getDoctor();
		String idPatient = appointment.getPatient();
		String date = appointment.getDate();
		List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();

		Optional<Appointment> appointmentOptional = appointments.stream().filter(app -> (app.getDate().equals(date)
				&& app.getDoctor().equals(idDoctor) && app.getPatient().equals(idPatient))).findAny();

		if (!appointmentOptional.isEmpty())
			return null;
		return verify(appointment, idDoctor);
	}

	@Override
	public Appointment update(Appointment appointment) {
		return save(appointment);
	}

}