package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.StateDao;
import org.brijframework.college.model.State;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("stateDao")
public class StateDaoImpl extends DAOImpl<Integer, State> implements StateDao {
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<State> getStateByCountryId(int id) {
		return (List<State>) (sessionFactory.getCurrentSession()
				.createCriteria(State.class).add(Restrictions.eq("country.id",
				id))).add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	public List<State> getStateById(int id) {
		return (List<State>) (sessionFactory.getCurrentSession()
				.createCriteria(State.class).add(Restrictions.eq("id", id)))
				.list();
	}

	@Override
	public State getStateByName(int countryId, String stateName) {
		return (State) sessionFactory.getCurrentSession()
				.createCriteria(State.class)
				.add(Restrictions.eq("stateName", stateName).ignoreCase())
				.add(Restrictions.eq("country.countryId", countryId))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}
}
