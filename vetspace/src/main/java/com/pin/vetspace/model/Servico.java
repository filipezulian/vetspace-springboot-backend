package com.pin.vetspace.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "servicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Long id;
    
    private String nome;
    
    private double valor;
   
}
