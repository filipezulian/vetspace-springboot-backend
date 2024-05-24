package com.pin.vetspace.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		
}
