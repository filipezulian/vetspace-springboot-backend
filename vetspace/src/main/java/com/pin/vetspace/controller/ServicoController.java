package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pin.vetspace.model.Servico;
import com.pin.vetspace.service.ServicoService;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;
    

    @PostMapping("/cadastrar")
    public ResponseEntity<Servico> cadastrarServico(@RequestBody Servico servico) {
        Servico servicoSalvo = servicoService.salvarServico(servico);
        return new ResponseEntity<>(servicoSalvo, HttpStatus.CREATED);
    }
    
    @PutMapping("/editar/{servicoId}")
    public ResponseEntity<Servico> editarServico(@PathVariable Long servicoId, @RequestBody Servico servico){
        servico.setId(servicoId);
        Servico servicoAtualizado = servicoService.editarServico(servico);
        return new ResponseEntity<>(servicoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{servicoId}")
    public ResponseEntity<Void> excluirServico(@PathVariable Long servicoId){
        servicoService.excluirServico(servicoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{servicoId}")
    public ResponseEntity<Servico> buscarServicoPorId(@PathVariable Long servicoId){
        Servico servico = servicoService.buscarServicoPorId(servicoId);
        return ResponseEntity.ok(servico);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Servico> buscarServicoPorNome(@PathVariable String nome) {
        Servico servico = servicoService.buscarServicoPorNome(nome);
        return ResponseEntity.ok(servico);
    }
    
	@GetMapping()
    public ResponseEntity<List<Servico>> buscarTodosServicos(){
		return ResponseEntity.ok().body(servicoService.buscarTodosServicos());
    }

}
