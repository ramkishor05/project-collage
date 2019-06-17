package org.brijframework.college.service.impl;

import org.brijframework.college.dao.IsbnDao;
import org.brijframework.college.model.ISBN;
import org.brijframework.college.service.ISBNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ISBNService")
public class ISBNServiceImpl extends CRUDServiceImpl<Integer, ISBN, IsbnDao> implements ISBNService{
	@Autowired
	public ISBNServiceImpl(IsbnDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateISBN(String isbnNo, int bookId) {
		ISBN isbn=dao.getISBNByBookIdAndISBNNO(isbnNo,bookId);
		isbn.setIssueStatus("TRUE");
		dao.update(isbn);
	}

}
