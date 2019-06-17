package org.brijframework.college.commom.convertor;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.model.City;
import org.brijframework.college.model.Country;
import org.brijframework.college.model.State;
import org.brijframework.college.models.dto.CityDTO;

public class CityEntityToDTO {
	private static final CityEntityToDTO cityEntityToDTO = new CityEntityToDTO();

	public static final CityEntityToDTO getInstance() {
		return cityEntityToDTO;
	}

	public CityDTO getInstance(City city) {
		CityDTO cityDTO = null;
		if (city != null) {
			cityDTO = new CityDTO();
			if (city.isActive() == true)
				cityDTO.setActive("true");
			else
				cityDTO.setActive("false");
			State state = city.getState();
			Country country = city.getCountry();
			cityDTO.setCityName(city.getCityName());
			//cityDTO.setStateCode(state.getStateCode());
			cityDTO.setStateName(state.getStateName());
			cityDTO.setStateId(state.getStateId());
			cityDTO.setCountryId(country.getCountryId());
			cityDTO.setCountryName(country.getCountryName());
			//cityDTO.setCountryCode(country.getCountryCode());
			cityDTO.setId(city.getId());
			//cityDTO.setCityCode(city.getCityCode());
			cityDTO.setUpdatedAt(city.getUpdatedAt().toString());

		}
		return cityDTO;
	}

	public List<CityDTO> getCityListFromCityEntityToDto(List<City> list) {
		List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
		for (City city : list) {
			CityDTO cityDTO = new CityDTO();
			if (city.isActive() == true)
				cityDTO.setActive("true");
			else
				cityDTO.setActive("false");
			State state = city.getState();
			Country country = city.getCountry();
			//cityDTO.setStateCode(city.getCityCode());
			cityDTO.setCityName(city.getCityName());
			//cityDTO.setStateCode(state.getStateCode());
			cityDTO.setStateName(state.getStateName());
			cityDTO.setStateId(state.getStateId());
			cityDTO.setCountryId(country.getCountryId());
			cityDTO.setCountryName(country.getCountryName());
			//cityDTO.setCountryCode(country.getCountryCode());

			cityDTO.setId(city.getCityId());

			cityDTOs.add(cityDTO);
		}
		return cityDTOs;
	}
}
