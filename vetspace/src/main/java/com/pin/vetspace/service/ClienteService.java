package com.pin.vetspace.service;

import com.pin.vetspace.model.Cliente;

public interface ClienteService {

	Cliente salvarCliente(Cliente cliente);
	Cliente buscarCliente(Cliente cliente);
	Cliente atualizarCliente(Cliente cliente);
	Cliente excluirCliente(Cliente cliente);
	Cliente buscarClienteNome(Cliente cliente);
}
