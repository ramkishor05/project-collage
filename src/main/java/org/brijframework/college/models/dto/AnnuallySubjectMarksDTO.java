package org.brijframework.college.models.dto;

import java.util.List;

public class AnnuallySubjectMarksDTO {
	private int annuaallySubjectMarksId;
	private String sessionName;
	private int sessionId;
	private String className;
	private int classId;;
	private String sectionName;
	private int sectionId;
	private String examReportType;
	private String examType;
	private String subjectsName;
	private int subjectsId;
	private String maxMarks;
	private String gainMarks;
	private List<AnnualExamReportDTO> annualExamReportDTOs;

	public int getAnnuaallySubjectMarksId() {
		return annuaallySubjectMarksId;
	}

	public void setAnnuaallySubjectMarksId(int annuaallySubjectMarksId) {
		this.annuaallySubjectMarksId = annuaallySubjectMarksId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getExamReportType() {
		return examReportType;
	}

	public void setExamReportType(String examReportType) {
		this.examReportType = examReportType;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getSubjectsName() {
		return subjectsName;
	}

	public void setSubjectsName(String subjectsName) {
		this.subjectsName = subjectsName;
	}

	public int getSubjectsId() {
		return subjectsId;
	}

	public void setSubjectsId(int subjectsId) {
		this.subjectsId = subjectsId;
	}

	public List<AnnualExamReportDTO> getAnnualExamReportDTOs() {
		return annualExamReportDTOs;
	}

	public void setAnnualExamReportDTOs(
			List<AnnualExamReportDTO> annualExamReportDTOs) {
		this.annualExamReportDTOs = annualExamReportDTOs;
	}

	public String getGainMarks() {
		return gainMarks;
	}

	public void setGainMarks(String gainMarks) {
		this.gainMarks = gainMarks;
	}

}
