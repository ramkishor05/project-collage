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
@Table(name = "employee_salary")
public class EmployeeSalary extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_salary_id")
	private Integer employeeSalaryId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employees employees;
	@Column(name = "salary_amount")
	private Integer salaryAmount;
	@Column(name = "paid_amount")
	private Integer paidAmount;
	@Column(name = "date_of_payment")
	private Date dateOfPayment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "month_id")
	private Month month;
	@Column(name = "is_active")
	private boolean active;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@Column(name = "salary_paid_status")
	private String salaryPaidStatus;
	@Column(name = "slip_no")
	private Integer slipNo;
	@Column(name = "paid_by")
	private String paidBy;
	@Column(name = "paid_amount_in_word")
	private String paidAmountInWord;
	@Column(name = "cheque_no")
	private String chequeNo;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "fine_amount")
	private Integer fine;
	@Column(name = "incentive")
	private Integer incentive;
	@Column(name = "due")
	private Integer due;
	@Column(name = "salary")
	private Integer salary;
	@Column(name="advance")
	private Integer advance;
	
	public Integer getAdvance() {
		return advance;
	}
	public void setAdvance(Integer advance) {
		this.advance = advance;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Integer getDue() {
		return due;
	}
	public void setDue(Integer due) {
		this.due = due;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public Integer getIncentive() {
		return incentive;
	}
	public void setIncentive(Integer incentive) {
		this.incentive = incentive;
	}
	public Integer getFine() {
		return fine;
	}
	public void setFine(Integer fine) {
		this.fine = fine;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	public String getPaidAmountInWord() {
		return paidAmountInWord;
	}
	public void setPaidAmountInWord(String paidAmountInWord) {
		this.paidAmountInWord = paidAmountInWord;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Integer getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(Integer slipNo) {
		this.slipNo = slipNo;
	}
	public String getSalaryPaidStatus() {
		return salaryPaidStatus;
	}
	public void setSalaryPaidStatus(String salaryPaidStatus) {
		this.salaryPaidStatus = salaryPaidStatus;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Integer getEmployeeSalaryId() {
		return employeeSalaryId;
	}
	public void setEmployeeSalaryId(Integer employeeSalaryId) {
		this.employeeSalaryId = employeeSalaryId;
	}
	public Employees getEmployees() {
		return employees;
	}
	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
	public Integer getSalaryAmount() {
		return salaryAmount;
	}
	public void setSalaryAmount(Integer salaryAmount) {
		this.salaryAmount = salaryAmount;
	}
	public Integer getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		this.month = month;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
