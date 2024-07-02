package com.pin.vetspace.service;

import java.util.List;

import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.Usuario;

public interface UsuarioService {

    Long salvarUsuario(Usuario cliente);

    Usuario buscarUsuarioPorId(Long id);

    Usuario editarUsuario(Usuario cliente);

    void excluirUsuario(Long id);

    Usuario buscarUsuarioPorNome(String nome);

    Usuario buscarUsuarioPorEmail(String email);
    
    Usuario autenticar(Credencial credencial);
    
    List<Usuario> buscarTodos();
    
    List<Usuario> buscarTodosComPermissaoTres(); 

}

