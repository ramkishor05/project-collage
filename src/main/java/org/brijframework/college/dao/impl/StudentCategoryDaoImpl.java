package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.StudentCategoryDao;
import org.brijframework.college.model.StudentCategory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("studentCategoryDao")
public class StudentCategoryDaoImpl extends DAOImpl<Integer, StudentCategory>
		implements StudentCategoryDao {

	@Override
	public StudentCategory getStudentCategory(String studentCategory) {
		return (StudentCategory) sessionFactory.getCurrentSession()
				.createCriteria(StudentCategory.class)
				.add(Restrictions.eq("studentCategoriesName", studentCategory).ignoreCase())
				.uniqueResult();
	}

}
