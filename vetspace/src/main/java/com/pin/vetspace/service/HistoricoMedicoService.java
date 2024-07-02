package com.pin.vetspace.service;

import java.util.List;

import com.pin.vetspace.dto.HistoricoMedicoDTO;
import com.pin.vetspace.model.HistoricoMedico;


public interface HistoricoMedicoService {
	HistoricoMedico cadastrarHistoricoMedico(Long petId, HistoricoMedico historicoMedico);
    HistoricoMedico editarHistoricoMedico(Long id, HistoricoMedico historicoMedico);
    List<HistoricoMedicoDTO> listarHistoricoMedicoPorPetId(Long petId);
}

