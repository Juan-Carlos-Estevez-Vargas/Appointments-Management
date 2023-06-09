package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;
import com.juan.estevez.app.entities.Appointment;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Appointment y el ID Integer.
 * 
 * @author Juan Carlos Estevez Vargas.
 */
public interface IAppointmentRepository extends CrudRepository<Appointment, Integer>{
}