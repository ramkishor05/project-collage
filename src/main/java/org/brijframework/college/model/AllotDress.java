package org.brijframework.college.model;

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
@Table(name = "allot_dress")
public class AllotDress extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "allotdress_id")
	private Integer allotDressId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dress_id")
	private Dress dress;
	@Column(name = "quantity")
	private Integer quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_admission_number")
	private Students students;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_purchase_id")
	private UniformPurchase uniformPurchase;
	@Column(name = "type")
	private String type;
	@Column(name = "category")
	private String category;
	@Column(name = "size")
	private String size;
	@Column(name = "price")
	private Integer price;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "netamount")
	private Integer netamount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses classes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subjects subject;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	
		public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

		public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public StudentClasses getClasses() {
		return classes;
	}

	public void setClasses(StudentClasses classes) {
		this.classes = classes;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getNetamount() {
		return netamount;
	}

	public void setNetamount(Integer netamount) {
		this.netamount = netamount;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	public Integer getAllotDressId() {
		return allotDressId;
	}

	public void setAllotDressId(Integer allotDressId) {
		this.allotDressId = allotDressId;
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

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public UniformPurchase getUniformPurchase() {
		return uniformPurchase;
	}

	public void setUniformPurchase(UniformPurchase uniformPurchase) {
		this.uniformPurchase = uniformPurchase;
	}

}
