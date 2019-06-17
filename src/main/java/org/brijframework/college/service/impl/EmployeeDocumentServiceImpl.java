package org.brijframework.college.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.DocumentCategoryDao;
import org.brijframework.college.dao.EmployeeDao;
import org.brijframework.college.dao.EmployeeDocumentDao;
import org.brijframework.college.model.DocumentsCategory;
import org.brijframework.college.model.EmployeeDocument;
import org.brijframework.college.models.dto.EmployeeDocumentDTO;
import org.brijframework.college.service.EmployeeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeDocumentService")
public class EmployeeDocumentServiceImpl extends
		CRUDServiceImpl<Integer, EmployeeDocument, EmployeeDocumentDao>
		implements EmployeeDocumentService {

	@Autowired
	public EmployeeDocumentServiceImpl(EmployeeDocumentDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DocumentCategoryDao documentCategoryDao;
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<EmployeeDocumentDTO> findDocumentsforEmployee(int id) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeDocumentDTO> listdto = new ArrayList<EmployeeDocumentDTO>();
		List<DocumentsCategory> lists = documentCategoryDao
				.getDocumentByType("Employee");
		if (!lists.isEmpty())

		{
			for (DocumentsCategory document : lists)

			{
				EmployeeDocumentDTO dto = new EmployeeDocumentDTO();
				dto.setDocumentName(document.getDocumentCategoryName());
				dto.setDocumentCategoryId(document.getDocumentCategoryId());
				EmployeeDocument empdoc = dao.findEmployeeDocument(
						document.getDocumentCategoryId(), id);
				if (empdoc == null) {
					dto.setFileName("");
					dto.setEmployeeDocumentId(0);
					dto.setUploadedOn("-");
				} else {
					dto.setEmployeeDocumentId(empdoc.getEmployeeDocumentId());
					dto.setFileName(empdoc.getFileName());
					dto.setUploadedOn(df.format(empdoc.getUploadedOn()));
				}
				listdto.add(dto);
			}
		}
		return listdto;
	}

	@Override
	public void saveDocuments(EmployeeDocumentDTO employeeDocumentDTO) {
		for (EmployeeDocumentDTO dto : employeeDocumentDTO.getDocumentList()) {
			if (dto.getDocumentFile().getSize() > 0) {
				EmployeeDocument empdoc = dao.findEmployeeDocument(
						dto.getDocumentCategoryId(),
						employeeDocumentDTO.getEmployeeId());
				if (empdoc == null) {
					EmployeeDocument stu = new EmployeeDocument();
					stu.setActive(true);
					stu.setDocument(documentCategoryDao.get(dto
							.getDocumentCategoryId()));
					stu.setFileName(dto.getFileName());
					stu.setEmployees(employeeDao.get(employeeDocumentDTO
							.getEmployeeId()));
					stu.setUploadedOn(new Date());
					dao.create(stu);
				} else {
					empdoc.setFileName(dto.getFileName());
					empdoc.setUploadedOn(new Date());
				}

			}
		}

	}

	@Override
	public void saveDocuments(List<EmployeeDocumentDTO> documentList) {
		for (EmployeeDocumentDTO dto : documentList) {
			if (dto.getDocumentFile().getSize() > 0) {

				EmployeeDocument stu = new EmployeeDocument();
				stu.setActive(true);
				stu.setDocument(documentCategoryDao.get(dto
						.getDocumentCategoryId()));
				stu.setFileName(dto.getFileName());
				stu.setEmployees(employeeDao.get(dto.getEmployeeId()));
				stu.setUploadedOn(new Date());
				dao.create(stu);

			}
		}

	}

}
