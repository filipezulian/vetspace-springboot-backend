package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	Optional<Consulta> findById(long id);

	List<Consulta> findAll();
	
    List<Consulta> findByConfirmadoTrue();

    List<Consulta> findByConfirmadoFalse();

	
}
