package org.brijframework.college.dao;

import org.brijframework.college.model.StudentDocument;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentDocumentDao extends DAO<Integer,StudentDocument>{

	StudentDocument findStudentDocument(Integer documentCategoryId, String id);

}
