package com.pin.vetspace.repository;

import com.pin.vetspace.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findById(long id);
	
	Cliente findByNome(String nome);
	
	List<Cliente> findAll();
	
	List<Cliente> findByPermissao(Permissao permissao);
	
	Optional<Cliente> findByEmail(String email);
}
