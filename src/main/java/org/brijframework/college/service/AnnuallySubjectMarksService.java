package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.AnnuallySubjectMarks;
import org.brijframework.college.models.dto.AnnuallySubjectMarksDTO;

public interface AnnuallySubjectMarksService extends
		CRUDService<Integer, AnnuallySubjectMarks> {
	public List<AnnuallySubjectMarksDTO> getAnnuallySubjectMarks(int sessionId,
			int classId, int sectionId, String examType, String annualExamType);


}
