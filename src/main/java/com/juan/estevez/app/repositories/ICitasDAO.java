package com.juan.estevez.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.juan.estevez.app.entities.Citas;

/**
 * Interface para manejar el repositorio CRUD con JPA del tipo Citas y el ID Integer
 * Esta interface extiende de la clase genérica CrudRepository
 * @author Juan Carlos Estevez Vargas
 */
public interface ICitasDAO extends CrudRepository<Citas, Integer>{

}
