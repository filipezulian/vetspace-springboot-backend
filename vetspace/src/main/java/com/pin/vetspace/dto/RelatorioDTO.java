package com.pin.vetspace.dto;

import lombok.Data;

import java.util.List;

@Data
public class RelatorioDTO {

    private List<String> materiais;
    private List<String> servicos;
    private Double valorTotal;
}

