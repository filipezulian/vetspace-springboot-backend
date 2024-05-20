package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pin.vetspace.model.Cliente;
import com.pin.vetspace.model.Pet;
import com.pin.vetspace.service.PetService;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet) {
        Pet petSalvo = petService.salvarPet(pet);
        return new ResponseEntity<>(petSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{petId}")
    public ResponseEntity<Pet> editarPet(@PathVariable Long petId, @RequestBody Pet pet){
        pet.setId(petId);
        Pet petAtualizado = petService.editarPet(pet);
        return new ResponseEntity<>(petAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{petId}")
    public ResponseEntity<Void> excluirPet(@PathVariable Long petId){
        petService.excluirPet(petId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> buscarPetPorId(@PathVariable Long petId){
        Pet pet = petService.buscarPetPorId(petId);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Pet> buscarPetPorNome(@PathVariable String nome){
        Pet pet = petService.buscarPetPorNome(nome);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/cliente/{nome}")
    public ResponseEntity<Pet> buscarPetPorCliente(@PathVariable Cliente cliente){
        Pet pet = petService.buscarPetPorCliente(cliente);
        return ResponseEntity.ok(pet);
    }
}
