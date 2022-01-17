package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.entities.Citas;
import com.juan.estevez.app.repositories.ICitasDAO;
import com.juan.estevez.app.services.ICitasServiceApi;

/**
 * Clase que hereda de la clase GenericServiceImp pasando como parámetro el tipo de dato Citas y el
 * tipo de dato de su llave primaria, la clase además implementa la interface ICitasServiceApi la cual 
 * se encarga de generar los métodos CRUD para una Cita
 * @author Juan Carlos Estevez Vargas
 */

@Service
public class CitasServiceImp extends GenericServiceImp<Citas, Integer> implements ICitasServiceApi{
	/**
	 * Instancia de la interface ICitasDAO para setear el tipo de dato al CRUD
	 */
	@Autowired
	private ICitasDAO citasDAO;

	/**
	 * Sobre-escritura del método para obtener el tipo de implementación, en este caso Citas
	 */
	@Override
	public CrudRepository<Citas, Integer> getRepository() {
		return citasDAO;
	}

}
