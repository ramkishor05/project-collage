package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "parents")
public class Parents extends AbstractPO<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parents_id")
	private Integer id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "parents_name")
	private String parentsName;
	@Column(name = "parents_email")
	private String parentsEmail;
	@Column(name = "parents_mobile")
	private String parentsMobile;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_admission_number")
	private Students students;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getParentsName() {
		return parentsName;
	}

	public void setParentsName(String parentsName) {
		this.parentsName = parentsName;
	}

	public String getParentsEmail() {
		return parentsEmail;
	}

	public void setParentsEmail(String parentsEmail) {
		this.parentsEmail = parentsEmail;
	}

	public String getParentsMobile() {
		return parentsMobile;
	}

	public void setParentsMobile(String parentsMobile) {
		this.parentsMobile = parentsMobile;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}
}
