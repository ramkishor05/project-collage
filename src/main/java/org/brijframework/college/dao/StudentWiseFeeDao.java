package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.StudentWiseFee;

public interface StudentWiseFeeDao extends DAO<Integer, StudentWiseFee> {

	List<StudentWiseFee> getStudentWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId, int sectionId, String studentId);

	StudentWiseFee getStudentWiseFee(int sessionId, int classId,
			int categoryId, int sectionId, String studentId);

	List<StudentWiseFee> getFeeAllotement(int sessionId, int classId,
			int sectionId, int monthId, String admissionNo);

	List<StudentWiseFee> getStudentByAdmissionNoFromDateToDate(int monthId);

	List<StudentWiseFee> getStudentFeeAllotementBySessionId(int sessionId);

	List<StudentWiseFee> getAllotedFeeByStudentId(int sessionId, int classId,
			int sectionId, String studentId);

	StudentWiseFee getDues(Integer sessionId, Integer classId,
			Integer sectionId, int monthId, String studentAdmissionNo);

	StudentWiseFee getduesbyMonth(String studentId, int i, int sessionId);

	List<StudentWiseFee> getStudentWiseFee(Integer sessionId, int monthId,
			int categoryId, int studentId);

	

}
