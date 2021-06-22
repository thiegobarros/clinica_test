package com.thiego.clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiego.clinica.models.Especialidade;
import com.thiego.clinica.repositories.EspecialidadeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clinica/")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@GetMapping("/especialidades")
	public List<Especialidade> listEspecialidades() {
		return especialidadeRepository.findAll();
	}
	
	@PostMapping("/especialidade")
	public Especialidade createSensor(@RequestBody Especialidade especialidadeDto) {
		return especialidadeRepository.save(especialidadeDto);
	}
}
