package com.juan.estevez.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.juan.estevez.app.commons.GenericServiceImp;
import com.juan.estevez.app.entities.Medico;
import com.juan.estevez.app.repositories.IMedicoDAO;
import com.juan.estevez.app.services.IMedicoServiceApi;

/**
 * Clase que hereda de la clase GenericServiceImp pasando como parámetro el tipo de dato Medico y el
 * tipo de dato de su llave primaria, la clase además implementa la interface IMedicoServiceApi la cual 
 * se encarga de generar los métodos CRUD para un Medico
 * 
 * @author Juan Carlos Estevez Vargas
 */

@Service
public class MedicoServiceImp extends GenericServiceImp<Medico, String> implements IMedicoServiceApi {
	
	/**
	 * Instancia de la interface IMedicoDAO para setear el tipo de dato al CRUD
	 */
	@Autowired
	private IMedicoDAO medicoDAO;

	/**
	 * Sobre-escritura del método para obtener el tipo de implementación, en este caso Medico
	 */
	@Override
	public CrudRepository<Medico, String> getRepository() {
		return medicoDAO;
	}

}
