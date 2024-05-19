package com.pin.vetspace.service;

import com.pin.vetspace.model.Funcionario;

public interface FuncionarioService {

    Funcionario salvarFuncionario(Funcionario funcionario);

    Funcionario buscarFuncionarioPorId(Long id);

    Funcionario editarFuncionario(Funcionario funcionario);

    void excluirFuncionario(Long id);

    Funcionario buscarFuncionarioPorNome(String nome);

    Funcionario buscarFuncionarioPorEmail(String email);
    
    Funcionario autenticar(String email, String senha);
}
