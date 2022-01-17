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
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.services.IDoctorService;

/**
 * Controlador REST de la entidad Doctor
 * 
 * @author Juan Carlos Estevez Vargas
 */
@RestController
@RequestMapping("/Doctors")
public class DoctorRestController {

	@Autowired
	private IDoctorService doctorService;

	/**
	 * Método para retornan la lista de doctors
	 * 
	 * @return se retorna la lista de doctors
	 */
	@GetMapping("/")
	public List<Doctor> listDoctors() {
		return doctorService.list();
	}

	/**
	 * Método para insertar un doctor
	 * 
	 * @param doctor el cual se insertará a la base de datos
	 * @return se retorna una entidad de respuesta con el doctor insertado y un
	 *         status de OK
	 */
	@PostMapping("/save")
	public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
		Doctor obj = doctorService.save(doctor);
		return new ResponseEntity<Doctor>(obj, HttpStatus.OK);
	}

	/**
	 * Método encargado de buscar un doctor en específico
	 * 
	 * @param idDoctor por el cual se buscará el doctor
	 * @return se retorna el doctor encontrado
	 */
	@GetMapping("/search/{idDoctor}")
	public Doctor searchDoctor(@PathVariable String idDoctor) {
		return doctorService.get(idDoctor);
	}

	/**
	 * Método para eliminar un doctor
	 * 
	 * @param idDoctor por el cual se eliminará el registro
	 * @return se retorna el doctor eliminado
	 */
	@GetMapping("/delete/{idDoctor}")
	public ResponseEntity<Doctor> eliminar(@PathVariable String idDoctor) {
		Doctor doctor = doctorService.get(idDoctor);
		if (doctor != null) {
			doctorService.delete(idDoctor);
		} else {
			return new ResponseEntity<Doctor>(doctor, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

}
