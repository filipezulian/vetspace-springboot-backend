package com.pin.vetspace.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.model.Pet;
import com.pin.vetspace.model.RelatorioConsulta;
import com.pin.vetspace.repository.ConsultaRepository;
import com.pin.vetspace.repository.PetRepository;
import com.pin.vetspace.repository.RelatorioConsultaRepository;
import com.pin.vetspace.repository.UsuarioRepository;
import com.pin.vetspace.service.PetService;

import jakarta.transaction.Transactional;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	PetRepository petRepository;

	@Autowired
	ConsultaRepository consultaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RelatorioConsultaRepository relatorioConsultaRepository;

	@Autowired
	public PetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Pet salvarPetParaUsuario(Pet pet, Long userId) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
		if (usuarioOptional.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado");
		}
		Usuario usuario = usuarioOptional.get();
		pet.setUsuario(usuario);
		return petRepository.save(pet);
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
    @Transactional
    public void excluirPet(Long id) {
        Pet petExistente = petRepository.findById(id).orElse(null);

        if (petExistente != null) {
            // Excluir todas as consultas associadas ao pet
            List<Consulta> consultas = consultaRepository.findByPet(petExistente);
            for (Consulta consulta : consultas) {
                if (consulta.isRelatorio()) {
                    RelatorioConsulta relatorio = relatorioConsultaRepository.findByConsulta(consulta);
                    relatorioConsultaRepository.delete(relatorio);
                }
                consultaRepository.delete(consulta);
            }

            // Excluir o pet
            petRepository.delete(petExistente);
        } else {
            throw new RuntimeException("Pet não encontrado");
    }
}

	public List<Pet> buscarPetPorNome(String nome) {
		return petRepository.findByNome(nome);
	}

	@Override
	public List<Pet> buscarPetPorUsuario(Usuario usuario) {
		return petRepository.findByUsuario(usuario);
	}

}
