package com.thiego.clinica.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiego.clinica.models.Medico;
import com.thiego.clinica.presents.MedicoPresent;

@Service
public class MedicoConverter {
	@Autowired
	ModelMapper modelMapper;
	
	public List<MedicoPresent> toCollection(List<Medico> medicos) {
		return medicos.stream().map(medico -> toModel(medico)).collect(Collectors.toList());
	}

	public MedicoPresent toModel(Medico medico) {
		return modelMapper.map(medico, MedicoPresent.class);
	}
}
