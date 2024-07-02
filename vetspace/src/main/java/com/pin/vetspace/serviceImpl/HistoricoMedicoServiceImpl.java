package com.pin.vetspace.serviceImpl;

import com.pin.vetspace.dto.HistoricoMedicoDTO;
import com.pin.vetspace.model.HistoricoMedico;
import com.pin.vetspace.model.Pet;
import com.pin.vetspace.repository.HistoricoMedicoRepository;
import com.pin.vetspace.service.HistoricoMedicoService;
import com.pin.vetspace.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoMedicoServiceImpl implements HistoricoMedicoService {

    @Autowired
    private HistoricoMedicoRepository historicoMedicoRepository;

    @Autowired
    private PetService petService;

    /*@Override
    public HistoricoMedico cadastrarHistoricoMedico(Long petId, HistoricoMedico historicoMedico) {
        Pet pet = petService.buscarPetPorId(petId);
        historicoMedico.setPet(pet);
        return historicoMedicoRepository.save(historicoMedico);
    }*/
    
    @Override
    public HistoricoMedico cadastrarHistoricoMedico(Long petId, HistoricoMedico historicoMedico) {
        Pet pet = petService.buscarPetPorId(petId);
        if (historicoMedicoRepository.findByPet(pet).isEmpty()) {
            historicoMedico.setPet(pet);
            return historicoMedicoRepository.save(historicoMedico);
        } else {
            throw new IllegalArgumentException("Este pet já possui um histórico médico cadastrado.");
        }
    }

    @Override
    public HistoricoMedico editarHistoricoMedico(Long id, HistoricoMedico historicoMedico) {
        HistoricoMedico historicoExistente = historicoMedicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Histórico médico não encontrado para o ID: " + id));

        historicoExistente.setCastrado(historicoMedico.getCastrado());
        historicoExistente.setVacinas(historicoMedico.getVacinas());
        historicoExistente.setObs(historicoMedico.getObs());

        return historicoMedicoRepository.save(historicoExistente);
    }

    @Override
    public List<HistoricoMedicoDTO> listarHistoricoMedicoPorPetId(Long petId) {
        Pet pet = petService.buscarPetPorId(petId);
        List<HistoricoMedico> historicos = historicoMedicoRepository.findByPet(pet);
        return historicos.stream()
                .map(HistoricoMedicoDTO::new)
                .collect(Collectors.toList());
    }

}
