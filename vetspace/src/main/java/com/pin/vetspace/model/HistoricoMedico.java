package com.pin.vetspace.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "historico_medico")
public class HistoricoMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "histmed_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet", nullable = false)
    private Pet pet;

    @Column(name = "castrado")
    private Boolean castrado;
    
    @Column(name = "vacinas", columnDefinition = "integer[]")
    private int[] vacinas;

    @Column(name = "obs")
    private String obs;
}
