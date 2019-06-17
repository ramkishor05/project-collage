package org.brijframework.college.controller.admin.student;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.StudentDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class SectionWisePdfReport extends AbstractPdfView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		String classes="";
		String sections="";
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		String today = df.format(new Date());
		List<StudentDTO> list = (List<StudentDTO>) model.get("sectionWiseList");
		for(StudentDTO dto:list)
		{
			 classes=dto.getClassName();
			sections=dto.getSectionName();
			
		}
		PdfPTable table = new PdfPTable(5);
		table.setSpacingBefore(30);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.black);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.lightGray);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("Roll No.", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Student Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Father Name", font));
		table.addCell(cell);
		table.setWidthPercentage(100.0f);
		
		cell.setPhrase(new Phrase("Mobile", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);
		
		for(StudentDTO dto:list)
			
		{   
			table.addCell(dto.getRollno());
			table.addCell(dto.getFirstName()+" "+dto.getMiddleName()+" "+dto.getLastName());
			table.addCell(dto.getFatherName());
			table.addCell(dto.getMobile());
			table.addCell(dto.getAddressLine1());
		}
		document.add(new Paragraph("Student List of class " + classes+ "  and Section "+sections
				+  "\nDate:" + today));
		document.add(table);

}
}
