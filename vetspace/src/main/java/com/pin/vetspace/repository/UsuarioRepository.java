package com.pin.vetspace.repository;

import com.pin.vetspace.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findById(long id);
	
	Optional<Usuario> findByNome(String nome);
	
	List<Usuario> findAll();
	
    @Query("SELECT u FROM Usuario u WHERE u.permissao = :permissao")
    List<Usuario> findByPermissao(@Param("permissao") int permissao);
	
	Optional<Usuario> findByEmail(String email);
}
