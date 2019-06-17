package org.brijframework.college.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "student_admission")
public class StudentAdmission extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private int studentId;
	@Column(name = "s_no")
	private int sNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses classes;
	@Column(name = "reg_no")
	private String regNo;
	@Column(name = "student_name")
	private String studentName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	@Column(name = "mother_tounge")
	private String motherTounge;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "city_id")
	private City city;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;
	@Column(name = "physical_disability")
	private String physicalDisability;
	@Column(name = "image_name")
	private String imageName;
	@Column(name = "res_address")
	private String resAddress;
	@Column(name = "phone_no")
	private String phoneNo;
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "father_education")
	private String fatherEducation;
	@Column(name = "father_occupation")
	private String fatherOccupation;
	@Column(name = "father_annual_income")
	private String fatherAnnualIncome;
	@Column(name = "father_office_address")
	private String fatherOfficeAddress;
	@Column(name = "father_phone_no")
	private String fatherPhoneNo;
	@Column(name = "mother_name")
	private String motherName;
	@Column(name = "mother_education")
	private String motherEducation;
	@Column(name = "mother_occupation")
	private String motherOccupation;
	@Column(name = "mother_annual_income")
	private String motherAnnualIncome;
	@Column(name = "mother_office_address")
	private String motherOfficeAddress;
	@Column(name = "mother_phone_no")
	private String motherPhoneNo;
	@Column(name = "brother_or_sister_name1")
	private String brotherOrSisterName1;
	@Column(name = "brother_or_sister_age1")
	private String brotherOrSisterAge1;
	@Column(name = "brother_or_sister_class1")
	private String brotherOrSisterClass1;
	@Column(name = "brother_or_sister_school_or_college1")
	private String brotherOrSisterSchoolOrCollege1;
	@Column(name = "brother_or_sister_name2")
	private String brotherOrSisterName2;
	@Column(name = "brother_or_sister_age2")
	private String brotherOrSisterAge2;
	@Column(name = "brother_or_sister_class2")
	private String brotherOrSisterClass2;
	@Column(name = "brother_or_sister_school_or_college2")
	private String brotherOrSisterSchoolOrCollege2;
	@Column(name = "written_or_interview_date")
	private Date writtenOrIntervieDate;
	@Column(name = "admit_in_class")
	private String admitInClass;
	@Column(name = "principal_or_director_name")
	private String principalOrDirectorName;
	@Column(name = "admission_no")
	private String admissionNo;
	@Column(name = "admission_date")
	private Date admissionDate;
	@Column(name = "fee_receipt_no")
	private String feeReceiptNo;
	@Column(name = "fee_receipt_date")
	private Date feeReceiptDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	@Column(name="school_name")
	private String schoolName;
	@Column(name="school_address")
	private String schoolAddress;
	@Column(name="school_phone")
	private String schoolPhone;
	@Column(name="transfer_status")
	private String transferStatus;
	@Column(name="is_active")
	private Boolean active;
	@Column(name="is_delete")
	private Boolean delete;
	
	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public StudentClasses getClasses() {
		return classes;
	}

	public void setClasses(StudentClasses classes) {
		this.classes = classes;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMotherTounge() {
		return motherTounge;
	}

	public void setMotherTounge(String motherTounge) {
		this.motherTounge = motherTounge;
	}

	public String getPhysicalDisability() {
		return physicalDisability;
	}

	public void setPhysicalDisability(String physicalDisability) {
		this.physicalDisability = physicalDisability;
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

	public Date getWrittenOrIntervieDate() {
		return writtenOrIntervieDate;
	}

	public void setWrittenOrIntervieDate(Date writtenOrIntervieDate) {
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

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getFeeReceiptNo() {
		return feeReceiptNo;
	}

	public void setFeeReceiptNo(String feeReceiptNo) {
		this.feeReceiptNo = feeReceiptNo;
	}

	public Date getFeeReceiptDate() {
		return feeReceiptDate;
	}

	public void setFeeReceiptDate(Date feeReceiptDate) {
		this.feeReceiptDate = feeReceiptDate;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub

	}

}
