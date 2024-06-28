package com.pin.vetspace.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Blog;
import com.pin.vetspace.repository.BlogRepository;
import com.pin.vetspace.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private final BlogRepository blogRepository;
	
	@Autowired
	public BlogServiceImpl (BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public List<Blog> buscarTodos() {
		return blogRepository.findAll();
	}		

	@Override
	public Blog salvarBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	@Override
	public void excluirBlog(Long id) throws Exception {
		Optional<Blog> optionalBlog = blogRepository.findById(id);
		if (optionalBlog.isPresent()) {
			blogRepository.delete(optionalBlog.get());
		} else {
			throw new Exception("Blog não encontrado do ID: " + id);
		}
	}

	@Override
    public Blog editarBlog(Long id, String descricao, String titulo) throws Exception {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            blog.setDescricao(descricao);
            blog.setTitulo(titulo);
            return blogRepository.save(blog);
        } else {
            throw new Exception("Blog não encontrado do ID: " + id);
        }
    }
	
}
