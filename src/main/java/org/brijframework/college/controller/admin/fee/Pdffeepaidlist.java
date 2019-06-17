package org.brijframework.college.controller.admin.fee;


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

	public class Pdffeepaidlist 
		extends AbstractPdfView {
			@SuppressWarnings("unchecked")
			@Override
			protected void buildPdfDocument(Map model, Document document,
					PdfWriter writer, HttpServletRequest request,
					HttpServletResponse response) throws Exception {
			
				String classes="";
				String section="";
				String sessionName="";
				String monthName="";
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
				String today = df.format(new Date());
				List<StudentDTO> list = (List<StudentDTO>) model.get("list");
				for(StudentDTO dto:list)
				{
					 classes=dto.getClassName();
					 section=dto.getSectionName();
					 sessionName=dto.getSessionDuration();
					 monthName=dto.getMonthName();
					
				}
				PdfPTable table = new PdfPTable(4);
				table.setSpacingBefore(30);
				Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				font.setColor(Color.black);

				// define table header cell
				PdfPCell cell = new PdfPCell();
				cell.setBackgroundColor(Color.lightGray);
				cell.setPadding(5);

				
				cell.setPhrase(new Phrase("Student Name", font));
				table.addCell(cell);
				// write table header
				cell.setPhrase(new Phrase("Balance Amount", font));
				table.addCell(cell);

				table.setWidthPercentage(100.0f);
				
				cell.setPhrase(new Phrase("Mobile", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Address", font));
				table.addCell(cell);
				
				for(StudentDTO dto:list)
					
				{   
					table.addCell(dto.getFirstName()+" "+dto.getMiddleName()+" "+dto.getLastName());
					table.addCell(dto.getBalanceAmount());
					table.addCell(dto.getMobile());
					table.addCell(dto.getAddressLine1()+" "+dto.getCityName());
				}
				document.add(new Paragraph("List of Students who have Paid fees of class " + classes
						+ "  Section " + section + "  of session  " + sessionName + "  for month  "
						+ monthName +  "\nDate:" + today));
				document.add(table);
				
			}
	}
			





