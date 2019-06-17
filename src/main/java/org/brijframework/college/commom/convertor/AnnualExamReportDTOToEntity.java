package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.AnnualExamReport;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.AnnualExamReportDTO;

public class AnnualExamReportDTOToEntity {
	public static final AnnualExamReportDTOToEntity annualExamReportDTOToEntity = new AnnualExamReportDTOToEntity();

	public static final AnnualExamReportDTOToEntity getInstance() {
		return annualExamReportDTOToEntity;
	}

	public AnnualExamReport convertEntityToDTO(
			AnnualExamReportDTO annualExamReportDTO) {
		AnnualExamReport annualExamReport = new AnnualExamReport();
		Session session = new Session();
		session.setSessionId(annualExamReportDTO.getSessionId());
		StudentClasses studentClasses = new StudentClasses();
		studentClasses.setClassId(annualExamReportDTO.getClassId());
		Section section = new Section();
		section.setSectionId(annualExamReportDTO.getSectionId());
		Students students = new Students();
		students.setStudentAdmissionNo(annualExamReportDTO.getStudentId());
		Subjects subjects = new Subjects();
		subjects.setSubjectsId(annualExamReportDTO.getSubjectId());
		annualExamReport.setMaxMarks(annualExamReportDTO.getMaxMarks());
		annualExamReport.setSection(section);
		annualExamReport.setSession(session);
		annualExamReport.setStudentClasses(studentClasses);
		annualExamReport.setStudents(students);
		annualExamReport.setAnnualExamType(annualExamReportDTO.getAnnualExamType());
		annualExamReport.setExamType(annualExamReportDTO.getExamType());
		return annualExamReport;
	}
}

