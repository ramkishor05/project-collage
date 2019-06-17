package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.BookPurchase;

public interface BookPurchaseDao extends DAO<Integer, BookPurchase> {

	List<BookPurchase> findCurrentSessionPurchases(int sessionId);

	List<String> findBookList(Integer sessionId, int id);

	BookPurchase findbookDetails(String name, Integer sessionId, int id);

	BookPurchase findoutofStockBook(String name, Integer sessionId, int id);

	List<BookPurchase> findbookstock(int sessionId, int classId, String name);

	List<String> findbookbysubject(int subjectId, Integer sessionId);

	List<BookPurchase> getbookBoughtDetails(int stockPurchaseId);

	List<BookPurchase> findbookbysubject(int subjectId, Integer sessionId,
			String name);

	List<BookPurchase> findBooksbyclass(int classId, int sessionId);

	List<String> findBookListclassSection(Integer sessionId, int classId,
			int sectionId);

	BookPurchase findbookDetailsnew(String name, Integer sessionId,
			int classId, int sectionId);

	BookPurchase findoutofStockBooknew(String name, Integer sessionId,
			int classId, int sectionId);

	List<BookPurchase> findBooksbyclasssection(int sessionId, int classId,
			int sectionId);

	List<BookPurchase> findbookstocknew(int sessionId, int classId,
			int sectionId, String name);

	
	

}
