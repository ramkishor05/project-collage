package org.brijframework.college.models.dto;

public class EmployeeAttendanceDTO {
	private int employeeAttendanceId;
	private int employeeId;
	private String attendanceStatus;
	private String dateOfAttendance;
	private int departmentId;
	private String status;
	private String firstName;
	private int totalNoOfDays;
	private int noOfDaysPresent;
	private int noOfDaysAbsent;
	private int noOfDaysLatet;
	private int noOfDaysLeave;
	private double percentageOfAttendance;
	private int totalLate;
	private int totalLeave;
	
	

	public int getTotalLate() {
		return totalLate;
	}

	public void setTotalLate(int totalLate) {
		this.totalLate = totalLate;
	}

	public int getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	public int getTotalNoOfDays() {
		return totalNoOfDays;
	}

	public void setTotalNoOfDays(int totalNoOfDays) {
		this.totalNoOfDays = totalNoOfDays;
	}

	public int getNoOfDaysPresent() {
		return noOfDaysPresent;
	}

	public void setNoOfDaysPresent(int noOfDaysPresent) {
		this.noOfDaysPresent = noOfDaysPresent;
	}

	public int getNoOfDaysAbsent() {
		return noOfDaysAbsent;
	}

	public void setNoOfDaysAbsent(int noOfDaysAbsent) {
		this.noOfDaysAbsent = noOfDaysAbsent;
	}

	public double getPercentageOfAttendance() {
		return percentageOfAttendance;
	}

	public void setPercentageOfAttendance(double percentageOfAttendance) {
		this.percentageOfAttendance = percentageOfAttendance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEmployeeAttendanceId() {
		return employeeAttendanceId;
	}

	public void setEmployeeAttendanceId(int employeeAttendanceId) {
		this.employeeAttendanceId = employeeAttendanceId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getDateOfAttendance() {
		return dateOfAttendance;
	}

	public void setDateOfAttendance(String dateOfAttendance) {
		this.dateOfAttendance = dateOfAttendance;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getNoOfDaysLatet() {
		return noOfDaysLatet;
	}

	public void setNoOfDaysLatet(int noOfDaysLatet) {
		this.noOfDaysLatet = noOfDaysLatet;
	}

	public int getNoOfDaysLeave() {
		return noOfDaysLeave;
	}

	public void setNoOfDaysLeave(int noOfDaysLeave) {
		this.noOfDaysLeave = noOfDaysLeave;
	}

}
