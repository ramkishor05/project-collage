package org.brijframework.college.service;

import java.util.List;
import java.util.Map;

import org.brijframework.college.model.MonthlyExamReport;
import org.brijframework.college.models.dto.MonthlyExamReportDTO;

public interface MonthlyExamReportService extends
		CRUDService<Integer, MonthlyExamReport> {
	public MonthlyExamReportDTO getMonthlyExamReport(int sessionId,
			int classId, int sectionId, String studentId, int subjectId,
			int monthId);

	public void saveMonthlyExamReportData(
			MonthlyExamReportDTO monthlyExamReportDTO);
	
	public void updateMonthlyExamReportData(
			MonthlyExamReportDTO monthlyExamReportDTO);

	public Map<String, Object> getMonthlyExamReport(int sessionId, int classId,
			int sectionId, String studentId);

	List<MonthlyExamReportDTO> getMonthlyExamReport(int sessionId, int classId,
			int sectionId, String studentId, int monthId);

	public MonthlyExamReportDTO getMonthlyExamReport(int monthlyExamReportId);

}
