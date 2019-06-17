package org.brijframework.college.models.dto;

import java.util.List;

public class SubjectMarksDTO {
	private String studentId;
	private String subjectId;
	private String maxMarks;
	private String gainMarks;
	List<SubjectMarksDTO> subjectMarksDTOs;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getGainMarks() {
		return gainMarks;
	}

	public void setGainMarks(String gainMarks) {
		this.gainMarks = gainMarks;
	}

	public List<SubjectMarksDTO> getSubjectMarksDTOs() {
		return subjectMarksDTOs;
	}

	public void setSubjectMarksDTOs(List<SubjectMarksDTO> subjectMarksDTOs) {
		this.subjectMarksDTOs = subjectMarksDTOs;
	}

}
