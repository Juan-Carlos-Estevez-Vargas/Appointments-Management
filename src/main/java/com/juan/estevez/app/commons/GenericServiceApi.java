package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.List;

/**
 * Interface general para manejar el API y el CRUD de manera genérica (Todos los
 * tipos).
 * 
 * @param <T>  Tipo de implementación (Doctor, Patient, Appointment).
 * @param <ID> Tipo de dato de la llave primaria de la tabla.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
public interface GenericServiceApi<T, ID extends Serializable> {

	/**
	 * Se encarga de guardar registros en la base de datos.
	 * 
	 * @param entity Tipo de entidad a guardar (Doctor, Patient, Appointment).
	 * @return Un tipo genérico según el Repository insertado (Doctor, Patient,
	 *         Appointment).
	 */
	T save(T entity);

	/**
	 * Se encarga actualizar registros en la base de datos.
	 * 
	 * @param entity Tipo de entidad a actualizar (Doctor, Patient, Appointment).
	 * @return Un tipo genérico segun el Repository actualizado (Doctor, Patient,
	 *         Appointment).
	 */
	T update(T entity);

	/**
	 * Se encarga de eliminar registros en la base de datos.
	 * 
	 * @param id por el cual se eliminará el registro.
	 */
	void delete(ID id);

	/**
	 * Se encarga de obtener registros en la base de datos parametrizados por un id
	 * en especifico.
	 * 
	 * @param id por el cual se buscará el registro.
	 * @return Un tipo genérico según el Repository obtenido (Doctor, Patient,
	 *         Appointment).
	 */
	T get(ID id);

	/**
	 * Se encarga de listar registros en la base de datos.
	 * 
	 * @return Lista de todos los elementos existentes en la base de datos.
	 */
	List<T> list();
}
