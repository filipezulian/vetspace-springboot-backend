package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.model.Funcionario;
import com.pin.vetspace.model.Permissao;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Optional<Funcionario> findById(long id);
	
	Optional<Funcionario> findByNome(String nome);
	
	List<Funcionario> findAll();
	
	//List<Funcionario> findByPermissao(Integer permissaoId);
	
	 Optional<Funcionario> findByEmail(String email);
}
