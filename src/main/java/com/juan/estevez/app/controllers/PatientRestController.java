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
import com.juan.estevez.app.entities.Patient;
import com.juan.estevez.app.services.IPatientService;

/**
 * Controlador REST de la entidad Paciente
 * 
 * @author Juan Carlos Estevez Vargas
 */
@RestController
@RequestMapping("/Patients")
public class PatientRestController {

	@Autowired
	private IPatientService patientService;

	/**
	 * Método para retornan la lista de pacientes
	 * 
	 * @return se retorna la lista de pacientes abstraida de la base de datos
	 */
	@GetMapping("/")
	public List<Patient> listPatients() {
		return patientService.list();
	}

	/**
	 * Método para insertar un paciente
	 * 
	 * @param paciente el cual se guardará en la base de datos
	 * @return se retorna una entidad con el entity de respuesta y un status
	 */
	@PostMapping("/save")
	public ResponseEntity<Patient> save(@RequestBody Patient patient) {
		Patient obj = patientService.save(patient);
		return new ResponseEntity<Patient>(obj, HttpStatus.OK);
	}
	
	/**
	 * Método para actualizar un paciente
	 * 
	 * @param paciente el cual se actualizará en la base de datos
	 * @return se retorna una entidad con el entity de respuesta y un status
	 */
	@PostMapping("/update")
	public ResponseEntity<Patient> update(@RequestBody Patient patient) {
		Patient obj = patientService.update(patient);
		return new ResponseEntity<Patient>(obj, HttpStatus.OK);
	}

	/**
	 * Método encargado de buscar un paciente en específico
	 * 
	 * @param idPaciente por el cual se buscará el paciente
	 * @return se retorna el paciente encontrado
	 */
	@GetMapping("/search/{idPatient}")
	public Patient searchPatient(@PathVariable String idPatient) {
		return patientService.get(idPatient);
	}

	/**
	 * Método para eliminar un paciente
	 * 
	 * @param idPaciente por el cual se eliminará el paciente
	 * @return se retorna el paciente eliminado
	 */
	@GetMapping("/delete/{idPatient}")
	public ResponseEntity<Patient> delete(@PathVariable String idPatient) {
		Patient patient = patientService.get(idPatient);
		if (patient != null) {
			patientService.delete(idPatient);
		} else {
			return new ResponseEntity<Patient>(patient, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
}
