package com.pin.vetspace.repository;

import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.model.RelatorioConsulta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatorioConsultaRepository extends JpaRepository<RelatorioConsulta, Long> {

	RelatorioConsulta findByConsulta(Consulta consulta);

	RelatorioConsulta findByConsultaId(Long consultaId);
}
