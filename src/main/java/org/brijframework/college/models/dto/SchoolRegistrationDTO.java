package org.brijframework.college.models.dto;

import org.springframework.web.multipart.MultipartFile;

public class SchoolRegistrationDTO {
	private Integer schoolRegistrationId;
	private String schoolName;
	private String schoolAddress;
	private String schoolLogoName;
	private String contactNo;
	private MultipartFile schoolLogo; 
	
	
	public MultipartFile getSchoolLogo() {
		return schoolLogo;
	}
	public void setSchoolLogo(MultipartFile schoolLogo) {
		this.schoolLogo = schoolLogo;
	}
	public Integer getSchoolRegistrationId() {
		return schoolRegistrationId;
	}
	public void setSchoolRegistrationId(Integer schoolRegistrationId) {
		this.schoolRegistrationId = schoolRegistrationId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getSchoolLogoName() {
		return schoolLogoName;
	}
	public void setSchoolLogoName(String schoolLogoName) {
		this.schoolLogoName = schoolLogoName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
}
