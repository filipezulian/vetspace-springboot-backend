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

import com.pin.vetspace.exception.ErroAutenticacao;
import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Long> cadastrarUsuario(@RequestBody Usuario usuario) {
        Long usuarioId = usuarioService.salvarUsuario(usuario);
        return new ResponseEntity<>(usuarioId, HttpStatus.CREATED);
    }
    
    @PutMapping("/editar/{usuarioId}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
    	usuario.setId(usuarioId);
    	Usuario UsuarioAtualizado = usuarioService.editarUsuario(usuario);
        return new ResponseEntity<>(UsuarioAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{usuarioId}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long usuarioId){
        usuarioService.excluirUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long usuarioId){
        Usuario Usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        return ResponseEntity.ok(Usuario);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Usuario> buscarUsuarioPorNome(@PathVariable String nome){
        Usuario Usuario = usuarioService.buscarUsuarioPorNome(nome);
        return ResponseEntity.ok(Usuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable String email){
        Usuario Usuario = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(Usuario);
    }
    
    @PostMapping("/autenticar")
    public ResponseEntity<Usuario> autenticar(@RequestBody Credencial credencial) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticar(credencial);
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (ErroAutenticacao e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
