package com.pin.vetspace.dto;

import java.time.LocalDate;
import com.pin.vetspace.enums.TipoPet;
import com.pin.vetspace.model.Pet;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetDTO {

    private TipoPet tipo;
    private boolean sexo;
    private String nome;
    private LocalDate nascimento;

    public PetDTO( TipoPet tipo, boolean sexo, String nome, LocalDate nascimento) {
        this.tipo = tipo;
        this.sexo = sexo;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public PetDTO(Pet pet) {
        this.tipo = pet.getTipo();
        this.sexo = pet.isSexo();
        this.nome = pet.getNome();
        this.nascimento = pet.getNascimento();
    }
}
