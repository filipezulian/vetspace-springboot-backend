package com.pin.vetspace.service;

import com.pin.vetspace.model.RelatorioConsulta;
import com.pin.vetspace.serviceImpl.RelatorioConsultaServiceImpl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PdfGenerationService {
	
    @Autowired
    private RelatorioConsultaServiceImpl relatorioConsultaService;
    
    public byte[] generatePdf(RelatorioConsulta relatorio) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.setLeading(14.5f);

                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float yPosition = yStart;

                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);

                contentStream.showText("Relatório de Consulta");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Funcionário: " + relatorio.getFuncionario().getNome());
                contentStream.newLine();
                contentStream.newLine();

                // Converte arrays de int para List<Long>
                List<Long> materialIds = IntStream.of(relatorio.getMaterial()).asLongStream().boxed().collect(Collectors.toList());
                List<Long> servicoIds = IntStream.of(relatorio.getServico()).asLongStream().boxed().collect(Collectors.toList());

                // Busca os nomes dos materiais e serviços
                List<String> nomesMateriais = relatorioConsultaService.buscarNomesMateriaisPorIds(materialIds);
                List<String> nomesServicos = relatorioConsultaService.buscarNomesServicosPorIds(servicoIds);

                // Materiais Utilizados
                contentStream.showText("Materiais Utilizados: ");
                adicionarListaComQuebraDeLinha(contentStream, nomesMateriais);
                contentStream.newLine();

                // Serviços Realizados
                contentStream.showText("Serviços Realizados: ");
                adicionarListaComQuebraDeLinha(contentStream, nomesServicos);
                contentStream.newLine();

                // Valor Total
                contentStream.newLine();
                contentStream.showText("Valor Total: " + relatorio.getValorTotal());

                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao gerar PDF: " + e.getMessage());
        }
    }

    private void adicionarListaComQuebraDeLinha(PDPageContentStream contentStream, List<String> itens) throws IOException {
        for (int i = 0; i < itens.size(); i++) {
            contentStream.showText(itens.get(i));
            if ((i + 1) % 2 == 0 && i < itens.size() - 1) { // quebra de linha a cada 2 itens
                contentStream.showText(",");
                contentStream.newLine();
            } else if (i < itens.size() - 1) {
                contentStream.showText(", ");
            }
        }
        contentStream.newLine(); // garantir nova linha após todos os itens
    }
}
