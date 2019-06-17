package org.brijframework.college.models.dto;

import java.util.List;

public class CommonDTO {

	List<StudentDTO> studentDTOs;
	List<SubjectDTO> subjectDTOs;
	List<FeeDiscountDTO> feeDiscountDTOs;
	List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs;
	private Float paid;
	private Float unPaid;
	private Float totalFee;
	private Float lastPaidAmount;
	private Float currentPaidAmount;
	private int receiptNo;
	private int lFNo;
	private String currentPaidAmountDate;
	private String months;
	private String fineAmount;
	private StudentDTO studentDTO;
	private StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO;
	private String lastDate;
	private List<ClassWiseFeeDTO> classWiseFeeDTOs;
	private List<SectionWiseFeeDTO> sectionWiseFeeDTOs;
	private List<StudentWiseFeeDTO> studentWiseFeeDTOs;
	private List<MonthDTO> monthDTOs;
	private String discount;
	private List<FeesCategoriesDTO> feecategoriesDTOs;
	private List<Object> prvList;
	private String due;
	private String paidAmount;
	private List<FeecategoryAmountDTO> feecategoryAmountDTOs;
	private String maxMarks;
	private String gainMarks;

	public List<Object> getPrvList() {
		return prvList;
	}

	public void setPrvList(List<Object> setPrvList) {
		this.prvList = setPrvList;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public List<FeesCategoriesDTO> getFeecategoriesDTOs() {
		return feecategoriesDTOs;
	}

	public void setFeecategoriesDTOs(List<FeesCategoriesDTO> feecategoriesDTOs) {
		this.feecategoriesDTOs = feecategoriesDTOs;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public List<StudentWiseFeeDTO> getStudentWiseFeeDTOs() {
		return studentWiseFeeDTOs;
	}

	public void setStudentWiseFeeDTOs(List<StudentWiseFeeDTO> studentWiseFeeDTOs) {
		this.studentWiseFeeDTOs = studentWiseFeeDTOs;
	}

	public List<ClassWiseFeeDTO> getClassWiseFeeDTOs() {
		return classWiseFeeDTOs;
	}

	public void setClassWiseFeeDTOs(List<ClassWiseFeeDTO> classWiseFeeDTOs) {
		this.classWiseFeeDTOs = classWiseFeeDTOs;
	}

	public List<SectionWiseFeeDTO> getSectionWiseFeeDTOs() {
		return sectionWiseFeeDTOs;
	}

	public void setSectionWiseFeeDTOs(List<SectionWiseFeeDTO> sectionWiseFeeDTOs) {
		this.sectionWiseFeeDTOs = sectionWiseFeeDTOs;
	}

	public List<StudentDTO> getStudentDTOs() {
		return studentDTOs;
	}

	public void setStudentDTOs(List<StudentDTO> studentDTOs) {
		this.studentDTOs = studentDTOs;
	}

	public List<SubjectDTO> getSubjectDTOs() {
		return subjectDTOs;
	}

	public void setSubjectDTOs(List<SubjectDTO> subjectDTOs) {
		this.subjectDTOs = subjectDTOs;
	}

	

	public List<FeeDiscountDTO> getFeeDiscountDTOs() {
		return feeDiscountDTOs;
	}

	public void setFeeDiscountDTOs(List<FeeDiscountDTO> feeDiscountDTOs) {
		this.feeDiscountDTOs = feeDiscountDTOs;
	}

	public Float getPaid() {
		return paid;
	}

	public void setPaid(Float paid) {
		this.paid = paid;
	}

	public Float getUnPaid() {
		return unPaid;
	}

	public void setUnPaid(Float unPaid) {
		this.unPaid = unPaid;
	}

	public Float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	public List<StudentFeeSubmissionDetailsDTO> getStudentFeeSubmissionDetailsDTOs() {
		return studentFeeSubmissionDetailsDTOs;
	}

	public void setStudentFeeSubmissionDetailsDTOs(
			List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs) {
		this.studentFeeSubmissionDetailsDTOs = studentFeeSubmissionDetailsDTOs;
	}

	public Float getLastPaidAmount() {
		return lastPaidAmount;
	}

	public void setLastPaidAmount(Float lastPaidAmount) {
		this.lastPaidAmount = lastPaidAmount;
	}

	public Float getCurrentPaidAmount() {
		return currentPaidAmount;
	}

	public void setCurrentPaidAmount(Float currentPaidAmount) {
		this.currentPaidAmount = currentPaidAmount;
	}

	public String getCurrentPaidAmountDate() {
		return currentPaidAmountDate;
	}

	public void setCurrentPaidAmountDate(String currentPaidAmountDate) {
		this.currentPaidAmountDate = currentPaidAmountDate;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

	public int getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public int getlFNo() {
		return lFNo;
	}

	public void setlFNo(int lFNo) {
		this.lFNo = lFNo;
	}

	public StudentDTO getStudentDTO() {
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	public StudentFeeSubmissionDetailsDTO getStudentFeeSubmissionDetailsDTO() {
		return studentFeeSubmissionDetailsDTO;
	}

	public void setStudentFeeSubmissionDetailsDTO(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO) {
		this.studentFeeSubmissionDetailsDTO = studentFeeSubmissionDetailsDTO;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public List<MonthDTO> getMonthDTOs() {
		return monthDTOs;
	}

	public void setMonthDTOs(List<MonthDTO> monthDTOs) {
		this.monthDTOs = monthDTOs;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public List<FeecategoryAmountDTO> getFeecategoryAmountDTOs() {
		return feecategoryAmountDTOs;
	}

	public void setFeecategoryAmountDTOs(
			List<FeecategoryAmountDTO> feecategoryAmountDTOs) {
		this.feecategoryAmountDTOs = feecategoryAmountDTOs;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getGainMarks() {
		return gainMarks;
	}

	public void setGainMarks(String gainMarks) {
		this.gainMarks = gainMarks;
	}

}
