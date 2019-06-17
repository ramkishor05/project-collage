package org.brijframework.college.dao;

import org.brijframework.college.model.EmployeeDocument;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeDocumentDao extends DAO<Integer,EmployeeDocument>{

	EmployeeDocument findEmployeeDocument(Integer documentCategoryId, int id);

}
