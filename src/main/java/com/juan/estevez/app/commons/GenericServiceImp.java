package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Clase abstracta y genérica que implementa la interfaz mas genérica para
 * modificar registros en la base de datos usando JPA
 * 
 * 
 * @param <T>  = Tipo de repositorio a manejar (Medico, Paciente, Cita)
 * @param <ID> = Tipo de dato de la llave primario del Repository a manejar
 * 
 * @author Juan Carlos Estevez Vargas
 */

@Service
public abstract class GenericServiceImp<T, ID extends Serializable> implements GenericServiceApi<T, ID> {

	/**
	 * Método abstracto para obtener el repositorio a manejar (Medico, Paciente,
	 * Cita)
	 * 
	 * @return este método retorna las operaciones CRUD según el Repository (Medico,
	 *         Paciente, Cita)
	 */
	public abstract CrudRepository<T, ID> getRepository();

	/**
	 * Método genérico para insertar un registro según el repositorio a trabajar
	 * 
	 * @return retorna el registro almacenado
	 */
	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}

	/**
	 * Método genérico para borrar un registro según el repositorio a trabajar
	 */
	@Override
	public void delete(ID id) {
		getRepository().deleteById(id);
	}

	/**
	 * Método genérico para obtener un registro según el repositorio a trabajar
	 * 
	 * @return retorna el registro obtenido
	 */
	@Override
	public T get(ID id) {
		Optional<T> obj = getRepository().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	/**
	 * Método genérico para listar los registros según el repositorio a trabajar
	 * 
	 * @return retorna la lista de registros obtenida
	 */
	@Override
	public List<T> list() {
		List<T> returnList = new ArrayList<>();
		getRepository().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

}
