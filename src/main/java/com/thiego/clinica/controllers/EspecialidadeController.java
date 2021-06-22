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

import com.thiego.clinica.models.Especialidade;
import com.thiego.clinica.repositories.EspecialidadeRepository;
import com.thiego.clinica.presents.EspecialidadePresent;
import com.thiego.clinica.converters.EspecialidadeConverter;
import com.thiego.clinica.dtos.EspecialidadeDto;
import com.thiego.clinica.services.especialidade.EspecialidadeStoreService;
import com.thiego.clinica.services.especialidade.EspecialidadeUpdateService;
import com.thiego.clinica.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clinica/")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	private EspecialidadeConverter especialidadeConverter;
	
	@Autowired
	private EspecialidadeStoreService store;
	
	@Autowired
	private EspecialidadeUpdateService update;

	@GetMapping("/especialidade")
	public List<EspecialidadePresent> listEspecialidade() {
		return especialidadeConverter.toCollection(especialidadeRepository.findAll());
	}
	
	@PostMapping("/especialidade")
	public ResponseEntity<EspecialidadePresent> createEspecialidade(@RequestBody EspecialidadeDto especialidadeDto) {
		return store.createEspecialidade(especialidadeDto);
	}
	
	@GetMapping("/especialidade/{id}")
	public ResponseEntity<EspecialidadePresent> getEspecialidade(@PathVariable Long id) {
		Especialidade especialidade = especialidadeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não existe com id :" + id));
		return ResponseEntity.ok(especialidadeConverter.toModel(especialidade));
	}
	
	@PutMapping("/especialidade/{id}")
	public ResponseEntity<EspecialidadePresent> updateEspecialidade(@PathVariable Long id, @RequestBody EspecialidadeDto especialidadeDto) {
		return ResponseEntity.ok(especialidadeConverter.toModel(update.updateEspecialidade(id, especialidadeDto)));
	}
	
	@DeleteMapping("/especialidade/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEspecialidade(@PathVariable Long id) {
		Especialidade especialidade = especialidadeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não existe com id :" + id));
		especialidadeRepository.delete(especialidade);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
