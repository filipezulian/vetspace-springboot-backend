package com.pin.vetspace.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pin.vetspace.model.Consulta;
import com.pin.vetspace.model.RelatorioConsulta;
import com.pin.vetspace.model.Servico;
import com.pin.vetspace.model.Usuario;
import com.pin.vetspace.repository.ConsultaRepository;
import com.pin.vetspace.repository.MaterialRepository;
import com.pin.vetspace.repository.RelatorioConsultaRepository;
import com.pin.vetspace.repository.ServicoRepository;
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
    
    @Autowired
    private ServicoRepository servicoRepository;
    
    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public RelatorioConsulta cadastrarRelatorioConsulta(Long consultaId, Long funcionarioId, RelatorioConsulta relatorioConsulta) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        Usuario funcionario = usuarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        relatorioConsulta.setConsulta(consulta);
        relatorioConsulta.setFuncionario(funcionario);
        
     // Calcula o valor total
        double valorTotal = calcularValorTotal(relatorioConsulta);
        relatorioConsulta.setValorTotal(valorTotal);

        return relatorioConsultaRepository.save(relatorioConsulta);
    }
    
    @Override
    public RelatorioConsulta buscarRelatorioPorId(Long relatorioId) {
        return relatorioConsultaRepository.findById(relatorioId)
                .orElseThrow(() -> new RuntimeException("Relatório de consulta não encontrado com o ID: " + relatorioId));
    }
    
    private double calcularValorTotal(RelatorioConsulta relatorioConsulta) {
        double total = 0;

        // Converte arrays de int para List<Long>
        List<Long> servicoIds = Arrays.stream(relatorioConsulta.getServico()).asLongStream().boxed().collect(Collectors.toList());
        List<Long> materialIds = Arrays.stream(relatorioConsulta.getMaterial()).asLongStream().boxed().collect(Collectors.toList());

        // Busca valores dos serviços
        List<Double> valoresServicos = servicoRepository.findValoresByIds(servicoIds);
        total += valoresServicos.stream().mapToDouble(Double::doubleValue).sum();

        // Busca valores dos materiais
        List<Double> valoresMateriais = materialRepository.findValoresByIds(materialIds);
        total += valoresMateriais.stream().mapToDouble(Double::doubleValue).sum();

        return total;
    }
       
}
