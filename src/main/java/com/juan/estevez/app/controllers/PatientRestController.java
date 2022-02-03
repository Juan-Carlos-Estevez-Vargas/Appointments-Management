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
import com.juan.estevez.app.dto.PatientDTO;
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

/**
 * Controlador REST de la entidad Patient.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
@RestController
@RequestMapping("/patient")
public class PatientRestController {

	private IPatientService patientService;
	private ModelMapper modelMapper;

	@Autowired
	public PatientRestController(IPatientService patientService, ModelMapper modelMapper) {
		this.patientService = patientService;
		this.modelMapper = modelMapper;
	}

	/**
	 * Encargado de mostrar la lista de pacientes existentes en la base de datos.
	 * 
	 * @return Resulset con los pacientes obtenidos de la base de datos.
	 */
	@GetMapping("/")
	public List<Patient> listPatients() {
		return patientService.list();
	}

	/**
	 * Se encarga de insertar un Patient en la base de datos.
	 * 
	 * @param patient a insertar en la base de datos.
	 * @return Una entidad que contiene el paciente guardado y un status de la
	 *         respuesta HTTP.
	 */
	@PostMapping
	public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDto) {
		//patientService.save(modelMapper.map(patientDto, Patient.class)modelMapper.map()
		return new ResponseEntity<>(
				patientDto,
				HttpStatus.OK);
	}

	/**
	 * Se encarga de actualizar un paciente en la base de datos.
	 * 
	 * @param paciente a actualizar en la base de datos.
	 * @return Una entidad de respuesta que contiene el paciente actualizado y un
	 *         status de la respuesta HTTP.
	 */
	@PutMapping
	public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO patientDto) {
		return new ResponseEntity<>(
				modelMapper.map(patientService.update(modelMapper.map(patientDto, Patient.class)), PatientDTO.class),
				HttpStatus.OK);
	}

	/**
	 * Busca un paciente en específico parametrizado por su id.
	 * 
	 * @param idPatient por el cual se buscará el paciente en la base de datos.
	 * @return Un paciente encontrado y encapsulado en una estructura de tipo
	 *         Patient.
	 */
	@GetMapping("/findById/{idPatient}")
	public PatientDTO searchPatient(@PathVariable String idPatient) {
		return modelMapper.map(patientService.get(idPatient), PatientDTO.class);
	}

	/**
	 * Elimina un paciente de la base de datos.
	 * 
	 * @param idPatient por el cual se eliminará el registro.
	 * @return Estructura de tipo Patient con el paciente eliminado.
	 */
	@DeleteMapping("{idPatient}")
	public ResponseEntity<PatientDTO> delete(@PathVariable String idPatient) {
		PatientDTO patient = modelMapper.map(patientService.get(idPatient), PatientDTO.class);
		patientService.delete(idPatient);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
}
