package org.brijframework.college.controller.admin.student;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.StudentAttendanceDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class StudentAttendanceReportAsPdf extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int totalNoOfDays = 0;
		int noOfDaysPresent = 0;
		int noOfDaysAbsent = 0;
		int late=0;
		int leave=0;
		double percentageOfAttendance = 0.0;
		String month = "";
		String classes = "";
		String section = "";

		@SuppressWarnings("unchecked")
		List<StudentDTO> studentDTOs = (List<StudentDTO>) model.get("list");

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN,10);
		font.setColor(Color.black);
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100);
		table.setSpacingAfter(20);
		table.setSpacingBefore(20);
		font = FontFactory.getFont(FontFactory.COURIER_BOLD,10);
		font.setColor(Color.black);

		// define table header cell
		// cell 1

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("Student Name", font));
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("Total Number Of Days", font));
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("Number Of Days Present", font));
		table.addCell(cell);

		cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("Number Of Days Absent", font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("Number Of Days Late", font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("Number Of Days Leave", font));
		table.addCell(cell);
		cell = new PdfPCell();
		cell.setBackgroundColor(Color.yellow);
		cell.addElement(new Paragraph("% Of Attendance", font));
		table.addCell(cell);

		for (StudentDTO studentDTO : studentDTOs) {
			table.addCell(studentDTO.getFullName());
			for (StudentAttendanceDTO studentAttendanceDTO : studentDTO
					.getStudentAttendanceDTOs()) {
				totalNoOfDays = studentAttendanceDTO.getTotalAttendance();
				noOfDaysPresent = studentAttendanceDTO.getTotalPresent();
				noOfDaysAbsent = studentAttendanceDTO.getTotalAbsent();
				late=studentAttendanceDTO.getTotalLate();
				leave=studentAttendanceDTO.getTotalLeave();
				percentageOfAttendance = studentAttendanceDTO.getPercent();
			}
			table.addCell(String.valueOf(totalNoOfDays));
			table.addCell(String.valueOf(noOfDaysPresent));
			table.addCell(String.valueOf(noOfDaysAbsent));
			table.addCell(String.valueOf(late));
			table.addCell(String.valueOf(leave));
			table.addCell(String.valueOf(percentageOfAttendance) + "%");
			month = studentDTO.getMonthName();
			classes = studentDTO.getClassName();
			section = studentDTO.getSectionName();
		}
		document.add(new Paragraph("Attendance Report for the Month of \""
				+ month + "\" of Class \"" + classes + "\" of Section \""
				+ section + "\""));
		document.add(table);

	}

}
