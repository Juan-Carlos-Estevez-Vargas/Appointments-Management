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

import com.juan.estevez.app.entities.Citas;
import com.juan.estevez.app.services.ICitasServiceApi;

/**
 * Controlador REST de la entidad Médico
 * 
 * @author Juan Carlos Estevez Vargas
 */
@RestController
@RequestMapping("/Citas")
public class CitasRestCintroller {
	/**
	 * Inyectando la instancia de la interface IMedicoServiceApi
	 */
	@Autowired
	private ICitasServiceApi citasServiceAPI;

	/**
	 * Método para retornan la lista de médicos
	 * @return
	 */
	@GetMapping("/")
	public List<Citas> listarCitas() {
		return citasServiceAPI.list();
	}

	/**
	 * Método para actualizar o insertar un médico
	 * @param medico
	 * @return
	 */
	@PostMapping("/guardar")
	public ResponseEntity<Citas> guardar(@RequestBody Citas cita) {
		Citas obj = citasServiceAPI.save(cita);
		return new ResponseEntity<Citas>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{idCita}")
	public Citas buscarPersona(@PathVariable int idCita) {
		return citasServiceAPI.get(idCita);
	}

	/**
	 * Método para eliminar un médico
	 * @param idMedico
	 * @return
	 */
	@GetMapping("/eliminar/{idCita}")
	public ResponseEntity<Citas> eliminar(@PathVariable int idCita) {
		Citas cita = citasServiceAPI.get(idCita);
		if (cita != null) {
			citasServiceAPI.delete(idCita);
		} else {
			return new ResponseEntity<Citas>(cita, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Citas>(cita, HttpStatus.OK);
	}
}
