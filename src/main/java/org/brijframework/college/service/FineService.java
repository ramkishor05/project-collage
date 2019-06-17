package org.brijframework.college.service;

import org.brijframework.college.model.Fine;
import org.brijframework.college.models.dto.FineDTO;

public interface FineService extends CRUDService<Integer, Fine> {
	public FineDTO getFineByName(String Name, Integer integer);

	public void createFine(FineDTO fine);

	public void updateFine(FineDTO fine);
}
