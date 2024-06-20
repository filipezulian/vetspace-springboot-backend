package com.pin.vetspace.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulta_id")
    private Long id;
    
    @Column(name = "servicos", columnDefinition = "integer[]")
    private int[] servicos;
    
    @OneToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
    private Pet pet;
    
    private LocalDate data;
    
    private String obs;
    
    private boolean confirmado;
    
    private boolean relatorio;
}
