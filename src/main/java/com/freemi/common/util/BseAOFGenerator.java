package com.freemi.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freemi.entity.investment.BseAOFDocument;
import com.freemi.entity.investment.MFCustomers;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/*
Note-
using itextpdf to generate pdf and pdfbox to convert pdf to .tff file.
It was noticed that when image converted in linux, .tff file contents were distored.
After debugging, it was identified that, font 'Courier' was not installed in Centos where the war was deployed.
In linux, check list of fonts available by command 'fc-list'
TO mitigate file issue, courier and courier-bold font file .ttf was downloaded and placed in server /usr/share/fonts/custom
Then recached by command fc-cache
This solved distortion issue.
*/

public class BseAOFGenerator {
	private static final Logger logger = LogManager.getLogger(BseAOFGenerator.class);

	public static BseAOFDocument aofGenerator(BseAOFDocument aoffilestatus, MFCustomers investForm,String fileName, String imageAbsPath, String kycStatus, String aofbasepath){
		logger.info("Beginning process to generate AOF file - "+ (aofbasepath+fileName));
		String flag = "SUCCESS";
		kycStatus = "";	//
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4);
//		BseAOFDocument aoffilestatus = new BseAOFDocument();
		if(aoffilestatus == null) {
			logger.info("No existing record in DB hence create new BseAOFDocument() object");
			aoffilestatus = new BseAOFDocument();
		}
		try
		{
			writer = PdfWriter.getInstance(document, new FileOutputStream(aofbasepath+fileName));
			document.open();
			/*document.add(new Paragraph("A Hello World PDF document."));*/

			/*
			Font f1 = new Font(FontFamily.COURIER,8,Font.BOLD);
			Font f2 = new Font(FontFamily.COURIER,8,Font.NORMAL);
			Font f3 = new Font(FontFamily.COURIER,6,Font.NORMAL);
			*/
			Font f1 = new Font(FontFamily.COURIER,8,Font.BOLD);
			Font f2 = new Font(FontFamily.COURIER,8,Font.NORMAL);
			Font f3 = new Font(FontFamily.COURIER,6,Font.NORMAL);
			
			
			// 0th row - image

			PdfPTable table21 = new PdfPTable(1); // 3 columns.
			table21.setWidthPercentage(100); //Width 100%

			PdfPCell cell71 = new PdfPCell();
			cell71.setBorderColor(BaseColor.BLACK);
			cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//cell71.setFixedHeight(60);
			cell71.setMinimumHeight(50);
			cell71.setPaddingTop(15);
			Image img;
			try {
				//				img = Image.getInstance("E:\\BITBUCKET REPOSITORY\\freemi\\src\\main\\webapp\\resources\\images\\freemi.png");
				img = Image.getInstance(imageAbsPath);
				//				img = Image.get
				img.setAlt("FREEMI AOF FORM");
				Chunk c21 = new Chunk(img, 1, 1);
				Paragraph p21 = new Paragraph();
				//p21.setPaddingTop(15);
				p21.add(c21);
				cell71.addElement(p21);
			} catch (MalformedURLException e) {
				logger.error("Error processing, ",e);
			} catch (IOException e) {
				logger.error("Error AOF",e);
			} 



			table21.addCell(cell71);
			document.add(table21);


			// 1st row
			PdfPTable table = new PdfPTable(6); // 3 columns.
			table.setWidthPercentage(100); //Width 100%
			// table.setSpacingBefore(10f); //Space before table
			// table.setSpacingAfter(10f); //Space after table

			//Set Column widths
			float[] columnWidths = {1f, 1f, 1f,1f, 1f, 1f};
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Broker/Agent Code ARN:",f1));
			cell1.setBorderColor(BaseColor.BLACK);
			// cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph(CommonConstants.BROKER_CODE,f2)); //value
			cell2.setBorderColor(BaseColor.BLACK);
			// cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("SUB-BROKER",f1));
			cell3.setBorderColor(BaseColor.BLACK);
			// cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell4 = new PdfPCell(new Paragraph(""));
			cell4.setBorderColor(BaseColor.BLACK);
			// cell4.setPaddingLeft(10);
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell5 = new PdfPCell(new Paragraph("EUIN",f1));
			cell5.setBorderColor(BaseColor.BLACK);
			// cell5.setPaddingLeft(10);
			cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

			//			PdfPCell cell6 = new PdfPCell(new Paragraph(CommonConstants.EUIN_CODE,f2));
			PdfPCell cell6 = new PdfPCell(new Paragraph(""));
			cell6.setBorderColor(BaseColor.BLACK);
			// cell6.setPaddingLeft(10);
			cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

			//To avoid having the cell border and the content overlap, if you are having thick cell borders
			//cell1.setUserBorderPadding(true);
			//cell2.setUserBorderPadding(true);
			//cell3.setUserBorderPadding(true);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);

			document.add(table);

			// 2nd row
			PdfPTable table2 = new PdfPTable(1); // 3 columns.
			table2.setWidthPercentage(100); //Width 100%

			PdfPCell cell7 = new PdfPCell(new Paragraph("Unit Folder Information",f1));
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);


