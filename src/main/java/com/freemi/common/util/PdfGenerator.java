package com.freemi.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public abstract class PdfGenerator extends AbstractView {

	public PdfGenerator() {
        setContentType("application/pdf");
    }
 
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	 protected Document newDocument() {
	        return new Document(PageSize.A4);
	    }
	     
	    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
	        return PdfWriter.getInstance(document, os);
	    }
	     
	    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
	            throws DocumentException {
	 
	        writer.setViewerPreferences(getViewerPreferences());
	    }
	     
	    protected int getViewerPreferences() {
	        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
	    }
	     
	    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
	    }
	     
	    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
	            HttpServletRequest request, HttpServletResponse response) throws Exception;

}
