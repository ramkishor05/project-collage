package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.BookSell;
import org.brijframework.college.models.dto.BookSellDTO;

public interface BookSellService extends CRUDService<Integer, BookSell> {

	List<BookSellDTO> savepayment(BookSellDTO bookSellDTO);

	List<BookSellDTO> getSoldBookDetails(String from, String to) throws ParseException;

	List<BookSellDTO> getBookReceipt(int id);

	void updatePaymentStatus(String paidStatus, int receiptno);

	List<BookSellDTO> getUnpaidBookDues(int classId, int sessionId);

	BookSellDTO submitdues(BookSellDTO bookSellDTO);

	BookSellDTO getBookDuesReceipt(int receiptNo);

	List<BookSellDTO> getBookDueDetails(int receiptno);
	
	List<BookSellDTO> getSoldBookDetails(String stringDate) throws ParseException;

	void savestudentbooks(BookSellDTO bookSellDTO);

	List<BookSellDTO> getStudentBooks(int classId, int sessionId);

	List<BookSellDTO> getBooksSold(int feeId);

	List<BookSellDTO> getStudentBooksbought(int classId, int sessionId);

	List<BookSellDTO> getBooksSoldtoStudent(String studentId);

	Long findSize();

	List<BookSellDTO> getSoldBookDetailsbypage(int i);

	List<BookSellDTO> getTodaySoldDetails() throws ParseException;

	List<BookSellDTO> getDatewiseSoldDetails(String newDate) throws ParseException;

}
