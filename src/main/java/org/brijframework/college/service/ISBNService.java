package org.brijframework.college.service;

import org.brijframework.college.model.ISBN;

public interface ISBNService extends CRUDService<Integer, ISBN> {

	void updateISBN(String isbnNo, int bookId);
	

}
