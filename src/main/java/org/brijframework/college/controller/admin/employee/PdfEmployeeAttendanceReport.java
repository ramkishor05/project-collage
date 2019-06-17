package org.brijframework.college.controller.admin.employee;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brijframework.college.models.dto.EmployeeAttendanceDTO;
import org.brijframework.college.models.dto.EmployeesDTO;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfEmployeeAttendanceReport extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		document.setMargins(10, 10, 10, 10);
		Date date = new Date();
		int late = 0;
		String name = "";
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		DecimalFormat def = new DecimalFormat();
		def.setMaximumFractionDigits(2);
		String from = "";
		String to = "";
		String today = df.format(date);
		@SuppressWarnings("unchecked")
		List<EmployeesDTO> employeesDTOs = (List<EmployeesDTO>) model
				.get("list");
		PdfPTable table = new PdfPTable(2);
		table.setSpacingBefore(30);
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(Color.black);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.lightGray);
		cell.setPadding(5);

		// write table header
		cell.setPhrase(new Phrase("Date", font));
		table.addCell(cell);

		table.setWidthPercentage(100.0f);

		
		  for (EmployeesDTO employeesDTO : employeesDTOs) {
		  
		  for (EmployeeAttendanceDTO employeeAttendanceDTO : employeesDTO
		  .getEmployeeAttendanceDTOs()) { cell.setPhrase(new Phrase("Date",
		  font)); table.addCell(cell);
		  
		  }
		  
		  }
		 
		
		  cell.setPhrase(new Phrase("Total", font)); table.addCell(cell);
		 
		cell.setPhrase(new Phrase("Attendance Status", font));
		table.addCell(cell);

		for (EmployeesDTO employeesDTO : employeesDTOs) {
			 table.addCell(employeesDTO.getFullName()); 
			for (EmployeeAttendanceDTO employeeAttendanceDTO : employeesDTO
					.getEmployeeAttendanceDTOs()) {
				table.addCell(employeeAttendanceDTO.getDateOfAttendance());
				table.addCell(employeeAttendanceDTO.getAttendanceStatus());
				late = employeeAttendanceDTO.getTotalLate();
			}

		}

		document.add(new Paragraph("Attendance Report Of " + name + " from "
				+ from + " to " + to + "\nDate" + today));
		document.add(table);
		for (EmployeesDTO employeesDTO : employeesDTOs) {
			 table.addCell(employeesDTO.getFullName()); document
					.add(new Paragraph("Total "
							+ (employeesDTO.getNoOfDaysPresent() + late)
							+ "/"
							+ (int) employeesDTO.getTotalNoOfDays()
							+ " \n Percentage "
							+ def.format(employeesDTO
									.getPercentageOfAttendance()) + "%"));

		}

	}

}
