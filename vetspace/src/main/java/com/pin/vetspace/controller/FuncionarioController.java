package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.salvarFuncionario(funcionario);
        return new ResponseEntity<>(funcionarioSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{funcionarioId}")
    public ResponseEntity<Funcionario> editarFuncionario(@PathVariable Long funcionarioId, @RequestBody Funcionario funcionario){
        funcionario.setId(funcionarioId);
        Funcionario funcionarioAtualizado = funcionarioService.editarFuncionario(funcionario);
        return new ResponseEntity<>(funcionarioAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{funcionarioId}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long funcionarioId){
        funcionarioService.excluirFuncionario(funcionarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long funcionarioId){
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(funcionarioId);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorNome(@PathVariable String nome){
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorNome(nome);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorEmail(@PathVariable String email){
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorEmail(email);
        return ResponseEntity.ok(funcionario);
    }
}

