package com.empresa.ciclo4.repository;

import com.empresa.ciclo4.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface PersonaRepository extends MongoRepository<Persona, Long>{
    
}
