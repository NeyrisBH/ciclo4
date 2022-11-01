package com.empresa.ciclo4.util;

import com.empresa.ciclo4.model.Persona;
import com.empresa.ciclo4.repository.PersonaRepository;
import com.empresa.ciclo4.services.PersonaService;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Usuario
 */
public class PersonaServiceImpl implements PersonaService {
    
    PersonaRepository repositorio;

    @Override
    public List<Persona> consultarPersonas() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Persona> consultarPersonaPorId(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return repositorio.insert(persona);
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        return repositorio.save(persona);
    }

    @Override
    public void eliminarPersonaPorId(Long id) {
        repositorio.deleteById(id);
    }
    
}
