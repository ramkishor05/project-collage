package org.brijframework.college.commom.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.brijframework.college.model.Library;
import org.brijframework.college.models.dto.LibraryDTO;

public class LibraryEntityToDTO {
	
	public final static LibraryEntityToDTO libraryEntityToDto= new LibraryEntityToDTO();
	
	public static LibraryEntityToDTO getInstance()
	{
		return libraryEntityToDto;
	}
	
	public LibraryDTO convertLibraryEntityToDto(Library library)
	{
		DateFormat df = new SimpleDateFormat("yyyy/mmm/dd");
		
		LibraryDTO libraryDTO= new LibraryDTO();
		libraryDTO.setBookName(library.getBookName());
		libraryDTO.setClassId(library.getClassId().getClassId());
		libraryDTO.setLibraryBookId(library.getLibraryBookId());
		libraryDTO.setQuantity(library.getQuantity());
		libraryDTO.setRemainingQuantity(library.getRemainingQuantity());
		libraryDTO.setSubjectName(library.getSubjectName());
		libraryDTO.setCreateDate(df.format(library.getDate()));
		libraryDTO.setBookAuthor(library.getBookAuthor());
		libraryDTO.setBookPublisher(library.getBookPublisher());
		libraryDTO.setBookEditionNumber(library.getBookEditionNumber());
		libraryDTO.setTotalBookPage(library.getTotalBookPage());
		libraryDTO.setBookCopyright(library.getBookCopyright());
		return libraryDTO;
	}
	
	
	

}
