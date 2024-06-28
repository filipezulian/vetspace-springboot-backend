package com.pin.vetspace.controller;

import java.util.List;

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

import com.pin.vetspace.model.Blog;
import com.pin.vetspace.service.BlogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

	private final BlogService blogService;
	
	@GetMapping()
    public ResponseEntity<List<Blog>> buscarTodos(){
		return ResponseEntity.ok().body(blogService.buscarTodos());
    }
		
	@PostMapping("/publicar")
    public ResponseEntity<Blog> salvarBlog(@RequestBody Blog blog) {
         blogService.salvarBlog(blog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Blog> editarBlog(@PathVariable Long id, @RequestBody Blog blog) {
        try {
            Blog blogEditado = blogService.editarBlog(id, blog.getDescricao(), blog.getTitulo());
            return ResponseEntity.ok(blogEditado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirBlog(@PathVariable Long id) {
        try {
            blogService.excluirBlog(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
