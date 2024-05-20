package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.enums.TipoPet;
import com.pin.vetspace.model.Cliente;
import com.pin.vetspace.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

	Optional<Pet> findById(long id);
	
	Optional<Pet> findByNome(String nome);
	
	List<Pet> findAll();
	
	Optional<Pet> findByCliente(Cliente cliente);
	
	Optional<Pet> findByTipo(TipoPet tipo);

}
