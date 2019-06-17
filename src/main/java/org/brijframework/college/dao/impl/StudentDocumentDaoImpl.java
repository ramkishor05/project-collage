package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.StudentDocumentDao;
import org.brijframework.college.model.StudentDocument;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("studentDocumentDao")
public class StudentDocumentDaoImpl extends DAOImpl<Integer, StudentDocument>
		implements StudentDocumentDao {

	@Override
	public StudentDocument findStudentDocument(Integer documentCategoryId,
			String id) {
		return (StudentDocument) sessionFactory.getCurrentSession()
				.createCriteria(StudentDocument.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("document.documentCategoryId", documentCategoryId))
				.add(Restrictions.eq("student", id)).uniqueResult();
	}

}
