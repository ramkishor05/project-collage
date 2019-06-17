package org.brijframework.college.models.dto;


public class ClassWiseFeeDTO {
	private Integer classWiseFeeId;
	private int sessionId;
	private int monthId;
	private int classId;
	private int feesCategoriesId;
	private String feesCategoriesName;
	private String feeAmount;
	private boolean active;
	private String sessionName;
	private String monthName;
	private String className;
	private String installment;

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFeesCategoriesName() {
		return feesCategoriesName;
	}

	public void setFeesCategoriesName(String feesCategoriesName) {
		this.feesCategoriesName = feesCategoriesName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public Integer getClassWiseFeeId() {
		return classWiseFeeId;
	}

	public void setClassWiseFeeId(Integer classWiseFeeId) {
		this.classWiseFeeId = classWiseFeeId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public int getFeesCategoriesId() {
		return feesCategoriesId;
	}

	public void setFeesCategoriesId(int feesCategoriesId) {
		this.feesCategoriesId = feesCategoriesId;
	}

	public String getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
