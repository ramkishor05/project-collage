package org.brijframework.college.service;

import org.brijframework.college.model.AnnualExamTotalGainMarks;
import org.brijframework.college.models.dto.AnnualExamReportDTO;

public interface AnnualExamTotalGainMarksService extends
		CRUDService<Integer, AnnualExamTotalGainMarks> {
	public void saveAnnualExamTotalGainMarks(
			AnnualExamReportDTO annualExamReportDTO);
}
