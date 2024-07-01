package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pin.vetspace.enums.TipoPet;
import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

	Optional<Pet> findById(long id);
	
	List<Pet> findByNome(String nome);
	
	List<Pet> findAll();
	
	List<Pet> findByUsuario(Usuario usuario);
	
	Optional<Pet> findByTipo(TipoPet tipo);

}
