package com.pin.vetspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	Permissao findByNome(String nome);
}
