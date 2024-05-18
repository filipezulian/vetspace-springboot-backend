package com.pin.vetspace.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Cliente;
import com.pin.vetspace.repository.ClienteRepository;
import com.pin.vetspace.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        Cliente existeCliente = clienteRepository.findByNome(cliente.getNome());

        if (existeCliente != null) {
            throw new Error("Cliente já existe");
        }

        cliente.setSenha(passwordEncoder.encode(cliente.getSenha())); 
        Cliente clienteNovo = clienteRepository.save(cliente);

        return clienteNovo;
    }


    @Override
    public Cliente buscarClientePorId(Long id) {
		return null;
        // Implementação
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
		return cliente;
        // Implementação
    }

    @Override
    public void excluirCliente(Long id) {
        // Implementação
    }

    @Override
    public Cliente buscarClientePorNome(String nome) {
		return null;
        // Implementação
    }

    @Override
    public Cliente buscarClientePorEmail(String email) {
		return null;
        // Implementação
    }
}
