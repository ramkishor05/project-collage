package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.EmployeeAttendance;

public interface EmployeeAttendanceDao extends DAO<Integer, EmployeeAttendance> {

	List<EmployeeAttendance> getRegisterByEmployeeId(Integer employeeId,
			Date frmDate, Date tDate);

	EmployeeAttendance getAttendanceReportByIdDate(int id, java.sql.Date sDate);

	List<EmployeeAttendance> getEmployeeAttendanceList(int employeeId,
			Date frmDate, Date tDate);

	List<Date> totalSchoolOpenDays(Date frmDate, Date tDate);

}
