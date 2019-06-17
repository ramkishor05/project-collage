package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.StudentAdmission;
import org.brijframework.college.model.Students;

public interface StudentsAdmissionDao extends DAO<String, Students> {

	List<Students> getStudent(String firstName);

	public List<StudentAdmission> getAllApplication();

	public StudentAdmission getStudent(int id);

	public Students getInActiveStudentById(String id);

	List<Students> getStudentByClassIdAndName(int classId, String firstName,
			int sessionId);

	List<Students> getStudentListById(int courseId, int branchId, int batchId);

	List<Students> getStudentsbyClassSectionId(int classId, int sectionId,
			int sessionId);

	Students getActiveStudentById(String id);

	List<Students> getStudentbyFatherName(String fatherName, int sessionId);

	List<Students> getStudentbyFatherName(int categoryId, int sessionId);

	List<Students> getStudentByMatchFromName(int classId, int sectionId,
			String studentName);

	public List<Students> getStudentByClassId(Integer classId);

	List<Students> getCancelledList(int sessionId);

	int getMaxLfno(int sessionId, int classId);

	List<Students> getStudentbyclasssession(int sessionId, int classId);

	List<Students> getStudentsByClassId(int classId);

	List<Students> getStudentsbysession(Integer sessionId);

	List<Students> getStudentsbysessionandpageno(Integer sessionId, int p);

	List<Students> getStudentbyAdmNo(String admissionNo);

	List<Students> getStudentsByClassIdandSession(int classId, int sessionId);

	List<Students> getStudentsByClassIdandSessionpageno(int classId,
			int sessionId, int pageno);

	Students getStudentbyuserId(Integer id);

	Students getAdmissionNo();

	Students checkExistStudent(String studentId, int sessionId);

	List<Students> getStudentbyCategory(int categoryId, int sessionId,
			int classId);

	public void ceateAdmission(StudentAdmission studentAdmission);

	List<StudentAdmission> getCancelledAllApplication();

	List<StudentAdmission> getTransferredAllApplication();
}
