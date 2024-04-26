package com.pin.vetspace.service;

import com.pin.vetspace.model.Funcionario;

public interface FuncionarioService {

	Funcionario salvarFuncionario(Funcionario funcionario);
	Funcionario buscarFuncionario(Funcionario funcionario);
	Funcionario atualizarFuncionario(Funcionario funcionario);
	Funcionario excluirFuncionario(Funcionario funcionario);
	Funcionario buscarFuncionarioNome(Funcionario funcionario);
}
