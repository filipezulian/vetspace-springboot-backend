package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.dto.PetDTO;
import com.pin.vetspace.model.Pet;
import com.pin.vetspace.service.PetService;
import com.pin.vetspace.service.UsuarioService;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    
    private final UsuarioService usuarioService;

    /*@PostMapping("/cadastrar")
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet) {
        Pet petSalvo = petService.salvarPet(pet);
        return new ResponseEntity<>(petSalvo, HttpStatus.CREATED);
    }*/
    
    @PostMapping("/cadastrar/{usuarioId}")
    public ResponseEntity<Pet> cadastrarPet(@PathVariable Long usuarioId, @RequestBody Pet pet) {
        Pet petSalvo = petService.salvarPetParaUsuario(pet, usuarioId);
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

    @GetMapping("/nomePet/{nome}")
    public ResponseEntity<List<Pet>> buscarPetsPorNome(@PathVariable String nome) {
        List<Pet> pets = petService.buscarPetPorNome(nome);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<Pet>> buscarPetPorUsuario(@PathVariable String nome) {
        Usuario usuario = usuarioService.buscarUsuarioPorNome(nome);
        List<Pet> pets = petService.buscarPetPorUsuario(usuario);
        return ResponseEntity.ok(pets);
    }
    
    @GetMapping("/usuario/{usuarioId}/pets")
    public ResponseEntity<List<PetDTO>> buscarPetsPorUsuarioId(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        List<Pet> pets = petService.buscarPetPorUsuario(usuario);
        List<PetDTO> petDTOs = pets.stream().map(PetDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(petDTOs);
    }
    
}
