package org.brijframework.college.controller.admin.student;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentTransferDTO;
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

public class TransferCertificate extends AbstractPdfView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*StudentDTO student = (StudentDTO) model.get("student");
		StudentTransferDTO transfer = (StudentTransferDTO) model
				.get("Transfer");
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		//imagePath += "/img/agschool.jpg";
		imagePath += "/img/scan.jpg";
		PdfPTable table2 = new PdfPTable(2);
		float[] columnWidths = {1f, 6};
		table2.setWidths(columnWidths);
		
	
		PdfPCell cell = new PdfPCell();
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_CENTER);

		cell.addElement(new Chunk(img, 15, -5));
		cell.setBorder(Rectangle.NO_BORDER);
		table2.addCell(cell);
		Paragraph paragraph;
		paragraph = new Paragraph();
		Font fonts = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
		fonts.setColor(Color.black);
		paragraph.add(new Chunk("Y.M.B. Public School\nOpp.Shiv Mandir, G.T. Road, Lal Kuan, Ghaziabad.", fonts));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(paragraph);
		 Chunk chunk=new Chunk("Transfer Certificate");
         chunk.setUnderline(+1f,-2f);
		paragraph = new Paragraph();
		 paragraph.add(chunk);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		table2.addCell(cell);
		PdfPTable table = new PdfPTable(2);
		table.setSpacingBefore(30);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.black);
	cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Admission No.",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(transfer.getStudentId(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Student Name",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(student.getFirstName() + " " + student.getMiddleName()
				+ " " + student.getLastName(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Father's Name",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(student.getFatherName(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Date of Admission",font));
		table.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(student.getAdmissionDate(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Session",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(student.getSessionDuration(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Class",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(student.getClassName(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Date of Transfer",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(transfer.getDateOfTransfer(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Reason of leaving",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(transfer.getReason(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Moral Character",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(transfer.getMoral(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Behavior",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(transfer.getBehavior(),font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("Progress",font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph(transfer.getProgress(),font));
		table.addCell(cell);
		
        document.add(table2);
		
		document.add(table);*/
		document.add(new Paragraph("\n\n\n               Sign of Principal                  School Seal                 Sign of Student"));

	}

}
