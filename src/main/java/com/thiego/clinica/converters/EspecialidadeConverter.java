package com.thiego.clinica.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiego.clinica.models.Especialidade;
import com.thiego.clinica.presents.EspecialidadePresent;

@Service
public class EspecialidadeConverter {
	@Autowired
	ModelMapper modelMapper;
	
	public List<EspecialidadePresent> toCollection(List<Especialidade> especialidades) {
		return especialidades.stream().map(especialidade -> toModel(especialidade)).collect(Collectors.toList());
	}

	public EspecialidadePresent toModel(Especialidade especialidade) {
		return modelMapper.map(especialidade, EspecialidadePresent.class);
	}
}
