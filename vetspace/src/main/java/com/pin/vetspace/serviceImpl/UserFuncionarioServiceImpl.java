package com.pin.vetspace.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pin.vetspace.exception.ErroAutenticacao;
import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.UserFuncionario;
import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.repository.UserFuncionarioRepository;
import com.pin.vetspace.repository.UsuarioRepository;
import com.pin.vetspace.service.UserFuncionarioService;

@Service
public class UserFuncionarioServiceImpl implements UserFuncionarioService {

    @Autowired
    UserFuncionarioRepository funcionarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    public UserFuncionarioServiceImpl(UserFuncionarioRepository funcionarioRepository, UsuarioRepository usuarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserFuncionario salvarFuncionario(UserFuncionario userFuncionario) {
        Optional<UserFuncionario> existeFuncionario = funcionarioRepository.findByEmail(userFuncionario.getUsuario().getEmail());

        if (existeFuncionario.isPresent()) {
            throw new Error("Funcionário já existe");
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String senhaHash = bcrypt.encode(userFuncionario.getUsuario().getSenha());
        
        userFuncionario.getUsuario().setPermissao(2);
        userFuncionario.getUsuario().setSenha(senhaHash);

        // Salvar o usuario primeiro
        Usuario usuarioSalvo = usuarioRepository.save(userFuncionario.getUsuario());
        
        // Associar o usuario salvo ao userFuncionario e então salvar o userFuncionario
        userFuncionario.setUsuario(usuarioSalvo);
        return funcionarioRepository.save(userFuncionario);
    }

    @Override
    public UserFuncionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    @Override
    public UserFuncionario editarFuncionario(UserFuncionario userFuncionario) {
        UserFuncionario funcionarioExistente = funcionarioRepository.findByUsuarioId(userFuncionario.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID fornecido: " + userFuncionario.getUsuario().getId()));

        funcionarioExistente.setEspecializao(userFuncionario.getEspecializao());
        funcionarioExistente.setPlantao(userFuncionario.getPlantao());
        
        Usuario usuarioExistente = funcionarioExistente.getUsuario();
        Usuario usuarioAtualizado = userFuncionario.getUsuario();
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
        
        if (usuarioAtualizado.getSenha() != null) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String senhaHash = bcrypt.encode(usuarioAtualizado.getSenha());
            usuarioExistente.setSenha(senhaHash);
        }
        

        usuarioRepository.save(usuarioExistente);
        funcionarioRepository.save(funcionarioExistente); 
        
        return funcionarioExistente; 
    }

    @Override
    public void excluirFuncionario(Long id) {
        Optional<UserFuncionario> funcionarioOptional = funcionarioRepository.findByUsuarioId(id);
        
        if (funcionarioOptional.isPresent()) {
            UserFuncionario funcionario = funcionarioOptional.get();
            
            // Exclua o funcionário da tabela UserFuncionario
            funcionarioRepository.delete(funcionario);
            
            // Exclua o usuário associado da tabela usuarios
            Usuario usuario = funcionario.getUsuario();
            usuarioRepository.delete(usuario);
        } else {
            throw new RuntimeException("Funcionário não encontrado");
        }
    }
    
    @Override
    public UserFuncionario buscarFuncionarioPorNome(String nome) {
        return funcionarioRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    @Override
    public UserFuncionario buscarFuncionarioPorEmail(String email) {
        return funcionarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    @Override
    public UserFuncionario autenticar(Credencial credencial) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        
        Optional<UserFuncionario> funcionario = funcionarioRepository.findByEmail(credencial.getEmail());
        
        if (!funcionario.isPresent()) {
            throw new ErroAutenticacao("Usuário Inválido");
        }
        
        UserFuncionario u = funcionario.get();
        if (!bcrypt.matches(credencial.getSenha(), u.getUsuario().getSenha())) {
            throw new ErroAutenticacao("Senha Inválida");
        }
        
        return u;
    }

    @Override
    public List<UserFuncionario> buscarFuncionariosPorPlantao(Integer plantao) {
        List<UserFuncionario> funcionarios = funcionarioRepository.findByPlantao(plantao);
        if (funcionarios.isEmpty()) {
            throw new RuntimeException("Não há funcionários atribuídos ao plantão " + plantao);
        }
        return funcionarios;
    }
}
