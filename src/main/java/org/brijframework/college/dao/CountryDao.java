package org.brijframework.college.dao;

import org.brijframework.college.model.Country;

public interface CountryDao extends DAO<Integer, Country> {
	public Country getCountryByName(String countryName);
}
