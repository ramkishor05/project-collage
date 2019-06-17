package org.brijframework.college.models.dto;

public class FeeDiscountDTO {
	private Integer feeDiscountId;
	private String feeDiscountName;
	private Double feeDiscountAmount;
	private String className;
	private Integer classId;
	private String studentId;
	private String studentName;
	private Integer sectionId;
	private String sectionName;
	private String dueDate;
	private String fromDate;
	private String toDate;

	public Integer getFeeDiscountId() {
		return feeDiscountId;
	}

	public void setFeeDiscountId(Integer feeDiscountId) {
		this.feeDiscountId = feeDiscountId;
	}

	public Double getFeeDiscountAmount() {
		return feeDiscountAmount;
	}

	public void setFeeDiscountAmount(Double feeDiscountAmount) {
		this.feeDiscountAmount = feeDiscountAmount;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getFeeDiscountName() {
		return feeDiscountName;
	}

	public void setFeeDiscountName(String feeDiscountName) {
		this.feeDiscountName = feeDiscountName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
