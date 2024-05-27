package com.pin.vetspace.repository;

import com.pin.vetspace.model.*;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findById(long id);
	
	Optional<Usuario> findByNome(String nome);
	
	List<Usuario> findAll();
	
	//List<Usuario> findByPermissao(Integer permissao);
	
	Optional<Usuario> findByEmail(String email);
	
//	@Query("SELECT u.nome, u.email, uf.especializao, uf.plantao FROM usuarios u left join user_funcionario uf on u.user_id = uf.user_id where permissao != 3")
//    List<Usuario> findAllFuncionarios();
}
