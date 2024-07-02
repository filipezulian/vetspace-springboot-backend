package com.pin.vetspace.dto;

import com.pin.vetspace.model.HistoricoMedico;

import lombok.Data;

@Data
public class HistoricoMedicoDTO {
    private Boolean castrado;
    private int[] vacinas;
    private String obs;


    public HistoricoMedicoDTO(HistoricoMedico historicoMedico) {
        this.castrado = historicoMedico.getCastrado();
        this.vacinas = historicoMedico.getVacinas();
        this.obs = historicoMedico.getObs();
    }
}
