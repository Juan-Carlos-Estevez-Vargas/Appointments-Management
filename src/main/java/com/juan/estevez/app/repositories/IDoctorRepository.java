package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;
import com.juan.estevez.app.entities.Doctor;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Doctor y el ID
 * String
 * 
 * @author Juan Carlos Estevez Vargas
 */
public interface IDoctorRepository extends CrudRepository<Doctor, String> {

}
