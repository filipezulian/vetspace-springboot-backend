package com.pin.vetspace.service;

import com.pin.vetspace.model.Material;
import java.util.List;

public interface MaterialService {
    
    Material cadastrarMaterial(Material material);
    
    List<Material> listarMateriais();
    
    Material atualizarMaterial(Long materialId, Material material) throws Exception;
    
    void excluirMaterial(Long materialId) throws Exception;
    
    Material buscarPorId(Long materialId) throws Exception;
    
    List<Material> buscarPorNome(String nome);
}

