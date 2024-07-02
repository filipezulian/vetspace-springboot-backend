package com.pin.vetspace.repository;

import com.pin.vetspace.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    
    List<Material> findByNome(String nome);
    
    @Query("SELECT m.valor FROM Material m WHERE m.id IN :ids")
    List<Double> findValoresByIds(@Param("ids") List<Long> ids);
    
    @Query("SELECT m.nome FROM Material m WHERE m.id IN :ids")
    List<String> findNomesByIds(@Param("ids") List<Long> ids);
}

