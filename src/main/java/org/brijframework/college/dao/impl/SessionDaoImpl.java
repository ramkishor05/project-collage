package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("sessionDao")
public class SessionDaoImpl extends DAOImpl<Integer, Session> implements
		SessionDao {

	@Override
	public Session findCurrentSession() {
		return (Session) sessionFactory.getCurrentSession()
				.createCriteria(Session.class)
				.add(Restrictions.eq("currentSession", true)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Session> findallsessions() {
		return (List<Session>) sessionFactory.getCurrentSession()
				.createCriteria(Session.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("currentSession", false)).list();
	}

	@Override
	public Session getSessionByName(String sessionName) {
		return (Session) sessionFactory.getCurrentSession()
				.createCriteria(Session.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("sessionDuration", sessionName).ignoreCase())
				.uniqueResult();
	}

}
