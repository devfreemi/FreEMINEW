package com.freemi.ui.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.freemi.common.util.CommonConstants;
import com.itextpdf.text.pdf.PdfDocument;

@Controller
@ControllerAdvice
public class ErrorHandlers implements ErrorController{
	
	@Autowired
	Environment env;
	
	private static final Logger logger = LogManager.getLogger(ErrorHandlers.class);
	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String pageNotFound(HttpServletRequest request, Exception ex){
		logger.info("Controlleradvice- page not found"+ request.getRequestURI());
		return "pagenotfound";
	}
	
	
	@RequestMapping("/error")
    public String handleError(Model map, HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		logger.error("Error received http code- "+ status);
		if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "errors/error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "errors/error-500";
	        }
	    }
		map.addAttribute("contextcdn", env.getProperty(CommonConstants.CDN_URL));
		
        return "errors/error";
    }
	

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	public static void main(String[] args){
		 String base64File = "";
		 String filePath="E:\\AOF\\AOF_26273_TES876T836.pdf";
		 String tiffFile = "E:\\AOF\\AOF_26273_TES876T836.tiff";
	        File file = new File(filePath);
	        /*try (FileInputStream imageInFile = new FileInputStream(file)) {
	            // Reading a file from file system
	            byte fileData[] = new byte[(int) file.length()];
	            imageInFile.read(fileData);
	            base64File = Base64.getEncoder().encodeToString(fileData);
	            System.out.println("File- \n"+ base64File);
	            
//	            byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(base64File.getBytes());
	            
	            byte[] decoded= Files.readAllBytes(new File(filePath.toString()).toPath());
	            
	            System.out.println("byte array- \n"+ decoded.toString());
	            
	        } catch (FileNotFoundException e) {
	            System.out.println("File not found" + e);
	        } catch (IOException ioe) {
	            System.out.println("Exception while reading the file " + ioe);
	        }
	        
	        System.out.println("Complete");*/
//	        return base64File;
	        
	        try {
	        String extension = "tiff";
	        PDDocument document = PDDocument.load(new File(filePath));
	        PDFRenderer pdfRenderer = new PDFRenderer(document);
	        /*for (int page = 0; page < document.getNumberOfPages(); ++page) {
	            BufferedImage bim = pdfRenderer.renderImageWithDPI(
	              page, 300, ImageType.RGB);
	           
					ImageIOUtil.writeImage(
					  bim, String.format(tiffFile, extension), 300);
				
	        }*/
	        
	        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
	           
	        ImageIOUtil.writeImage(bim, String.format(tiffFile, extension), 300);
	        
	        document.close();
	        
//	        System.out.println(Arrays.toString(Files.readAllBytes(new File(tiffFile.toString()+".tiff").toPath())));
	        
	       /* String str = new String(Files.readAllBytes(new File(tiffFile.toString()+".tiff").toPath()), StandardCharsets.UTF_8);
	        System.out.println(str);*/
	        
	        byte[] fileContent = FileUtils.readFileToByteArray(new File(tiffFile));
	        String encodedString = Base64.getEncoder().encodeToString(fileContent);
	        
	        System.out.println(encodedString);
	        System.out.println("Complete conversion");
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	}
	
	

}
