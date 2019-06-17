package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.commom.convertor.LibraryDTOToEntity;
import org.brijframework.college.commom.convertor.LibraryEntityToDTO;
import org.brijframework.college.dao.IsbnDao;
import org.brijframework.college.dao.LibraryDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.model.ISBN;
import org.brijframework.college.model.Library;
import org.brijframework.college.models.dto.IsbnDTO;
import org.brijframework.college.models.dto.LibraryDTO;
import org.brijframework.college.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("LibraryService")
public class LibraryServiceImpl extends
		CRUDServiceImpl<Integer, Library, LibraryDao> implements LibraryService {

	@Autowired
	StudentClassesDao studentClassesDao;
	@Autowired
	IsbnDao isbnDao;
	@Autowired
	LibraryDao libraryDao;

	@Autowired
	public LibraryServiceImpl(LibraryDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addBook(LibraryDTO libraryDTO) {
		LibraryDTOToEntity libraryDTOToEntity = LibraryDTOToEntity.getInstance();
		Library library = libraryDTOToEntity.convertLibratyDTOToEntity(libraryDTO);
		library.setClassId(studentClassesDao.get(libraryDTO.getClassId()));
		dao.create(library);
		Library library2 = dao.getLastRecord();
		if (libraryDTO.getIsbnList() != null) {

			for (IsbnDTO isbnDTO : libraryDTO.getIsbnList()) {

				ISBN isbn = new ISBN();
				isbn.setIsbnNo(isbnDTO.getIsbnNo());
				isbn.setLibraryBookId(libraryDao.get(library2.libraryBookId));
				isbnDao.create(isbn);
			}
		}
	}

	@Override
	public List<LibraryDTO> getSubjectByClass(Integer classId) {
		List<Library> libraries=dao.getSubjectByClass(classId);
		List<LibraryDTO> libraryDTOs= new ArrayList<LibraryDTO>();
		LibraryEntityToDTO libraryEntityToDTO=LibraryEntityToDTO.getInstance();
		for (Library library : libraries) {
			LibraryDTO libraryDTO= libraryEntityToDTO.convertLibraryEntityToDto(library);
			libraryDTOs.add(libraryDTO);
		}
		
		return libraryDTOs;
		
		
		
	}

	@Override
	public List<LibraryDTO> getSubjectDetails(Integer classId,
			String subjectName) {
		
		List<Library> libraries= dao.getSubjectDetails(classId, subjectName);
		List<LibraryDTO> libraryDTOs= new ArrayList<LibraryDTO>();
		LibraryEntityToDTO libraryEntityToDTO=LibraryEntityToDTO.getInstance();
		for (Library library : libraries) {
			LibraryDTO libraryDTO= libraryEntityToDTO.convertLibraryEntityToDto(library);
			libraryDTOs.add(libraryDTO);
		}
		
		return libraryDTOs;
	}

	@Override
	public void updateLibraryBook(int bookId, int classId) {
	
		Library library=dao.get(bookId);
		library.setRemainingQuantity(library.getRemainingQuantity()-1);
		dao.update(library);
		
	}
}
