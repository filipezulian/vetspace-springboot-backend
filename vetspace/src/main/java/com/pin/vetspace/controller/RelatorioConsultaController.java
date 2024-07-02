package com.pin.vetspace.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pin.vetspace.model.RelatorioConsulta;
import com.pin.vetspace.service.PdfGenerationService;
import com.pin.vetspace.service.RelatorioConsultaService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioConsultaController {

    @Autowired
    private RelatorioConsultaService relatorioConsultaService;
    
    @Autowired
    private PdfGenerationService pdfGenerationService;

    @PostMapping("/cadastrar/consulta/{consultaId}/funcionario/{funcionarioId}")
    public ResponseEntity<RelatorioConsulta> cadastrarRelatorioConsulta(
            @PathVariable Long consultaId,
            @PathVariable Long funcionarioId,
            @RequestBody RelatorioConsulta relatorioConsulta) {

        RelatorioConsulta novoRelatorioConsulta = relatorioConsultaService.cadastrarRelatorioConsulta(consultaId, funcionarioId, relatorioConsulta);
        return ResponseEntity.ok(novoRelatorioConsulta);
    }
    
    @GetMapping("/{relatorioId}/pdf")
    public ResponseEntity<byte[]> downloadRelatorioPdf(@PathVariable Long relatorioId) {
        try {
            // Busca o relatório pelo ID
            RelatorioConsulta relatorio = relatorioConsultaService.buscarRelatorioPorId(relatorioId);

            // Gera o PDF baseado no relatório encontrado
            byte[] pdfBytes = pdfGenerationService.generatePdf(relatorio);

            // Define o cabeçalho para fazer o download do PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "relatorio.pdf");

            // Retorna a resposta com o PDF como um array de bytes
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar erros
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
