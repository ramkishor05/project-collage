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

@Entity
@Table(name = "edit_fee_amount")
@SuppressWarnings("serial")
public class EditFeeAmount extends AbstractPO<Integer> {
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "edit_fee_amount_id")
	private Integer editFeeAmountId;
	@Column(name = "discount")
	private Double discount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "month_id")
	private Month month;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses studentClasses;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fee_category_id")
	private FeesCategories feesCategories;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Students students;

	public Integer getEditFeeAmountId() {
		return editFeeAmountId;
	}

	public void setEditFeeAmountId(Integer editFeeAmountId) {
		this.editFeeAmountId = editFeeAmountId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public StudentClasses getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(StudentClasses studentClasses) {
		this.studentClasses = studentClasses;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public FeesCategories getFeesCategories() {
		return feesCategories;
	}

	public void setFeesCategories(FeesCategories feesCategories) {
		this.feesCategories = feesCategories;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

}
