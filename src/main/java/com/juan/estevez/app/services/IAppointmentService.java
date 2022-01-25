package com.juan.estevez.app.services;

import com.juan.estevez.app.commons.GenericServiceApi;
import com.juan.estevez.app.dto.AppointmentDTO;
import com.juan.estevez.app.entities.Appointment;

/**
 * Interface para manipular los métodos CRUD del tipo Appointment. Esta interface
 * extiende de la clase generica GenericServiceApi y pasamos como parámetro el
 * tipo de dato Appointment junto con el tipo de dato de la llave primaria.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
public interface IAppointmentService extends GenericServiceApi<Appointment, Integer>{

	AppointmentDTO save(AppointmentDTO appointmentDto);

	AppointmentDTO update(AppointmentDTO appointmentDto);

}