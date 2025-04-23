package org.example.projetgestionstockp.Controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetgestionstockp.Model.Article;
import org.example.projetgestionstockp.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Controller
public class PdfExportController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=articles.pdf");

        List<Article> articles = articleRepository.findAll();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph title = new Paragraph("Liste des articles", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);
        document.add(new Paragraph(" ")); // Ligne vide

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, articles);

        document.add(table);
        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Nom", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantité", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Seuil Alerte", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prix", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Service", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<Article> articles) {
        for (Article article : articles) {
            table.addCell(article.getNom());
            table.addCell(article.getDescription());
            table.addCell(String.valueOf(article.getQuantite()));
            table.addCell(String.valueOf(article.getSeuilAlerte()));
            table.addCell(String.valueOf(article.getPrix()));
            table.addCell(article.getTypeArticle().toString());
            table.addCell(article.getServiceAffecte().toString());
        }
    }
}
