package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.DocumentsCategory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DocumentCategoryDao extends DAO<Integer,DocumentsCategory>{

	List<DocumentsCategory> getDocumentByType(String type);

}
