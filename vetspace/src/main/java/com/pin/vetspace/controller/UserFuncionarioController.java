package com.pin.vetspace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.pin.vetspace.exception.ErroAutenticacao;
import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.UserFuncionario;
import com.pin.vetspace.service.UserFuncionarioService;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class UserFuncionarioController {

    private final UserFuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UserFuncionario> cadastrarFuncionario(@RequestBody UserFuncionario userFuncionario) {
        try {
            UserFuncionario funcionarioSalvo = funcionarioService.salvarFuncionario(userFuncionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/editar/{user_id}")
    public ResponseEntity<UserFuncionario> editarFuncionario(@PathVariable Long user_id, @RequestBody UserFuncionario userFuncionario) {
        try {
        	userFuncionario.getUsuario().setId(user_id);
            
            UserFuncionario funcionarioAtualizado = funcionarioService.editarFuncionario(userFuncionario);
            
            return ResponseEntity.ok(funcionarioAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @DeleteMapping("/excluir/{user_id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long user_id) {
        try {
            funcionarioService.excluirFuncionario(user_id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserFuncionario> buscarFuncionarioPorUserId(@PathVariable Long user_id) {
        try {
            UserFuncionario funcionario = funcionarioService.buscarFuncionarioPorUserId(user_id);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<UserFuncionario> buscarFuncionarioPorNome(@PathVariable String nome) {
        try {
            UserFuncionario funcionario = funcionarioService.buscarFuncionarioPorNome(nome);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserFuncionario> buscarFuncionarioPorEmail(@PathVariable String email) {
        try {
            UserFuncionario funcionario = funcionarioService.buscarFuncionarioPorEmail(email);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/plantao/{plantao}")
    public ResponseEntity<List<UserFuncionario>> buscarFuncionariosPorPlantao(@PathVariable Integer plantao) {
        try {
            List<UserFuncionario> funcionarios = funcionarioService.buscarFuncionariosPorPlantao(plantao);
            if (funcionarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(funcionarios);
            }
            return ResponseEntity.ok(funcionarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity<UserFuncionario> autenticar(@RequestBody Credencial credencial) {
        try {
            UserFuncionario funcionarioAutenticado = funcionarioService.autenticar(credencial);
            return ResponseEntity.ok(funcionarioAutenticado);
        } catch (ErroAutenticacao e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
}
