package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.brijframework.college.model.StudentAttendance;
import org.brijframework.college.models.dto.StudentAttendanceDTO;
import org.brijframework.college.models.dto.StudentDTO;

public interface StudentAttendanceService extends
		CRUDService<Integer, StudentAttendance> {

	List<StudentAttendanceDTO> getStudentAttendance(String studentId,
			int monthId) throws ParseException;

	void setAttendanceRecord(String id, String title, int classId,
			int sectionId, int emplyeeId, String currentDate)
			throws ParseException;

	List<StudentDTO> getWeeklyAttendanceRegister(int classId, int sectionId)
			throws Exception;

	List<StudentDTO> getStudentsAttendanceList(int classId,
			int sectionId, int monthId) throws ParseException;
	
	List<StudentDTO> getStudentsDateWiseAttendanceList(int classId,
			int sectionId, String currentDate) throws ParseException;
	
	List<StudentAttendanceDTO> getStudentsCurrentDateAttendanceList(int classId,
			int sectionId, String currentDate) throws ParseException;
	
	List<StudentAttendanceDTO> getStudentAttendanceSummary(int classId,int sectionId)throws ParseException ;
	public Map<String, Object> studentsDateWiseAttendanceList(int classId,
			int sectionId, String currentDate)throws ParseException ;

	/*Object getStudentsAttendanceListAsPdf(int classId, int sectionId,
			int monthId) throws ParseException;*/

}