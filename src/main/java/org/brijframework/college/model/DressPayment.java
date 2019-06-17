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
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "dress_payment")
public class DressPayment extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dress_payment_id")
	private Integer dressPaymentId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dress_id")
	private Dress dress;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_purchase_id")
	private UniformPurchase uniformPurchase;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "total_amount")
	private Integer totalAmount;
	@Column(name = "paid_amount")
	private Integer paidAmount;
	@Column(name = "in_word")
	private String inword;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Students students;
	@Column(name = "receipt_no")
	private Integer receiptNo;
	@Column(name = "pay_date")
	private Date payDate;
	@Column(name = "discount")
	private Integer discount;
	@Column(name = "dues")
	private Integer dues;
	@Column(name = "payment_mode")
	private String paymentMode;
	@Column(name = "cheque_no")
	private Integer chequeNo;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "paid_status")
	private String paidStatus;
	@Column(name = "dues_receiptno")
	private Integer duesReceiptNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_wise_fee_id")
	private StudentWiseFee studentFee;
	
	
	public StudentWiseFee getStudentFee() {
		return studentFee;
	}
	public void setStudentFee(StudentWiseFee studentFee) {
		this.studentFee = studentFee;
	}
	public Integer getDuesReceiptNo() {
		return duesReceiptNo;
	}
	public void setDuesReceiptNo(Integer duesReceiptNo) {
		this.duesReceiptNo = duesReceiptNo;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public Integer getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(Integer chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getDues() {
		return dues;
	}
	public void setDues(Integer dues) {
		this.dues = dues;
	}
	public UniformPurchase getUniformPurchase() {
		return uniformPurchase;
	}
	public void setUniformPurchase(UniformPurchase uniformPurchase) {
		this.uniformPurchase = uniformPurchase;
	}
	public Integer getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(Integer receiptNo) {
		this.receiptNo = receiptNo;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Integer getDressPaymentId() {
		return dressPaymentId;
	}
	public void setDressPaymentId(Integer dressPaymentId) {
		this.dressPaymentId = dressPaymentId;
	}
	public Dress getDress() {
		return dress;
	}
	public void setDress(Dress dress) {
		this.dress = dress;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getInword() {
		return inword;
	}
	public void setInword(String inword) {
		this.inword = inword;
	}
	public Students getStudents() {
		return students;
	}
	public void setStudents(Students students) {
		this.students = students;
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	

}
