package com.pin.vetspace.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Funcionario;
import com.pin.vetspace.repository.FuncionarioRepository;
import com.pin.vetspace.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        Funcionario existeFuncionario = funcionarioRepository.findByNome(funcionario.getNome());

        if (existeFuncionario != null) {
            throw new Error("Funcionário já existe");
        }

        funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));
        Funcionario funcionarioNovo = funcionarioRepository.save(funcionario);

        return funcionarioNovo;
    }

    @Override
    public Funcionario buscarFuncionarioPorId(Long id) {
		return null;
        // Implementação
    }

    @Override
    public Funcionario atualizarFuncionario(Funcionario funcionario) {
		return funcionario;
        // Implementação
    }

    @Override
    public void excluirFuncionario(Long id) {
        // Implementação
    }

    @Override
    public Funcionario buscarFuncionarioPorNome(String nome) {
		return null;
        // Implementação
    }

    @Override
    public Funcionario buscarFuncionarioPorEmail(String email) {
		return null;
        // Implementação
    }
}
