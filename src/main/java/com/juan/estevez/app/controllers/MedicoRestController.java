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

import com.juan.estevez.app.entities.Medico;
import com.juan.estevez.app.services.IMedicoServiceApi;

/**
 * Controlador REST de la entidad Médico
 * 
 * @author Juan Carlos Estevez Vargas
 */
@RestController
@RequestMapping("/Medicos")
public class MedicoRestController {

	/**
	 * Inyectando la instancia de la interface IMedicoServiceApi
	 */
	@Autowired
	private IMedicoServiceApi medicoServiceAPI;

	/**
	 * Método para retornan la lista de médicos
	 * 
	 * @return
	 */
	@GetMapping("/")
	public List<Medico> listarMedicos() {
		return medicoServiceAPI.list();
	}

	/**
	 * Método para actualizar o insertar un médico
	 * 
	 * @param medico
	 * @return
	 */
	@PostMapping("/guardar")
	public ResponseEntity<Medico> guardar(@RequestBody Medico medico) {
		Medico obj = medicoServiceAPI.save(medico);
		return new ResponseEntity<Medico>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{idMedico}")
	public Medico buscarPersona(@PathVariable String idMedico) {
		return medicoServiceAPI.get(idMedico);
	}

	/**
	 * Método para eliminar un médico
	 * @param idMedico
	 * @return
	 */
	@GetMapping("/eliminar/{idMedico}")
	public ResponseEntity<Medico> eliminar(@PathVariable String idMedico) {
		Medico medico = medicoServiceAPI.get(idMedico);
		if (medico != null) {
			medicoServiceAPI.delete(idMedico);
		} else {
			return new ResponseEntity<Medico>(medico, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Medico>(medico, HttpStatus.OK);
	}

}
