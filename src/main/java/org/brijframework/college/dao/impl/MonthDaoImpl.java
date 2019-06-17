package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.model.Month;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("monthDao")
@SuppressWarnings("unchecked")
public class MonthDaoImpl extends DAOImpl<Integer, Month> implements MonthDao {

	@Override
	public List<Integer> getMonthId() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(Month.class)
				.setProjection(
						Projections.distinct(Projections.property("monthId")))
				.list();
	}

	@Override
	public Month getMonthBySerialno(int serialNo) {
		return (Month) sessionFactory
				.getCurrentSession()
				.createCriteria(Month.class)
				.add(Restrictions.eq("serialNo", serialNo))
				.uniqueResult();
	}

	@Override
	public List<Month> getMonthInserial() {
		return (List<Month>) sessionFactory.getCurrentSession()
				.createCriteria(Month.class)
				.setMaxResults(12)
				.addOrder(Order.asc("serialNo"))
				.list();
	}

	@Override
	public List<Month> findSomeMonths(int months1) {
		return (List<Month>) sessionFactory.getCurrentSession()
				.createCriteria(Month.class)
				.setFirstResult(months1)
				.list();
	}

}
