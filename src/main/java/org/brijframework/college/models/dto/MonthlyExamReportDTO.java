package org.brijframework.college.models.dto;

import java.util.List;

public class MonthlyExamReportDTO {
	private int monthlyExamReportId;
	private int sessionId;
	private String sessionName;
	private int ClassId;
	private String ClassName;
	private int sectionId;
	private String sectionName;
	private int monthId;
	private String monthName;
	private String studentId;
	private String[] studentIds;
	private String studentName;
	private int subjectId;
	private String subjectName;
	private Double maxMarks;
	private Double marks;
	private String createdAt;
	private String updatedAt;
	private List<SubjectMarksDTO> subjectMarksDTOs;

	public String[] getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String[] studentIds) {
		this.studentIds = studentIds;
	}

	public int getMonthlyExamReportId() {
		return monthlyExamReportId;
	}

	public void setMonthlyExamReportId(int monthlyExamReportId) {
		this.monthlyExamReportId = monthlyExamReportId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public int getClassId() {
		return ClassId;
	}

	public void setClassId(int classId) {
		ClassId = classId;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Double getMarks() {
		return marks;
	}

	public void setMarks(Double marks) {
		this.marks = marks;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<SubjectMarksDTO> getSubjectMarksDTOs() {
		return subjectMarksDTOs;
	}

	public void setSubjectMarksDTOs(List<SubjectMarksDTO> subjectMarksDTOs) {
		this.subjectMarksDTOs = subjectMarksDTOs;
	}

}
