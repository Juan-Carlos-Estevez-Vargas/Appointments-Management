package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.juan.estevez.app.entities.Medico;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Medico y el ID String
 * Esta interface extiende de la clase genérica CrudRepository
 * @author Juan Carlos Estevez Vargas
 */
public interface IMedicoDAO extends CrudRepository<Medico, String> {

}
