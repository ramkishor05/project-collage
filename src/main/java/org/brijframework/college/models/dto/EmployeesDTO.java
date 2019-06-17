package org.brijframework.college.models.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lovely
 * 
 */
public class EmployeesDTO {

	private Integer id;

	private String firstName;

	private String lastName;
	private String fullName;
	private String motherName;

	private String fatherName;

	private String dob;

	private String gender;

	private String highestQualification;

	private String passoutMonth;

	private String passoutYear;

	private String occupation;

	private String bloodGroup;

	private String birthPlace;

	private String motherTongue;

	private String address;

	private String stateName;

	private String city;
	private int cityId;
	private int stateId;
	private int countryId;

	private String pinCode;

	private String mobile;

	private String email;

	private MultipartFile imamgeName;
	private String imagename;

	private String updatedAt;

	private Integer departmentId;

	private String joiningDate;
	private String departmentName;
	private String active;
	private String countryName;
	private int totalNoOfDays;
	private int noOfDaysPresent;
	private int noOfDaysAbsent;
	private double percentageOfAttendance;
	public List<EmployeeAttendanceDTO> employeeAttendanceDTOs;
	private String from;
	private String to;
	private String userName;
	private int userId;
	private String password;
	private String status;
	private String className;
	private String sectionName;
	private int salary;
	public List<EmployeesDTO> employeeslist;
	private List<EmployeeDocumentDTO> documentList;
	
	public List<EmployeeDocumentDTO> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<EmployeeDocumentDTO> documentList) {
		this.documentList = documentList;
	}

	public List<EmployeesDTO> getEmployeeslist() {
		return employeeslist;
	}

	public void setEmployeeslist(List<EmployeesDTO> employeeslist) {
		this.employeeslist = employeeslist;
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getTotalNoOfDays() {
		return totalNoOfDays;
	}

	public void setTotalNoOfDays(int totalNoOfDays) {
		this.totalNoOfDays = totalNoOfDays;
	}

	public int getNoOfDaysPresent() {
		return noOfDaysPresent;
	}

	public void setNoOfDaysPresent(int noOfDaysPresent) {
		this.noOfDaysPresent = noOfDaysPresent;
	}

	public int getNoOfDaysAbsent() {
		return noOfDaysAbsent;
	}

	public void setNoOfDaysAbsent(int noOfDaysAbsent) {
		this.noOfDaysAbsent = noOfDaysAbsent;
	}

	public double getPercentageOfAttendance() {
		return percentageOfAttendance;
	}

	public void setPercentageOfAttendance(double percentageOfAttendance) {
		this.percentageOfAttendance = percentageOfAttendance;
	}

	public List<EmployeeAttendanceDTO> getEmployeeAttendanceDTOs() {
		return employeeAttendanceDTOs;
	}

	public void setEmployeeAttendanceDTOs(
			List<EmployeeAttendanceDTO> employeeAttendanceDTOs) {
		this.employeeAttendanceDTOs = employeeAttendanceDTOs;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getPassoutMonth() {
		return passoutMonth;
	}

	public void setPassoutMonth(String passoutMonth) {
		this.passoutMonth = passoutMonth;
	}

	public String getPassoutYear() {
		return passoutYear;
	}

	public void setPassoutYear(String passoutYear) {
		this.passoutYear = passoutYear;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public MultipartFile getImamgeName() {
		return imamgeName;
	}

	public void setImamgeName(MultipartFile imamgeName) {
		this.imamgeName = imamgeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * public File getImamgeName() { return imamgeName; }
	 * 
	 * public void setImamgeName(File imamgeName) { this.imamgeName =
	 * imamgeName; }
	 */

}
