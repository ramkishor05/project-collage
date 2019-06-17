package org.brijframework.college.models.dto;

import java.util.List;

public class EditFeeAmountDTO {
	private Integer editFeeAmountId;
	private Double currentAmount;
	private Double discount;
	private int monthId;
	private Integer monthSerialNo;
	private String monthName;
	private int classId;
	private String className;
	private int sectionId;
	private String sectionName;
	private int feesCategoriesId;
	private String feeCategoryName;
	private int sessionId;
	private String sessionName;
	private String studentAdmissionNo;
	private String studentFullName;
	private List<Integer> monthIds;
	private String statusVarible;

	public Integer getEditFeeAmountId() {
		return editFeeAmountId;
	}

	public void setEditFeeAmountId(Integer editFeeAmountId) {
		this.editFeeAmountId = editFeeAmountId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getFeesCategoriesId() {
		return feesCategoriesId;
	}

	public void setFeesCategoriesId(int feesCategoriesId) {
		this.feesCategoriesId = feesCategoriesId;
	}

	public String getFeeCategoryName() {
		return feeCategoryName;
	}

	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public String getStudentFullName() {
		return studentFullName;
	}

	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}

	public List<Integer> getMonthIds() {
		return monthIds;
	}

	public void setMonthIds(List<Integer> monthIds) {
		this.monthIds = monthIds;
	}

	public String getStatusVarible() {
		return statusVarible;
	}

	public void setStatusVarible(String statusVarible) {
		this.statusVarible = statusVarible;
	}

	public Integer getMonthSerialNo() {
		return monthSerialNo;
	}

	public void setMonthSerialNo(Integer monthSerialNo) {
		this.monthSerialNo = monthSerialNo;
	}

	public Double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}

}
