package com.pin.vetspace.repository;

import com.pin.vetspace.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    
    List<Material> findByNome(String nome);
}

