package com.empresa.ciclo4.services;

import com.empresa.ciclo4.model.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Usuario
 */

public interface PersonaService {
    public List<Persona> consultarPersonas();
    public Optional<Persona> consultarPersonaPorId(Long id);
    public Persona crearPersona (Persona persona);
    public Persona actualizarPersona (Persona persona);
    public void eliminarPersonaPorId (Long id);
    public void eliminarPersona (Persona persona);
}
