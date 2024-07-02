package com.pin.vetspace.controller;

import com.pin.vetspace.dto.HistoricoMedicoDTO;
import com.pin.vetspace.model.HistoricoMedico;
import com.pin.vetspace.service.HistoricoMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoMedicoController {

    @Autowired
    private HistoricoMedicoService historicoMedicoService;

    @PostMapping("/cadastrar/pet/{petId}")
    public ResponseEntity<HistoricoMedico> cadastrarHistoricoMedico(@PathVariable Long petId, @RequestBody HistoricoMedico historicoMedico) {


        HistoricoMedico novoHistorico = historicoMedicoService.cadastrarHistoricoMedico(petId, historicoMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoHistorico);
    }
 

    @PutMapping("/editar/{id}")
    public ResponseEntity<HistoricoMedico> editarHistoricoMedico(@PathVariable Long id, @RequestBody HistoricoMedico historicoMedico) {
        HistoricoMedico historicoAtualizado = historicoMedicoService.editarHistoricoMedico(id, historicoMedico);
        if (historicoAtualizado != null) {
            return ResponseEntity.ok(historicoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/pet/{petId}")
    public ResponseEntity<List<HistoricoMedicoDTO>> listarHistoricoMedicoPorPetId(@PathVariable Long petId) {
        List<HistoricoMedicoDTO> historicos = historicoMedicoService.listarHistoricoMedicoPorPetId(petId);
        return ResponseEntity.ok(historicos);
    }

}

