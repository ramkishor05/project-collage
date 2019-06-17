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
@Table(name = "student_transfer")
public class StudentTransfer extends AbstractPO<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transfer_id")
	private Integer transferId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Students student;
	@Column(name = "date_of_withdrawl")
	private Date dateOfWithdrawl;
	@Column(name = "in_word_dob")
	private String inWordDob;
	@Column(name = "religion_caste")
	private String religionCaste;
	@Column(name = "new_class")
	private String newClassName;
	@Column(name = "newclass_inword")
	private String newClassInWord;
	@Column(name = "conduct")
	private String conduct;
	@Column(name = "result")
	private String result;
	@Column(name = "is_active")
	private Boolean active;
	@Column(name="class_in_word")
	private String classInWord;
	
	

	public String getClassInWord() {
		return classInWord;
	}

	public void setClassInWord(String classInWord) {
		this.classInWord = classInWord;
	}

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Date getDateOfWithdrawl() {
		return dateOfWithdrawl;
	}

	public void setDateOfWithdrawl(Date dateOfWithdrawl) {
		this.dateOfWithdrawl = dateOfWithdrawl;
	}

	public String getInWordDob() {
		return inWordDob;
	}

	public void setInWordDob(String inWordDob) {
		this.inWordDob = inWordDob;
	}

	public String getReligionCaste() {
		return religionCaste;
	}

	public void setReligionCaste(String religionCaste) {
		this.religionCaste = religionCaste;
	}

	public String getNewClassName() {
		return newClassName;
	}

	public void setNewClassName(String newClassName) {
		this.newClassName = newClassName;
	}

	public String getNewClassInWord() {
		return newClassInWord;
	}

	public void setNewClassInWord(String newClassInWord) {
		this.newClassInWord = newClassInWord;
	}

	public String getConduct() {
		return conduct;
	}

	public void setConduct(String conduct) {
		this.conduct = conduct;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
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
