package com.pin.vetspace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pin.vetspace.dto.ConsultaClienteDTO;
import com.pin.vetspace.dto.ConsultaDTO;
import com.pin.vetspace.dto.ConsultaFuncionarioDTO;
import com.pin.vetspace.model.Consulta;

@Service
public interface ConsultaService {
	
    Consulta salvarConsulta(Consulta consulta);
    
    Consulta salvarConsultaCliente(ConsultaClienteDTO consultaClienteDTO);
    
    //Consulta salvarConsultaFuncionario(ConsultaFuncionarioDTO consultaFuncionarioDTO);

    //Consulta buscarConsultaPorId(Long id);

    //Consulta buscarConsultaPorNome(String nome);
    
    //List<ConsultaDTO> buscarTodasConsultas();

    List<ConsultaDTO> buscarConsultasConfirmadas();

    List<ConsultaDTO> buscarConsultasNaoConfirmadas();
    
    Consulta salvarConsultaFuncionario(ConsultaFuncionarioDTO consultaFuncionarioDTO);
    
    ConsultaDTO aprovarConsulta(Long consultaId) throws Exception;
    
    void rejeitarConsulta(Long consultaId) throws Exception;
    
    Consulta buscarConsultaPorId(Long id);

	void excluirConsulta(Long consultaId) throws Exception;
}
