package com.pin.vetspace.service;

import com.pin.vetspace.model.RelatorioConsulta;

public interface RelatorioConsultaService {
    RelatorioConsulta cadastrarRelatorioConsulta(Long consultaId, Long funcionarioId, RelatorioConsulta relatorioConsulta);
    RelatorioConsulta buscarRelatorioPorId(Long relatorioId);
}
