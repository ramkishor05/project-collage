package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.SchoolRegistrationDao;
import org.brijframework.college.model.SchoolRegistration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("schoolRegistrationDao")
public class SchoolRegistrationDaoImpl extends DAOImpl<Integer, SchoolRegistration>
implements SchoolRegistrationDao {

	@Override
	public SchoolRegistration getRegisterSchool() {
		
		return (SchoolRegistration) sessionFactory.getCurrentSession()
				.createCriteria(SchoolRegistration.class)
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

}
