package com.pin.vetspace.service;

import java.util.List;

import com.pin.vetspace.model.Blog;

public interface BlogService {
	
	List<Blog> buscarTodos();
	
	Blog salvarBlog(Blog blog);
	
	void excluirBlog(Long id) throws Exception;
	
	Blog editarBlog(Long id, String descricao, String titulo)throws Exception;
}
