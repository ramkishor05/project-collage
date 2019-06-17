package org.brijframework.college.models.dto;

public class StudentWiseFeeDTO {
	private Integer studentWiseFeeId;
	private String studentAdmissionNo;
	private int sessionId;
	private int classId;
	private int monthId;
	private int sectionId;
	private int feesCategoriesId;
	private String feesCategoriesName;
	private String feeAmount;
	private boolean active;
	private String firstName;
	private String middleName;
	private String lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Integer getStudentWiseFeeId() {
		return studentWiseFeeId;
	}

	public void setStudentWiseFeeId(Integer studentWiseFeeId) {
		this.studentWiseFeeId = studentWiseFeeId;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
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
