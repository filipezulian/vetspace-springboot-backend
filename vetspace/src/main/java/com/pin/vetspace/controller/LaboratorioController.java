package com.pin.vetspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pin.vetspace.model.Laboratorio;
import com.pin.vetspace.service.LaboratorioService;
import java.util.List;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping("cadastrar")
    public ResponseEntity<Laboratorio> cadastrarLaboratorio(@RequestBody Laboratorio laboratorio) {
        Laboratorio novoLaboratorio = laboratorioService.cadastrarLaboratorio(laboratorio);
        return ResponseEntity.ok(novoLaboratorio);
    }

    @GetMapping
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioService.listarLaboratorios();
        return ResponseEntity.ok(laboratorios);
    }

    @PutMapping("/editar/{laboratorioId}")
    public ResponseEntity<Laboratorio> atualizarLaboratorio(@PathVariable Long laboratorioId, @RequestBody Laboratorio laboratorio) {
        try {
            Laboratorio atualizado = laboratorioService.atualizarLaboratorio(laboratorioId, laboratorio);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/excluir/{laboratorioId}")
    public ResponseEntity<Void> excluirLaboratorio(@PathVariable Long laboratorioId) {
        try {
            laboratorioService.excluirLaboratorio(laboratorioId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
