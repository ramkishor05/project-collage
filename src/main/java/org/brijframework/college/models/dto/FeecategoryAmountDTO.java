package org.brijframework.college.models.dto;

import java.util.List;

public class FeecategoryAmountDTO {
	private String feeCategoryName;
	private String password;
	private String newPassword;
	private String confirmPassword;
	private double feeCatgoryAmount;
	private Integer fromClassId;
	private Integer toClassId;
	private Integer fromSectionId;
	private Integer toSectionId;
	private List<Integer> studentId;
	private String fromSession;
	private Integer toSession;
	private String retryCount;
	private String filePath;
	private String month;
	private String paidAmount;
	private String fineAmount;
	private String feePaidStatus;
	private String feePaidDate;
	private int sessionId;
	private String studentAdmissionNo;
	private int slipNo;
	private int employeeId;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFeeCategoryName() {
		return feeCategoryName;
	}

	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}

	public double getFeeCatgoryAmount() {
		return feeCatgoryAmount;
	}

	public void setFeeCatgoryAmount(double feeCatgoryAmount) {
		this.feeCatgoryAmount = feeCatgoryAmount;
	}

	public Integer getFromClassId() {
		return fromClassId;
	}

	public void setFromClassId(Integer fromClassId) {
		this.fromClassId = fromClassId;
	}

	public Integer getToClassId() {
		return toClassId;
	}

	public void setToClassId(Integer toClassId) {
		this.toClassId = toClassId;
	}

	public Integer getFromSectionId() {
		return fromSectionId;
	}

	public void setFromSectionId(Integer fromSectionId) {
		this.fromSectionId = fromSectionId;
	}

	public Integer getToSectionId() {
		return toSectionId;
	}

	public void setToSectionId(Integer toSectionId) {
		this.toSectionId = toSectionId;
	}

	public List<Integer> getStudentId() {
		return studentId;
	}

	public void setStudentId(List<Integer> studentId) {
		this.studentId = studentId;
	}

	public String getFromSession() {
		return fromSession;
	}

	public void setFromSession(String fromSession) {
		this.fromSession = fromSession;
	}

	public Integer getToSession() {
		return toSession;
	}

	public void setToSession(Integer toSession) {
		this.toSession = toSession;
	}

	public String getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(String retryCount) {
		this.retryCount = retryCount;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getFeePaidStatus() {
		return feePaidStatus;
	}

	public void setFeePaidStatus(String feePaidStatus) {
		this.feePaidStatus = feePaidStatus;
	}

	public String getFeePaidDate() {
		return feePaidDate;
	}

	public void setFeePaidDate(String feePaidDate) {
		this.feePaidDate = feePaidDate;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public int getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(int slipNo) {
		this.slipNo = slipNo;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
