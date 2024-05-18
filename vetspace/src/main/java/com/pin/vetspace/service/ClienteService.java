package com.pin.vetspace.service;

import com.pin.vetspace.model.Cliente;

public interface ClienteService {

    Cliente salvarCliente(Cliente cliente);

    Cliente buscarClientePorId(Long id);

    Cliente atualizarCliente(Cliente cliente);

    void excluirCliente(Long id);

    Cliente buscarClientePorNome(String nome);

    Cliente buscarClientePorEmail(String email);
}