			// 3rd Row
			PdfPTable table3 = new PdfPTable(2); // 3 columns.
			table3.setWidthPercentage(100); //Width 100%

			float[] columnWidths3 = {1f, 2f};
			table3.setWidths(columnWidths3);

			PdfPCell cell8 = new PdfPCell(new Paragraph("Name of the First Applicant :",f1));
			cell8.setBorderColor(BaseColor.BLACK);
			// cell8.setPaddingLeft(10);
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell9 = new PdfPCell(new Paragraph(investForm.getInvName().toUpperCase(),f2));
			cell9.setBorderColor(BaseColor.BLACK);
			// cell9.setPaddingLeft(10);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table3.addCell(cell8);
			table3.addCell(cell9);

			document.add(table3);

			// 4th row
			PdfPTable table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			float[] columnWidths4 = {1f, 1f, 1f};
			table4.setWidths(columnWidths4);

			PdfPCell cell11 = new PdfPCell(new Paragraph("PAN Number:"+" "+ investForm.getPan1().toUpperCase(),f1));

			cell11.setBorderColor(BaseColor.BLACK);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);
			cell11 = new PdfPCell(new Paragraph("KYC:"+" "+kycStatus,f1));
			table4.addCell(cell11);

//			DOB format
			SimpleDateFormat baseFormat = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat baseFormat2 = new SimpleDateFormat("dd/mm/yyyy");
			SimpleDateFormat aofDateFormat = new SimpleDateFormat("dd-mm-yyyy");
			String dobinFormat ="";
			try {
				logger.info("Date of birth- "+ investForm.getCustomerdob() + " -> "+ investForm.getInvDOB());
//				dobinFormat = baseFormat.format(investForm.getInvDOB());
				dobinFormat = baseFormat2.format(baseFormat2.parse(investForm.getCustomerdob()) );
			}catch(Exception e) {
				logger.error("AOF date format issue of DOB with format dd/mm/yyyy. ", e);
				try {
					logger.info("Try Database format- yyyy-mm-dd");
					dobinFormat = aofDateFormat.format(baseFormat.parse(investForm.getInvDOB()));
				}catch(Exception e2) {
					logger.error("Issue with 2nd format too. Set as date format...",e2);
					
				}
			}
			cell11 = new PdfPCell(new Paragraph("Date of birth: "+ dobinFormat,f1));
			table4.addCell(cell11);
			document.add(table4);


			// 5th row
			// 5th row - 1st column
			table4 = new PdfPTable(2); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			float[] columnWidths5 = {2f, 1f};
			table4.setWidths(columnWidths5);

			cell11 = new PdfPCell();

			Chunk c1 = new Chunk("Name of Guardian: ",f1);
			Chunk c2 = new Chunk("",f2);
			Phrase p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			Paragraph paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			// cell11.setBorderColor(BaseColor.BLACK);
			// cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);

			// 5th row- 2nd column

			cell11 = new PdfPCell();
			c1 = new Chunk("PAN:",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);
			cell11.addElement(paragraph2);

			table4.addCell(cell11);

			document.add(table4);

			// 6th row
			table4 = new PdfPTable(1); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			float[] columnWidths6 = {1f};
			table4.setWidths(columnWidths6);
			cell11 = new PdfPCell();

			c1 = new Chunk("Contact Address: "+ investForm.getAddressDetails().getAddress1(),f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);
			cell11.addElement(paragraph2);

			table4.addCell(cell11);

			document.add(table4);

			// 7th row

			table4 = new PdfPTable(1); // 1 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths6);
			cell9 = new PdfPCell(new Paragraph(investForm.getAddressDetails().getAddress2(),f2)); //address 2
			cell9.setBorderColor(BaseColor.BLACK);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell9);

			document.add(table4);

			// 8th row

			table4 = new PdfPTable(1); // 1 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths6);
			cell9 = new PdfPCell(new Paragraph(investForm.getAddressDetails().getAddress3(),f2)); //address 3
			cell9.setBorderColor(BaseColor.BLACK);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell9);

			document.add(table4);

			// 9th row
			// 9th row- 1st column
			table4 = new PdfPTable(4); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			float[] columnWidths9 = {1f, 1f,1f,1f};
			table4.setWidths(columnWidths9);

			cell11 = new PdfPCell();

			c1 = new Chunk("City: ",f1);
			c2 = new Chunk(investForm.getAddressDetails().getCity(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			// paragraph2.setPaddingTop(0);
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 9th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Pincode: ",f1);
			c2 = new Chunk(investForm.getAddressDetails().getPinCode(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 9th row- 3rd column
			cell11 = new PdfPCell();

			c1 = new Chunk("State: ",f1);
			c2 = new Chunk(InvestFormConstants.states.get(investForm.getAddressDetails().getState()),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 9th row- 4th column
			cell11 = new PdfPCell();

			c1 = new Chunk("Country: ",f1);
			c2 = new Chunk((investForm.getAddressDetails().getCountry()=="" || investForm.getAddressDetails().getCountry()==null)?"INDIA":investForm.getAddressDetails().getCountry(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			document.add(table4);

			// 10th row
			// 10th row- 1st column
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			float[] columnWidths93 = {1f, 1f,1f};
			table4.setWidths(columnWidths93);

			cell11 = new PdfPCell();

			c1 = new Chunk("Tel.(Off):",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 10th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Tel.(Res):",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 10th row- 3rd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Email:",f1);
			c2 = new Chunk(investForm.getEmail(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			document.add(table4);

			// 11th row
			// 11th row- 1st column
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths93);

			cell11 = new PdfPCell();

			c1 = new Chunk("Fax(Off):",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 11th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Fax(Res):",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 11th row- 3rd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Mobile:",f1);
			c2 = new Chunk(investForm.getMobile(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			document.add(table4);

			// 12th row
			// 12th row- 1st column
			table4 = new PdfPTable(2); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%
			float[] columnWidths1221 = {2f,1f};
			table4.setWidths(columnWidths1221);

			cell11 = new PdfPCell();

			c1 = new Chunk("Mode of Holding:",f1);
			c2 = new Chunk(getHoldingMode(investForm.getHoldingMode()),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 12th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Occupation:",f1);
			c2 = new Chunk(getOccupationName(investForm.getOccupation()),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			document.add(table4);

			// 13th row
			table3 = new PdfPTable(2); // 3 columns.
			table3.setWidthPercentage(100); //Width 100%

			table3.setWidths(columnWidths3);

			cell8 = new PdfPCell(new Paragraph("Name of the Second Applicant : ",f1));
			cell8.setBorderColor(BaseColor.BLACK);
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell9 = new PdfPCell(new Paragraph(investForm.getApplicant2(),f2));
			cell9.setBorderColor(BaseColor.BLACK);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table3.addCell(cell8);
			table3.addCell(cell9);

			document.add(table3);

			// 14th row
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths4);

			cell11 = new PdfPCell(new Paragraph("PAN Number: "+ investForm.getPan2(),f1));

			cell11.setBorderColor(BaseColor.BLACK);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);
			cell11 = new PdfPCell(new Paragraph("KYC: ",f1));
			table4.addCell(cell11);

			cell11 = new PdfPCell(new Paragraph("Date of birth: ",f1));
			table4.addCell(cell11);

			document.add(table4);

			// 15th row
			table3 = new PdfPTable(2); // 3 columns.
			table3.setWidthPercentage(100); //Width 100%

			table3.setWidths(columnWidths3);

			cell8 = new PdfPCell(new Paragraph("Name of the Third Applicant :",f1));
			cell8.setBorderColor(BaseColor.BLACK);
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell9 = new PdfPCell(new Paragraph("",f2));
			cell9.setBorderColor(BaseColor.BLACK);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table3.addCell(cell8);
			table3.addCell(cell9);

			document.add(table3);

			// 16th row
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths4);

			cell11 = new PdfPCell(new Paragraph("PAN Number: ",f1));

			cell11.setBorderColor(BaseColor.BLACK);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);
			cell11 = new PdfPCell(new Paragraph("KYC: ",f1));
			table4.addCell(cell11);

			cell11 = new PdfPCell(new Paragraph("Date of birth: ",f1));
			table4.addCell(cell11);

			document.add(table4);


			// 17th row
			table2 = new PdfPTable(1); // 3 columns.
			table2.setWidthPercentage(100); //Width 100%

			cell7 = new PdfPCell(new Paragraph("Other Details of Sole / 1st Applicant",f2));
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 18th row

			table2 = new PdfPTable(1); // 3 columns.
			table2.setWidthPercentage(100); //Width 100%

			cell7 = new PdfPCell(new Paragraph("Overseas Address(In case of NRI Investor):",f2)); //append string add value
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 19th row
			// 19th row- 1st column
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths93);

			cell11 = new PdfPCell();

			c1 = new Chunk("City:",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 19th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Pincode:",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 19th row- 3rd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Country:",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			document.add(table4);

			// 20th row
			table2 = new PdfPTable(1); // 3 columns.
			table2.setWidthPercentage(100); //Width 100%

			cell7 = new PdfPCell(new Paragraph("Bank Mandate Details",f1));
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 21st row
			// 21st row - 1st column
			table4 = new PdfPTable(2); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths5);

			cell11 = new PdfPCell();

			c1 = new Chunk("Name of Bank:",f1);
			c2 = new Chunk(investForm.getBankDetails().getBankName(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			// cell11.setBorderColor(BaseColor.BLACK);
			// cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);

			// 21st row- 2nd column

			cell11 = new PdfPCell();
			c1 = new Chunk("Branch:",f1);
			c2 = new Chunk(investForm.getBankDetails().getBankBranch(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);
			cell11.addElement(paragraph2);

			table4.addCell(cell11);

			document.add(table4);

			// 22nd row
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths4);

			cell11 = new PdfPCell(new Paragraph("A/C No.: "+ investForm.getBankDetails().getAccountNumber(),f1));

			cell11.setBorderColor(BaseColor.BLACK);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);
			cell11 = new PdfPCell(new Paragraph("A/C Type: "+ InvestFormConstants.accountTypes.get(investForm.getBankDetails().getAccountType()),f1));
			table4.addCell(cell11);

			cell11 = new PdfPCell(new Paragraph("IFSC Code: "+ investForm.getBankDetails().getIfscCode(),f1));
			table4.addCell(cell11);

			document.add(table4);

			// 23rd row
			table2 = new PdfPTable(1); // 3 columns.
			table2.setWidthPercentage(100); //Width 100%

			cell7 = new PdfPCell(new Paragraph("Bank Address: "+investForm.getBankDetails().getBankAddress(),f1));
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 24th row
			// 24th row- 1st column
			table4 = new PdfPTable(4); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths9);

			cell11 = new PdfPCell();

			c1 = new Chunk("City: ",f1);
			c2 = new Chunk(investForm.getBankDetails().getBankCity(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			// paragraph2.setPaddingTop(0);
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 24th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Pincode: ",f1);
			c2 = new Chunk("",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 24th row- 3rd column
			cell11 = new PdfPCell();

			c1 = new Chunk("State: ",f1);
			c2 = new Chunk(investForm.getBankDetails().getBranchState(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 24th row- 4th column
			cell11 = new PdfPCell();

			c1 = new Chunk("Country: ",f1);
			c2 = new Chunk("INDIA",f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			document.add(table4);

			// 25th row
			table2 = new PdfPTable(1); // 3 columns.
			table2.setWidthPercentage(100); //Width 100%

			cell7 = new PdfPCell(new Paragraph("Nomination Details",f1));
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 26th row
			// 26th row - 1st column
			table4 = new PdfPTable(2); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths5);

			cell11 = new PdfPCell();

			c1 = new Chunk("Nominee Name: ",f1);
			c2 = new Chunk(investForm.getNominee().getNomineeName(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			// cell11.setBorderColor(BaseColor.BLACK);
			// cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);

			// 26th row- 2nd column

			cell11 = new PdfPCell();
			c1 = new Chunk("Relationship: ",f1);
			c2 = new Chunk(investForm.getNominee().getNomineeRelation(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);
			cell11.addElement(paragraph2);

			table4.addCell(cell11);

			document.add(table4);

			// 27th row

			table2 = new PdfPTable(1); // 1 columns.
			table2.setWidthPercentage(100); //Width 100%
			/*
			cell7 = new PdfPCell(new Paragraph("Guardian Name(If Nominee is Minor):",f2)); //append string add value
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);*/

			cell11 = new PdfPCell();
			c1 = new Chunk("Guardian Name(If Nominee is Minor): ",f1);
			if(investForm.getNominee().getIsNomineeMinor()!=null) {
				c2 = new Chunk(investForm.getNominee().getIsNomineeMinor().equals("N")?"":investForm.getNominee().getNomineeGuardian(),f2);
			}else {
				c2 = new Chunk("",f2);
			}
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);
			cell11.addElement(paragraph2);

			table2.addCell(cell11);
			document.add(table2);

			// 28th row
			table2 = new PdfPTable(1); // 1 column.
			table2.setWidthPercentage(100); //Width 100%

			cell7 = new PdfPCell(new Paragraph("Nominee Address: "+ investForm.getNominee().getNomineeAddress1() + " "+ investForm.getNominee().getNomineeAddress2(),f2)); //append string add value
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 29th row
			// 29th row- 1st column
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths93);

			cell11 = new PdfPCell();

			c1 = new Chunk("City:",f1);
			c2 = new Chunk(investForm.getNominee().getNomineeCity(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			// 29th row- 2nd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Pincode:",f1);
			c2 = new Chunk(investForm.getNominee().getNomineeCity(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);

			// 29th row- 3rd column
			cell11 = new PdfPCell();

			c1 = new Chunk("Country:",f1);
			c2 = new Chunk(investForm.getNominee().getNomineeCountry(),f2);
			p11 = new Phrase();
			p11.add(c1);
			p11.add(c2);
			paragraph2 = new Paragraph();
			paragraph2.add(p11);

			cell11.addElement(paragraph2);
			table4.addCell(cell11);
			document.add(table4);

			// 30th row
			table2 = new PdfPTable(1); // 1 columns.
			table2.setWidthPercentage(100); //Width 100%
			c1 = new Chunk("Declaration and Signature",f3);
			c1.setUnderline(0.1f, -2f);
			Paragraph paragraph1 = new Paragraph(c1);
			c2 = new Chunk("I/We confirm that details provided by me/us are true and correct. The ARN holder has disclosed to me/us all the commission (In the form of trail\r\n" + 
					"commission or any other mode), payable to him for the different competing Schemes of various Mutual Fund From amongst which the scheme\r\n" + 
					"is being recommended to me/us.",f3);
			paragraph2 = new Paragraph(c2);

			cell7 = new PdfPCell();
			cell7.addElement(paragraph1);
			cell7.addElement(paragraph2);
			cell7.setBorderColor(BaseColor.BLACK);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table2.addCell(cell7);
			document.add(table2);

			// 31st row
			table3 = new PdfPTable(2); // 3 columns.
			table3.setWidthPercentage(100); //Width 100%

			float[] columnWidths3111 = {1f, 1f};
			table3.setWidths(columnWidths3111);

			cell8 = new PdfPCell(new Paragraph("Date : "+ new Date(),f2)); //append to add value
			cell8.setBorderColor(BaseColor.BLACK);
			// cell8.setPaddingLeft(10);
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);

			cell9 = new PdfPCell(new Paragraph("Place : "+ investForm.getAddressDetails().getCity(),f2));
			cell9.setBorderColor(BaseColor.BLACK);
			// cell9.setPaddingLeft(10);
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table3.addCell(cell8);
			table3.addCell(cell9);

			document.add(table3);

			// 32nd row
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths4);

			//			cell11 = new PdfPCell(new Paragraph("",f1));
			cell11 = new PdfPCell();
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setPaddingLeft(10);
			cell11.setPaddingTop(5);
			cell11.setFixedHeight(50);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell11.setBorderColor(BaseColor.BLACK);
			Image img1;
			if(investForm.getCustomerSignature1()!=""){
				try {
					Base64 decoder = new Base64();
					byte[] imageByte = decoder.decode(investForm.getCustomerSignature1().split(",")[1]);
					img1 = Image.getInstance(imageByte);
					//				img = Image.get
					img1.setAlt("Signature 1");
					Chunk c21 = new Chunk(img1, 1, 1);
					Paragraph p21 = new Paragraph();

					//p21.setPaddingTop(15);
					p21.add(c21);
					cell11.addElement(p21);
				} catch (MalformedURLException e) {
					logger.error("Error processing ",e);
				} catch (IOException e) {
					logger.error("Error processing AOF data ",e);
				} 
			}else{
				cell11.addElement(new Paragraph("",f2));
			}

			table4.addCell(cell11);
			
			
			cell11 = new PdfPCell();
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setPaddingLeft(10);
			cell11.setPaddingTop(5);
			cell11.setFixedHeight(50);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell11.setBorderColor(BaseColor.BLACK);
			if(investForm.getCustomerSignature2()!="" && ( investForm.getHoldingMode().equals("JO") ||  investForm.getHoldingMode().equals("AS"))){
				Image img2;
				logger.info("Cusomer holdng mode requires to set 2nd applicant signature too.");
				try {
					Base64 decoder = new Base64();
					byte[] imageByte = decoder.decode(investForm.getCustomerSignature2().split(",")[1]);
					img2 = Image.getInstance(imageByte);
					//				img = Image.get
					img2.setAlt("Signature 2");
					Chunk c21 = new Chunk(img2, 1, 1);
					Paragraph p21 = new Paragraph();

					//p21.setPaddingTop(15);
					p21.add(c21);
					cell11.addElement(p21);
				} catch (MalformedURLException e) {
					logger.error("Error processing signature 2",e);
				} catch (IOException e) {
					logger.error("Error processing AOF data for signature 2",e);
				} 
			}else{
				cell11.addElement(new Paragraph("",f2));
			}
//			cell11 = new PdfPCell(new Paragraph("",f1));
			table4.addCell(cell11);

			cell11 = new PdfPCell(new Paragraph("",f1));
			table4.addCell(cell11);

			document.add(table4);

			// 33rd row
			table4 = new PdfPTable(3); // 3 columns.
			table4.setWidthPercentage(100); //Width 100%

			table4.setWidths(columnWidths4);

			cell11 = new PdfPCell(new Paragraph("1st applicant Signature :",f1));

			cell11.setBorderColor(BaseColor.BLACK);
			cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

			table4.addCell(cell11);
			cell11 = new PdfPCell(new Paragraph("2nd applicant Signature :",f1));
			table4.addCell(cell11);

			cell11 = new PdfPCell(new Paragraph("3rd applicant Signature :",f1));
			table4.addCell(cell11);

			document.add(table4);
			logger.info("AOF pdf generator complete for user- "+ investForm.getPan1());
			// ---------------------------------------------
		}catch(Exception e){
//			e.printStackTrace();
			logger.error("Exception generated while crating AOF file. Unable to process...",e);
			flag="FAIL";
		}finally {
			try {
				document.close();
				writer.close();
			}catch(Exception e) {
				logger.error("Error closing file after AOF write ..", e);
			}
			logger.info("Close AOF document after writing successful");
		}
		
		logger.info("Returning AOF generation status- "+ flag);
		aoffilestatus.setFilegenerationstatus(flag);
		
		try {
			Path filepath = Paths.get(aofbasepath+fileName);
			if(Files.exists(filepath)) {
				byte[] filearray = Files.readAllBytes(filepath);
				aoffilestatus.setAofpdf(filearray);
			}
		}catch(Exception e) {
			logger.info("Failed to read file",e);
		}
		return aoffilestatus;

	}	


	private static String getOccupationName(String occupationCode){
		String occName="NA";
		System.out.println(occupationCode);
		Map<String,String> occupationList = InvestFormConstants.occupationList;
		for (String name : occupationList.keySet())  
//            System.out.println("key: " + name); 
		
		occName = occupationList.get(occupationCode);
		System.out.println(occName);
		return occName;

	}
	
	/*private static String getStateName(String stateCode){
		String stateName="NA";
		
		Map<String,String> occupationList = InvestFormConstants.states;
		for (String name : occupationList.keySet())  
//            System.out.println("key: " + name); 
		
		stateName = occupationList.get(occupationCode);
		System.out.println(occName);
		return occName;

	}*/
	
	private static String getHoldingMode(String holdingMode){
		String holding="NA";
		logger.info(holdingMode);
		Map<String,String> holdingList = InvestFormConstants.holdingMode;
		
		/*
		 * for (String name : holdingList.keySet()) logger.info("getHoldingMode ey: " +
		 * name);
		 */
		
		holding = holdingList.get(holdingMode);
		System.out.println("getHoldingMode: "+ holding);
		return holding;

	}

/*
	public static void main(String[] args) {
		//String k = "<html><body> This is my Project </body></html>";


		try {
			FileInputStream s = new FileInputStream(new File("E:\\AOF\\\\htm1.txt"));
			BufferedReader br = new BufferedReader( new InputStreamReader(s));
			StringBuilder sb = new StringBuilder();
			String line;
			while(( line = br.readLine()) != null ) {
				sb.append( line );
				sb.append( '\n' );
			}
			br.close();
			k = sb.toString();
		}catch(Exception e) {

		}

		try {

			FileOutputStream file = new FileOutputStream(new File("D:\\DEBA\\PDFG\\Test.pdf"));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();
			InputStream is = new ByteArrayInputStream(k.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AddressDetails a =new AddressDetails();
		//		Nominee

		UserBankDetails b = new UserBankDetails();
		b.setAccountNumber("312123123123");
		b.setAccountCountry("INDIA");
		b.setAccountType("Saving Account");
		b.setBankAddress("asda adasdasd");
		b.setBankBranch("Kaikhali");
		b.setBankCity("kolkata");
		b.setIfscCode("ICIC23232");
		b.setBankName("State bank of INdia");


		MFNominationForm n = new MFNominationForm();
		n.setNomineeName("SUMANTA MAHANTY");
		n.setNomineeRelation("adasdasd");
		BseMFInvestForm m = new BseMFInvestForm();
		m.setPan1("cqops7539c");
		m.setMobile("9123123123");
		m.setEmail("asdasd@g.com");
		m.setPan2("3sdfsdfs");
		m.setApplicant2("madhuparna");
		m.setHoldingMode("SI");
		m.setOccupation("02");

		a.setAddress1("82");
		a.setAddress2("ss sarani");
		a.setAddress3("haiderpara");
		a.setCity("siliguri");
		a.setPinCode("765432");
		a.setCountry("INDIA");
		a.setState("WB");
		m.setAddressDetails(a);
		m.setBankDetails(b);
		m.setNominee(n);
		m.setInvName("asdas asdasd");
		aofGenerator(m,"232323", "E:/BITBUCKET REPOSITORY/freemi/src/main/webapp/resources/images/freemi.png","VERIFIED","E:/AOF/");

	}
	*/
	
	
	/*
	public static void main(String[] args) {
		
		PdfWriter writer = null;
		Document document = new Document(PageSize.A4);
		
		try {
			Path filepath = Paths.get("D:\\AOF\\GGGPM9876H.pdf");
			if(Files.exists(filepath)) {
				byte[] filearray = Files.readAllBytes(filepath);
//				DataSource dataSource = new ByteArrayDataSource(java.util.Base64.getDecoder().decode(filearray), "application/pdf");
				DataSource dataSource = new ByteArrayDataSource(filearray, "application/pdf");
				System.out.println("Done");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
	
}
