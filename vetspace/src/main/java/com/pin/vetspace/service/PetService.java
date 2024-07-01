package com.pin.vetspace.service;

import com.pin.vetspace.model.Usuario;

import java.util.List;

import com.pin.vetspace.model.Pet;

public interface PetService {

    //Pet salvarPet(Pet pet);

    Pet buscarPetPorId(Long id);

    Pet editarPet(Pet pet);

    void excluirPet(Long id);

    List<Pet> buscarPetPorNome(String nome);

    List<Pet> buscarPetPorUsuario(Usuario usuario);
    
    Pet salvarPetParaUsuario(Pet pet, Long userId);
    
}
