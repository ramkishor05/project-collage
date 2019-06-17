package org.brijframework.college.controller.admin.fee;

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
import org.apache.poi.ss.usermodel.Workbook;
import org.brijframework.college.models.dto.StudentDTO;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

public class ClassFeeDefaulter extends AbstractXlsxStreamingView  {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook  workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String classes = "";
		List<StudentDTO> list = (List<StudentDTO>) model.get("list");
		
		for (StudentDTO dto : list) {
			classes = dto.getClassName();

		}
		HSSFSheet excelSheet = workbook.createSheet("Defaulter List of class " + classes);
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
		excelHeader.createCell(0).setCellValue("Student Name");
		excelHeader.getCell(0).setCellStyle(style);
		excelHeader.createCell(1).setCellValue("Balance Amount");
		excelHeader.getCell(1).setCellStyle(style);
		excelHeader.createCell(2).setCellValue("Mobile");
		excelHeader.getCell(2).setCellStyle(style);
		excelHeader.createCell(3).setCellValue("Address");
		excelHeader.getCell(3).setCellStyle(style);
		setExcelRows(excelSheet, list);
	}

	public void setExcelRows(HSSFSheet excelSheet, List<StudentDTO> list) {
		int record = 1;
		for (StudentDTO dto : list) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(dto.getFirstName()+" "+dto.getMiddleName()+" "+dto.getLastName());
			excelRow.createCell(1).setCellValue(dto.getBalanceAmount());
			excelRow.createCell(2).setCellValue(dto.getMobile());
			excelRow.createCell(3).setCellValue(dto.getAddressLine1());
		}
	}
}
