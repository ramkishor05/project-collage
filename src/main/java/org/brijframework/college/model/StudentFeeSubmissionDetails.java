package org.brijframework.college.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_fee_submission_details")
@SuppressWarnings("serial")
public class StudentFeeSubmissionDetails extends AbstractPO<Integer> {
	@Id
	@Column(name = "student_fee_submission_details_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentFeeSubmissionDetailsId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_admission_number")
	private Students students;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses studentClasses;
	@Column(name = "gross_amount")
	private double grossAmount;
	@Column(name = "paid_amount")
	private Float paidAmount;
	@Column(name = "fee_paid_status")
	private String feePaidStatus;
	@Column(name = "paid_by")
	private String paidBy;
	@Column(name = "l_f_no")
	private Integer lFNo;
	@Column(name = "reciept_no")
	private Integer recieptNo;
	@Column(name = "common_reciept_no")
	private Integer commonRecieptNo;
	@Column(name = "paid_amount_in_word")
	private String paidAmountInWord;
	@Column(name = "cheque_no")
	private String chequeNo;
	@Column(name = "fee_paid_date")
	private Date feePaidDate;
	@Column(name = "bank_name")
	private String bankName;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "studentFeeSubmissionDetails")
	private StudentFine studentFine;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "studentFeeSubmissionDetails")
	private Discount discount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "month_id")
	private Month month;

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentFeeSubmissionDetailsId() {
		return studentFeeSubmissionDetailsId;
	}

	public void setStudentFeeSubmissionDetailsId(
			Integer studentFeeSubmissionDetailsId) {
		this.studentFeeSubmissionDetailsId = studentFeeSubmissionDetailsId;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public StudentClasses getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(StudentClasses studentClasses) {
		this.studentClasses = studentClasses;
	}

	public Float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getFeePaidStatus() {
		return feePaidStatus;
	}

	public void setFeePaidStatus(String feePaidStatus) {
		this.feePaidStatus = feePaidStatus;
	}

	public Date getFeePaidDate() {
		return feePaidDate;
	}

	public void setFeePaidDate(Date feePaidDate) {
		this.feePaidDate = feePaidDate;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Integer getlFNo() {
		return lFNo;
	}

	public void setlFNo(Integer lFNo) {
		this.lFNo = lFNo;
	}

	public Integer getRecieptNo() {
		return recieptNo;
	}

	public void setRecieptNo(Integer recieptNo) {
		this.recieptNo = recieptNo;
	}

	public String getPaidAmountInWord() {
		return paidAmountInWord;
	}

	public void setPaidAmountInWord(String paidAmountInWord) {
		this.paidAmountInWord = paidAmountInWord;
	}

	public StudentFine getStudentFine() {
		return studentFine;
	}

	public void setStudentFine(StudentFine studentFine) {
		this.studentFine = studentFine;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getCommonRecieptNo() {
		return commonRecieptNo;
	}

	public void setCommonRecieptNo(Integer commonRecieptNo) {
		this.commonRecieptNo = commonRecieptNo;
	}

}
