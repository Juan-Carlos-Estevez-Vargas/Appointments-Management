package com.juan.estevez.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.estevez.app.dto.AppointmentDTO;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.services.IAppointmentService;

/**
 * Controlador REST de la entidad Appointment.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentRestController {

	private IAppointmentService appointmentService;
	
	@Autowired
	public AppointmentRestController(IAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	/**
	 * Encargado de mostrar la lista de Appointments existentes en la base de datos.
	 * 
	 * @return Resulset con los Appointments obtenidos de la base de datos.
	 */
	@GetMapping("/findAll")
	public List<Appointment> listAppointments() {
		return appointmentService.list();
	}

	/**
	 * Se encarga de insertar una Appointment.
	 * 
	 * @param appointment a insertar en la base de datos.
	 * @return Una entidad con el entity que contiene el Appointment guardado y un
	 *         status de la respuesta HTTP.
	 */
	@PostMapping
	public ResponseEntity<Appointment> save(@RequestBody AppointmentDTO appointmentDto) {
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(appointmentDto.getIdAppointment());
		appointment.setDoctor(appointmentDto.getDoctor());
		appointment.setPatient(appointmentDto.getPatient());
		appointment.setHour(appointmentDto.getHour());
		Appointment obj = appointmentService.save(appointment);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/**
	 * Se encarga de actualizar una Appointment.
	 * 
	 * @param appointment a actualizar en la base de datos.
	 * @return Una entidad de respuesta que contiene el Appointment actualizado y un
	 *         status de la respuesta HTTP.
	 */
	@PutMapping
	public ResponseEntity<Appointment> update(@RequestBody AppointmentDTO appointmentDto) {
		Appointment appointment = new Appointment();
		appointment.setIdAppointment(appointmentDto.getIdAppointment());
		appointment.setDoctor(appointmentDto.getDoctor());
		appointment.setPatient(appointmentDto.getPatient());
		appointment.setHour(appointmentDto.getHour());
		Appointment obj = appointmentService.update(appointment);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/**
	 * Busca una Appointment en específico parametrizada por su id.
	 * 
	 * @param idAppointment por el cual se buscará la cita en la base de datos.
	 * @return Una cita encontrada y encapsulada en una estructura de tipo
	 *         Appointment.
	 */
	@GetMapping("/findById/{idAppointment}")
	public Appointment searchAppointment(@PathVariable int idAppointment) {
		return appointmentService.get(idAppointment);
	}

	/**
	 * Elimina una Cita de la base de datos.
	 * 
	 * @param idAppointment por el cual se eliminará la Cita.
	 * @return Estructura de tipo Appointment con la cita eliminada.
	 */
	@DeleteMapping("{idAppointment}")
	public ResponseEntity<Appointment> delete(@PathVariable int idAppointment) {
		Appointment appointment = appointmentService.get(idAppointment);
		appointmentService.delete(idAppointment);
		return new ResponseEntity<>(appointment, HttpStatus.OK);
	}
}
