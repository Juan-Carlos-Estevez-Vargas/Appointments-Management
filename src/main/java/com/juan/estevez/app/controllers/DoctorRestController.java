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

import com.juan.estevez.app.dto.DoctorDTO;
import com.juan.estevez.app.entities.Doctor;
import com.juan.estevez.app.services.IDoctorService;

/**
 * Controlador REST de la entidad Doctor.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

	private IDoctorService doctorService;
	
	@Autowired
	public DoctorRestController(IDoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/**
	 * Encargado de mostrar la lista de Doctors existentes en la base de datos.
	 * 
	 * @return Resulset con los Appointments obtenidos de la base de datos.
	 */
	@GetMapping("/findAll")
	public List<Doctor> listDoctors() {
		return doctorService.list();
	}

	/**
	 * Se encarga de insertar un Doctor en la base de datos.
	 * 
	 * @param doctor a insertar en la base de datos.
	 * @return Una entidad que contiene el Doctor guardado y un status de la
	 *         respuesta HTTP.
	 */
	@PostMapping
	public ResponseEntity<Doctor> save(@RequestBody DoctorDTO doctorDto) {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor(doctorDto.getIdDoctor());
		doctor.setDoctorsName(doctorDto.getDoctorsName());
		doctor.setIdType(doctorDto.getIdType());
		doctor.setNumberProfessionalCard(doctorDto.getNumberProfessionalCard());
		doctor.setSpecialty(doctorDto.getSpecialty());
		doctor.setYearsExperience(doctorDto.getYearsExperience());
		doctor.setAttentionStartTime(doctorDto.getAttentionStartTime());
		doctor.setAttentionEndTime(doctorDto.getAttentionEndTime());
		Doctor obj = doctorService.save(doctor);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/**
	 * Se encarga de actualizar un Doctor en la base de datos.
	 * 
	 * @param doctor a actualizar en la base de datos.
	 * @return Una entidad de respuesta que contiene el Doctor actualizado y un
	 *         status de la respuesta HTTP.
	 */
	@PutMapping
	public ResponseEntity<Doctor> update(@RequestBody DoctorDTO doctorDto) {
		Doctor doctor = new Doctor();
		doctor.setIdDoctor(doctorDto.getIdDoctor());
		doctor.setDoctorsName(doctorDto.getDoctorsName());
		doctor.setIdType(doctorDto.getIdType());
		doctor.setNumberProfessionalCard(doctorDto.getNumberProfessionalCard());
		doctor.setSpecialty(doctorDto.getSpecialty());
		doctor.setYearsExperience(doctorDto.getYearsExperience());
		doctor.setAttentionStartTime(doctorDto.getAttentionStartTime());
		doctor.setAttentionEndTime(doctorDto.getAttentionEndTime());
		Doctor obj = doctorService.update(doctor);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	/**
	 * Busca un Doctor en específico parametrizado por su id.
	 * 
	 * @param idDoctor por el cual se buscará el mèdico en la base de datos.
	 * @return Un mèdico encontrado y encapsulado en una estructura de tipo Doctor.
	 */
	@GetMapping("/findById/{idDoctor}")
	public Doctor searchDoctor(@PathVariable String idDoctor) {
		return doctorService.get(idDoctor);
	}

	/**
	 * Elimina un Doctor de la base de datos.
	 * 
	 * @param idDoctor por el cual se eliminará el resgistro.
	 * @return Estructura de tipo Doctor con el mèdico eliminado.
	 */
	@DeleteMapping("{idDoctor}")
	public ResponseEntity<Doctor> delete(@PathVariable String idDoctor) {
		Doctor doctor = doctorService.get(idDoctor);
		doctorService.delete(idDoctor);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

}
