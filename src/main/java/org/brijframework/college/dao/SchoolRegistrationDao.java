package org.brijframework.college.dao;

import org.brijframework.college.model.SchoolRegistration;

public interface SchoolRegistrationDao extends DAO<Integer, SchoolRegistration> {

	SchoolRegistration getRegisterSchool();

}
