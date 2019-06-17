package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.FeesCategories;
import org.brijframework.college.models.dto.FeesCategoriesDTO;

public interface FeesCategoriesService extends CRUDService<Integer, FeesCategories>{

	public FeesCategoriesDTO getFeeCategoriesDTOById(int feeCategoryId);

	public void createFeeCategories(FeesCategoriesDTO feeCategoryDTO);

	public void updateFeeCategories(FeesCategoriesDTO feeCategoryDTO);

	public String verifyduplicatename(String name);
	
	public List<FeesCategoriesDTO> getFeesCategories(String studentAdmissionNo,int recieptNo);

}
