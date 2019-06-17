package org.brijframework.college.models.dto;

import java.util.List;

public class AnnualExamReportDTO {
	private int annaulExamReportId;
	private int sessionId;
	private String sessionName;
	private int ClassId;
	private String ClassName;
	private int sectionId;
	private String sectionName;
	private String studentId;
	private String studentName;
	private int subjectId;
	private String subjectName;
	private Double maxMarks;
	private Double gainMarks;
	private String annualExamType;
	private String examType;
	private List<AnnualExamReportDTO> annualExamReportDTOs;
	private String fatherName;
	private int rollno;
	List<AnnuallySubjectMarksDTO> annuallySubjectMarksDTOs;
	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public int getAnnaulExamReportId() {
		return annaulExamReportId;
	}

	public void setAnnaulExamReportId(int annaulExamReportId) {
		this.annaulExamReportId = annaulExamReportId;
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

	public Double getGainMarks() {
		return gainMarks;
	}

	public void setGainMarks(Double gainMarks) {
		this.gainMarks = gainMarks;
	}

	public String getAnnualExamType() {
		return annualExamType;
	}

	public void setAnnualExamType(String annualExamType) {
		this.annualExamType = annualExamType;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public List<AnnualExamReportDTO> getAnnualExamReportDTOs() {
		return annualExamReportDTOs;
	}

	public void setAnnualExamReportDTOs(
			List<AnnualExamReportDTO> annualExamReportDTOs) {
		this.annualExamReportDTOs = annualExamReportDTOs;
	}

	public List<AnnuallySubjectMarksDTO> getAnnuallySubjectMarksDTOs() {
		return annuallySubjectMarksDTOs;
	}

	public void setAnnuallySubjectMarksDTOs(
			List<AnnuallySubjectMarksDTO> annuallySubjectMarksDTOs) {
		this.annuallySubjectMarksDTOs = annuallySubjectMarksDTOs;
	}

}
