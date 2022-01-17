package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;
import com.juan.estevez.app.entities.Patient;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Paciente y el ID
 * String
 * 
 * @author Juan Carlos Estevez Vargas
 */
public interface IPatientRepository extends CrudRepository<Patient, String> {

}
