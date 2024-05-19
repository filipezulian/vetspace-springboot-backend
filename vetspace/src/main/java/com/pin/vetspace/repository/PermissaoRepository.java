package com.pin.vetspace.repository;

import com.pin.vetspace.model.Permissao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	Permissao findByNome(String nome);
}