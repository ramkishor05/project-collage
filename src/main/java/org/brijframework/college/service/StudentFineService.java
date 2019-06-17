package org.brijframework.college.service;

import java.util.Date;

import org.brijframework.college.model.StudentFine;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.models.dto.StudentFineDTO;

public interface StudentFineService extends CRUDService<Integer, StudentFine> {

	void createStudentFine(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO);

	StudentFineDTO getStudentFineById(int studentFineId);

	StudentFineDTO getStudentFineFromToDate(String studentAdmissionNo,
			int sectionId, int classId, String fromDate, String toDate);

	void updateStudentFine(int studentFineId, String fineName, String fineAmount);
}
