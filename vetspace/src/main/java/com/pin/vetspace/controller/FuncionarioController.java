package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pin.vetspace.model.Funcionario;
import com.pin.vetspace.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.salvarFuncionario(funcionario);
    }
}
