package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.CountryDao;
import org.brijframework.college.model.Country;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("CountryDao")
public class CountryDaoImpl extends DAOImpl<Integer, Country> implements
		CountryDao {

	@Override
	public Country getCountryByName(String countryName) {
	
		return  (Country) sessionFactory.getCurrentSession()
				.createCriteria(Country.class)
				.add(Restrictions.eq("countryName", countryName))
				.add(Restrictions.eq("active", true)).uniqueResult();
	}

}
