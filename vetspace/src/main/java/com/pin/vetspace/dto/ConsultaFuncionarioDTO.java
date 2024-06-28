package com.pin.vetspace.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaFuncionarioDTO {

    private Long id;
    private Long petId; // ID do pet
    private LocalDateTime data;
    private String obs;
    private int[] servicos;

    // Construtor com todos os campos
    public ConsultaFuncionarioDTO(Long id, Long petId, LocalDateTime data, String obs, int[] servicos) {
        this.id = id;
        this.petId = petId;
        this.data = data;
        this.obs = obs;
        this.servicos = servicos;
    }

}



