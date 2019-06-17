package org.brijframework.college.models.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class StudentDTO {
	private String id;
	private String studentId;
	private String admissionNo;
	private String admissionDate;
	private String firstName;
	private String middleName;
	private String lastName;
	private int sessionId;
	private int classId;
	private int sectionId;
	private String dateOfBirth;
	private String gender;
	private String physicallyChallenged;
	private String nationality;
	private String religionName;
	private int studentCategoryId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private int cityId;
	private int stateId;
	private String state;
	private String pinCode;
	private int countryId;
	private String country;
	private String phone;
	private String mobile;
	private String email;
	private MultipartFile photo;
	private String active;
	private Date createdAt;
	private Date updatedAt;
	private String className;
	private String sectionName;
	private String categoryName;
	private String fatherName;
	private String motherName;
	private String imageName;
	private String cityName;
	private String countryName;
	private String stateName;
	private String fullName;
	private List<SubjectDTO> SubjectId;
	public List<StudentAttendanceDTO> studentAttendanceDTOs;
	public List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs;
	private String sessionDuration;
	private String balanceAmount;
	private String cancellationDate;
	private int defaulterStatus;
	private String lfno;
	private String monthName;
	private int monthId;
	private String rollno;
	private MultipartFile parentphoto1;
	private MultipartFile parentphoto2;
	private MultipartFile parentphoto3;
	private String password;
	private String photo1name;
	private String photo2name;
	private String photo3name;
	private String username;
	private int pageno;
	private int totalpage;
	private int userId;
	private String studentAttendanceStatus;
	private String currentDate;
	private String guardianName;
	private List<SubjectDTO> subjectDTO;
	private String today;
	private List<StudentDocumentDTO> documentList;
	private String schoolName;
	private String schoolPhone;
	private String schoolAddress;
	private String religionCaste;
	private String newClass;
	private String dateOfWithdrawl;
	private String admitclass;
	private int admitClassId;
    private String srno;
	
	
	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}

	
	
	
	public String getAdmitclass() {
		return admitclass;
	}

	public void setAdmitclass(String admitclass) {
		this.admitclass = admitclass;
	}

	public int getAdmitClassId() {
		return admitClassId;
	}

	public void setAdmitClassId(int admitClassId) {
		this.admitClassId = admitClassId;
	}

	public String getReligionCaste() {
		return religionCaste;
	}

	public void setReligionCaste(String religionCaste) {
		this.religionCaste = religionCaste;
	}

	public String getNewClass() {
		return newClass;
	}

	public void setNewClass(String newClass) {
		this.newClass = newClass;
	}

	public String getDateOfWithdrawl() {
		return dateOfWithdrawl;
	}

	public void setDateOfWithdrawl(String dateOfWithdrawl) {
		this.dateOfWithdrawl = dateOfWithdrawl;
	}

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

	public List<StudentDocumentDTO> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<StudentDocumentDTO> documentList) {
		this.documentList = documentList;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public List<SubjectDTO> getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(List<SubjectDTO> subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoto1name() {
		return photo1name;
	}

	public void setPhoto1name(String photo1name) {
		this.photo1name = photo1name;
	}

	public String getPhoto2name() {
		return photo2name;
	}

	public void setPhoto2name(String photo2name) {
		this.photo2name = photo2name;
	}

	public String getPhoto3name() {
		return photo3name;
	}

	public void setPhoto3name(String photo3name) {
		this.photo3name = photo3name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getParentphoto1() {
		return parentphoto1;
	}

	public void setParentphoto1(MultipartFile parentphoto1) {
		this.parentphoto1 = parentphoto1;
	}

	public MultipartFile getParentphoto2() {
		return parentphoto2;
	}

	public void setParentphoto2(MultipartFile parentphoto2) {
		this.parentphoto2 = parentphoto2;
	}

	public MultipartFile getParentphoto3() {
		return parentphoto3;
	}

	public void setParentphoto3(MultipartFile parentphoto3) {
		this.parentphoto3 = parentphoto3;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public String getLfno() {
		return lfno;
	}

	public void setLfno(String lfno) {
		this.lfno = lfno;
	}

	public int getDefaulterStatus() {
		return defaulterStatus;
	}

	public void setDefaulterStatus(int defaulterStatus) {
		this.defaulterStatus = defaulterStatus;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPhysicallyChallenged() {
		return physicallyChallenged;
	}

	public void setPhysicallyChallenged(String physicallyChallenged) {
		this.physicallyChallenged = physicallyChallenged;
	}

	public String getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<StudentFeeSubmissionDetailsDTO> getFeeSubmissionDetailsDTOs() {
		return feeSubmissionDetailsDTOs;
	}

	public void setFeeSubmissionDetailsDTOs(
			List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs) {
		this.feeSubmissionDetailsDTOs = feeSubmissionDetailsDTOs;
	}

	public List<StudentAttendanceDTO> getStudentAttendanceDTOs() {
		return studentAttendanceDTOs;
	}

	public void setStudentAttendanceDTOs(
			List<StudentAttendanceDTO> studentAttendanceDTOs) {
		this.studentAttendanceDTOs = studentAttendanceDTOs;
	}

	public List<SubjectDTO> getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(List<SubjectDTO> subjectId) {
		SubjectId = subjectId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public int getStudentCategoryId() {
		return studentCategoryId;
	}

	public void setStudentCategoryId(int studentCategoryId) {
		this.studentCategoryId = studentCategoryId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getStudentAttendanceStatus() {
		return studentAttendanceStatus;
	}

	public void setStudentAttendanceStatus(String studentAttendanceStatus) {
		this.studentAttendanceStatus = studentAttendanceStatus;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

}
