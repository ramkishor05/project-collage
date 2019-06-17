package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.State;

public interface StateDao extends DAO<Integer, State> {
	List<State> getStateByCountryId(int id);

	List<State> getStateById(int id);
	
	public State getStateByName(int countryId,String stateName);
}
