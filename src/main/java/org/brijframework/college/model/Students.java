package org.brijframework.college.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "students")
public class Students extends AbstractPO<String> {

	@Column(name = "student_id")
	private String studentId;
	@Id
	@Column(name = "student_admission_number")
	private String studentAdmissionNo;
	@Column(name = "admission_date")
	private Date admissionDate;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "class_id")
	private StudentClasses classes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "physically_challenged")
	private String physicallyChallenged;
	@Column(name = "nationality")
	private String nationality;
	@Column(name = "religion")
	private String religion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_categories_id")
	private StudentCategory studentCategory;
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "city_id")
	private City city;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;
	@Column(name = "pin_code")
	private String pinCode;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
	@Column(name = "phone")
	private String phone;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "email")
	private String email;
	@Column(name = "photo_file_name")
	private String photoFileName;
	@Column(name = "is_active")
	private boolean active;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Column(name = "roll_no")
	private int rollNo;
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "mother_name")
	private String motherName;
	@Column(name = "date_of_cancellation")
	private Date cancellationDate;
	@Column(name = "lf_no")
	private int lfNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@Column(name = "photo1_file_name")
	private String photo1FileName;
	@Column(name = "photo2_file_name")
	private String photo2FileName;
	@Column(name = "photo3_file_name")
	private String photo3FileName;
	@Column(name = "password")
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "students")
	private List<StudentAttendance> studentAttendances;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "guardian_name")
	private String guardianName;
	@Column(name="school_name")
	private String schoolName;
	@Column(name="school_address")
	private String schoolAddress;
	@Column(name="school_phone")
	private String schoolPhone;
	@Column(name="unique_id")
	private Integer uniqueId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admitclass_id")
	private StudentClasses admitClassId;
	@Column(name="sr_no")
	private String srno;
	
	
	
	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}
	
	

	public StudentClasses getAdmitClassId() {
		return admitClassId;
	}

	public void setAdmitClassId(StudentClasses admitClassId) {
		this.admitClassId = admitClassId;
	}

	public Integer getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(Integer uniqueId) {
		this.uniqueId = uniqueId;
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

	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public List<StudentAttendance> getStudentAttendances() {
		return studentAttendances;
	}

	public void setStudentAttendances(List<StudentAttendance> studentAttendances) {
		this.studentAttendances = studentAttendances;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto1FileName() {
		return photo1FileName;
	}

	public void setPhoto1FileName(String photo1FileName) {
		this.photo1FileName = photo1FileName;
	}

	public String getPhoto2FileName() {
		return photo2FileName;
	}

	public void setPhoto2FileName(String photo2FileName) {
		this.photo2FileName = photo2FileName;
	}

	public String getPhoto3FileName() {
		return photo3FileName;
	}

	public void setPhoto3FileName(String photo3FileName) {
		this.photo3FileName = photo3FileName;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "students")
	private Parents parents;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "students")
	private List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailsList;

	public int getLfNo() {
		return lfNo;
	}

	public void setLfNo(int lfNo) {
		this.lfNo = lfNo;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getPhysicallyChallenged() {
		return physicallyChallenged;
	}

	public void setPhysicallyChallenged(String physicallyChallenged) {
		this.physicallyChallenged = physicallyChallenged;
	}

	public StudentClasses getClasses() {
		return classes;
	}

	public void setClasses(StudentClasses classes) {
		this.classes = classes;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getStudentAdmissionNo() {
		return studentAdmissionNo;
	}

	public void setStudentAdmissionNo(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getFirstName() {
		return firstName;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
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

	@Override
	public String getId() {
		return studentAdmissionNo;
	}

	@Override
	public void setId(String studentAdmissionNo) {
		this.studentAdmissionNo = studentAdmissionNo;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public StudentCategory getStudentCategory() {
		return studentCategory;
	}

	public void setStudentCategory(StudentCategory studentCategory) {
		this.studentCategory = studentCategory;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Parents getParents() {
		return parents;
	}

	public void setParents(Parents parents) {
		this.parents = parents;
	}

	public List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetailsList() {
		return studentFeeSubmissionDetailsList;
	}

	public void setStudentFeeSubmissionDetailsList(
			List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailsList) {
		this.studentFeeSubmissionDetailsList = studentFeeSubmissionDetailsList;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
