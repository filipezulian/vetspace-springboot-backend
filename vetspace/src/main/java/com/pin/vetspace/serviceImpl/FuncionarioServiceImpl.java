package com.pin.vetspace.serviceImpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pin.vetspace.model.Funcionario;
import com.pin.vetspace.repository.FuncionarioRepository;
import com.pin.vetspace.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	FuncionarioRepository funcionarioRepository;

	@Override
	public Funcionario salvarFuncionario(Funcionario funcionario) {
		Funcionario existeFuncionario = funcionarioRepository.findByNome(funcionario.getNome());

		if (existeFuncionario != null) {
			throw new Error("Funcionário já existe");
		}

		funcionario.setSenha(passwordEncoder().encode(funcionario.getSenha()));
		Funcionario funcionarioNovo = funcionarioRepository.save(funcionario);

		return funcionarioNovo;
	}

	@Override
	public Funcionario buscarFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario atualizarFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario excluirFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario buscarFuncionarioNome(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return null;
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
