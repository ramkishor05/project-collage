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
@Table(name = "uniform_purchase")
public class UniformPurchase extends AbstractPO<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_purchase_id")
	private Integer productPurchaseId;
	@Column(name = "product_no")
	private Integer productNo;
	@Column(name = "uniform_name")
	private String uniformName;
	@Column(name = "uniform_category")
	private String uniformCategory;
	@Column(name = "uniform_size")
	private String uniformSize;
	@Column(name = "quantity_bought")
	private Integer boughtQuantity;
	@Column(name = "quantity_remaining")
	private Integer remainingQuantity;
	@Column(name = "date_of_purchase")
	private Date dateOfPurchase;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "is_active")
	private Boolean active;
	@Column(name = "shop_name")
	private String shopName;
	@Column(name = "uniform_price")
	private Integer uniformPrice;
	@Column(name = "net_amount")
	private Integer netAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_purchase_id")
	private UniformSupplier stocksupplier;
	
	
	
	
	public UniformSupplier getStocksupplier() {
		return stocksupplier;
	}
	public void setStocksupplier(UniformSupplier stocksupplier) {
		this.stocksupplier = stocksupplier;
	}
	public Integer getProductPurchaseId() {
		return productPurchaseId;
	}
	public void setProductPurchaseId(Integer productPurchaseId) {
		this.productPurchaseId = productPurchaseId;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
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
	public Integer getBoughtQuantity() {
		return boughtQuantity;
	}
	public void setBoughtQuantity(Integer boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}
	public Integer getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getUniformPrice() {
		return uniformPrice;
	}
	public void setUniformPrice(Integer uniformPrice) {
		this.uniformPrice = uniformPrice;
	}
	
	
	public Integer getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Integer netAmount) {
		this.netAmount = netAmount;
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
