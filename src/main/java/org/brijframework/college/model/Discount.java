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

@SuppressWarnings("serial")
@Entity
@Table(name = "discount")
public class Discount extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "discount_id")
	private Integer discountId;
	@Column(name = "discount_about")
	private String discountAbout;
	@Column(name = "discount_amount")
	private double discountAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Students students;
	@Column(name = "is_delete")
	private boolean active;
	@Column(name = "created_at")
	private Date cratedAt;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_fee_sub_detail_id")
	private StudentFeeSubmissionDetails studentFeeSubmissionDetails;

	public Date getCratedAt() {
		return cratedAt;
	}

	public void setCratedAt(Date cratedAt) {
		this.cratedAt = cratedAt;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getDiscountAbout() {
		return discountAbout;
	}

	public void setDiscountAbout(String discountAbout) {
		this.discountAbout = discountAbout;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
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

	public StudentFeeSubmissionDetails getStudentFeeSubmissionDetails() {
		return studentFeeSubmissionDetails;
	}

	public void setStudentFeeSubmissionDetails(
			StudentFeeSubmissionDetails studentFeeSubmissionDetails) {
		this.studentFeeSubmissionDetails = studentFeeSubmissionDetails;
	}

}
