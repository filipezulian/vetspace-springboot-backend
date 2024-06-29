package com.pin.vetspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pin.vetspace.dto.ConsultaClienteDTO;
import com.pin.vetspace.dto.ConsultaDTO;
import com.pin.vetspace.dto.ConsultaFuncionarioDTO;
import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.service.ConsultaService;


import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/cliente")
    public ResponseEntity<ConsultaDTO> salvarConsultaCliente(@RequestBody ConsultaClienteDTO consultaClienteDTO) {
        Consulta consultaSalva = consultaService.salvarConsultaCliente(consultaClienteDTO);
        ConsultaDTO consultaDTO = new ConsultaDTO(
                consultaSalva.getId(),
                consultaSalva.getPet(),
                consultaSalva.getData(),
                consultaSalva.getObs(),
                consultaSalva.isConfirmado());
        return ResponseEntity.ok(consultaDTO);
    }


    @GetMapping("/confirmadas")
    public ResponseEntity<List<ConsultaDTO>> buscarConsultasConfirmadas() {
        List<ConsultaDTO> consultasConfirmadas = consultaService.buscarConsultasConfirmadas();
        return ResponseEntity.ok(consultasConfirmadas);
    }

    @GetMapping("/nao-confirmadas")
    public ResponseEntity<List<ConsultaDTO>> buscarConsultasNaoConfirmadas() {
        List<ConsultaDTO> consultasNaoConfirmadas = consultaService.buscarConsultasNaoConfirmadas();
        return ResponseEntity.ok(consultasNaoConfirmadas);
    }
    
    @PostMapping("/funcionario")
    public ResponseEntity<Void> salvarConsultaFuncionario(@RequestBody ConsultaFuncionarioDTO consultaFuncionarioDTO) {
        consultaService.salvarConsultaFuncionario(consultaFuncionarioDTO);
        return ResponseEntity.ok().build();
    }
    
   /* @PutMapping("/aprovar/{consultaId}")
    public ResponseEntity<ConsultaDTO> aprovarConsulta(@PathVariable Long consultaId) {
        ConsultaDTO consultaAprovada = consultaService.aprovarConsulta(consultaId);
        return ResponseEntity.ok(consultaAprovada);
    }*/
    
    @PutMapping("/aprovar/{consultaId}")
    public ResponseEntity<ConsultaDTO> aprovarConsulta(@PathVariable Long consultaId) {
        try {
            ConsultaDTO consultaAprovada = consultaService.aprovarConsulta(consultaId);
            return ResponseEntity.ok(consultaAprovada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    

}
