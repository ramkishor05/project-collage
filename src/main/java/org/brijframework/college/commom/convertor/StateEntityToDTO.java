package org.brijframework.college.commom.convertor;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.model.Country;
import org.brijframework.college.model.State;
import org.brijframework.college.models.dto.StateDTO;

public class StateEntityToDTO {
	private static final StateEntityToDTO stateEntityToDTO = new StateEntityToDTO();

	public static final StateEntityToDTO getInstance() {
		return stateEntityToDTO;
	}

	public StateDTO getInstance(State state) {
		StateDTO stateDTO = new StateDTO();
		if (state.isActive() == true)
			stateDTO.setActive("yes");
		else
			stateDTO.setActive("no");
		//stateDTO.setStateCode(state.getStateCode());
		stateDTO.setStateName(state.getStateName());
		Country country = state.getCountry();
		stateDTO.setCountryId(country.getCountryId());
		stateDTO.setCountryName(country.getCountryName());
		//stateDTO.setCountryCode(country.getCountryCode());
		stateDTO.setCreatedAt(state.getCreatedAt().toString());
		stateDTO.setId(state.getId());
		stateDTO.setUpdatedAt(state.getUpdatedAt().toString());
		return stateDTO;
	}

	public List<StateDTO> getStateListFromStateEntityToDto(List<State> list) {
		List<StateDTO> stateDTOs = new ArrayList<StateDTO>();
		for (State State : list) {
			StateDTO stateDTO = new StateDTO();
			if (State.isActive() == true)
				stateDTO.setActive("yes");
			else
				stateDTO.setActive("no");
			//stateDTO.setStateCode(State.getStateCode());
			stateDTO.setStateName(State.getStateName());
			Country country = State.getCountry();
			stateDTO.setCountryId(country.getCountryId());
			stateDTO.setCountryName(country.getCountryName());
			//stateDTO.setCountryCode(country.getCountryCode());
			stateDTO.setId(State.getStateId());
			stateDTOs.add(stateDTO);
		}
		return stateDTOs;
	}
}
