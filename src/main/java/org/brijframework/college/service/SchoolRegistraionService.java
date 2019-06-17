package org.brijframework.college.service;

import org.brijframework.college.model.SchoolRegistration;
import org.brijframework.college.models.dto.SchoolRegistrationDTO;

public interface SchoolRegistraionService extends CRUDService<Integer, SchoolRegistration> {

	String registerSchool(SchoolRegistrationDTO schoolRegistrationDTO);

	SchoolRegistrationDTO getSchoolList();

	String updateRegisteredSchool(SchoolRegistrationDTO schoolRegistrationDTO);

	String deleteRegisteredSchool(int id);

}
