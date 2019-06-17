package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.City;
import org.brijframework.college.models.dto.CityDTO;

public interface CityService extends CRUDService<Integer, City> {
	void addCity(CityDTO cityDTO);

	void updateCity(CityDTO cityDTO);

	List<CityDTO> getCityByStateIdAndcountryId(int id, int countryId);

	List<CityDTO> getCityByStateId(int id);
	CityDTO getCityByStateIdAndcountryId(int id, int countryId,String cityName);
	CityDTO getCityById(int cityId);
}
