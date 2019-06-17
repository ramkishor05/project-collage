package org.brijframework.college.models.dto;

import java.util.List;

public class UniformSupplierDTO {

	private int stockPurchaseId;
	private String supplierName;
	private int balanceAmount;
	private int grossAmount;
	private String dateOfPurchase;
	private int paidAmount;
	private int supplierId;
	private int discount;
	private int dues;
	private String chequeno;
	private String bankName;
	private String paidStatus;
	private String paymentMode;
	private int netPayableAmount;
	private List<UniformPurchaseDTO> uniformPurchase;
	private int receiptNo;
	private String supplierNo;
	private String inwords;
	
	
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public int getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}
	public List<UniformPurchaseDTO> getUniformPurchase() {
		return uniformPurchase;
	}
	public void setUniformPurchase(List<UniformPurchaseDTO> uniformPurchase) {
		this.uniformPurchase = uniformPurchase;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getDues() {
		return dues;
	}
	public void setDues(int dues) {
		this.dues = dues;
	}
	public String getChequeno() {
		return chequeno;
	}
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
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
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public int getNetPayableAmount() {
		return netPayableAmount;
	}
	public void setNetPayableAmount(int netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
	public String getInwords() {
		return inwords;
	}
	public void setInwords(String inwords) {
		this.inwords = inwords;
	}
	
	
	
	public int getStockPurchaseId() {
		return stockPurchaseId;
	}
	public void setStockPurchaseId(int stockPurchaseId) {
		this.stockPurchaseId = stockPurchaseId;
	}
	public int getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(int grossAmount) {
		this.grossAmount = grossAmount;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(int balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	
}
