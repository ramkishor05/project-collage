package org.brijframework.college.commom.convertor;

import java.text.SimpleDateFormat;

import org.brijframework.college.model.FeeDiscount;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.FeeDiscountDTO;

public class FeeDiscountEnityToDTO {
	private static final FeeDiscountEnityToDTO feeDiscountEnityToDTO = new FeeDiscountEnityToDTO();

	public static final FeeDiscountEnityToDTO getInstance() {
		return feeDiscountEnityToDTO;
	}

	public FeeDiscountDTO convertEntityToDto(FeeDiscount feeDiscount) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FeeDiscountDTO feeDiscountDTO = new FeeDiscountDTO();
		Students students = feeDiscount.getStudents();
		StudentClasses studentClasses = feeDiscount.getStudentClasses();
		Section section = feeDiscount.getSection();
		feeDiscountDTO.setClassId(studentClasses.getClassId());
		feeDiscountDTO.setClassName(studentClasses.getClassName());
		feeDiscountDTO.setFeeDiscountAmount(feeDiscount.getFeeDiscountAmount());
		feeDiscountDTO.setFeeDiscountId(feeDiscount.getFeeDiscountId());
		feeDiscountDTO.setSectionId(section.getSectionId());
		feeDiscountDTO.setSectionName(section.getSectionName());
		feeDiscountDTO.setStudentId(students.getStudentAdmissionNo());
		feeDiscountDTO.setStudentName(students.getFirstName() + " "
				+ students.getMiddleName() + " " + students.getLastName());
		feeDiscountDTO.setFeeDiscountName(feeDiscount.getFeeDiscountName());
		feeDiscountDTO.setDueDate(sdf.format(feeDiscount.getLastDate()));
		feeDiscountDTO.setFromDate(sdf.format(feeDiscount.getFromDate()));
		feeDiscountDTO.setToDate(sdf.format(feeDiscount.getToDate()));
		return feeDiscountDTO;
	}
}
