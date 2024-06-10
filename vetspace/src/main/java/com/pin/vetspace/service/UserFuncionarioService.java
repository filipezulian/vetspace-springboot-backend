package com.pin.vetspace.service;

import java.util.List;

import com.pin.vetspace.dto.FuncionarioDTO;
import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.UserFuncionario;

public interface UserFuncionarioService {

    UserFuncionario salvarFuncionario(UserFuncionario userFuncionario);

    UserFuncionario editarFuncionario(UserFuncionario userFuncionario);

    void excluirFuncionario(Long id);

    FuncionarioDTO buscarFuncionarioPorNome(String nome);

    FuncionarioDTO buscarFuncionarioPorEmail(String email);
    
    UserFuncionario autenticar(Credencial credencial);
    
    FuncionarioDTO buscarFuncionarioPorUserId(Long funcId);
    
    List<FuncionarioDTO> buscarFuncionariosPorPlantao(Integer plantao);
    
    List<FuncionarioDTO> buscarTodosFuncionarios();
}
