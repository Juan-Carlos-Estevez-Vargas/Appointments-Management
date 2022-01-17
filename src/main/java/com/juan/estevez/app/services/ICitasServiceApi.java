package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.GenericServiceApi;
import com.juan.estevez.app.entities.Citas;

/**
 * Interface para manipular los métodos CRUD del tipo Citas. Esta interface
 * extiende de la clase generica GenericServiceApi y pasamos como parámetro el
 * tipo de dato Citas junto con el tipo de dato de la llave primaria
 * 
 * @author Juan Carlos Estevez Vargas
 */
public interface ICitasServiceApi extends GenericServiceApi<Citas, Integer>{

}
