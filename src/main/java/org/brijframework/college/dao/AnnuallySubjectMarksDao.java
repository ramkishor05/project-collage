package org.brijframework.college.dao;

import org.brijframework.college.model.AnnuallySubjectMarks;

public interface AnnuallySubjectMarksDao extends
		DAO<Integer, AnnuallySubjectMarks> {
	public AnnuallySubjectMarks getAnnuallySubjectMarks(int sessionId,
			int classId, int sectionId,int subjectId, String examType, String annualExamType);

}
