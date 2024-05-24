package com.pin.vetspace.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.pin.vetspace.model.UserFuncionario;
import com.pin.vetspace.model.Usuario;

public interface UserFuncionarioRepository extends JpaRepository<UserFuncionario, Long> {

    Optional<UserFuncionario> findById(long id);
    
    Optional<UserFuncionario> findByUsuarioId(Long userId);
    
    @Query("SELECT uf FROM UserFuncionario uf WHERE uf.usuario.nome = :nome")
    Optional<UserFuncionario> findByNome(@Param("nome") String nome);

    List<UserFuncionario> findAll();

    @Query("SELECT uf FROM UserFuncionario uf WHERE uf.usuario.email = :email")
    Optional<UserFuncionario> findByEmail(@Param("email") String email);

    List<UserFuncionario> findByPlantao(Integer plantao);
    
    UserFuncionario findByUsuario(Usuario usuario);
    
    @Query("SELECT uf FROM UserFuncionario uf JOIN uf.usuario u WHERE u.id = :userId")
    Optional<UserFuncionario> findByUserId(@Param("userId") Long userId);
}

