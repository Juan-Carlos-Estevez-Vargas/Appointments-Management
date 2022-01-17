package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.GenericServiceApi;
import com.juan.estevez.app.entities.Patient;

/**
 * Interface para manipular los métodos CRUD del tipo Paciente. Esta interface
 * extiende de la clase generica GenericServiceApi y pasamos como parámetro el
 * tipo de dato Paciente junto con el tipo de dato de la llave primaria
 * 
 * @author Juan Carlos Estevez Vargas
 */
public interface IPatientService extends GenericServiceApi<Patient, String> {

}
