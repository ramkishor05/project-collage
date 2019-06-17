package org.brijframework.college.models.dto;

public class SectionWiseFeeDTO {
	private Integer sectionWiseFeeId;

	private int sessionId;
	private int classId;
	private int monthId;
	private int sectionId;
	private int feesCategoriesId;
	private String feesCategoriesName;
	private String feeAmount;
	private boolean active;
	private String sessionName;
	private String monthName;
	private String className;
	private String sectionName;
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

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getFeesCategoriesName() {
		return feesCategoriesName;
	}

	public void setFeesCategoriesName(String feesCategoriesName) {
		this.feesCategoriesName = feesCategoriesName;
	}

	public Integer getSectionWiseFeeId() {
		return sectionWiseFeeId;
	}

	public void setSectionWiseFeeId(Integer sectionWiseFeeId) {
		this.sectionWiseFeeId = sectionWiseFeeId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
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
