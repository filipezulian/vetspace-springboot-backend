package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pin.vetspace.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

	Optional<Servico> findById(long id);

	Optional<Servico> findByNome(String nome);

	List<Servico> findAll();
	
	@Query("SELECT s.valor FROM Servico s WHERE s.id IN :ids")
    List<Double> findValoresByIds(@Param("ids") List<Long> ids);
	
    @Query("SELECT s.nome FROM Servico s WHERE s.id IN :ids")
    List<String> findNomesByIds(@Param("ids") List<Long> ids);
	
}
