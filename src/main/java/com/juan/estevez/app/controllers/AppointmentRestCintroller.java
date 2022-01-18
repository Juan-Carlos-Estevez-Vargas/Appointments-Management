package com.juan.estevez.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juan.estevez.app.entities.Appointment;
import com.juan.estevez.app.services.IAppointmentService;

/**
 * Controlador REST de la entidad Appointment
 * 
 * @author Juan Carlos Estevez Vargas
 */
@RestController
@RequestMapping("/Appointment")
public class AppointmentRestCintroller {

	@Autowired
	private IAppointmentService appointmentService;

	/**
	 * Método para retornar la lista de Appointments
	 * 
	 * @return se retorna el resulset de la base de datos
	 */
	@GetMapping("/")
	public List<Appointment> listAppointments() {
		return appointmentService.list();
	}

	/**
	 * Método para insertar una Appointment
	 * 
	 * @param appointment a insertar
	 * @return se retorna una entidad de respuesta con la Cita insertada y el status
	 *         OK
	 */
	@PostMapping("/save")
	public ResponseEntity<Appointment> save(@RequestBody Appointment appointment) {
		Appointment obj = appointmentService.save(appointment);
		return new ResponseEntity<Appointment>(obj, HttpStatus.OK);
	}

	/**
	 * Método para actualizar una Appointment
	 * 
	 * @param appointment a actualizar
	 * @return se retorna una entidad de respuesta con la Cita actualizada y el
	 *         status OK
	 */
	@PostMapping("/update")
	public ResponseEntity<Appointment> update(@RequestBody Appointment appointment) {
		Appointment obj = appointmentService.update(appointment);
		return new ResponseEntity<Appointment>(obj, HttpStatus.OK);
	}
	
	/**
	 * Método encargado de buscar una Appointment en específico
	 * 
	 * @param idAppointment por el cual se buscará la cita
	 * @return se retorna la cita encontrada
	 */
	@GetMapping("/search/{idAppointment}")
	public Appointment searchAppointment(@PathVariable int idAppointment) {
		return appointmentService.get(idAppointment);
	}

	/**
	 * Método para eliminar una Cita
	 * 
	 * @param idAppointment por el cual se eliminará la Cita
	 * @return se retorna la cita eliminada
	 */
	@GetMapping("/delete/{idAppointment}")
	public ResponseEntity<Appointment> delete(@PathVariable int idAppointment) {
		Appointment appointment = appointmentService.get(idAppointment);
		appointmentService.delete(idAppointment);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
}
