package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.FineDao;
import org.brijframework.college.model.Fine;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class FineDaoImpl extends DAOImpl<Integer, Fine> implements FineDao {

	@Override
	public Fine getFineByName(String Name, int sessionId) {
		return (Fine) sessionFactory.getCurrentSession()
				.createCriteria(Fine.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("fineName", Name)).uniqueResult();
	}

}
