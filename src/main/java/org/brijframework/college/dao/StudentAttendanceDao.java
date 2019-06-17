package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.StudentAttendance;

public interface StudentAttendanceDao extends DAO<Integer, StudentAttendance> {
	List<StudentAttendance> getStudentAttendanceByStudentId(String studentId);

	StudentAttendance getAttendanceReportByIdDate(String id, java.sql.Date sDate);

	StudentAttendance getAttendanceReportById(String studentId, int employeeId,
			java.sql.Date sDate);

	List<StudentAttendance> getStudentAttendanceList(int courseid,
			int branchid, int batchid, int subjectid, String fromdate,
			String todate);

	List<StudentAttendance> getStudentAttendanceBystudentId(String studentId,
			Date fromDate, Date toDate);

	StudentAttendance getAttendenceByDate(String id, java.sql.Date myDate);

	List<StudentAttendance> getStudentAttendanceDayByDay(int classId,
			int sectionId, String studentAdmissionNumber, Date fromdate,
			Date toDate);

	List<StudentAttendance> getReportByCourseBranchBatchStudentId(int courseId,
			int batchId, int branchId, String admissionNo, int subjectId,
			Date fromdate, Date todate);

	List<StudentAttendance> getReportByCourseBranchBatchSubjectId(int courseId,
			int branchId, int batchId, int subjectsId, Date from, Date to);

	List<StudentAttendance> getMonthlyReport(String id, Integer id2, Date from,
			Date to);

	StudentAttendance getAttendanceReportByIdDate(int classId, int sectionId,
			String id, java.sql.Date sDate);
	List<Date> totalSchoolOpenDays(int classId,int sectionId,Date fromDate,Date toDate);
	List<StudentAttendance> totalStudentSchoolOpenDays(int classId,int sectionId,String studentId,Date fromDate,Date toDate);
}
