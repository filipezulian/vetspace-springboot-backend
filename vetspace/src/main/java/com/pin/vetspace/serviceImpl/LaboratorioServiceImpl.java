package com.pin.vetspace.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pin.vetspace.model.Laboratorio;
import com.pin.vetspace.repository.LaboratorioRepository;
import com.pin.vetspace.service.LaboratorioService;

import java.util.List;

@Service
public class LaboratorioServiceImpl implements LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Override
    public Laboratorio cadastrarLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public List<Laboratorio> listarLaboratorios() {
        return laboratorioRepository.findAll();
    }

    @Override
    public Laboratorio atualizarLaboratorio(Long laboratorioId, Laboratorio laboratorio) throws Exception {
        Laboratorio existente = laboratorioRepository.findById(laboratorioId)
                .orElseThrow(() -> new Exception("Laboratório não encontrado"));

        existente.setServico(laboratorio.getServico());
        existente.setNome(laboratorio.getNome());
        existente.setTelefone(laboratorio.getTelefone());

        return laboratorioRepository.save(existente);
    }

    @Override
    public void excluirLaboratorio(Long laboratorioId) throws Exception {
        Laboratorio existente = laboratorioRepository.findById(laboratorioId)
                .orElseThrow(() -> new Exception("Laboratório não encontrado"));

        laboratorioRepository.delete(existente);
    }
}
