package com.thiego.clinica.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiego.clinica.converters.MedicoConverter;
import com.thiego.clinica.dtos.MedicoDto;
import com.thiego.clinica.exception.ResourceNotFoundException;
import com.thiego.clinica.models.Medico;
import com.thiego.clinica.presents.MedicoPresent;
import com.thiego.clinica.repositories.MedicoRepository;
import com.thiego.clinica.services.medico.MedicoStoreService;
import com.thiego.clinica.services.medico.MedicoUpdateService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clinica/")
public class MedicoController {
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private MedicoConverter medicoConverter;
	
	@Autowired
	private MedicoStoreService store;
	
	@Autowired
	private MedicoUpdateService update;

	@GetMapping("/medico")
	public List<MedicoPresent> listMedico() {
		return medicoConverter.toCollection(medicoRepository.findAll());
	}
	
	@PostMapping("/medico")
	public ResponseEntity<MedicoPresent> createMedico(@RequestBody MedicoDto medicoDto) {
		return store.createMedico(medicoDto);
	}
	
	@GetMapping("/medico/{id}")
	public ResponseEntity<Medico> getMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não existe com id :" + id));
		return ResponseEntity.ok(medico);
	}
	
	@PutMapping("/medico/{id}")
	public ResponseEntity<Medico> updateMedico(@PathVariable Long id, @RequestBody MedicoDto medicoDto) {
		return update.updateMedico(id, medicoDto);
	}
	
	@DeleteMapping("/medico/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteMedico(@PathVariable Long id) {
		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não existe com id :" + id));
		medicoRepository.delete(medico);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
