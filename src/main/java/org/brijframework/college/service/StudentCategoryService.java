package org.brijframework.college.service;

import org.brijframework.college.model.StudentCategory;
import org.brijframework.college.models.dto.StudentCategoryDTO;

public interface StudentCategoryService extends
		CRUDService<Integer, StudentCategory> {
	public StudentCategory getCategoryById(Integer id);

	public void setActiveById(int id);
	
	public StudentCategoryDTO getCategory(String studentCategory);

}
