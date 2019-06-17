package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.IsbnDao;
import org.brijframework.college.dao.LibraryDao;
import org.brijframework.college.model.ISBN;
import org.brijframework.college.model.Library;
import org.brijframework.college.model.StudentClasses;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("IsbnDao")
public class IsbnDaoImpl extends DAOImpl<Integer, ISBN> implements IsbnDao {

	@Override
	public ISBN getISBNbyLibraryBookId(Integer libraryBookId) {

		return (ISBN) sessionFactory
				.getCurrentSession()
				.createCriteria(ISBN.class)
				.add(Restrictions.eq("libraryBookId.libraryBookId",
						libraryBookId)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ISBN> getBooksISBN_No(int libraryBookId) {

		return sessionFactory
				.getCurrentSession()
				.createCriteria(ISBN.class)
				.add(Restrictions.eq("libraryBookId.libraryBookId",
						libraryBookId))
				.add(Restrictions.eq("issueStatus", "FALSE")).list();
	}

	@Override
	public ISBN getISBNByBookIdAndISBNNO(String isbnNo, int bookId) {
	
		return (ISBN) sessionFactory
				.getCurrentSession()
				.createCriteria(ISBN.class)
				.add(Restrictions.eq("libraryBookId.libraryBookId",
						bookId))
				.add(Restrictions.eq("isbnNo", isbnNo)).uniqueResult();
	}

}
