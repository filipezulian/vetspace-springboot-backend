package com.pin.vetspace.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pin.vetspace.dto.ConsultaClienteDTO;
import com.pin.vetspace.dto.ConsultaDTO;
import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.repository.ConsultaRepository;
import com.pin.vetspace.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;
    

    @Override
    public Consulta salvarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public Consulta salvarConsultaCliente(ConsultaClienteDTO consultaClienteDTO) {
        Consulta consulta = new Consulta();
        consulta.setPet(consultaClienteDTO.getPet());
        consulta.setData(consultaClienteDTO.getData());
        consulta.setObs(consultaClienteDTO.getObs());
        consulta.setConfirmado(false); // Sempre falso para cliente
        return consultaRepository.save(consulta);
    }


    /*@Override
    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }*/


    @Override
    public List<ConsultaDTO> buscarConsultasConfirmadas() {
        List<Consulta> consultas = consultaRepository.findByConfirmadoTrue();
        return consultas.stream()
                .map(consulta -> new ConsultaDTO(
                        consulta.getId(),
                        consulta.getPet(),
                        consulta.getData(),
                        consulta.getObs(),
                        consulta.isConfirmado())) // Passa o valor de confirmado da entidade Consulta
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> buscarConsultasNaoConfirmadas() {
        List<Consulta> consultas = consultaRepository.findByConfirmadoFalse();
        return consultas.stream()
                .map(consulta -> new ConsultaDTO(
                        consulta.getId(),
                        consulta.getPet(),
                        consulta.getData(),
                        consulta.getObs(),
                        consulta.isConfirmado())) // Passa o valor de confirmado da entidade Consulta
                .collect(Collectors.toList());
    }
}
