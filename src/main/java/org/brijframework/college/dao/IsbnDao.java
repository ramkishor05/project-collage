package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.ISBN;
import org.brijframework.college.model.Library;

public interface IsbnDao  extends DAO<Integer, ISBN>{

	ISBN getISBNbyLibraryBookId(Integer libraryBookId);

	List<ISBN> getBooksISBN_No(int bookId);

	ISBN getISBNByBookIdAndISBNNO(String isbnNo, int bookId);
	
	
	

}
