package org.brijframework.college.dao;

import org.brijframework.college.model.StudentCategory;

public interface StudentCategoryDao extends DAO<Integer, StudentCategory> {
public StudentCategory getStudentCategory(String studentCategory);
	
}
