package com.pin.vetspace.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "FUNCIONARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long id;
    
    private Integer permissao;

    private String nome;

    private int plantao;

    private String especializacao;

    private String telefone;

    private String email;

    private String senha;

    //@Lob
    //@Basic(fetch = FetchType.LAZY)
    //@Column(name = "foto", columnDefinition = "bytea")
    @Column(nullable = true)
    private byte[] foto;
}
