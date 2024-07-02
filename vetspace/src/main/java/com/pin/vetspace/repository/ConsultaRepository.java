package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.model.Pet;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	Optional<Consulta> findById(long id);

	List<Consulta> findAll();
	
    List<Consulta> findByConfirmadoTrue();

    List<Consulta> findByConfirmadoFalse();

	List<Consulta> findByPet(Pet pet);
	
	@Modifying
	@Query("DELETE FROM RelatorioConsulta r WHERE r.consulta.id = :consultaId")
	void deleteRelatorioByConsultaId(@Param("consultaId") Long consultaId);
}
