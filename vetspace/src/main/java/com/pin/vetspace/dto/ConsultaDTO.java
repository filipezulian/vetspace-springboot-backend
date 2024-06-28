package com.pin.vetspace.dto;

import java.time.LocalDateTime;

import com.pin.vetspace.model.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaDTO {

    private Long id;
    private Pet pet;
    private LocalDateTime  data;
    private String obs;
    private boolean confirmado;

    public ConsultaDTO(Long id, Pet pet, LocalDateTime  data, String obs, boolean confirmado) {
        this.id = id;
        this.pet = pet;
        this.data = data;
        this.obs = obs;
        this.confirmado = confirmado;
    }

    // Construtor sem o id, pois geralmente não é necessário no retorno
    public ConsultaDTO(Pet pet, LocalDateTime  data, String obs, boolean confirmado) {
        this.pet = pet;
        this.data = data;
        this.obs = obs;
        this.confirmado = confirmado;
    }
}
