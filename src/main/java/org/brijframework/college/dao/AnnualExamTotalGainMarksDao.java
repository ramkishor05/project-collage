package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.AnnualExamTotalGainMarks;

public interface AnnualExamTotalGainMarksDao extends
		DAO<Integer, AnnualExamTotalGainMarks> {
	public AnnualExamTotalGainMarks getAnnualExamTotalGainMarks(int sessionId,
			int classId, int sectionId, String studentId, String annualExamType);

	public List<AnnualExamTotalGainMarks> getMaxAnnualExamTotalGainMarks(
			int sessionId, int classId, int sectionId, String annualExamType);

}
