package org.brijframework.college.controller.examreport;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.MonthDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.SubjectDTO;
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

public class PDFMonthlyExam extends AbstractPdfView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = (Map<String, Object>) model.get("map");
		StudentDTO student = (StudentDTO) model.get("student");
		List<MonthDTO> monthDTOs=(List<MonthDTO>) map.get("MonthDTO");
		List<SubjectDTO> subjectDTOs=(List<SubjectDTO>) map.get("SubjectDTO");
		int monthSize=subjectDTOs.size();
		PdfPTable table = new PdfPTable(1+monthSize);
		PdfPTable table3 = new PdfPTable(3);
		float[] columnWidthss1 = {4.5f,1,4.5f };
		table3.setWidths(columnWidthss1);
	/*	table3.setWidthPercentage(100);*/
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.black);

		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		//imagePath += "/img/agschool.jpg";
		imagePath += "/img/nida.jpg";
		//imagePath += "/img/nida.jpg";
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
		 font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
		font.setColor(Color.black);
		paragraph.add(new Chunk("Nida Inter College , Massuri, Ghaziabad", font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(paragraph);
		 Chunk chunk=new Chunk("Monthly Test Report");
         chunk.setUnderline(+1f,-2f);
		paragraph = new Paragraph();
		 paragraph.add(chunk);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		table2.addCell(cell);
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
		// define table header cell
		cell = new PdfPCell();
		cell.setBackgroundColor(Color.lightGray);
		cell.addElement(new Paragraph("Months", font));
		table.addCell(cell);
		for (SubjectDTO subject : subjectDTOs) {
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subject.getSubjectName(), font));
			table.addCell(cell);
			
		}
		for (MonthDTO month : monthDTOs) {
			cell = new PdfPCell();

			cell.addElement(new Paragraph(month.getMonthName(), font));
			table.addCell(cell);
			for (SubjectDTO subject : subjectDTOs) {
			
				for (CommonDTO common : subject.getCommonDTOs()) {
					cell = new PdfPCell();
					if(common.getGainMarks()=="")
						cell.addElement(new Paragraph("-", font));
					else
					cell.addElement(new Paragraph(common.getGainMarks()+"/"+common.getMaxMarks(), font));
					table.addCell(cell);
				}
			}
			
		}
		/*Paragraph paragraph9 = new Paragraph();
		paragraph9.add(new Chunk("Monthly Test Report", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 15)));
		paragraph9.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph9);*/
	
	
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Name        "
				+ student.getFirstName() + " "
				+ student.getMiddleName() + " " + student.getLastName(), font));
		table3.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Roll no.       "+ student.getRollno(), font));
		table3.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Class         " + student.getClassName() + "  "
				+ student.getSectionName()+"\n" , font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("\n", font));
		table3.addCell(cell);
		
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);

		cell.addElement(new Paragraph("Father's name          "
				+ student.getFatherName()+"\n\n", font));
		table3.addCell(cell);
		document.add(table2);
		document.add(table3);
		document.add(table);
	}

}

