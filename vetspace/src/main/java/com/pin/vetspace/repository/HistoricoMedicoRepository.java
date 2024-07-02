package com.pin.vetspace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.dto.HistoricoMedicoDTO;
import com.pin.vetspace.model.HistoricoMedico;
import com.pin.vetspace.model.Pet;

public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedico, Long> {
	
	List<HistoricoMedico> findByPet(Pet pet);
}
