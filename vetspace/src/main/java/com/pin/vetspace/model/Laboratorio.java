package com.pin.vetspace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "laboratorio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laboratorio_id")
    private Long id;

    @Column(name = "servico", columnDefinition = "integer[]")
    private int[] servico;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "telefone", length = 11)
    private String telefone;
}
