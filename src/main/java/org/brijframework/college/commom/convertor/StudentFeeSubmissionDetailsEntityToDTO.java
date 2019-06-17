package org.brijframework.college.commom.convertor;

import java.text.SimpleDateFormat;

import org.brijframework.college.model.Section;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;

public class StudentFeeSubmissionDetailsEntityToDTO {
	private static final StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = new StudentFeeSubmissionDetailsEntityToDTO();

	public static final StudentFeeSubmissionDetailsEntityToDTO getInstance() {
		return studentFeeSubmissionDetailsEntityToDTO;
	}

	public StudentFeeSubmissionDetailsDTO convertEntityToDTO(
			StudentFeeSubmissionDetails studentFeeSubmissionDetails) {
		StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = new StudentFeeSubmissionDetailsDTO();
		if (studentFeeSubmissionDetails == null) {
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Students students = studentFeeSubmissionDetails.getStudents();
			Section section = studentFeeSubmissionDetails.getSection();
			StudentClasses studentClasses = studentFeeSubmissionDetails
					.getStudentClasses();
			studentFeeSubmissionDetailsDTO
					.setSessionDuration(studentFeeSubmissionDetails
							.getSession().getSessionDuration());
			if (studentFeeSubmissionDetails.getFeePaidDate() == null) {
			} else {
				studentFeeSubmissionDetailsDTO.setFeePaidDate(simpleDateFormat
						.format(studentFeeSubmissionDetails.getFeePaidDate()));
			}
			studentFeeSubmissionDetailsDTO
					.setFeePaidStatus(studentFeeSubmissionDetails
							.getFeePaidStatus());
			studentFeeSubmissionDetailsDTO.setClassId(studentClasses
					.getClassId());
			studentFeeSubmissionDetailsDTO.setClassName(studentClasses
					.getClassName());
			studentFeeSubmissionDetailsDTO.setSectionId(section.getSectionId());
			studentFeeSubmissionDetailsDTO.setSectionName(section
					.getSectionName());
			studentFeeSubmissionDetailsDTO
					.setPaidAmount(studentFeeSubmissionDetails.getPaidAmount()
							.toString());
			studentFeeSubmissionDetailsDTO.setStudentAdmissionNo(students
					.getStudentAdmissionNo());
			studentFeeSubmissionDetailsDTO
					.setStudentId(students.getStudentId());
			studentFeeSubmissionDetailsDTO
					.setStudentFeeSubmissionDetailsId(studentFeeSubmissionDetails
							.getStudentFeeSubmissionDetailsId());
			studentFeeSubmissionDetailsDTO.setStudentName(students
					.getFirstName()
					+ " "
					+ students.getMiddleName()
					+ " "
					+ students.getLastName());

			studentFeeSubmissionDetailsDTO
					.setPaidBy(studentFeeSubmissionDetails.getPaidBy());
			studentFeeSubmissionDetailsDTO
					.setRecieptNo(studentFeeSubmissionDetails.getRecieptNo());
			studentFeeSubmissionDetailsDTO
					.setCommonRecieptNo(studentFeeSubmissionDetails
							.getCommonRecieptNo());
			studentFeeSubmissionDetailsDTO.setlFno(studentFeeSubmissionDetails
					.getlFNo());
			studentFeeSubmissionDetailsDTO.setMonth(studentFeeSubmissionDetails
					.getMonth().getMonthName());
			if (studentFeeSubmissionDetails.getPaidBy().equals("cheque")) {
				studentFeeSubmissionDetailsDTO
						.setChequeNo(studentFeeSubmissionDetails.getChequeNo());
			}
			if (studentFeeSubmissionDetails.getPaidAmountInWord() == null) {
			} else {
				studentFeeSubmissionDetailsDTO
						.setPaidAmountInWord(studentFeeSubmissionDetails
								.getPaidAmountInWord());
			}
		}
		return studentFeeSubmissionDetailsDTO;
	}
}
