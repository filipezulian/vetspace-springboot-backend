package com.pin.vetspace.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public Cliente salvarCliente(Cliente cliente) {
		Cliente existeCliente = clienteRepository.findByNome(cliente.getNome());

        if(existeCliente != null){
            throw new Error("Cliente já existe");
        }

        cliente.setSenha(passwordEncoder().encode(cliente.getSenha())); 
        Cliente clienteNovo = clienteRepository.save(cliente);

        return clienteNovo;
	}

	
	@Override
	public Cliente buscarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente atualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente excluirCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscarClienteNome(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
