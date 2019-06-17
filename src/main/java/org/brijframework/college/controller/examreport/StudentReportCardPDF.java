package org.brijframework.college.controller.examreport;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.MonthDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@SuppressWarnings({ "unchecked", "unused" })
public class StudentReportCardPDF extends AbstractPdfView {
	@Override
	protected Document newDocument() {
		return new Document(PageSize.A4.rotate());
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String imagePath = request.getSession().getServletContext()
				.getRealPath("/");
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		StudentDTO student = (StudentDTO) model.get("Student");
		// ------------------------- Table Header-------------------------
		PdfPTable tables1 = new PdfPTable(1);
		tables1.setWidthPercentage(100);
		PdfPTable tables2 = new PdfPTable(1);
		tables2.setWidthPercentage(100);
		PdfPTable tables = new PdfPTable(3);
		float[] columnWidthss = {7.9f,0.1f,2 };
		tables.setWidths(columnWidthss);
		tables.setWidthPercentage(100);
		PdfPTable table3 = new PdfPTable(3);
		float[] columnWidthss1 = {4.5f,1,4.5f };
		table3.setWidths(columnWidthss1);
	
		
		PdfPTable headerTable = new PdfPTable(2);
		headerTable.setSpacingBefore(30);
		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
		font.setColor(Color.black);
		Paragraph paragraph = new Paragraph();
		PdfPCell cell1 = new PdfPCell();
		paragraph = new Paragraph("Half Yearly Exam", font);
		paragraph.setIndentationLeft(50);
		cell1.addElement(paragraph);
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.setExtraParagraphSpace(10);
		headerTable.addCell(cell1);

		cell1 = new PdfPCell();
		paragraph = new Paragraph("Annually Exam", font);
		paragraph.setIndentationLeft(50);
		cell1.addElement(paragraph);
		cell1.setBorder(Rectangle.NO_BORDER);
		headerTable.addCell(cell1);
	
		//imagePath += "/img/agschool.jpg";
		imagePath += "/img/nida.jpg";
		//imagePath += "/img/nida.jpg";
		PdfPTable table2 = new PdfPTable(2);
		float[] columnWidths4 = {1f, 6};
		table2.setWidths(columnWidths4);
		Image img = Image.getInstance(imagePath);
		img.setAlignment(Element.ALIGN_CENTER);
		cell1 = new PdfPCell();
		cell1.addElement(new Chunk(img, 15, -5));
		cell1.setBorder(Rectangle.NO_BORDER);
		table2.addCell(cell1);
		paragraph = new Paragraph();
		 font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
		font.setColor(Color.black);
		paragraph.add(new Chunk("Nida Inter College , Massuri, Ghaziabad", font));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		cell1.addElement(paragraph);
		 Chunk chunk=new Chunk("Report Card");
         chunk.setUnderline(+1f,-2f);
		paragraph = new Paragraph();
		 paragraph.add(chunk);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell1.addElement(paragraph);
		table2.addCell(cell1);
		// -------------------------Report Table-------------------------------
		float[] columnWidths = new float[] { 1f, 5f, 3f, 3f, 3f, 3f, 3f, 3f, 3f };
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100);

		font.setColor(Color.black);

