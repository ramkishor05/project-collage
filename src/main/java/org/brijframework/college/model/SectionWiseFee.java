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
@Table(name = "section_wise_fee")
public class SectionWiseFee extends AbstractPO<Integer>{
	
	@Id
	@Column(name = "section_wise_fee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sectionWiseFeeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses classes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "month_id")
	private Month month;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private FeesCategories feesCategories;
	
	@Column(name = "fee_amount")
	private double feeAmount;
	@Column(name = "is_active")
	private boolean active;
	
	
	public Integer getSectionWiseFeeId() {
		return sectionWiseFeeId;
	}
	public void setSectionWiseFeeId(Integer sectionWiseFeeId) {
		this.sectionWiseFeeId = sectionWiseFeeId;
	}
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
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		this.month = month;
	}
	public FeesCategories getFeesCategories() {
		return feesCategories;
	}
	public void setFeesCategories(FeesCategories feesCategories) {
		this.feesCategories = feesCategories;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
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
	public StudentClasses getClasses() {
		return classes;
	}
	public void setClasses(StudentClasses classes) {
		this.classes = classes;
	}
	
	
	

}
