package org.brijframework.college.models.dto;

import org.springframework.web.multipart.MultipartFile;

public class StudentAdmissionDTO {
	private int studentId;
	private int sNo;
	private int classId;
	private String className;
	private String regNo;
	private String studentName;
	private String gender;
	private String dateOfBirth;
	private String motherTounge;
	private int countryId;
	private String countryName;
	private String physicalDisability;
	private MultipartFile photo;
	private String imageName;
	private String resAddress;
	private String phoneNo;
	private String fatherName;
	private String fatherEducation;
	private String fatherOccupation;
	private String fatherAnnualIncome;
	private String fatherOfficeAddress;
	private String fatherPhoneNo;
	private String motherName;
	private String motherEducation;
	private String motherOccupation;
	private String motherAnnualIncome;
	private String motherOfficeAddress;
	private String motherPhoneNo;
	private String brotherOrSisterName1;
	private String brotherOrSisterAge1;
	private String brotherOrSisterClass1;
	private String brotherOrSisterSchoolOrCollege1;
	private String brotherOrSisterName2;
	private String brotherOrSisterAge2;
	private String brotherOrSisterClass2;
	private String brotherOrSisterSchoolOrCollege2;
	private String writtenOrIntervieDate;
	private String admitInClass;
	private String principalOrDirectorName;
	private String admissionNo;
	private String admissionDate;
	private String feeReceiptNo;
	private String feeReceiptDate;
	private int stateId;
	private int cityId;
	private int sectionId;
	private String stateName;
	private String cityName;
	private String sectionName;
	private String schoolName;
	private String schoolPhone;
	private String schoolAddress;
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMotherTounge() {
		return motherTounge;
	}

	public void setMotherTounge(String motherTounge) {
		this.motherTounge = motherTounge;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPhysicalDisability() {
		return physicalDisability;
	}

	public void setPhysicalDisability(String physicalDisability) {
		this.physicalDisability = physicalDisability;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getResAddress() {
		return resAddress;
	}

	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherEducation() {
		return fatherEducation;
	}

	public void setFatherEducation(String fatherEducation) {
		this.fatherEducation = fatherEducation;
	}

	public String getFatherOccupation() {
		return fatherOccupation;
	}

	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}

	public String getFatherAnnualIncome() {
		return fatherAnnualIncome;
	}

	public void setFatherAnnualIncome(String fatherAnnualIncome) {
		this.fatherAnnualIncome = fatherAnnualIncome;
	}

	public String getFatherOfficeAddress() {
		return fatherOfficeAddress;
	}

	public void setFatherOfficeAddress(String fatherOfficeAddress) {
		this.fatherOfficeAddress = fatherOfficeAddress;
	}

	public String getFatherPhoneNo() {
		return fatherPhoneNo;
	}

	public void setFatherPhoneNo(String fatherPhoneNo) {
		this.fatherPhoneNo = fatherPhoneNo;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherEducation() {
		return motherEducation;
	}

	public void setMotherEducation(String motherEducation) {
		this.motherEducation = motherEducation;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getMotherAnnualIncome() {
		return motherAnnualIncome;
	}

	public void setMotherAnnualIncome(String motherAnnualIncome) {
		this.motherAnnualIncome = motherAnnualIncome;
	}

	public String getMotherOfficeAddress() {
		return motherOfficeAddress;
	}

	public void setMotherOfficeAddress(String motherOfficeAddress) {
		this.motherOfficeAddress = motherOfficeAddress;
	}

	public String getMotherPhoneNo() {
		return motherPhoneNo;
	}

	public void setMotherPhoneNo(String motherPhoneNo) {
		this.motherPhoneNo = motherPhoneNo;
	}

	public String getBrotherOrSisterName1() {
		return brotherOrSisterName1;
	}

	public void setBrotherOrSisterName1(String brotherOrSisterName1) {
		this.brotherOrSisterName1 = brotherOrSisterName1;
	}

	public String getBrotherOrSisterAge1() {
		return brotherOrSisterAge1;
	}

	public void setBrotherOrSisterAge1(String brotherOrSisterAge1) {
		this.brotherOrSisterAge1 = brotherOrSisterAge1;
	}

	public String getBrotherOrSisterClass1() {
		return brotherOrSisterClass1;
	}

	public void setBrotherOrSisterClass1(String brotherOrSisterClass1) {
		this.brotherOrSisterClass1 = brotherOrSisterClass1;
	}

	public String getBrotherOrSisterSchoolOrCollege1() {
		return brotherOrSisterSchoolOrCollege1;
	}

	public void setBrotherOrSisterSchoolOrCollege1(
			String brotherOrSisterSchoolOrCollege1) {
		this.brotherOrSisterSchoolOrCollege1 = brotherOrSisterSchoolOrCollege1;
	}

	public String getBrotherOrSisterName2() {
		return brotherOrSisterName2;
	}

	public void setBrotherOrSisterName2(String brotherOrSisterName2) {
		this.brotherOrSisterName2 = brotherOrSisterName2;
	}

	public String getBrotherOrSisterAge2() {
		return brotherOrSisterAge2;
	}

	public void setBrotherOrSisterAge2(String brotherOrSisterAge2) {
		this.brotherOrSisterAge2 = brotherOrSisterAge2;
	}

	public String getBrotherOrSisterClass2() {
		return brotherOrSisterClass2;
	}

	public void setBrotherOrSisterClass2(String brotherOrSisterClass2) {
		this.brotherOrSisterClass2 = brotherOrSisterClass2;
	}

	public String getBrotherOrSisterSchoolOrCollege2() {
		return brotherOrSisterSchoolOrCollege2;
	}

	public void setBrotherOrSisterSchoolOrCollege2(
			String brotherOrSisterSchoolOrCollege2) {
		this.brotherOrSisterSchoolOrCollege2 = brotherOrSisterSchoolOrCollege2;
	}

	public String getWrittenOrIntervieDate() {
		return writtenOrIntervieDate;
	}

	public void setWrittenOrIntervieDate(String writtenOrIntervieDate) {
		this.writtenOrIntervieDate = writtenOrIntervieDate;
	}

	public String getAdmitInClass() {
		return admitInClass;
	}

	public void setAdmitInClass(String admitInClass) {
		this.admitInClass = admitInClass;
	}

	public String getPrincipalOrDirectorName() {
		return principalOrDirectorName;
	}

	public void setPrincipalOrDirectorName(String principalOrDirectorName) {
		this.principalOrDirectorName = principalOrDirectorName;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getFeeReceiptNo() {
		return feeReceiptNo;
	}

	public void setFeeReceiptNo(String feeReceiptNo) {
		this.feeReceiptNo = feeReceiptNo;
	}

	public String getFeeReceiptDate() {
		return feeReceiptDate;
	}

	public void setFeeReceiptDate(String feeReceiptDate) {
		this.feeReceiptDate = feeReceiptDate;
	}

}