		// define table header cell
		// cell 1
		PdfPCell cell = new PdfPCell();
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 9);
		cell.addElement(new Paragraph("S.No", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();
		font = FontFactory.getFont(FontFactory.COURIER_BOLD);
		cell.addElement(new Paragraph("SUBJECT", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph("UT+Oral+  Project", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Exam", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Total", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		font = FontFactory.getFont(FontFactory.COURIER_BOLD);
		cell.addElement(new Paragraph("UT+Oral+  Project", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Exam", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Total", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Grand Total", font));
		table.addCell(cell);
		// end heading

		// table body data
		List<SubjectDTO> subjectDTOs = (List<SubjectDTO>) map
				.get("SubjectDTOs");
		font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		int i = 1;
		// add subject data
		for (SubjectDTO subjectDTO : subjectDTOs) {
			// cell 1
			cell = new PdfPCell();

			cell.addElement(new Paragraph("" + i + "", font));
			table.addCell(cell);
			// cell 2
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getSubjectName(), font));
			table.addCell(cell);
			// cell 3
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getHalfYearlyTU(), font));
			table.addCell(cell);
			// cell 4
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getHalfYearlyExam(), font));
			table.addCell(cell);
			// cell 5
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getHalfYearlyTotal(), font));
			table.addCell(cell);
			// cell 6
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getAnnualYearlyTU(), font));
			table.addCell(cell);
			// cell 7
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getAnnualYearlyExam(),
					font));
			table.addCell(cell);
			// cell 8
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getAnnualYearlyTotal(),
					font));
			table.addCell(cell);
			// cell 9
			cell = new PdfPCell();

			cell.addElement(new Paragraph(subjectDTO.getGrandTotal(), font));
			table.addCell(cell);
			i++;
		}
		// end subject data
		// other need data
		// total row
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("=>", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 9);
		cell.addElement(new Paragraph("Total", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();
		font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		String halfYearlyTotal = (String) map.get("halfYearlyTotal");
		cell.addElement(new Paragraph(halfYearlyTotal, font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		String annuallyTotal = (String) map.get("annuallyTotal");
		cell.addElement(new Paragraph(annuallyTotal, font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		String grandTotal = (String) map.get("grandTotal");
		cell.addElement(new Paragraph(grandTotal, font));
		table.addCell(cell);
		// end total row
		// % of marks obtained
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("% of marks obtained", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		String halfYearGainPercenatge = (String) map
				.get("halfYearGainPercenatge");
		cell.addElement(new Paragraph(halfYearGainPercenatge, font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		String annuallyGainPercentage = (String) map
				.get("annuallyGainPercentage");
		cell.addElement(new Paragraph(annuallyGainPercentage, font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		String grandGainPercentage = (String) map.get("grandGainPercentage");
		cell.addElement(new Paragraph(grandGainPercentage, font));
		table.addCell(cell);
		// %end of marks obtained
		// Highest % in class
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Highest % in class", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		String halfYearlyMaxGainPercentage = (String) map
				.get("halfYearlyMaxGainPercentage");
		cell.addElement(new Paragraph(halfYearlyMaxGainPercentage, font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		String annuallyMaxGainPercentage = (String) map
				.get("annuallyMaxGainPercentage");
		cell.addElement(new Paragraph(annuallyMaxGainPercentage, font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		String grandMaxGainPercentage = (String) map
				.get("grandMaxGainPercentage");
		cell.addElement(new Paragraph(grandMaxGainPercentage, font));
		table.addCell(cell);
		// end Highest % in class
		// rank
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Rank", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// end rank
		// attendance in %
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Attendance in %", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// end attendance in %
		// uniform & performance
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Uniform & Performance", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// end uniform & performance
		// Sign. of class teacher
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Sign. of Class Teacher", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// end Sign of class teacher
		// Sign. of principal
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Sign. of Principal", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// end of Sign. of principal
		// Sign. of parents
		// cell 1
		cell = new PdfPCell();

		cell.addElement(new Paragraph("*", font));
		table.addCell(cell);
		// cell 2
		cell = new PdfPCell();

		cell.addElement(new Paragraph("Sign. of Parents", font));
		table.addCell(cell);
		// cell 3
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 4
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 5
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 6
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 7
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 8
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// cell 9
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" ", font));
		table.addCell(cell);
		// end Sign of parents
		// end need data
		table.addCell(cell);
		cell = new PdfPCell();
		paragraph = new Paragraph();
		paragraph.add(new Chunk("REMARKS\nHALF YEARLY", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 12)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		tables2.addCell(cell);
		cell = new PdfPCell();

		cell.addElement(new Paragraph(" \n\n\n", font));
		tables2.addCell(cell);
		cell = new PdfPCell();
		paragraph = new Paragraph();
		paragraph.add(new Chunk("ANNUAL EXAM", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 12)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		tables2.addCell(cell);
		cell = new PdfPCell();

		paragraph = new Paragraph();
		paragraph.add(new Chunk("\n\n\n ", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 12)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		tables2.addCell(cell);
		cell = new PdfPCell();

		paragraph = new Paragraph();
		paragraph.add(new Chunk("FINAL RESULT", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 12)));
		paragraph.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(paragraph);
		paragraph = new Paragraph();
		paragraph.add(new Chunk("\n \nPassed in........................\n\n Promoted to.......................\n\nDetained in.......................\n\nNew Session Commences from.......................\n\n....................Dated..............\n", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 12)));
		
		cell.addElement(paragraph);
		tables2.addCell(cell);
		/*Paragraph paragraph9 = new Paragraph();
		paragraph9.add(new Chunk("Report Card", FontFactory.getFont(
				FontFactory.TIMES_BOLD, 15)));
		paragraph9.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph9);*/
		font = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);
	
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
		/*cell = new PdfPCell();
		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.addElement(new Paragraph("", font));
		table3.addCell(cell);
		*/
		cell = new PdfPCell();
		cell.addElement(headerTable);
		tables1.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(table);
		tables1.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(tables1);
		tables.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(new Paragraph(new Chunk("    ")));
		tables.addCell(cell);
		cell = new PdfPCell();
		cell.addElement(tables2);
		tables.addCell(cell);
		document.add(table2);
		document.add(table3);
		document.add(tables);

	}

}
