package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.DressPayment;
import org.brijframework.college.models.dto.DressPaymentDTO;

public interface DressPaymentService extends CRUDService<Integer, DressPayment> {

	List<DressPaymentDTO> savepaymentdetails(int paidAmount, String inword,
			String admissionNo, int total);

	List<DressPaymentDTO> getSoldDetails(String from, String to)
			throws ParseException;

	List<DressPaymentDTO> getDressReceipt(int receiptNo);

	List<DressPaymentDTO> getUniformpaymentdetails(int id, String id2,
			String id3, int total, int net);

	List<DressPaymentDTO> getUniformReceipt(int receiptNo);

	List<DressPaymentDTO> getSoldUniformDetails(String from, String to)
			throws ParseException;

	List<DressPaymentDTO> submitpaymentdetails(DressPaymentDTO dressPaymentDTO);

	void updatePaymentStatus(String paidStatus, int receiptno);

	List<DressPaymentDTO> getClasswiseDues(int classId, int sessionId);

	DressPaymentDTO submitdues(DressPaymentDTO dressPaymentDTO);

	public DressPaymentDTO getDuesReceipt(int receiptNo);

	List<DressPaymentDTO> getDueDetails(int receiptno);

	List<DressPaymentDTO> getSoldUniformDetailsbysize(String name,
			String category, String size);

	void savesolddetails(String studentId, int total);

	List<DressPaymentDTO> getStudentUniforms(int classId, int sessionId);

	List<DressPaymentDTO> geUniformSold(int feeId);

List<DressPaymentDTO> getStudentSoldUniforms(int classId, int sessionId);

Long findSize();

List<DressPaymentDTO> getOverAllUniform(int i);

List<DressPaymentDTO> getTodaySoldDetails() throws ParseException;

List<DressPaymentDTO> getDateWiseSoldDetails(String newDate) throws ParseException;}
