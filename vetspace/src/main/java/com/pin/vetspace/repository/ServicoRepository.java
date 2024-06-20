package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pin.vetspace.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

	Optional<Servico> findById(long id);

	Optional<Servico> findByNome(String nome);

	List<Servico> findAll();
	
	
}
