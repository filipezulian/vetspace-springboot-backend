package com.pin.vetspace.service;

import java.util.List;

import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.UserFuncionario;

public interface UserFuncionarioService {

    UserFuncionario salvarFuncionario(UserFuncionario userFuncionario);

    //UserFuncionario buscarFuncionarioPorId(Long id);

    UserFuncionario editarFuncionario(UserFuncionario userFuncionario);

    void excluirFuncionario(Long id);

    UserFuncionario buscarFuncionarioPorNome(String nome);

    UserFuncionario buscarFuncionarioPorEmail(String email);
    
    UserFuncionario autenticar(Credencial credencial);
    
    List<UserFuncionario> buscarFuncionariosPorPlantao(Integer plantao);
    
    UserFuncionario buscarFuncionarioPorUserId(Long userId);
}
