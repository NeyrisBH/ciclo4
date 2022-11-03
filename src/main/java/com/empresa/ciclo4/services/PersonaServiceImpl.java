package com.empresa.ciclo4.services;

import com.empresa.ciclo4.model.Persona;
import com.empresa.ciclo4.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository repositorio;

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

    @Override
    public void eliminarPersona(Persona persona) {
        repositorio.delete(persona);
    }
    
}
