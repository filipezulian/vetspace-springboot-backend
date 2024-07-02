package com.pin.vetspace.repository;

import com.pin.vetspace.dto.RelatorioDTO;
import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.model.RelatorioConsulta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RelatorioConsultaRepository extends JpaRepository<RelatorioConsulta, Long> {

	RelatorioConsulta findByConsulta(Consulta consulta);

	RelatorioConsulta findByConsultaId(Long consultaId);
}
