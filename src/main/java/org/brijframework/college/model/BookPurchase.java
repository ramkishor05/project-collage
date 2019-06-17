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
@Table(name = "book_purchase")
public class BookPurchase extends AbstractPO<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_purchase_id")
	private Integer bookPurchaseId;
	@Column(name = "product_no")
	private int productNo;
	@Column(name = "book_title")
	private String bookTitle;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "class_id")
	private StudentClasses classes;
	@Column(name = "quantity_bought")
	private int boughtQuantity;
	@Column(name = "quantity_remaining")
	private int remainingQuantity;
	@Column(name = "date_of_purchase")
	private Date dateOfPurchase;
	@Column(name = "amount")
	private int amount;
	@Column(name = "is_active")
	private Boolean active;
	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "book_price")
	private int bookPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subjects subject;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_purchase_id")
	private BookSupplier stocksupplier;
	@Column(name = "net_amount")
	private Integer netAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	
	
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Integer getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Integer netAmount) {
		this.netAmount = netAmount;
	}
	public BookSupplier getStocksupplier() {
		return stocksupplier;
	}
	public void setStocksupplier(BookSupplier stocksupplier) {
		this.stocksupplier = stocksupplier;
	}
	public Subjects getSubject() {
		return subject;
	}
	public void setSubject(Subjects subject) {
		this.subject = subject;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Integer getBookPurchaseId() {
		return bookPurchaseId;
	}
	public void setBookPurchaseId(Integer bookPurchaseId) {
		this.bookPurchaseId = bookPurchaseId;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public StudentClasses getClasses() {
		return classes;
	}
	public void setClasses(StudentClasses classes) {
		this.classes = classes;
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
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	@Override
	public Integer getId() {
		
		return null;
	}
	@Override
	public void setId(Integer id) {
	
		
	}
	

}
