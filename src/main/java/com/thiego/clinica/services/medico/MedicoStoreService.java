package com.thiego.clinica.services.medico;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thiego.clinica.converters.MedicoConverter;
import com.thiego.clinica.dtos.MedicoDto;
import com.thiego.clinica.models.Medico;
import com.thiego.clinica.presents.MedicoPresent;
import com.thiego.clinica.repositories.MedicoRepository;
import com.thiego.clinica.repositories.EspecialidadeRepository;

@Service
public class MedicoStoreService {
	@Autowired
	private MedicoRepository medicoRepository;
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	@Autowired
	private MedicoConverter medicoConverter;

	public ResponseEntity<MedicoPresent> createMedico(MedicoDto medicoDto) {
		ModelMapper modelMapper = new ModelMapper();
		Medico medico = modelMapper.map(medicoDto, Medico.class);
		medico.setEspecialidade(especialidadeRepository.getOne(medicoDto.getEspecialidadeId()));
		return ResponseEntity.ok(medicoConverter.toModel(medicoRepository.save(medico)));
	}
}
