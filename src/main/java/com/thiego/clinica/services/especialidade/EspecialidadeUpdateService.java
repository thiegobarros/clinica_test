package com.thiego.clinica.services.especialidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thiego.clinica.dtos.EspecialidadeDto;
import com.thiego.clinica.exception.ResourceNotFoundException;
import com.thiego.clinica.models.Especialidade;
import com.thiego.clinica.repositories.EspecialidadeRepository;

@Service
public class EspecialidadeUpdateService {
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	public Especialidade updateEspecialidade(Long id, EspecialidadeDto especialidadeDto) {
		Especialidade especialidade = especialidadeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não existe com id :" + id));
		especialidade.setNome(especialidadeDto.getNome());
		especialidade.setDescricao(especialidadeDto.getDescricao());
		especialidade.setAtivo(especialidadeDto.getAtivo());
		Especialidade updated = especialidadeRepository.save(especialidade);
		return updated;
	}
}
