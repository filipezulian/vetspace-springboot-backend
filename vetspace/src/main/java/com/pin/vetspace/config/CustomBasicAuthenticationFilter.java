package com.pin.vetspace.config;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pin.vetspace.model.Cliente;
import com.pin.vetspace.model.Funcionario;
import com.pin.vetspace.repository.ClienteRepository;
import com.pin.vetspace.repository.FuncionarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
	private static final String AUTHORIZATION = "Authorization";
	private static final String BASIC = "Basic ";
	private final FuncionarioRepository funcionarioRepository;
	private final ClienteRepository clienteRepository;

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isBasicAuthentication(request)) {
			String[] credentials = decodeBase64(getHeader(request).replace(BASIC, "")).split(":");

			String nome = credentials[0];
			String senha = credentials[1];

			// Verifica se é um funcionário
			Funcionario funcionario = funcionarioRepository.findByNome(nome);
			if (funcionario != null) {
				if (!passwordEncoder().matches(senha, funcionario.getSenha())) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Senha incorreta");
					return;
				}
				// Verifica se a permissão do funcionário permite o acesso
				if (funcionario.getPermissao() != 2) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Acesso não autorizado");
					return;
				}
				setAutenticacao(funcionario);
				filterChain.doFilter(request, response);
				return;
			}


			Cliente cliente = clienteRepository.findByNome(nome);
			if (cliente != null) {
				if (!passwordEncoder().matches(senha, cliente.getSenha())) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Senha incorreta");
					return;
				}
				// Verifica se a permissão do cliente permite o acesso
				if (cliente.getPermissao() != 3) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write("Acesso não autorizado");
					return;
				}
				setAutenticacao(cliente);
				filterChain.doFilter(request, response);
				return;
			}

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Usuário não encontrado!");
			return;
		}

		filterChain.doFilter(request, response);
	}

	private void setAutenticacao(Funcionario funcionario) {
	    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_FUNCIONARIO");
	    Authentication authentication = new UsernamePasswordAuthenticationToken(funcionario, null, authorities);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private void setAutenticacao(Cliente cliente) {
	    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CLIENTE");
	    Authentication authentication = new UsernamePasswordAuthenticationToken(cliente, null, authorities);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String decodeBase64(String base64) {
		byte[] decodeBytes = Base64.getDecoder().decode(base64);
		return new String(decodeBytes);
	}

	private boolean isBasicAuthentication(HttpServletRequest request) {
		String header = getHeader(request);
		return header != null && header.startsWith(BASIC);
	}

	private String getHeader(HttpServletRequest request) {
		return request.getHeader(AUTHORIZATION);
	}
}
