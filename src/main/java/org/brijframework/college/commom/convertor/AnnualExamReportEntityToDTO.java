package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.AnnualExamReport;
import org.brijframework.college.models.dto.AnnualExamReportDTO;

public class AnnualExamReportEntityToDTO {
	private static final AnnualExamReportEntityToDTO annualExamReportEntityToDTO = new AnnualExamReportEntityToDTO();

	public static final AnnualExamReportEntityToDTO getInstance() {
		return annualExamReportEntityToDTO;
	}

	public AnnualExamReportDTO convertEntityToDTO(
			AnnualExamReport annualExamReport) {
		AnnualExamReportDTO annualExamReportDTO = new AnnualExamReportDTO();
		annualExamReportDTO.setClassId(annualExamReport.getStudentClasses()
				.getClassId());
		annualExamReportDTO.setClassName(annualExamReport.getStudentClasses()
				.getClassName());
		annualExamReportDTO.setMaxMarks(annualExamReport.getMaxMarks());
		annualExamReportDTO.setAnnaulExamReportId(annualExamReport
				.getAnnualExamReportId());
		annualExamReportDTO.setSectionId(annualExamReport.getSection()
				.getSectionId());
		annualExamReportDTO.setSectionName(annualExamReport.getSection()
				.getSectionName());
		annualExamReportDTO.setSessionId(annualExamReport.getSession()
				.getSessionId());
		annualExamReportDTO.setSessionName(annualExamReport.getSession()
				.getSessionDuration());
		annualExamReportDTO.setStudentId(annualExamReport.getStudents()
				.getStudentAdmissionNo());
		annualExamReportDTO.setStudentName(annualExamReport.getStudents()
				.getFirstName()
				+ " "
				+ annualExamReport.getStudents().getMiddleName()
				+ " "
				+ annualExamReport.getStudents().getLastName());
		annualExamReportDTO.setAnnualExamType(annualExamReport
				.getAnnualExamType());
		annualExamReportDTO.setExamType(annualExamReport.getExamType());
		return annualExamReportDTO;
	}
}
