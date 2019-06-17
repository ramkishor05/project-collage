package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.City;

public interface CityDao extends DAO<Integer, City> {
	List<City> getCityByStateIdAndcountryId(int id, int countryId);

	List<City> getCityByStateId(int id);
	public City getCityByStateIdAndcountryId(int id, int countryId,
			String cityName);
}
