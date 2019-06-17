package org.brijframework.college.controller.admin.student;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.brijframework.college.models.dto.StudentDTO;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class SectionWiseExcelReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<StudentDTO> list = (List<StudentDTO>) model.get("sectionWiseList");
		
		String classes="";
		String sections="";
		for(StudentDTO dto:list)
		{
			 classes=dto.getClassName();
			sections=dto.getSectionName();
			
		}
		HSSFSheet excelSheet = workbook.createSheet("SectionWise Student List of class"+classes+"and Section"+sections);
		excelSheet.setDefaultColumnWidth(30);
		
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Roll No.");
		excelHeader.getCell(0).setCellStyle(style);
		excelHeader.createCell(1).setCellValue("Student Name");
		excelHeader.getCell(1).setCellStyle(style);
		excelHeader.createCell(2).setCellValue("Father's Name");
		excelHeader.getCell(2).setCellStyle(style);
		excelHeader.createCell(3).setCellValue("Mobile");
		excelHeader.getCell(3).setCellStyle(style);
		excelHeader.createCell(4).setCellValue("Address");
		excelHeader.getCell(4).setCellStyle(style);
		setExcelRows(excelSheet, list);
	}

	public void setExcelRows(HSSFSheet excelSheet, List<StudentDTO> list) {
		int record = 1;
		for (StudentDTO dto : list) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(dto.getRollno());
			excelRow.createCell(1).setCellValue(
					dto.getFirstName() + " " + dto.getMiddleName() + " "
							+ dto.getLastName());
			excelRow.createCell(2).setCellValue(dto.getFatherName());
			excelRow.createCell(3).setCellValue(dto.getMobile());
			excelRow.createCell(4).setCellValue(dto.getAddressLine1());
		}
	}
}

