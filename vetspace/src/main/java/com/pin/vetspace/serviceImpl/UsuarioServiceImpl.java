package com.pin.vetspace.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pin.vetspace.exception.ErroAutenticacao;
import com.pin.vetspace.model.Blog;
import com.pin.vetspace.model.Credencial;
import com.pin.vetspace.model.Pet;
import com.pin.vetspace.model.UserFuncionario;
import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.repository.BlogRepository;
import com.pin.vetspace.repository.PetRepository;
import com.pin.vetspace.repository.UserFuncionarioRepository;
import com.pin.vetspace.repository.UsuarioRepository;
import com.pin.vetspace.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UserFuncionarioRepository userFuncionarioRepository;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	PetRepository petRepository;

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		Optional<Usuario> existeUsuario = usuarioRepository.findByEmail(usuario.getEmail());

		if (existeUsuario.isPresent()) {
			throw new Error("Usuario já existe");
		}

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		String senhaHash = bcrypt.encode(usuario.getSenha());

		usuario.setPermissao(3);
		usuario.setSenha(senhaHash);

		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
	}

	@Override
	public Usuario editarUsuario(Usuario usuario) {
		Usuario usuarioExistente = buscarUsuarioPorId(usuario.getId());

		if (usuarioExistente == null) {
			throw new RuntimeException("usuario não encontrado com o ID fornecido: " + usuario.getId());
		} else {
			if (usuario.getNome() != null) {
				usuarioExistente.setNome(usuario.getNome());
			}
			if (usuario.getEmail() != null) {
				usuarioExistente.setEmail(usuario.getEmail());
			}
			if (usuario.getTelefone() != null) {
				usuarioExistente.setTelefone(usuario.getTelefone());
			}
			if (usuario.getSenha() != null) {
				BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
				String senhaHash = bcrypt.encode(usuario.getSenha());
				usuarioExistente.setSenha(senhaHash);
			}

			return usuarioRepository.save(usuarioExistente);
		}
	}

	/*
	 * @Override
	 * 
	 * @Transactional public void excluirUsuario(Long id) { Optional<Usuario>
	 * optionalUsuario = usuarioRepository.findById(id); if
	 * (optionalUsuario.isPresent()) { Usuario usuario = optionalUsuario.get();
	 * UserFuncionario userFuncionario =
	 * userFuncionarioRepository.findByUsuario(usuario); if (userFuncionario !=
	 * null) { throw new Error("Não foi possível deletar esse usuário"); }
	 * usuarioRepository.delete(usuario); } else { throw new
	 * EntityNotFoundException("Usuário não encontrado com o ID: " + id); } }
	 */

	@Override
	@Transactional
	public void excluirUsuario(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		optionalUsuario.ifPresent(usuario -> {
			// Verifica se o usuário está associado a UserFuncionario
			UserFuncionario userFuncionario = userFuncionarioRepository.findByUsuario(usuario);
			if (userFuncionario != null) {
				throw new RuntimeException(
						"Não é possível excluir esse usuário porque está associado a uma função de funcionário.");
			}

			// Exclui os pets associados ao usuário
			List<Pet> pets = petRepository.findByUsuario(usuario);
			petRepository.deleteAll(pets);

			// Exclui o usuário
			usuarioRepository.delete(usuario);
		});

		// Se optionalUsuario estiver vazio, lançamos uma exceção
		if (!optionalUsuario.isPresent()) {
			throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
		}
	}

	@Override
	public Usuario buscarUsuarioPorNome(String nome) {
		return usuarioRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("usuario não encontrado"));
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("usuario não encontrado"));
	}

	@Override
	public Usuario autenticar(Credencial credencial) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = usuarioRepository.findByEmail(credencial.getEmail());

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário Inválido");
		}

		Usuario u = usuario.get();
		if (!bcrypt.matches(credencial.getSenha(), u.getSenha())) {
			throw new ErroAutenticacao("Senha Inválida");
		}

		return u;
	}
}
