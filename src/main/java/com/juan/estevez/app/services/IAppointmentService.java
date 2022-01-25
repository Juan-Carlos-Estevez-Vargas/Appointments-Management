package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.GenericServiceApi;
import com.juan.estevez.app.dto.AppointmentDTO;
import com.juan.estevez.app.entities.Appointment;

/**
 * Interface para manipular los métodos CRUD del tipo Appointment. Esta
 * interface extiende de la clase generica GenericServiceApi y pasamos como
 * parámetro el tipo de dato Appointment junto con el tipo de dato de la llave
 * primaria.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
public interface IAppointmentService extends GenericServiceApi<Appointment, Integer> {

	/**
	 * Se encarga de transformar e insertar el objeto de tipo AppointmentDTO a un
	 * objeto de tipo Appointment para lograr insertar el registro a la base de
	 * datos
	 * 
	 * @param appointmentDto a transformar para luego insertar
	 * @return transformacion de la cita insertada mediante un POJO
	 */
	AppointmentDTO save(AppointmentDTO appointmentDto);

	/**
	 * Se encarga de transformar y actualizar el objeto de tipo AppointmentDTO a un
	 * objeto de tipo Appointment para lograr insertar el registro a la base de
	 * datos
	 * 
	 * @param appointmentDto a transformar para luego actualizar
	 * @return transformacion de la cita actualizada mediante un POJO
	 */
	AppointmentDTO update(AppointmentDTO appointmentDto);

}