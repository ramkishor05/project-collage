package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.State;
import org.brijframework.college.models.dto.StateDTO;

public interface StateService extends CRUDService<Integer, State> {
	void addState(StateDTO stateDTO);

	void updateState(StateDTO stateDTO);

	List<StateDTO> getStateByCountryId(int id);

	List<StateDTO> getStateById(int id);
	
	StateDTO getStateByName(int countryId,String stateName);
	StateDTO getStateByStateId(int stateId);
}
