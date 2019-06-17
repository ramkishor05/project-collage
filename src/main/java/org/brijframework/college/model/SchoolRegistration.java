package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="school_registration")
public class SchoolRegistration extends AbstractPO<Integer> {
	
	@Id
	@Column(name = "school_registration_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer schoolRegistrationId;
	@Column(name="school_name")
	private String schoolName;
	@Column(name="school_address")
	private String schoolAddress;
	@Column(name="school_logo_name")
	private String schoolLogoName;
	@Column(name="contact_no")
	private String contactNo;
	@Column(name="active")
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
