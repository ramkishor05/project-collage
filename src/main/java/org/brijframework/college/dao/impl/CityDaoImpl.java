package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.CityDao;
import org.brijframework.college.model.City;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("cityDao")
public class CityDaoImpl extends DAOImpl<Integer, City> implements CityDao {
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<City> getCityByStateIdAndcountryId(int id, int countryId) {
		return (List<City>) (sessionFactory.getCurrentSession().createCriteria(
				City.class).add(Restrictions.eq("state.stateId", id)))
				.add(Restrictions.eq("country.id", countryId))
				.add(Restrictions.eq("active", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCityByStateId(int id) {
		return (List<City>) (sessionFactory.getCurrentSession().createCriteria(
				City.class).add(Restrictions.eq("state.stateId", id))).add(
				Restrictions.eq("active", true)).list();
	}

	@Override
	public City getCityByStateIdAndcountryId(int id, int countryId,
			String cityName) {
		return (City) sessionFactory.getCurrentSession()
				.createCriteria(City.class)
				.add(Restrictions.eq("state.stateId", id))
				.add(Restrictions.eq("country.id", countryId))
				.add(Restrictions.eq("cityName", cityName).ignoreCase())
				.add(Restrictions.eq("active", true)).uniqueResult();
	}
}
