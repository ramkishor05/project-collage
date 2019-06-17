package org.brijframework.college.dao;

import org.brijframework.college.model.StudentClasses;

public interface StudentClassesDao extends DAO<Integer, StudentClasses>{
	StudentClasses getClassByName(String className);

}
