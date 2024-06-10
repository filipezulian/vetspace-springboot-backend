package com.pin.vetspace.dto;

import com.pin.vetspace.model.UserFuncionario;

import lombok.Data;

@Data
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private Integer plantao;
    private String especializacao;
    private String telefone;
    private String email;

    public FuncionarioDTO(UserFuncionario userFuncionario) {
        this.id = userFuncionario.getFuncId();
        this.nome = userFuncionario.getUsuario().getNome();
        this.plantao = userFuncionario.getPlantao();
        this.especializacao = userFuncionario.getEspecializao();
        this.telefone = userFuncionario.getUsuario().getTelefone();
        this.email = userFuncionario.getUsuario().getEmail();
    }
}

