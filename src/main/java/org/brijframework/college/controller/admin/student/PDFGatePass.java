package org.brijframework.college.controller.admin.student;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.GatePassDTO;
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

public class PDFGatePass extends AbstractPdfView {


	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudentDTO student = (StudentDTO) model.get("Student");
		GatePassDTO gate = (GatePassDTO) model.get("GatePassDTO");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		//imagePath += "/img/agschool.jpg";
		imagePath += "/img/nida.jpg";
		//imagePath += "/img/nida.jpg";
		PdfPTable table2 = new PdfPTable(2);
		float[] columnWidths = { 1f, 6 };
		table2.setWidths(columnWidths);
		table2.setWidthPercentage(100);
		PdfPTable table3 = new PdfPTable(3);
		float[] columnWidthss1 = { 5.5f, 3, 5f };
		table3.setWidths(columnWidthss1);
		PdfPTable tables = new PdfPTable(1);
		PdfPTable tables1 = new PdfPTable(1);
		PdfPCell cell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_LEFT);

		cell.addElement(new Chunk(img, 15, -5));
		cell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(cell);
		Paragraph paragraph;
		paragraph = new Paragraph();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
		font.setColor(Color.black);
		paragraph
				.add(new Chunk(
						"Nida Inter College , Massuri, Ghaziabad",
						font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(paragraph);
		Chunk chunk = new Chunk("Gate Pass");
		chunk.setUnderline(+1f, -2f);
		paragraph = new Paragraph();
		paragraph.add(chunk);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		table2.addCell(cell);
		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
		font.setColor(Color.black);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Gate Pass No.     "
				+ gate.getGatePassId() + "", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
         cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
         cell.addElement(new Paragraph("Student Id       "
				+ student.getStudentId() + "", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Date        " + gate.getLeavingDate()
				+ "", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
          cell.addElement(new Paragraph("Time       " + gate.getLeavingTime()
				+ "", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Name        " + student.getFirstName()
				+ " " + student.getMiddleName() + " " + student.getLastName(),
				font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Roll no.      " + student.getRollno(),
				font));
		table3.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Class        " + student.getClassName()
				+ "  " + student.getSectionName() + "\n", font));
		table3.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);

		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("  " , font));
		table3.addCell(cell);

		Paragraph paragraph1 = new Paragraph();
		paragraph1.add(new Chunk("\nAccompanied By:-" + gate.getLeavingWith(),
				FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9)));
		

		Paragraph paragraph2 = new Paragraph();
		paragraph2.add(new Chunk("Reason:-" + gate.getReason()+"\n\n", FontFactory
				.getFont(FontFactory.HELVETICA_BOLD, 9)));
		

		Paragraph paragraph3 = new Paragraph();
		paragraph3.add(new Chunk(
				" Principal           Class Teacher         Accompanied By",
				FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9)));
		paragraph3.setAlignment(Element.ALIGN_CENTER);
		cell = new PdfPCell();
		cell.addElement(table2);
		cell.setBorder(Rectangle.NO_BORDER);
		tables.addCell(cell);

		cell = new PdfPCell();
		cell.addElement(table3);
		cell.setBorder(Rectangle.NO_BORDER);
		tables.addCell(cell);

		cell = new PdfPCell();
		cell.addElement(paragraph1);
		cell.setBorder(Rectangle.NO_BORDER);
		tables.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(paragraph2);
		cell.setBorder(Rectangle.NO_BORDER);
		tables.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(paragraph3);
		cell.setBorder(Rectangle.NO_BORDER);
		tables.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(tables);
		tables1.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(new Paragraph("\n\n"));
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		tables1.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(tables);
		
		tables1.addCell(cell);
		
		document.add(tables1);
		document.addTitle("Gate Pass");
	}
}