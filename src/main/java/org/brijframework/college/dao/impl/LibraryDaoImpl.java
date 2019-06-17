package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.LibraryDao;
import org.brijframework.college.model.Library;
import org.brijframework.college.model.StudentClasses;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("LibraryDao")
public class LibraryDaoImpl extends DAOImpl<Integer, Library> implements
		LibraryDao {

	@Override
	public Library getLastRecord() {

		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("from Library order by libraryBookId DESC");
		query.setMaxResults(1);
		Library last = (Library) query.uniqueResult();
		return last;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Library> getSubjectByClass(Integer classId) {

		return sessionFactory.getCurrentSession().createCriteria(Library.class)
				.add(Restrictions.eq("classId.classId", classId)).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Library> getSubjectDetails(Integer classId, String subjectName) {
		return sessionFactory.getCurrentSession().createCriteria(Library.class)
				.add(Restrictions.eq("subjectName", subjectName))
				.add(Restrictions.eq("classId.classId", classId)).list();
	}

}
