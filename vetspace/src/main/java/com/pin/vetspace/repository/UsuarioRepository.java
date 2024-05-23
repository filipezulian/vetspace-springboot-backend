package com.pin.vetspace.repository;

import com.pin.vetspace.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findById(long id);
	
	Optional<Usuario> findByNome(String nome);
	
	List<Usuario> findAll();
	
	//List<Usuario> findByPermissao(Integer permissao);
	
	Optional<Usuario> findByEmail(String email);
}
