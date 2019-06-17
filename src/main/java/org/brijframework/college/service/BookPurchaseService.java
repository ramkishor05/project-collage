package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.BookPurchase;
import org.brijframework.college.models.dto.BookPurchaseDTO;

public interface BookPurchaseService extends CRUDService<Integer, BookPurchase> {

	int getProductNo();

	List<BookPurchaseDTO> getPurchaseDetails() throws ParseException;

	BookPurchaseDTO getPurchaseDetailsbyId(int bookPurchaseId);

	void savepurchasedetals(BookPurchaseDTO bookPurchaseDTO) throws ParseException;

	String updatepurchases(BookPurchaseDTO bookPurchaseDTO) throws ParseException;

	String cancelPurchase(int id);

	List<BookPurchaseDTO> getClasswiseBookList(int id);

	List<BookPurchaseDTO> getBookStockDetails(int classId, int sessionId);

	List<BookPurchaseDTO> getBookbySubject(int subjectId, Integer sessionId);

	List<BookPurchaseDTO> getBoughtDetails(int stockPurchaseId);

	List<BookPurchaseDTO> getClassWiseBooks(int classId, int sessionId);

	List<BookPurchaseDTO> getClassSectionBookList(int classId, int sectionId);

	List<BookPurchaseDTO> getClasssecttionWiseBooks(int sessionId, int classId,
			int sectionId);

	List<BookPurchaseDTO> getBookStockDetailsnew(int classId, int sessionId,
			int sectionId);

}
