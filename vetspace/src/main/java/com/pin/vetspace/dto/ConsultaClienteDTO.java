package com.pin.vetspace.dto;

import java.time.LocalDateTime;

import com.pin.vetspace.model.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaClienteDTO {

    private Pet pet;
    private LocalDateTime  data;
    private String obs;

    public ConsultaClienteDTO(Pet pet, LocalDateTime  data, String obs) {
        this.pet = pet;
        this.data = data;
        this.obs = obs;
    }
}
