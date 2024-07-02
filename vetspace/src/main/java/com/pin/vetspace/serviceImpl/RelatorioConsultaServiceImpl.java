package com.pin.vetspace.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.model.RelatorioConsulta;
import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.repository.ConsultaRepository;
import com.pin.vetspace.repository.RelatorioConsultaRepository;
import com.pin.vetspace.repository.UsuarioRepository;
import com.pin.vetspace.service.RelatorioConsultaService;

@Service
public class RelatorioConsultaServiceImpl implements RelatorioConsultaService {

    @Autowired
    private RelatorioConsultaRepository relatorioConsultaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public RelatorioConsulta cadastrarRelatorioConsulta(Long consultaId, Long funcionarioId, RelatorioConsulta relatorioConsulta) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        Usuario funcionario = usuarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        relatorioConsulta.setConsulta(consulta);
        relatorioConsulta.setFuncionario(funcionario);

        return relatorioConsultaRepository.save(relatorioConsulta);
    }
    
    @Override
    public RelatorioConsulta buscarRelatorioPorId(Long relatorioId) {
        return relatorioConsultaRepository.findById(relatorioId)
                .orElseThrow(() -> new RuntimeException("Relatório de consulta não encontrado com o ID: " + relatorioId));
    }
       
}
