package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Cliente salvarCliente(@RequestBody Cliente cliente){
        return clienteService.salvarCliente(cliente);
    }
}
