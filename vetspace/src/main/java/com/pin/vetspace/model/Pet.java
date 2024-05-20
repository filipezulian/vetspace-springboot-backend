package com.pin.vetspace.model;

import java.time.LocalDate;


import com.pin.vetspace.enums.TipoPet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import com.pin.vetspace.model.Cliente;

@Data
@Entity
@Table(name = "PET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;
    
    private TipoPet tipo;
    
    private boolean sexo;
    
    @ManyToOne
    private Cliente cliente;
    
    private String nome;
    
    private LocalDate nascimento;

}