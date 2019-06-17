package org.brijframework.college.models.dto;

public class AdvanceSalaryDTO {
	
	private int advanceSalaryId;
	private int employeeId;
	private String firstName;
	private String lastName;
	private int allotedMonthId;
	private String dateOfPayment;
	private int paidAmount;
	private boolean active;
	private int sessionId;
	private String employeeName;
	private String mobile;
	private String imagename;
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getAdvanceSalaryId() {
		return advanceSalaryId;
	}
	public void setAdvanceSalaryId(int advanceSalaryId) {
		this.advanceSalaryId = advanceSalaryId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAllotedMonthId() {
		return allotedMonthId;
	}
	public void setAllotedMonthId(int allotedMonthId) {
		this.allotedMonthId = allotedMonthId;
	}
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
