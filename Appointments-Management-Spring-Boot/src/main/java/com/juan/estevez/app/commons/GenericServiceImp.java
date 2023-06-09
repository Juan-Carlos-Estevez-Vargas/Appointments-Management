package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Clase abstracta y genérica que implementa la interfaz mas genérica para
 * modificar registros en la base de datos usando JPA.
 * 
 * 
 * @param <T>  Tipo de repositorio a manejar (Doctor, Patient, Appointment).
 * @param <L> Tipo de dato de la llave primario del Repository a manejar.
 * 
 * @author Juan Carlos Estevez Vargas.
 */

@Service
public abstract class GenericServiceImp<T, L extends Serializable> implements GenericServiceApi<T, L> {

	/**
	 * Se encarga de obtener el repositorio a manejar (Doctor, Patient, Appointment).
	 * 
	 * @return Las operaciones CRUD según el Repository obtenido(Doctor, Patient,
	 *         Appointment).
	 */
	public abstract CrudRepository<T, L> getRepository();

	/**
	 * Se encarga de insertar un registro en la base de datos según el Repository
	 * obtenido.
	 * 
	 * @return Registro insertado en la base de datos.
	 */
	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}

	/**
	 * Se encarga de actualizar un registro en la base de datos según el Repository
	 * obtenido.
	 * 
	 * @return Registro actualizado en la base de datos.
	 */
	@Override
	public T update(T entity) {
		return getRepository().save(entity);
	}

	/**
	 * Se encarga de borrar un registro en la base de datos según el Repository
	 * obtenido.
	 */
	@Override
	public void delete(L id) {
		Optional<T> obj = getRepository().findById(id);
		if (obj.isPresent()) {
			getRepository().deleteById(id);
		}
	}

	/**
	 * Se encarga de obtener un registro de la base de datos según el Repository
	 * obtenido.
	 * 
	 * @return Registro encontrado en la base de datos.
	 */
	@Override
	public T get(L id) {
		Optional<T> obj = getRepository().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	/**
	 * Lista los registros almacenados en la base de datos según el Repository
	 * obtenido.
	 * 
	 * @return Lista de registros obtenida de la base de datos.
	 */
	@Override
	public List<T> list() {
		List<T> returnList = new ArrayList<>();
		getRepository().findAll().forEach(returnList::add);
		return returnList;
	}

}
