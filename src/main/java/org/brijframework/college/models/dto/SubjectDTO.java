package org.brijframework.college.models.dto;

import java.util.List;

public class SubjectDTO {
	private Integer id;
	private String createdAt;
	private String updatedAt;
	private String active;
	private List<StudentAttendanceDTO> attendanceDTOs;
	private int totalAttendance;
	private int totalStudnetPresent;
	private int totalStudentAbsent;
	private double percentAttendance;
	private String email;
	private int month;
	private int year;
	private double averageAttendance;
	private List<CommonDTO> commonDTOs;
	private String halfYearlyTU;
	private String halfYearlyExam;
	private String halfYearlyTotal;
	private String annualYearlyTU;
	private String annualYearlyExam;
	private String annualYearlyTotal;
	private String grandTotal;
	private String subjectName;
	private int classId;
	private String className;
	private int sectionId;
	private String sectionName;
	private int sessionId;
	private String sessionDuration;
	private double maxMarks;
	private String admissionNo;
	private String fullName;
	private double gainMarks;
	
	
	

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getGainMarks() {
		return gainMarks;
	}

	public void setGainMarks(double gainMarks) {
		this.gainMarks = gainMarks;
	}

	public double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionDuration() {
		return sessionDuration;
	}

	public void setSessionDuration(String sessionDuration) {
		this.sessionDuration = sessionDuration;
	}

	public double getAverageAttendance() {
		return averageAttendance;
	}

	public void setAverageAttendance(double averageAttendance) {
		this.averageAttendance = averageAttendance;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPercentAttendance() {
		return percentAttendance;
	}

	public void setPercentAttendance(double percentAttendance) {
		this.percentAttendance = percentAttendance;
	}

	public int getTotalAttendance() {
		return totalAttendance;
	}

	public void setTotalAttendance(int totalAttendance) {
		this.totalAttendance = totalAttendance;
	}

	public int getTotalStudnetPresent() {
		return totalStudnetPresent;
	}

	public void setTotalStudnetPresent(int totalStudnetPresent) {
		this.totalStudnetPresent = totalStudnetPresent;
	}

	public int getTotalStudentAbsent() {
		return totalStudentAbsent;
	}

	public void setTotalStudentAbsent(int totalStudentAbsent) {
		this.totalStudentAbsent = totalStudentAbsent;
	}

	public List<StudentAttendanceDTO> getAttendanceDTOs() {
		return attendanceDTOs;
	}

	public void setAttendanceDTOs(List<StudentAttendanceDTO> attendanceDTOs) {
		this.attendanceDTOs = attendanceDTOs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public List<CommonDTO> getCommonDTOs() {
		return commonDTOs;
	}

	public void setCommonDTOs(List<CommonDTO> commonDTOs) {
		this.commonDTOs = commonDTOs;
	}

	public String getHalfYearlyTU() {
		return halfYearlyTU;
	}

	public void setHalfYearlyTU(String halfYearlyTU) {
		this.halfYearlyTU = halfYearlyTU;
	}

	public String getHalfYearlyExam() {
		return halfYearlyExam;
	}

	public void setHalfYearlyExam(String halfYearlyExam) {
		this.halfYearlyExam = halfYearlyExam;
	}

	public String getHalfYearlyTotal() {
		return halfYearlyTotal;
	}

	public void setHalfYearlyTotal(String halfYearlyTotal) {
		this.halfYearlyTotal = halfYearlyTotal;
	}

	public String getAnnualYearlyTU() {
		return annualYearlyTU;
	}

	public void setAnnualYearlyTU(String annualYearlyTU) {
		this.annualYearlyTU = annualYearlyTU;
	}

	public String getAnnualYearlyExam() {
		return annualYearlyExam;
	}

	public void setAnnualYearlyExam(String annualYearlyExam) {
		this.annualYearlyExam = annualYearlyExam;
	}

	public String getAnnualYearlyTotal() {
		return annualYearlyTotal;
	}

	public void setAnnualYearlyTotal(String annualYearlyTotal) {
		this.annualYearlyTotal = annualYearlyTotal;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
