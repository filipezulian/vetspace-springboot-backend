package com.pin.vetspace.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pin.vetspace.exception.ErroAutenticacao;
import com.pin.vetspace.model.Cliente;
import com.pin.vetspace.model.Permissao;
import com.pin.vetspace.repository.ClienteRepository;
import com.pin.vetspace.repository.PermissaoRepository;
import com.pin.vetspace.service.ClienteService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PermissaoRepository permissaoRepository;
	//private final PasswordEncoder passwordEncoder;

	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository) { //,PasswordEncoder passwordEncoder
		this.clienteRepository = clienteRepository;
		//this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Cliente salvarCliente(Cliente cliente) {
		Optional<Cliente> existeCliente = clienteRepository.findByEmail(cliente.getEmail());
		
		if (existeCliente.isPresent()) {
			throw new Error("Cliente já existe");
		}

		//cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
	    
		cliente.setPermissao(3);
	    Cliente clienteNovo = clienteRepository.save(cliente);


		return clienteNovo;
	}

	
	@Override
	public Cliente buscarClientePorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	@Override
	public Cliente editarCliente(Cliente cliente) {
		Cliente clienteExistente = buscarClientePorId(cliente.getId());

		if (clienteExistente == null) {
			throw new RuntimeException("Cliente não encontrado com o ID fornecido: " + cliente.getId());
		} else {
			if (cliente.getNome() != null) {
				clienteExistente.setNome(cliente.getNome());
			}
			if (cliente.getEmail() != null) {
				clienteExistente.setEmail(cliente.getEmail());
			}
			if (cliente.getTelefone() != null) {
				clienteExistente.setTelefone(cliente.getTelefone());
			}
			if (cliente.getSenha() != null) {
				clienteExistente.setSenha(cliente.getSenha());
			}

			return clienteRepository.save(clienteExistente);
		}
	}

	@Override
	public void excluirCliente(Long id) {
	    Cliente clienteExistente = buscarClientePorId(id);
	    
	    if (clienteExistente != null) {
	        clienteRepository.delete(clienteExistente);
	    } else {
	        throw new RuntimeException("Cliente não encontrado");
	    }
	}

	@Override
	public Cliente buscarClientePorNome(String nome) {
		return clienteRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	@Override
	public Cliente buscarClientePorEmail(String email) {
		return clienteRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	@Override
	public Cliente autenticar(String email, String senha) {
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);
		if (!cliente.isPresent()) {
			throw new ErroAutenticacao("Usuário Inválido");
		}

		if (!cliente.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida");
		}
		return cliente.get();
	}
}
