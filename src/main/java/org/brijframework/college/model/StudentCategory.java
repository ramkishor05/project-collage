package org.brijframework.college.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "student_categories")
public class StudentCategory extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_categories_id")
	private Integer studentCategoriesId;
	@Column(name = "student_categories_name")
	private String studentCategoriesName;
	@Column(name = "is_active")
	private boolean active;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "religion")
	private List<Students> students;

	public Integer getStudentCategoriesId() {
		return studentCategoriesId;
	}

	public void setStudentCategoriesId(Integer studentCategoriesId) {
		this.studentCategoriesId = studentCategoriesId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentCategoriesName() {
		return studentCategoriesName;
	}

	public void setStudentCategoriesName(String studentCategoriesName) {
		this.studentCategoriesName = studentCategoriesName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Students> getStudents() {
		return students;
	}

	public void setStudents(List<Students> students) {
		this.students = students;
	}

}
