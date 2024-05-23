package com.pin.vetspace.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.model.Pet;
import com.pin.vetspace.repository.PetRepository;
import com.pin.vetspace.service.PetService;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	PetRepository petRepository;

	@Autowired
	public PetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Pet salvarPet(Pet pet) {
		Optional<Pet> existePet =petRepository.findById(pet.getId());

		if (existePet.isPresent()) {
			throw new Error("Pet já existe");
		}

		Pet petNovo = petRepository.save(pet);

		return petNovo;
	}

	@Override
	public Pet buscarPetPorId(Long id) {
		return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
	}

	@Override
	public Pet editarPet(Pet pet) {
		Pet petExistente = buscarPetPorId(pet.getId());

		if (petExistente == null) {
			throw new RuntimeException("Pet não encontrado com o ID fornecido: " + pet.getId());
		} else {
			if (pet.getNome() != null) {
				petExistente.setNome(pet.getNome());
			}
			if (pet.getNascimento() != null) {
				petExistente.setNascimento(pet.getNascimento());
			}
			petExistente.setSexo(pet.isSexo());

			return petRepository.save(petExistente);
		}
	}

	@Override
	public void excluirPet(Long id) {
		Pet petExistente = buscarPetPorId(id);

		if (petExistente != null) {
			petRepository.delete(petExistente);
		} else {
			throw new RuntimeException("Pet não encontrado");
		}
	}

	@Override
	public Pet buscarPetPorNome(String nome) {
		return petRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
	}

	@Override
	public Pet buscarPetPorUsuario(Usuario usuario) {
		return petRepository.findByUsuario(usuario).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
	}

}
