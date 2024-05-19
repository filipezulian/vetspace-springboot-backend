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

import com.pin.vetspace.model.Cliente;
import com.pin.vetspace.service.ClienteService;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
    	Cliente clienteSalvo = clienteService.salvarCliente(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }
    
    @PutMapping("/editar/{clienteId}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente){
    	cliente.setId(clienteId);
    	Cliente clienteAtualizado = clienteService.editarCliente(cliente);
        return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{clienteId}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long clienteId){
        clienteService.excluirCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long clienteId){
        Cliente cliente = clienteService.buscarClientePorId(clienteId);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Cliente> buscarClientePorNome(@PathVariable String nome){
        Cliente cliente = clienteService.buscarClientePorNome(nome);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email){
        Cliente cliente = clienteService.buscarClientePorEmail(email);
        return ResponseEntity.ok(cliente);
    }
}
