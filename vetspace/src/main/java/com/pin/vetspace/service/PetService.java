package com.pin.vetspace.service;

import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.model.Pet;

public interface PetService {

    Pet salvarPet(Pet pet);

    Pet buscarPetPorId(Long id);

    Pet editarPet(Pet pet);

    void excluirPet(Long id);

    Pet buscarPetPorNome(String nome);

    Pet buscarPetPorUsuario(Usuario usuario);
    
}
