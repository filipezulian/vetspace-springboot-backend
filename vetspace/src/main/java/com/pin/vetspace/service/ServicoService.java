package com.pin.vetspace.service;

import java.util.List;

import com.pin.vetspace.model.Servico;

public interface ServicoService {
	
    Servico salvarServico(Servico servico);

    Servico buscarServicoPorId(Long id);

    Servico editarServico(Servico servico);

    void excluirServico(Long id);

    Servico buscarServicoPorNome(String nome);
    
    List<Servico> buscarTodosServicos();

}
