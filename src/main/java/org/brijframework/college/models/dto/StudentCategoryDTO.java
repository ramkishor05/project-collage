package org.brijframework.college.models.dto;

import java.util.List;

import org.brijframework.college.model.Students;

public class StudentCategoryDTO {

	private Integer studentCategoriesId;
	private String studentCategoriesName;
	private boolean active;
	private List<Students> students;

	
	public Integer getStudentCategoriesId() {
		return studentCategoriesId;
	}
	public void setStudentCategoriesId(Integer studentCategoriesId) {
		this.studentCategoriesId = studentCategoriesId;
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
