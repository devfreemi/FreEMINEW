package com.freemi.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.freemi.entity.general.Folios;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfBuilder extends PdfGenerator {
 
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("PDF Builder");
		// TODO Auto-generated method stub
		   // get data model which is passed by the Spring container
        List<Folios> listBooks = (List<Folios>) model.get("folioList");
         
        doc.add(new Paragraph("Recommended books for Spring framework"));
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("Book Title", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("ISBN", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Published Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
         
        // write table row data
        for (Folios aBook : listBooks) {
            table.addCell(aBook.getFolio_No());
            table.addCell(aBook.getInvestorName());
            /*table.addCell(aBook.getIsbn());
            table.addCell(aBook.getPublishedDate());
            table.addCell(String.valueOf(aBook.getPrice()));*/
        }
         
        doc.add(table);
         
		
	}
 

}
