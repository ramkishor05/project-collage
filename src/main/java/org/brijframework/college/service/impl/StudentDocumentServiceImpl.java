package org.brijframework.college.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.DocumentCategoryDao;
import org.brijframework.college.dao.StudentDocumentDao;
import org.brijframework.college.model.DocumentsCategory;
import org.brijframework.college.model.StudentDocument;
import org.brijframework.college.models.dto.StudentDocumentDTO;
import org.brijframework.college.service.StudentDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentDocumentService")
public class StudentDocumentServiceImpl extends
		CRUDServiceImpl<Integer, StudentDocument, StudentDocumentDao> implements
		StudentDocumentService {

	@Autowired
	public StudentDocumentServiceImpl(StudentDocumentDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DocumentCategoryDao documentCategoryDao;

	@Override
	public List<StudentDocumentDTO> getDocumentForStudent(String id) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<StudentDocumentDTO> listdto = new ArrayList<StudentDocumentDTO>();
		List<DocumentsCategory> lists = documentCategoryDao
				.getDocumentByType("Student");
		if (!lists.isEmpty())

		{
			for (DocumentsCategory document : lists)

			{
				StudentDocumentDTO dto = new StudentDocumentDTO();
				dto.setDocumentName(document.getDocumentCategoryName());
				dto.setDocumentCategoryId(document.getDocumentCategoryId());
				StudentDocument studoc = dao.findStudentDocument(
						document.getDocumentCategoryId(), id);
				if (studoc == null) {
					dto.setFileName("");
					dto.setStudentDocumentId(0);
					dto.setUploadedOn("-");
				} else {
					dto.setStudentDocumentId(studoc.getStudentDocumentId());
					dto.setFileName(studoc.getFileName());
					dto.setUploadedOn(df.format(studoc.getUploadedOn()));
				}
				listdto.add(dto);
			}
		}
		return listdto;
	}

	@Override
	public void saveDocuments(StudentDocumentDTO studentDocumentDTO) {
		for (StudentDocumentDTO dto : studentDocumentDTO.getDocumentList()) {
			if (dto.getDocumentFile().getSize() > 0) {
				StudentDocument studoc = dao.findStudentDocument(
						dto.getDocumentCategoryId(),
						studentDocumentDTO.getStudentId());
				if (studoc == null) {
					StudentDocument stu = new StudentDocument();
					stu.setActive(true);
					stu.setDocument(documentCategoryDao.get(dto
							.getDocumentCategoryId()));
					stu.setFileName(dto.getFileName());
					stu.setStudent(studentDocumentDTO.getStudentId());
					stu.setUploadedOn(new Date());
					dao.create(stu);
				} else {
					studoc.setFileName(dto.getFileName());
					studoc.setUploadedOn(new Date());
				}

			}
		}

	}

	@Override
	public void saveUploadDocuments(List<StudentDocumentDTO> documentList) {
		for (StudentDocumentDTO dto : documentList) {
			if (dto.getDocumentFile().getSize() > 0) {

				StudentDocument stu = new StudentDocument();
				stu.setActive(true);
				stu.setDocument(documentCategoryDao.get(dto
						.getDocumentCategoryId()));
				stu.setFileName(dto.getFileName());
				stu.setStudent(dto.getStudentId());
				stu.setUploadedOn(new Date());
				dao.create(stu);

			}
		}

	}

}
