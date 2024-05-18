package com.pin.vetspace.model;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    @ManyToOne
    @JoinColumn(name = "permissao")
    private Permissao permissao;

    private String nome;

    private int plantao;

    private String especializacao;

    private String telefone;

    private String email;

    private String senha;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "foto", columnDefinition = "bytea")
    private byte[] foto;
}
