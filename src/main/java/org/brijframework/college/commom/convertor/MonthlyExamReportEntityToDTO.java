package org.brijframework.college.commom.convertor;

import java.text.SimpleDateFormat;

import org.brijframework.college.model.MonthlyExamReport;
import org.brijframework.college.models.dto.MonthlyExamReportDTO;

public class MonthlyExamReportEntityToDTO {
	private static final MonthlyExamReportEntityToDTO monthlyExamReportEntityToDTO = new MonthlyExamReportEntityToDTO();

	public static final MonthlyExamReportEntityToDTO getInstance() {
		return monthlyExamReportEntityToDTO;
	}

	public MonthlyExamReportDTO convertEntityToDTO(
			MonthlyExamReport monthlyExamReport) {
		MonthlyExamReportDTO monthlyExamReportDTO = new MonthlyExamReportDTO();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd");
		monthlyExamReportDTO.setClassId(monthlyExamReport.getStudentClasses()
				.getClassId());
		monthlyExamReportDTO.setClassName(monthlyExamReport.getStudentClasses()
				.getClassName());
		monthlyExamReportDTO.setCreatedAt(simpleDateFormat
				.format(monthlyExamReport.getCreatedAt()));
		monthlyExamReportDTO.setMarks(monthlyExamReport.getMarks());
		monthlyExamReportDTO.setMaxMarks(monthlyExamReport.getMaxMarks());
		monthlyExamReportDTO.setMonthId(monthlyExamReport.getMonth()
				.getMonthId());
		monthlyExamReportDTO.setMonthlyExamReportId(monthlyExamReport
				.getMonthlyExamReportId());
		monthlyExamReportDTO.setMonthName(monthlyExamReport.getMonth()
				.getMonthName());
		monthlyExamReportDTO.setSectionId(monthlyExamReport.getSection()
				.getSectionId());
		monthlyExamReportDTO.setSectionName(monthlyExamReport.getSection()
				.getSectionName());
		monthlyExamReportDTO.setSessionId(monthlyExamReport.getSession()
				.getSessionId());
		monthlyExamReportDTO.setSessionName(monthlyExamReport.getSession()
				.getSessionDuration());
		monthlyExamReportDTO.setStudentId(monthlyExamReport.getStudents()
				.getStudentAdmissionNo());
		monthlyExamReportDTO.setStudentName(monthlyExamReport.getStudents()
				.getFirstName()
				+ " "
				+ monthlyExamReport.getStudents().getMiddleName()
				+ " "
				+ monthlyExamReport.getStudents().getLastName());
		monthlyExamReportDTO.setSubjectId(monthlyExamReport.getSubjects()
				.getSubjectsId());
		monthlyExamReportDTO.setSubjectName(monthlyExamReport.getSubjects()
				.getSubjectName());
		monthlyExamReportDTO.setUpdatedAt(simpleDateFormat
				.format(monthlyExamReport.getUpdatedAt()));
		return monthlyExamReportDTO;
	}
}
