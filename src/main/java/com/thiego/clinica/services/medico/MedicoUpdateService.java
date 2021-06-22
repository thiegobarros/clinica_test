package com.thiego.clinica.services.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiego.clinica.dtos.MedicoDto;
import com.thiego.clinica.models.Medico;
import com.thiego.clinica.repositories.MedicoRepository;
import com.thiego.clinica.repositories.EspecialidadeRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thiego.clinica.exception.ResourceNotFoundException;

@Service
public class MedicoUpdateService {
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	public Medico updateMedico(Long id, MedicoDto medicoDto) {
		Medico medico = medicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não existe com id :" + id));
		medico.setNome(medicoDto.getNome());
		medico.setData(medicoDto.getData());
		medico.setAtivo(medicoDto.getAtivo());
		medico.setEspecialidade(especialidadeRepository.findById(medicoDto.getEspecialidadeId()).get());
		Medico updated = medicoRepository.save(medico);
		return updated;
	}
}
