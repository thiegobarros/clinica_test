package com.thiego.clinica.services.especialidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thiego.clinica.converters.EspecialidadeConverter;
import com.thiego.clinica.dtos.EspecialidadeDto;
import com.thiego.clinica.models.Especialidade;
import com.thiego.clinica.presents.EspecialidadePresent;
import com.thiego.clinica.repositories.EspecialidadeRepository;

@Service
public class EspecialidadeStoreService {
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	@Autowired
	private EspecialidadeConverter especialidadeConverter;

	public ResponseEntity<EspecialidadePresent> createEspecialidade(EspecialidadeDto especialidadeDto) {
		ModelMapper modelMapper = new ModelMapper();
		Especialidade especialidade = modelMapper.map(especialidadeDto, Especialidade.class);
		return ResponseEntity.ok(especialidadeConverter.toModel(especialidadeRepository.save(especialidade)));
	}
}
