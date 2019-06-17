package org.brijframework.college.controller.admin.student;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.StudentAdmissionDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class NewPDFAdmission extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentAdmissionDTO studentDTO = (StudentAdmissionDTO) model
				.get("studentDTO");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		imagePath += "/img/nida.jpg";
		//imagePath += "/img/nida.jpg";
		String imagePath1 = request.getSession().getServletContext()
				.getRealPath("/");
		imagePath1 += "/img/studentImages/" + studentDTO.getImageName();
		document.open();
		PdfPTable tables1 = new PdfPTable(1);
		tables1.setWidthPercentage(100);
		PdfPTable tables2 = new PdfPTable(1);
		tables2.setWidthPercentage(100);
		PdfPTable tables = new PdfPTable(3);
		float[] columnWidthss = { 4.5f, 1, 4.5f };
		tables.setWidths(columnWidthss);
		tables.setWidthPercentage(100);
		PdfPTable table11 = new PdfPTable(1);
		table11.setWidthPercentage(100);
		Paragraph paragraph;
		PdfPCell pCell;
		paragraph = new Paragraph();

		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		font.setColor(Color.black);
		Font font1 = FontFactory.getFont(FontFactory.TIMES, 10);
		font.setColor(Color.black);
		paragraph.add(new Chunk("Nida Inter College", font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table11.addCell(pCell);
		PdfPTable table12 = new PdfPTable(3);
		float[] columnWidth12 = { 3, 4, 3 };
		table12.setWidths(columnWidth12);
		table12.setWidthPercentage(100);
		pCell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_RIGHT);

		pCell.addElement(new Chunk(img, 15, -5));
		pCell.setBorder(Rectangle.NO_BORDER);
		table12.addCell(pCell);
	
		paragraph = new Paragraph();
		paragraph.add(new Chunk("APPLICATION FORM\n(FOR ADMISSION)", font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table12.addCell(pCell);
		paragraph = new Paragraph();

		paragraph.add(new Chunk(""));

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table12.addCell(pCell);
		PdfPTable table31 = new PdfPTable(8);
		float[] columnWidthss31 = { 2, 2, 0.2f, 2, 2.5f, 0.1f, 2, 2.5f };
		table31.setWidths(columnWidthss31);
		table31.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("S No.", font));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(String.valueOf(studentDTO.getsNo()),
				font1));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Class :", font));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getClassName()+"-"+studentDTO.getSectionName(), font1));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Regn. No. :", font));
		table31.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getRegNo(), font1));
		table31.addCell(pCell);
		PdfPTable table14 = new PdfPTable(2);
		float[] columnWidthss14 = { 8, 2 };
		table14.setWidthPercentage(100);
		table14.setWidths(columnWidthss14);
		PdfPTable table17 = new PdfPTable(1);
		table17.setWidthPercentage(100);
		PdfPTable table15 = new PdfPTable(4);
		float[] columnWidthss15 = { 2, 3, 3, 2 };
		table15.setWidths(columnWidthss15);
		table15.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Name. :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getStudentName(), font1));
		table15.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Gender(Male/Female) :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getGender(), font1));
		table15.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Mother Tongue. :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherTounge(), font1));
		table15.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Nationality :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getCountryName(), font1));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("State :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getStateName(), font1));
		table15.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("City :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getCityName(), font1));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Address :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getResAddress(), font1));
		table15.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Mobile :", font));
		table15.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getPhoneNo(), font1));
		table15.addCell(pCell);
		
		PdfPTable table16 = new PdfPTable(2);
		float[] columnWidthss16 = { 4, 6 };
		table16.setWidths(columnWidthss16);
		table16.setWidthPercentage(100);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Date Of Birth :", font));
		table16.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getDateOfBirth(), font1));
		table16.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Any Physical/Mental Disability :", font));
		table16.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getPhysicalDisability(),
				font1));
		table16.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table15);
		table17.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table16);
		table17.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table17);
		table14.addCell(pCell);

		pCell = new PdfPCell();
		/* Image img1 = Image.getInstance(imagePath1); */

		pCell.addElement(new Chunk("Recent\nPhotograph\nOf Student"));
		// pCell.setBorder(Rectangle.NO_BORDER);
		table14.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Chunk("\n"));
		table14.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Chunk("\n"));
		table14.addCell(pCell);
		PdfPTable table18 = new PdfPTable(5);
		float[] columnWidthss18 = { 3, 4, 0.2f, 3, 4 };
		table18.setWidths(columnWidthss18);
		table18.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("FATHER :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("MOTHER :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Name :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFatherName(), font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Name :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherName(), font1));
		table18.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Education :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFatherEducation(), font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Education :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherEducation(), font1));
		table18.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Occupation :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFatherOccupation(), font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Occupation :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherOccupation(), font1));
		table18.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Signature.", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("__________", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Signature", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("____________", font));
		table18.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Annual Income. :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFatherAnnualIncome(),
				font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Annual Income :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherAnnualIncome(),
				font1));
		table18.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Office Address :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFatherOfficeAddress(),
				font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Office Address :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherOfficeAddress(),
				font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Ph. :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFatherPhoneNo(), font1));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Ph. :", font));
		table18.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getMotherPhoneNo(), font1));
		table18.addCell(pCell);

		PdfPTable table19 = new PdfPTable(2);
		float[] columnWidthss19 = {3,5};
		table19.setWidths(columnWidthss19);
		table19.setWidthPercentage(100);
		/*pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Res. Address.", font));
		table19.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getResAddress(), font1));
		table19.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Ph..", font));
		table19.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getPhoneNo(), font1));
		table19.addCell(pCell);*/
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table19.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("BROTHERS & SISTERS :", font));
		table19.addCell(pCell);

		
		PdfPTable table21 = new PdfPTable(7);
		float[] columnWidthss21 = { 3, 0.2f, 3, 0.2f, 3, 0.2f, 3 };
		table21.setWidths(columnWidthss21);
		table21.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Name ", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Age ", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Class ", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("School/College ", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getBrotherOrSisterName1(),
				font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getBrotherOrSisterAge1(),
				font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getBrotherOrSisterClass1(),
				font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO
				.getBrotherOrSisterSchoolOrCollege1(), font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getBrotherOrSisterName2(),
				font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getBrotherOrSisterAge2(),
				font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getBrotherOrSisterClass2(),
				font1));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table21.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO
				.getBrotherOrSisterSchoolOrCollege2(), font1));
		table21.addCell(pCell);

		PdfPTable table41 = new PdfPTable(2);
		float[] columnWidthss41 = {3,7};
		table41.setWidths(columnWidthss41);
		table41.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("\nPREVIOUS SCHOOL DETAILS", font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("School Name:", font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getSchoolName(), font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("School Address :", font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getSchoolAddress(), font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("School Phone :", font));
		table41.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getSchoolPhone(), font));
		table41.addCell(pCell);
		
		PdfPTable table22 = new PdfPTable(3);
		float[] columnWidthss22 = { 3, 3, 4 };
		table22.setWidths(columnWidthss22);
		table22.setWidthPercentage(100);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("UNDERTAKING", font));
		table22.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table22.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("", font));
		table22.addCell(pCell);

		PdfPTable table23 = new PdfPTable(1);
		table23.setWidthPercentage(100);
		paragraph = new Paragraph();
		paragraph
				.add(new Chunk(
						"We / I hereby declare that the information given above is correct to the best of our / my \n knowledge and that nothing has been concealed there from.If any information is found to be\n",
						font1));
		paragraph
				.add(new Chunk(
						"incorrect at any stage, we / I will unconditionally accept whatever action the school takes.\n\n",
						font1));
		paragraph
				.add(new Chunk(
						"If selected for admission.We / I undertake to ensure that our / my ward and me / I abide by all \n the rules and regulations of the school as down from time to time and as given the\n",
						font1));
		paragraph
				.add(new Chunk(
						"SCHOOL DIARY and to pay all school dues of our / my ward in time.We / I also undertake full responsibility of good conduct of our / my ward in the school.\n",
						font1));
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table23.addCell(pCell);

		PdfPTable table24 = new PdfPTable(3);
		float[] columnWidthss24 = { 3, 3, 4 };
		table24.setWidths(columnWidthss24);
		table24.setWidthPercentage(100);

		// chunk.setUnderline(+1f,-2f);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.add(new Chunk("____________"));
		paragraph1.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph1);
		table24.addCell(pCell);

		// chunk.setUnderline(+1f,-2f);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		paragraph1 = new Paragraph();
		paragraph1.add(new Chunk("______________"));
		paragraph1.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph1);
		table24.addCell(pCell);

		// chunk.setUnderline(+1f,-2f);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		paragraph1 = new Paragraph();
		paragraph1.add(new Chunk("____________"));
		paragraph1.setAlignment(Element.ALIGN_CENTER);
		pCell.addElement(paragraph1);
		table24.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Signature of Father", font1));
		table24.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Signature of Mother", font1));
		table24.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Date", font1));
		table24.addCell(pCell);

		PdfPTable table25 = new PdfPTable(1);
		table25.setWidthPercentage(100);
		paragraph = new Paragraph();
		paragraph
				.add(new Chunk(
						"\nGENERAL INSTRUCTIONS:\n Photocopy of the Birth Certificate/ Report Card / Transfer Certificate(TC) should be submitted\n "
								+ "together with the Application form.\n 3 stamp sized recent photographs of the child.\n Registration / Submission of the form does not guarantee admission.\n",
						font1));
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table25.addCell(pCell);

		PdfPTable table26 = new PdfPTable(2);
		float[] columnWidthss26 = { 3, 5 };
		table26.setWidths(columnWidthss26);
		table26.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("\nFOR OFFICE USE", font));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(" ", font));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(
				"To appear for written test/interview on ", font1));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getWrittenOrIntervieDate(),
				font));
		table26.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("ADMISSION TEST REPORT", font));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(" ", font));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Admit in Class", font1));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getAdmitInClass(), font1));
		table26.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Director/Principal", font1));
		table26.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getPrincipalOrDirectorName(),
				font1));
		table26.addCell(pCell);

		PdfPTable table27 = new PdfPTable(8);
		float[] columnWidthss27 = { 2.5f, 2, 2, 2, 2, 2, 2, 2 };
		table27.setWidths(columnWidthss27);
		table27.setWidthPercentage(100);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Admission No.", font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getAdmissionNo(), font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Date.", font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getAdmissionDate(), font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Fee Receipt No..", font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFeeReceiptNo(), font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph("Date", font1));
		table27.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(new Paragraph(studentDTO.getFeeReceiptDate(), font1));
		table27.addCell(pCell);

		PdfPTable table28 = new PdfPTable(1);
		table28.setWidthPercentage(100);
		paragraph = new Paragraph();
		paragraph.add(new Chunk("\nOffice Asst. Sign_________________________",
				font1));

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table28.addCell(pCell);
		paragraph = new Paragraph();
		paragraph
				.add(new Chunk(
						"\n\nNida Inter College , Massuri, Ghaziabad\n\n"
								,
						font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table28.addCell(pCell);

		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table11);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table12);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table31);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table14);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table18);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table41);
		tables1.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table19);
		tables1.addCell(pCell);
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table21);
		tables1.addCell(pCell);
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table22);
		tables2.addCell(pCell);
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table23);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table24);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table25);
		tables2.addCell(pCell);
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table26);
		tables2.addCell(pCell);
		/*pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table27);
		tables2.addCell(pCell);*/
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(table28);
		tables2.addCell(pCell);

		document.add(tables1);
		document.newPage();
		document.add(tables2);
		document.close();

	}

}
