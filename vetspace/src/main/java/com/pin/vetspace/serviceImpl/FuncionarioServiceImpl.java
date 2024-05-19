package com.pin.vetspace.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pin.vetspace.exception.ErroAutenticacao;

import com.pin.vetspace.model.Funcionario;
import com.pin.vetspace.repository.FuncionarioRepository;
import com.pin.vetspace.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    //private final PasswordEncoder passwordEncoder;

    @Autowired
    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) { //, PasswordEncoder passwordEncoder
        this.funcionarioRepository = funcionarioRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        Optional<Funcionario> existeFuncionario = funcionarioRepository.findByEmail(funcionario.getEmail());

        if (existeFuncionario.isPresent()) {
            throw new Error("Funcionário já existe");
        }

        //funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));
        funcionario.setPermissao(2);
        Funcionario funcionarioNovo = funcionarioRepository.save(funcionario);

        return funcionarioNovo;
    }

    @Override
    public Funcionario buscarFuncionarioPorId(Long id) {
    	return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    }

    @Override
    public Funcionario editarFuncionario(Funcionario funcionario) {
    	Funcionario funcionarioExistente = buscarFuncionarioPorId(funcionario.getId());
        
        if (funcionarioExistente == null) {
            throw new RuntimeException("Funcionário não encontrado com o ID fornecido: " + funcionario.getId());
		} else {
             if (funcionario.getNome() != null) {
                 funcionarioExistente.setNome(funcionario.getNome());
             }
             if (funcionario.getPlantao() != 0) {
                 funcionarioExistente.setPlantao(funcionario.getPlantao());
             }
             if (funcionario.getEspecializacao() != null) {
                 funcionarioExistente.setEspecializacao(funcionario.getEspecializacao());
             }
             if (funcionario.getTelefone() != null) {
                 funcionarioExistente.setTelefone(funcionario.getTelefone());
             }
             if (funcionario.getEmail() != null) {
                 funcionarioExistente.setEmail(funcionario.getEmail());
             }
             if (funcionario.getSenha() != null) {
                 funcionarioExistente.setSenha(funcionario.getSenha());
             }
             if (funcionario.getFoto() != null) {
                 funcionarioExistente.setFoto(funcionario.getFoto());
             }
        }
        
        return funcionarioRepository.save(funcionarioExistente);
    }


    @Override
    public void excluirFuncionario(Long id) {
	    Funcionario funcionarioExistente = buscarFuncionarioPorId(id);
	    
	    if (funcionarioExistente != null) {
	        funcionarioRepository.delete(funcionarioExistente);
	    } else {
	        throw new RuntimeException("Funcionario não encontrado");
	    }
	}

    @Override
    public Funcionario buscarFuncionarioPorNome(String nome) {
    	return funcionarioRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    }

    @Override
    public Funcionario buscarFuncionarioPorEmail(String email) {
    	return funcionarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    }

	@Override
	public Funcionario autenticar(String email, String senha) {
		Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
		if(!funcionario.isPresent()) {
			throw new ErroAutenticacao("Usuário Inválido");
		}
		
		if(!funcionario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida");
		}
		return funcionario.get();

	}
    
    
}
