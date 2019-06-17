package org.brijframework.college.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "book_supplier")
public class BookSupplier extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_purchase_id")
	private Integer stockPurchaseId;
	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "balance_amount")
	private Integer balanceAmount;
	@Column(name = "gross_amount")
	private Integer grossAmount;
	@Column(name = "paid_amount")
	private Integer paidAmount;
	@Column(name = "date_of_purchase")
	private Date dateOfPurchase;
	@Column(name = "receipt_no")
	private Integer receiptNo;
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
	@Column(name = "in_word")
	private String inword;
	@Column(name = "supplier_no")
	private String supplierNo;
	@Column(name = "is_active")
	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getStockPurchaseId() {
		return stockPurchaseId;
	}

	public void setStockPurchaseId(Integer stockPurchaseId) {
		this.stockPurchaseId = stockPurchaseId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Integer balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Integer getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Integer grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Integer getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Integer paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Integer getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(Integer receiptNo) {
		this.receiptNo = receiptNo;
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

	public String getInword() {
		return inword;
	}

	public void setInword(String inword) {
		this.inword = inword;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
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
