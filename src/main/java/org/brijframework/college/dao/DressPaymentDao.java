package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.DressPayment;

public interface DressPaymentDao extends DAO<Integer, DressPayment> {

	DressPayment getReceipt();

	List<DressPayment> getSoldDetails(Date date, Date date2);

	List<DressPayment> getReceiptData(int receiptNo);

	List<DressPayment> getStudentDues(String studentAdmissionNo);

	DressPayment getDuesReceiptData(int receiptNo);

	DressPayment getStudentDetails(Integer duesReceiptNo);

	List<DressPayment> findDues(Integer receiptNo);

	List<DressPayment> getSoldListbyId(Integer product);

	List<DressPayment> getStudentUniformSold(String studentAdmissionNo);

	List<DressPayment> getUniformsSold(int feeId);

	Long findSizeOfTable();

	List<DressPayment> getSoldDetailsbyPage(int page);

	List<DressPayment> getTodaysCollection(Date parse);

	

	

}
