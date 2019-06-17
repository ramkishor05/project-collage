package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.AnnualExamGainMarks;
import org.brijframework.college.models.dto.AnnualExamReportDTO;

public interface AnnualExamGainMarksService extends
		CRUDService<Integer, AnnualExamGainMarks> {
	public List<AnnualExamReportDTO> getAnnualExamGainMarksList(int sessionId,
			int classId, int sectionId, String studentId, String annualExamType,
			String examType);

}
