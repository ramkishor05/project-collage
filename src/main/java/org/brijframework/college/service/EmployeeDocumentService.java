package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.EmployeeDocument;
import org.brijframework.college.models.dto.EmployeeDocumentDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeDocumentService extends
		CRUDService<Integer, EmployeeDocument> {

	List<EmployeeDocumentDTO> findDocumentsforEmployee(int id);

	void saveDocuments(EmployeeDocumentDTO employeeDocumentDTO);

	void saveDocuments(List<EmployeeDocumentDTO> documentList);

}
