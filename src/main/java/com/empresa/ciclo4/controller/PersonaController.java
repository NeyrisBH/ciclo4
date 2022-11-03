package com.empresa.ciclo4.controller;

import com.empresa.ciclo4.model.Persona;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.ciclo4.services.PersonaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	@Autowired
	private PersonaService servicio;

	@GetMapping
	public ResponseEntity<?> consultarPersonas() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.consultarPersonas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultarPersonaPorId(@PathVariable Long id) {
		Optional<Persona> respuesta = servicio.consultarPersonaPorId(id);
		if (!respuesta.isPresent()) {
			JSONObject mensajeError = new JSONObject();
			mensajeError.put("mensaje", "Id no encontrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
		}
		return ResponseEntity.ok(respuesta.get());
	}

	@PostMapping
	public ResponseEntity<?> insertarPersona(@RequestBody Persona persona) {
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crearPersona(persona));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPersona(@RequestBody Persona persona, @PathVariable Long id) {
		Optional<Persona> respuesta = servicio.consultarPersonaPorId(id);
		if (!respuesta.isPresent()) {
			JSONObject mensajeError = new JSONObject();
			mensajeError.put("mensaje", "La persona consultada no se encuentra");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

		} else {
			if (persona.getId().equals(id)) {
				return ResponseEntity.ok(servicio.actualizarPersona(persona));
			} else {
				JSONObject mensajeErrorId = new JSONObject();
				mensajeErrorId.put("mensaje", "El id no corresponde a la persona");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeErrorId);
			}
		}
	}

	@DeleteMapping
	public ResponseEntity<?> eliminarPersona(@RequestBody Persona persona) {
		servicio.eliminarPersona(persona);
		JSONObject mensajeEliminar = new JSONObject();
		mensajeEliminar.put("mensaje", "El usuario fue eliminado correctamente");
		return ResponseEntity.status(HttpStatus.OK).body(mensajeEliminar);
	}

	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePartial(@RequestBody JsonPatch patch, @PathVariable Long id) {
		try {
			Optional<Persona> persona = servicio.consultarPersonaPorId(id);
			if (!persona.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode patched = patch.apply(objectMapper.convertValue(persona.get(), JsonNode.class));
			Persona personaPatched = objectMapper.treeToValue(patched, Persona.class);
			return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizarPersona(personaPatched));
		} catch (JsonPatchException | JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
