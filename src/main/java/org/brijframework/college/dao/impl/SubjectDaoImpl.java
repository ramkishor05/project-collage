package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.SubjectDao;
import org.brijframework.college.model.Subjects;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("subjectDao")
public class SubjectDaoImpl extends DAOImpl<Integer, Subjects> implements
		SubjectDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Subjects> getSubjectList(int classId, int sectionId,int sessionId) {
		return (List<Subjects>) (sessionFactory.getCurrentSession()
				.createCriteria(Subjects.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId",sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId))).list();
	}

	@Override
	public Subjects getSubjects(int classId, int sectionId, String subjectName,int sessionId) {
		return (Subjects) sessionFactory.getCurrentSession()
				.createCriteria(Subjects.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("subjectName", subjectName)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subjects> getSubjectforclass(int classId, Integer sessionId) {
		return (List<Subjects>) (sessionFactory.getCurrentSession()
				.createCriteria(Subjects.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId",sessionId))).list();
	}
}
