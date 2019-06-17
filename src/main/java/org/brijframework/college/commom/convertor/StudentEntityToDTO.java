package org.brijframework.college.commom.convertor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.model.Country;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.State;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.StudentDTO;

public class StudentEntityToDTO {

	private static final StudentEntityToDTO studentEntityToDTO = new StudentEntityToDTO();

	public static final StudentEntityToDTO getInstance() {
		return studentEntityToDTO;
	}

	public StudentDTO getConvertEntityToDTO(Students students) {
		StudentDTO studentDTO = new StudentDTO();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
		if (students == null) {
		} else {
			if (students.isActive() == true)
				studentDTO.setActive("true");
			else
				studentDTO.setActive("false");
			studentDTO.setAdmissionNo(students.getStudentAdmissionNo());
			/*
			 * if (students.isActive() == true) studentDTO.setActive("true"); else
			 * studentDTO.setActive("false");
			 */
			studentDTO.setFirstName(students.getFirstName());
			studentDTO.setMiddleName(students.getMiddleName());
			studentDTO.setLastName(students.getLastName());
			studentDTO.setFullName(students.getFirstName()+" "+students.getMiddleName()+" "+students.getLastName());
			studentDTO.setAddressLine1(students.getAddressLine1());
			studentDTO.setAddressLine2(students.getAddressLine2());
			studentDTO.setAdmissionDate(df.format(students.getAdmissionDate()));
			studentDTO.setSessionId(students.getSession().getSessionId());
			if (students.getCity() != null) {
				studentDTO.setCity(students.getCity().getCityName());
				studentDTO.setCityId(students.getCity().getCityId());
			}
			if (students.getCountry() != null) {
				Country country = students.getCountry();
				studentDTO.setCountryId(country.getCountryId());
				studentDTO.setCountry(country.getCountryName());
			}
			if (students.getState() != null) {
				State state = students.getState();
				studentDTO.setStateId(state.getStateId());
				studentDTO.setState(state.getStateName());
			}
			studentDTO.setSessionDuration(students.getSession()
					.getSessionDuration());
			studentDTO.setDateOfBirth(df.format(students.getDateOfBirth()));
			studentDTO.setEmail(students.getEmail());
			studentDTO.setFatherName(students.getFatherName());
			studentDTO.setGender(students.getGender());
			studentDTO.setPhysicallyChallenged(students.getPhysicallyChallenged());
			studentDTO.setMobile(students.getMobile());
			studentDTO.setMotherName(students.getMotherName());
			studentDTO.setNationality(students.getNationality());
			studentDTO.setPhone(students.getPhone());
			studentDTO.setPinCode(String.valueOf(students.getPinCode()));
			studentDTO.setLfno(String.valueOf(students.getLfNo()));
			studentDTO.setImageName(students.getPhotoFileName());
			studentDTO.setReligionName(students.getReligion());
			studentDTO.setSectionName(students.getSection().getSectionName());

			if (students.getStudentCategory() != null) {
				studentDTO.setStudentCategoryId(students.getStudentCategory()
						.getStudentCategoriesId());
				studentDTO.setCategoryName(students.getStudentCategory()
						.getStudentCategoriesName());
			}
			studentDTO.setUpdatedAt(students.getUpdatedAt());
			StudentClasses studentClasses = students.getClasses();
			studentDTO.setClassId(studentClasses.getClassId());
			studentDTO.setClassName(studentClasses.getClassName());
			Section section = students.getSection();
			studentDTO.setSectionId(section.getSectionId());
			studentDTO.setSectionName(section.getSectionName());
			studentDTO.setRollno(String.valueOf(students.getRollNo()));
			studentDTO.setPhoto1name(students.getPhoto1FileName());
			studentDTO.setPhoto2name(students.getPhoto2FileName());
			studentDTO.setPhoto3name(students.getPhoto3FileName());
			studentDTO.setPassword(students.getPassword());
			studentDTO.setStudentId(students.getStudentId());
			studentDTO.setGuardianName(students.getGuardianName());
			

		}
		return studentDTO;

	}

	public List<StudentDTO> getStudentsListFromStudentEntityToDto(
			List<Students> list) {
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for (Students students : list) {
			StudentDTO studentDTO = new StudentDTO();
			if (students.isActive() == true)
				studentDTO.setActive("true");
			else
				studentDTO.setActive("false");
			studentDTO.setFirstName(students.getFirstName());
			studentDTO.setMiddleName(students.getMiddleName());
			studentDTO.setLastName(students.getLastName());
			studentDTO.setAdmissionNo(students.getStudentAdmissionNo());
			studentDTO.setId(students.getStudentAdmissionNo());
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}
}
