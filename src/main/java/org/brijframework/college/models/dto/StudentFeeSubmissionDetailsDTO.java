package org.brijframework.college.models.dto;

import java.util.List;

public class StudentFeeSubmissionDetailsDTO {
	private Integer studentFeeSubmissionDetailsId;
	private String studentAdmissionNo;
	private String studentName;
	private Integer sectionId;
	private String sectionName;
	private Integer classId;
	private String className;
	private String grossAmount;
	private String paidAmount;
	private String feePaidStatus;
	private String feePaidDate;
	private String chequeNo;
	private String paidBy;
	private Integer recieptNo;
	private Integer lFno;
	private String paidAmountInWord;
	private String fineAmount;
	private String lastDate;
	private String fromDate;
	private String toDate;
	private String month;
	private Integer monthId;
	private List<MonthDTO> monthDTOs;
	private List<Integer> monthsId;
	private String discountAmount;
	private String discount;
	private Integer sessionId;
	private StudentFineDTO studentFineDTO;
	private List<FeecategoryAmountDTO> feecategoryAmountDTOs;
	private String studentId;
	private String bankName;
	private String dueAmount;
	private String netPayableAmount;
	private String recentPaidAmount;
	private String sessionDuration;
	private String fatherName;
	private String mobileNo;
	private List<FeesCategoriesDTO> feesCategoriesDTOs;
	private String status;
	private String commonString;
	private List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs;
	private StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO;
    private int commonRecieptNo;
    
    
	public int getCommonRecieptNo() {
		return commonRecieptNo;
	}

	public void setCommonRecieptNo(int commonRecieptNo) {
		this.commonRecieptNo = commonRecieptNo;
	}

	public String getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getSessionDuration() {
		return sessionDuration;
	}

	public void setSessionDuration(String sessionDuration) {
		this.sessionDuration = sessionDuration;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public List<MonthDTO> getMonthDTOs() {
		return monthDTOs;
	}

	public void setMonthDTOs(List<MonthDTO> monthDTOs) {
		this.monthDTOs = monthDTOs;
	}

	public List<Integer> getMonthsId() {
		return monthsId;
	}

	public void setMonthsId(List<Integer> monthsId) {
		this.monthsId = monthsId;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getStudentFeeSubmissionDetailsId() {
		return studentFeeSubmissionDetailsId;
	}

	public void setStudentFeeSubmissionDetailsId(
			Integer studentFeeSubmissionDetailsId) {
		this.studentFeeSubmissionDetailsId = studentFeeSubmissionDetailsId;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
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

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public Integer getRecieptNo() {
		return recieptNo;
	}

	public void setRecieptNo(Integer recieptNo) {
		this.recieptNo = recieptNo;
	}

	public Integer getlFno() {
		return lFno;
	}

	public void setlFno(Integer lFno) {
		this.lFno = lFno;
	}

	public String getPaidAmountInWord() {
		return paidAmountInWord;
	}

	public void setPaidAmountInWord(String paidAmountInWord) {
		this.paidAmountInWord = paidAmountInWord;
	}

	public String getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
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

	public StudentFineDTO getStudentFineDTO() {
		return studentFineDTO;
	}

	public void setStudentFineDTO(StudentFineDTO studentFineDTO) {
		this.studentFineDTO = studentFineDTO;
	}

	public List<FeecategoryAmountDTO> getFeecategoryAmountDTOs() {
		return feecategoryAmountDTOs;
	}

	public void setFeecategoryAmountDTOs(
			List<FeecategoryAmountDTO> feecategoryAmountDTOs) {
		this.feecategoryAmountDTOs = feecategoryAmountDTOs;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getNetPayableAmount() {
		return netPayableAmount;
	}

	public void setNetPayableAmount(String netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}

	public String getRecentPaidAmount() {
		return recentPaidAmount;
	}

	public void setRecentPaidAmount(String recentPaidAmount) {
		this.recentPaidAmount = recentPaidAmount;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public List<FeesCategoriesDTO> getFeesCategoriesDTOs() {
		return feesCategoriesDTOs;
	}

	public void setFeesCategoriesDTOs(List<FeesCategoriesDTO> feesCategoriesDTOs) {
		this.feesCategoriesDTOs = feesCategoriesDTOs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommonString() {
		return commonString;
	}

	public void setCommonString(String commonString) {
		this.commonString = commonString;
	}

	public List<StudentFeeSubmissionDetailsDTO> getFeeSubmissionDetailsDTOs() {
		return feeSubmissionDetailsDTOs;
	}

	public void setFeeSubmissionDetailsDTOs(
			List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs) {
		this.feeSubmissionDetailsDTOs = feeSubmissionDetailsDTOs;
	}

	public StudentFeeSubmissionDetailsDTO getStudentFeeSubmissionDetailsDTO() {
		return studentFeeSubmissionDetailsDTO;
	}

	public void setStudentFeeSubmissionDetailsDTO(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO) {
		this.studentFeeSubmissionDetailsDTO = studentFeeSubmissionDetailsDTO;
	}

}
