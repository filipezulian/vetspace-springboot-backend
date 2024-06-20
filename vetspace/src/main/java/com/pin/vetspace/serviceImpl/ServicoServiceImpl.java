package com.pin.vetspace.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pin.vetspace.model.Servico;
import com.pin.vetspace.repository.ServicoRepository;
import com.pin.vetspace.service.ServicoService;
@Service
public class ServicoServiceImpl implements ServicoService{
	
	@Autowired
	private final ServicoRepository servicoRepository;
	
	@Autowired
	public ServicoServiceImpl (ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}

	@Override
	public Servico salvarServico(Servico servico) {
		return servicoRepository.save(servico);
	}

	@Override
	public Servico buscarServicoPorId(Long id) {
		return servicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
	}

	@Override
	public Servico buscarServicoPorNome(String nome) {
		return servicoRepository.findByNome(nome).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
	}

	@Override
	public List<Servico> buscarTodosServicos() {
		return servicoRepository.findAll();
	}

	@Override
	public Servico editarServico(Servico servico) {
			Servico servicoExistente = buscarServicoPorId(servico.getId());

			if (servicoExistente == null) {
				throw new RuntimeException("serviço não encontrado com o ID fornecido: " + servico.getId());
			} else {
				if (servico.getNome() != null) {
					servicoExistente.setNome(servico.getNome());
				}
				if (servico.getValor() >= 0) {
					servicoExistente.setValor(servico.getValor());
				}

				return servicoRepository.save(servicoExistente);
			}
		}

	@Override
	public void excluirServico(Long id) {
		Servico servicoExistente = buscarServicoPorId(id);
		
		if(servicoExistente != null) {
			servicoRepository.delete(servicoExistente);
		}else {
			throw new RuntimeException("Serviço não encontrado");
		}
		
	}
	

}
