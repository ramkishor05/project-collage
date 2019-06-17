package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.Month;
import org.brijframework.college.models.dto.MonthDTO;

public interface MonthService  extends CRUDService<Integer, Month>{
	public List<MonthDTO> getMonthInOrderToSerialNo();

	public List<MonthDTO> getMonthInOrder();

}
