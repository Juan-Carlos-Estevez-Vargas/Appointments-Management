package com.juan.estevez.app.controllers;

import java.util.List;
import org.modelmapper.ModelMapper;
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
	private ModelMapper modelmapper;

	@Autowired
	public DoctorRestController(IDoctorService doctorService, ModelMapper modelmapper) {
		this.doctorService = doctorService;
		this.modelmapper = modelmapper;
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
	public ResponseEntity<DoctorDTO> save(@RequestBody DoctorDTO doctorDto) {
		return new ResponseEntity<>(
				modelmapper.map(doctorService.save(modelmapper.map(doctorDto, Doctor.class)), DoctorDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Se encarga de actualizar un Doctor en la base de datos.
	 * 
	 * @param doctor a actualizar en la base de datos.
	 * @return Una entidad de respuesta que contiene el Doctor actualizado y un
	 *         status de la respuesta HTTP.
	 */
	@PutMapping
	public ResponseEntity<DoctorDTO> update(@RequestBody DoctorDTO doctorDto) {
		return new ResponseEntity<>(
				modelmapper.map(doctorService.update(modelmapper.map(doctorDto, Doctor.class)), DoctorDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Busca un Doctor en específico parametrizado por su id.
	 * 
	 * @param idDoctor por el cual se buscará el mèdico en la base de datos.
	 * @return Un mèdico encontrado y encapsulado en una estructura de tipo Doctor.
	 */
	@GetMapping("/findById/{idDoctor}")
	public DoctorDTO searchDoctor(@PathVariable String idDoctor) {
		return modelmapper.map(doctorService.get(idDoctor), DoctorDTO.class);
	}

	/**
	 * Elimina un Doctor de la base de datos.
	 * 
	 * @param idDoctor por el cual se eliminará el resgistro.
	 * @return Estructura de tipo Doctor con el mèdico eliminado.
	 */
	@DeleteMapping("{idDoctor}")
	public ResponseEntity<DoctorDTO> delete(@PathVariable String idDoctor) {
		DoctorDTO doctor = modelmapper.map(doctorService.get(idDoctor), DoctorDTO.class);
		doctorService.delete(idDoctor);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

}
