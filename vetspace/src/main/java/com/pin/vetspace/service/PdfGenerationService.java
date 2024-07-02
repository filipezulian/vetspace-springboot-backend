package com.pin.vetspace.service;

import com.pin.vetspace.model.RelatorioConsulta;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService {

    public byte[] generatePdf(RelatorioConsulta relatorio) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.setLeading(14.5f); // Espaçamento entre linhas

                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float yPosition = yStart;

                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);

                contentStream.showText("Relatório de Consulta");
                contentStream.newLine();
                contentStream.showText("Consulta ID: " + relatorio.getConsulta().getId());
                contentStream.newLine();
                contentStream.showText("Funcionário: " + relatorio.getFuncionario().getNome());
                contentStream.newLine();
                contentStream.showText("Material Utilizado: ");
                for (int material : relatorio.getMaterial()) {
                    contentStream.showText(material + ", ");
                }
                contentStream.newLine();
                contentStream.showText("Serviços Realizados: ");
                for (int servico : relatorio.getServico()) {
                    contentStream.showText(servico + ", ");
                }
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
}
