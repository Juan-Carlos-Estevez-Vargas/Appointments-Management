package com.juan.estevez.app.commons;

import java.io.Serializable;
import java.util.List;

/**
 * Interface general para manejar el API y el CRUD de manera genérica (Todos los
 * tipos)
 * 
 * @param <T>  = Tipo de implementación (Medico, Paciente, Cita)
 * @param <ID> = Tipo de dato de la llave primaria de la tabla
 * 
 * @author Juan Carlos Estevez Vargas
 */
public interface GenericServiceApi<T, ID extends Serializable> {

	/**
	 * Método genérico para guardar registros en la base de datos
	 * 
	 * @param entity = Tipo de entidad a guardar (Medico, Paciente, Cita)
	 * @return retorna un tipo genérico según el Repository a trabajar (Medico,
	 *         Paciente, Cita)
	 */
	T save(T entity);

	/**
	 * Método genérico para eliminar registros en la base de datos
	 * 
	 * @param id por el cual se eliminará el registro
	 */
	void delete(ID id);

	/**
	 * Método genérico para obtener registros en la base de datos
	 * 
	 * @param id por el cual se buscará el registro
	 * @return retorna un tipo genérico según el Repository a trabajar (Medico,
	 *         Paciente, Cita)
	 */
	T get(ID id);

	/**
	 * étodo genérico para listar registros en la base de datos
	 * 
	 * @return retorna la lista de todos los elementos existentes en la base de
	 *         datos
	 */
	List<T> list();
}
