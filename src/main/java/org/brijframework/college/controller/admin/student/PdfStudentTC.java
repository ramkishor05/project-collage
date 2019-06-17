package org.brijframework.college.controller.admin.student;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.StudentDTO;
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

public class PdfStudentTC extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		StudentDTO dto = (StudentDTO) model.get("StudentDTO");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		imagePath += "img/gargi logo.jpg";
		document.open();
		document.add(new Chunk(""));
		PdfPTable table1 = new PdfPTable(3);
	float[] columnWidthss = { 2,6,2};
		table1.setWidths(columnWidthss);
		table1.setWidthPercentage(100);
		PdfPTable table2 = new PdfPTable(5);
		table2.setWidthPercentage(100);
		PdfPCell pCell;
	  pCell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_LEFT);

		pCell.addElement(new Chunk(img, 15, -5));
		pCell.setBorder(Rectangle.NO_BORDER);
		table1.addCell(pCell);
		Paragraph paragraph;
		paragraph = new Paragraph();

	Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
		font.setColor(Color.black);
		Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		font1.setColor(Color.black);
		paragraph.add(new Chunk("Gargi Public School\n", font));
		paragraph.add(new Chunk("(Gargi Public School New Delhi)", font1));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		pCell = new PdfPCell();
		pCell.setBorder(Rectangle.NO_BORDER);
		pCell.addElement(paragraph);
		table1.addCell(pCell);
	pCell = new PdfPCell();
		

			pCell.addElement(new Chunk(img, 15, -5));
			pCell.setBorder(Rectangle.NO_BORDER);
			table1.addCell(pCell);
			table1.completeRow();
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("    ")));

			table1.addCell(pCell);
			paragraph=new Paragraph();
			paragraph.add(new Chunk("Transfer Certificate Application\n", font));
			paragraph.add(new Chunk("(Please Use Capital Letters)", font1));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(paragraph);
			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("    ")));

			table1.addCell(pCell);
			paragraph=new Paragraph();
			paragraph.add(new Chunk("The Principal\n", font));
			paragraph.add(new Chunk("Gargi Public School\nGaziabad Uttar Pradesh\nPh:+38(123)456-7890", font1));
			paragraph.setAlignment(Element.ALIGN_LEFT);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(paragraph);
			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("    ")));

			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("Date:"+df.format(new Date()),font)));

			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("Sir/Madam,",font)));

			table1.addCell(pCell);
			
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("    ")));

			table1.addCell(pCell);
			pCell = new PdfPCell();
			pCell.setBorder(Rectangle.NO_BORDER);
			pCell.addElement(new Paragraph(new Chunk("    ")));

			table1.addCell(pCell);
			table2.addCell(new Paragraph("Dept.",font));
			table2.addCell(new Paragraph("Principal",font));
			table2.addCell(new Paragraph("Accounts",font));
			table2.addCell(new Paragraph("Library",font));
			table2.addCell(new Paragraph("Nurse",font));
			table2.addCell(new Paragraph("Sign",font1));
			table2.addCell(new Paragraph("",font1));
			table2.addCell(new Paragraph("",font1));
			table2.addCell(new Paragraph("",font1));
			table2.addCell(new Paragraph("",font1));
			document.add(table1);
			Paragraph paragraph9 = new Paragraph();
			paragraph9.add(new Chunk("I,.........."+dto.getFatherName()+"...........        Parent Of,..........."+dto.getFullName()+"..................................................\n", font1));
			paragraph9.add(new Chunk("(Student name as in the passport) studying in Gargi Public School in Year/Class\n", font1));
			paragraph9.add(new Chunk(""+dto.getSessionDuration()+"/"+dto.getClassName()+".....................     div ...........   "+dto.getSectionName()+" .........................................   C R No.\n", font1));
			paragraph9.add(new Chunk("...."+dto.getRollno()+".................... request you to issue a TransferCertificate with effect from\n", font1));
			paragraph9.add(new Chunk("....................................................to join another School(Name of School)\n.........................................................................................................\n", font1));
			paragraph9.add(new Chunk("Contact No:-Residence.........................................Mobile..................................................\n", font1));
			paragraph9.add(new Chunk("Note :- All the due as on today must be cleared and passport copy of student must be attached.\n", font1));
			paragraph9.add(new Chunk("\n", font1));
			paragraph9.add(new Chunk("Date........"+dto.getCurrentDate()+".........................Signature of Parent...................................................................\n", font1));
			paragraph9.add(new Chunk("Teacher's Signature..............................................................................Last Date of Attendance\n", font1));
			paragraph9.add(new Chunk("..................."+dto.getCurrentDate()+"..........................................................\n", font1));
			paragraph9.add(new Chunk("\n", font1));
			paragraph9.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph9);
		    document.add(table2);
			Paragraph paragraph5 = new Paragraph();
			paragraph5.add(new Chunk("\n", font1));
			paragraph5.add(new Chunk("T.C No.....................................Date..........................."+dto.getCurrentDate()+"......................\n", font1));
			paragraph5.add(new Chunk("Receipt By:Name:-..............................................Signature......................................................\n", font1));
			/*paragraph5.setAlignment(Element.ALIGN_CENTER);*/
			document.add(paragraph5);
			document.addTitle("TC");
			
		
		  
			document.close();

}
}