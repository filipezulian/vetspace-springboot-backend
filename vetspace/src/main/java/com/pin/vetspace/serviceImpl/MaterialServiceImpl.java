package com.pin.vetspace.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pin.vetspace.model.Material;
import com.pin.vetspace.repository.MaterialRepository;
import com.pin.vetspace.service.MaterialService;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Material cadastrarMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public List<Material> listarMateriais() {
        return materialRepository.findAll();
    }

    @Override
    public Material atualizarMaterial(Long materialId, Material material) throws Exception {
        Material existente = materialRepository.findById(materialId)
                .orElseThrow(() -> new Exception("Material não encontrado"));

        existente.setNome(material.getNome());
        existente.setValor(material.getValor());

        return materialRepository.save(existente);
    }

    @Override
    public void excluirMaterial(Long materialId) throws Exception {
        Material existente = materialRepository.findById(materialId)
                .orElseThrow(() -> new Exception("Material não encontrado"));

        materialRepository.delete(existente);
    }

    @Override
    public Material buscarPorId(Long materialId) throws Exception {
        return materialRepository.findById(materialId)
                .orElseThrow(() -> new Exception("Material não encontrado"));
    }

    @Override
    public List<Material> buscarPorNome(String nome) {
        return materialRepository.findByNome(nome);
    }
}
