package com.pin.vetspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pin.vetspace.model.RelatorioConsulta;
import com.pin.vetspace.service.RelatorioConsultaService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioConsultaController {

    @Autowired
    private RelatorioConsultaService relatorioConsultaService;

    @PostMapping("/cadastrar/consulta/{consultaId}/funcionario/{funcionarioId}")
    public ResponseEntity<RelatorioConsulta> cadastrarRelatorioConsulta(
            @PathVariable Long consultaId,
            @PathVariable Long funcionarioId,
            @RequestBody RelatorioConsulta relatorioConsulta) {

        RelatorioConsulta novoRelatorioConsulta = relatorioConsultaService.cadastrarRelatorioConsulta(consultaId, funcionarioId, relatorioConsulta);
        return ResponseEntity.ok(novoRelatorioConsulta);
    }
}
