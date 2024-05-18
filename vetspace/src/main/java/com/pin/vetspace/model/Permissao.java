package com.pin.vetspace.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PERMISSAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "permissao")
    private Integer permissao;
    
}
