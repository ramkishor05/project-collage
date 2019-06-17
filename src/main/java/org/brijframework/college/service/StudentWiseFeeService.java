package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.models.dto.StudentWiseFeeDTO;

public interface StudentWiseFeeService extends
		CRUDService<Integer, StudentWiseFee> {

	String saveStudentWiseFee(StudentWiseFeeDTO studentWiseFeeDTO);

	List<StudentWiseFeeDTO> getStudentWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId, int sectionId, String studentId);

	List<StudentWiseFeeDTO> getAllStudentFees();

	void setActiveById(int id);

	List<StudentWiseFeeDTO> getFeeAllotementByStudentId(int sessionId,
			int classId, int sectionId, String studentId);

	List<StudentWiseFee> getStudentWiseFee(Integer sessionId, int monthId,
			int categoryId, int studentId);

	String deleteAllotedFeesCategory(int classId, int monthId, int sectionId,
			int studentWiseFeeId, String studentAdmissionNo);

}
