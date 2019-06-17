package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.Library;
import org.brijframework.college.models.dto.LibraryDTO;

public interface LibraryService extends CRUDService<Integer, Library> {

	public void addBook(LibraryDTO libraryDTO);
	public List<LibraryDTO> getSubjectByClass(Integer classId);

	public List<LibraryDTO> getSubjectDetails(Integer classId,
			String subjectName);
	public void updateLibraryBook(int bookId, int classId);
	

	
	
}
