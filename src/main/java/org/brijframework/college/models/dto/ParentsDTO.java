package org.brijframework.college.models.dto;

public class ParentsDTO {

	private Integer id;
	private String parentsName;
	private String parentsEmail;
	private String parentsMobile;
	private String studentAdmissionNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStudentAdmissionNumber() {
		return studentAdmissionNumber;
	}

	public void setStudentAdmissionNumber(String studentAdmissionNumber) {
		this.studentAdmissionNumber = studentAdmissionNumber;
	}
}
