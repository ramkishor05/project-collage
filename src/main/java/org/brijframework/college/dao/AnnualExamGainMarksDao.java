package org.brijframework.college.dao;

import org.brijframework.college.model.AnnualExamGainMarks;

public interface AnnualExamGainMarksDao extends
		DAO<Integer, AnnualExamGainMarks> {
	public AnnualExamGainMarks getAnnualExamGainMarks(int subjectId,
			int annualExamReportId);

}
