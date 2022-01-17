package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.GenericServiceApi;
import com.juan.estevez.app.entities.Medico;

/**
 * Interface para manipular los métodos CRUD del tipo Médico Esta interface
 * extiende de la clase generica GenericServiceApi y pasamos como parámetro el
 * tipo de dato Médico junto con el tipo de dato de la llave primaria
 * 
 * @author Juan Carlos Estevez Vargas
 */
public interface IMedicoServiceApi extends GenericServiceApi<Medico, String> {

}
