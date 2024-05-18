package com.pin.vetspace.service;

import com.pin.vetspace.model.Funcionario;

public interface FuncionarioService {

    Funcionario salvarFuncionario(Funcionario funcionario);

    Funcionario buscarFuncionarioPorId(Long id);

    Funcionario atualizarFuncionario(Funcionario funcionario);

    void excluirFuncionario(Long id);

    Funcionario buscarFuncionarioPorNome(String nome);

    Funcionario buscarFuncionarioPorEmail(String email);
}
