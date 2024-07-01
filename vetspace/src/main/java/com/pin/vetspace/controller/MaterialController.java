package com.pin.vetspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pin.vetspace.model.Material;
import com.pin.vetspace.service.MaterialService;
import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Material> cadastrarMaterial(@RequestBody Material material) {
        Material novoMaterial = materialService.cadastrarMaterial(material);
        return ResponseEntity.ok(novoMaterial);
    }

    @GetMapping
    public ResponseEntity<List<Material>> listarMateriais() {
        List<Material> materiais = materialService.listarMateriais();
        return ResponseEntity.ok(materiais);
    }

    @PutMapping("/editar/{materialId}")
    public ResponseEntity<Material> atualizarMaterial(@PathVariable Long materialId, @RequestBody Material material) {
        try {
            Material atualizado = materialService.atualizarMaterial(materialId, material);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/excluir/{materialId}")
    public ResponseEntity<Void> excluirMaterial(@PathVariable Long materialId) {
        try {
            materialService.excluirMaterial(materialId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{materialId}")
    public ResponseEntity<Material> buscarPorId(@PathVariable Long materialId) {
        try {
            Material material = materialService.buscarPorId(materialId);
            return ResponseEntity.ok(material);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Material>> buscarPorNome(@PathVariable String nome) {
        List<Material> materiais = materialService.buscarPorNome(nome);
        return ResponseEntity.ok(materiais);
    }
}
