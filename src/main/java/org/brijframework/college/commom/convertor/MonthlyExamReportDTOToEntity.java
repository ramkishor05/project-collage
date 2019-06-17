package org.brijframework.college.commom.convertor;

import java.util.Date;

import org.brijframework.college.model.Month;
import org.brijframework.college.model.MonthlyExamReport;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.MonthlyExamReportDTO;

public class MonthlyExamReportDTOToEntity {
	public static final MonthlyExamReportDTOToEntity monthlyExamReportDTOToEntity = new MonthlyExamReportDTOToEntity();

	public static final MonthlyExamReportDTOToEntity getInstance() {
		return monthlyExamReportDTOToEntity;
	}

	public MonthlyExamReport convertEntityToDTO(
			MonthlyExamReportDTO monthlyExamReportDTO) {
		MonthlyExamReport monthlyExamReport = new MonthlyExamReport();
		Session session = new Session();
		session.setSessionId(monthlyExamReportDTO.getSessionId());
		StudentClasses studentClasses = new StudentClasses();
		studentClasses.setClassId(monthlyExamReportDTO.getClassId());
		Section section = new Section();
		section.setSectionId(monthlyExamReportDTO.getSectionId());
		Students students = new Students();
		students.setStudentAdmissionNo(monthlyExamReportDTO.getStudentId());
		Subjects subjects = new Subjects();
		subjects.setSubjectsId(monthlyExamReportDTO.getSubjectId());
		Month month = new Month();
		month.setMonthId(monthlyExamReportDTO.getMonthId());
		monthlyExamReport.setCreatedAt(new Date());
		monthlyExamReport.setMarks(monthlyExamReportDTO.getMarks());
		monthlyExamReport.setMaxMarks(monthlyExamReportDTO.getMaxMarks());
		monthlyExamReport.setMonth(month);
		monthlyExamReport.setSection(section);
		monthlyExamReport.setSession(session);
		monthlyExamReport.setStudentClasses(studentClasses);
		monthlyExamReport.setStudents(students);
		monthlyExamReport.setSubjects(subjects);
		monthlyExamReport.setUpdatedAt(new Date());
		monthlyExamReport.setMonthlyExamReportId(monthlyExamReportDTO.getMonthlyExamReportId());
		return monthlyExamReport;
	}
}
