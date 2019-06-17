package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.StudentDocument;
import org.brijframework.college.models.dto.StudentDocumentDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentDocumentService extends CRUDService<Integer,StudentDocument>{

	List<StudentDocumentDTO> getDocumentForStudent(String id);

	void saveDocuments(StudentDocumentDTO studentDocumentDTO);

	void saveUploadDocuments(List<StudentDocumentDTO> documentList);

}
