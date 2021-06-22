package com.thiego.clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thiego.clinica.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>  {
	@Query("FROM Medico md " + " WHERE md.especialidade.nome LIKE %:nome%")
	List<Medico> findAllWithEspecialidadeName(@Param("nome") String nome );
}
