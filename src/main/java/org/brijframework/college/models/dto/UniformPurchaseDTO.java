package org.brijframework.college.models.dto;

import java.util.List;

public class UniformPurchaseDTO {
	
	private int productPurchaseId;
	private int productNo;
	private String uniformName;
	private String uniformCategory;
	private String uniformSize;
	private int boughtQuantity;
	private int remainingQuantity;
	private String shopName;
	private int amount;
	private String dateOfPurchase;
	private int uniformPrice;
	private int totalPrice;
	private List<String> categoryList;
	private List<AllotDressDTO> allotedList;
	private String studentId;
	private int supplierId;
	private int netAmount;
	private int balanceAmount;
	private String paymentMode;
	private String bankName;
	private int paidAmount;
	private int chequeno;
	
	
	 
	public int getChequeno() {
		return chequeno;
	}
	public void setChequeno(int chequeno) {
		this.chequeno = chequeno;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}
	public int getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(int balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
public List<AllotDressDTO> getAllotedList() {
		return allotedList;
	}
	public void setAllotedList(List<AllotDressDTO> allotedList) {
		this.allotedList = allotedList;
	}
public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
public int getUniformPrice() {
		return uniformPrice;
	}
	public void setUniformPrice(int uniformPrice) {
		this.uniformPrice = uniformPrice;
	}
	public int getBoughtQuantity() {
		return boughtQuantity;
	}
	public void setBoughtQuantity(int boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}
	
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public int getProductPurchaseId() {
		return productPurchaseId;
	}
	public void setProductPurchaseId(int productPurchaseId) {
		this.productPurchaseId = productPurchaseId;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getUniformName() {
		return uniformName;
	}
	public void setUniformName(String uniformName) {
		this.uniformName = uniformName;
	}
	public String getUniformCategory() {
		return uniformCategory;
	}
	public void setUniformCategory(String uniformCategory) {
		this.uniformCategory = uniformCategory;
	}
	public String getUniformSize() {
		return uniformSize;
	}
	public void setUniformSize(String uniformSize) {
		this.uniformSize = uniformSize;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	

}
