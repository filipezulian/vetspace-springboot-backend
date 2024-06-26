package com.pin.vetspace.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pin.vetspace.model.Blog;


public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	List<Blog> findAll();
	
}
