package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.model.StudentClasses;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("StudentClassesDao")
public class StudentClassesDaoImpl extends DAOImpl<Integer, StudentClasses>
		implements StudentClassesDao {

	@Override
	public StudentClasses getClassByName(String className) {
		return (StudentClasses) sessionFactory.getCurrentSession()
				.createCriteria(StudentClasses.class)
				.add(Restrictions.eq("className", className)).uniqueResult();
	}

}
