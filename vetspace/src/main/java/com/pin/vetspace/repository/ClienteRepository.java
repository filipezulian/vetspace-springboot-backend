package com.pin.vetspace.repository;

import com.pin.vetspace.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findById(long id);
	
	Optional<Cliente> findByNome(String nome);
	
	List<Cliente> findAll();
	
	//List<Cliente> findByPermissao(Integer permissaoId);
	
	Optional<Cliente> findByEmail(String email);
}
