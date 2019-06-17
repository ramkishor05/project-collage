package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.BookSupplier;

public interface BookSupplierDao extends DAO<Integer, BookSupplier> {

	List<String> getSuppliersId();

	List<BookSupplier> getDetailsbySupplier(String supplierName, Date frmDate);

	BookSupplier getReceipt();

	BookSupplier getReceiptData(int receiptNo);

	List<BookSupplier> getBoughtDetails(Date frmDate);

	List<BookSupplier> getBoughtDetailsByDate(Date frmDate);

	List<BookSupplier> getOverallExpenseByPageNo(int pageNo);

	List<BookSupplier> getTodaysExpenses(Date date);

	List<BookSupplier> findDatewiseExpense(Date fromDate, Date toDate);

	BookSupplier getLastpaymenttoSupplier(String supplierName, String supplierNo);

}
