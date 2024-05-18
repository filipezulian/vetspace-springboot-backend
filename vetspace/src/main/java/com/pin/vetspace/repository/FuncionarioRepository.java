package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Funcionario findById(long id);
	
	Funcionario findByNome(String nome);
	
	List<Funcionario> findAll();
	
	List<Funcionario> findByPermissao(int permissao);
	
	 Optional<Funcionario> findByEmail(String email);
}
