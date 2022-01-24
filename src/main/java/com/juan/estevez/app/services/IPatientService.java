package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.GenericServiceApi;
import com.juan.estevez.app.dto.PatientDTO;
import com.juan.estevez.app.entities.Patient;

/**
 * Interface para manipular los métodos CRUD del tipo Patient. Esta interface
 * extiende de la clase generica GenericServiceApi y pasamos como parámetro el
 * tipo de dato Patient junto con el tipo de dato de la llave primaria.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
public interface IPatientService extends GenericServiceApi<Patient, String> {
	/**
	 * Guarda un registro de tipo Patient encapsulado en un DTO PatientDTO
	 * 
	 * @param patientDto a convertir para posteriormenet guardar
	 * @return objeto de tipo PacientDTO con la información del Patient guardado
	 */
	PatientDTO save(PatientDTO patientDto);
	/**
	 * Actualiza un registro de tipo Patient encapsulado en un DTO PacientDTO
	 * 
	 * @param pacientDto a convertir para posteriormente actualizar
	 * @return objeto de tipo PacientDTO con la información del Pacient actualizado
	 */
	PatientDTO update(PatientDTO patientDto);
}