package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.DocumentsCategory;
import org.brijframework.college.models.dto.DocumentCategoryDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DocumentCategoryService extends
		CRUDService<Integer, DocumentsCategory> {

	List<DocumentCategoryDTO> getDocumentByType(String type);

	void addDocumentCategory(DocumentCategoryDTO documentCategoryDTO);

	void editDocumentCategory(DocumentCategoryDTO documentCategoryDTO);

	void deleteCategory(int id);

}
