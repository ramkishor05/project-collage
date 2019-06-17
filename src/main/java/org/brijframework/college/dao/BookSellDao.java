package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.BookSell;

public interface BookSellDao extends DAO<Integer, BookSell> {

	BookSell getReceipt();

	List<BookSell> getReceiptData(int receiptNo);

	List<BookSell> getSoldDetails(Date from, Date to);

	List<BookSell> getStudentBookDues(String studentAdmissionNo);

	List<BookSell> findDues(Integer receiptNo);

	BookSell getBookDuesReceiptData(int receiptNo);

	BookSell getBookStudentDetails(Integer duesReceiptNo);
	
	List<BookSell> getSoldDetails(Date date);

	List<BookSell> getStudentBook(String studentAdmissionNo);

	List<BookSell> getBooksSold(int feeId);

	Long findSizeOfTable();

	List<BookSell> getSoldDetailsbypage(int i);

	List<BookSell> getTodaySoldDetails(Date parse);

}
