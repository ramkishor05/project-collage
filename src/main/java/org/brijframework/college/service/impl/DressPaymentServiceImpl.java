package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.brijframework.college.dao.AllotDressDao;
import org.brijframework.college.dao.DressDao;
import org.brijframework.college.dao.DressPaymentDao;
import org.brijframework.college.dao.FeesCategoriesDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.dao.StudentWiseFeeDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.UniformPurchaseDao;
import org.brijframework.college.model.AllotDress;
import org.brijframework.college.model.DressPayment;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.UniformPurchase;
import org.brijframework.college.models.dto.BookSellDTO;
import org.brijframework.college.models.dto.DressPaymentDTO;
import org.brijframework.college.service.DressPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("dressPaymentService")
public class DressPaymentServiceImpl extends
		CRUDServiceImpl<Integer, DressPayment, DressPaymentDao> implements
		DressPaymentService {
	@Autowired
	public DressPaymentServiceImpl(DressPaymentDao dao) {
		super(dao);

	}

	@Autowired
	AllotDressDao allotDressDao;
	@Autowired
	DressDao dressDao;
	@Autowired
	StudentsAdmissionDao studentDao;
	@Autowired
	UniformPurchaseDao uniformDao;
	@Autowired
	SessionDao sessionDao;
	@Autowired
	StudentFeeSubmissionDetailsDao feeDao;
	@Autowired
	FeesCategoriesDao feecategoryDao;
	@Autowired
	StudentWiseFeeDao studentWiseFeeDao;
	@Autowired
	StudentsAdmissionDao admissionDao;
	@Autowired
	MonthDao monthDao;

	@Override
	public List<DressPaymentDTO> savepaymentdetails(int paidAmount,
			String inword, String admissionNo, int total) {
		List<AllotDress> allotedDress = allotDressDao.findAll(AllotDress.class);
		int receiptNo = 0;
		Date date = new Date();
		DressPayment details = dao.getReceipt();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}
		for (AllotDress list : allotedDress) {
			DressPayment pay = new DressPayment();
			pay.setDress(dressDao.get(list.getDress().getDressId()));
			pay.setInword(inword);
			pay.setPaidAmount(paidAmount);
			pay.setQuantity(list.getQuantity());
			pay.setStudents(studentDao.get(admissionNo));
			pay.setTotalAmount(total);
			pay.setReceiptNo(receiptNo);
			pay.setPayDate(date);

			dao.create(pay);
			allotDressDao.deleteById(list.getAllotDressId());
		}
		return getDressReceipt(receiptNo);

	}

	@Override
	public List<DressPaymentDTO> getSoldDetails(String from, String to)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getSoldDetails(df.parse(from),
				df.parse(to));
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getTotalAmount() - list.getPaidAmount());
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			listdto.add(dto);
		}
		Set<Integer> attributes = new HashSet<Integer>();
		List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
		for (DressPaymentDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public List<DressPaymentDTO> getDressReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getReceiptData(receiptNo);
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getTotalAmount() - list.getPaidAmount());
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setFirstName(list.getStudents().getFirstName());
			dto.setMiddleName(list.getStudents().getMiddleName());
			dto.setLastName(list.getStudents().getLastName());
			dto.setDressName(list.getDress().getDressName());
			dto.setCategory(list.getDress().getCategory());
			dto.setSize(list.getDress().getSize());
			dto.setPrice(String.valueOf(list.getDress().getPrice()));
			dto.setAmount(list.getDress().getPrice() * list.getQuantity());
			dto.setFatherName(list.getStudents().getFatherName());
			dto.setQuantity(list.getQuantity());

			listdto.add(dto);
		}

		return listdto;
	}

	@Override
	public List<DressPaymentDTO> getUniformpaymentdetails(int paidAmount,
			String inword, String admissionNo, int total, int net) {
		List<AllotDress> allotedDress = allotDressDao.findAll(AllotDress.class);
		int receiptNo = 0;
		Date date = new Date();
		DressPayment details = dao.getReceipt();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}
		for (AllotDress list : allotedDress) {
			DressPayment pay = new DressPayment();
			pay.setUniformPurchase(uniformDao.get(list.getUniformPurchase()
					.getProductPurchaseId()));
			pay.setInword(inword);
			pay.setPaidAmount(paidAmount);
			pay.setQuantity(list.getQuantity());
			pay.setStudents(studentDao.get(admissionNo));
			pay.setTotalAmount(total);
			pay.setReceiptNo(receiptNo);
			pay.setPayDate(date);
			pay.setDiscount(total - net);
			pay.setDues(net - paidAmount);
			dao.create(pay);
			UniformPurchase uniform = uniformDao.get(list.getUniformPurchase()
					.getProductPurchaseId());
			uniform.setRemainingQuantity(uniform.getRemainingQuantity()
					- list.getQuantity());
			allotDressDao.deleteById(list.getAllotDressId());
		}
		return getUniformReceipt(receiptNo);
	}

	@Override
	public List<DressPaymentDTO> getUniformReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getReceiptData(receiptNo);
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			dto.setDues(list.getDues());
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setFirstName(list.getStudents().getFirstName());
			dto.setMiddleName(list.getStudents().getMiddleName());
			dto.setLastName(list.getStudents().getLastName());
			dto.setDressName(list.getUniformPurchase().getUniformName());
			dto.setCategory(list.getUniformPurchase().getUniformCategory());
			dto.setSize(list.getUniformPurchase().getUniformSize());
			dto.setPrice(String.valueOf(list.getUniformPurchase()
					.getUniformPrice()));
			dto.setAmount(list.getUniformPurchase().getUniformPrice()
					* list.getQuantity());
			dto.setFatherName(list.getStudents().getFatherName());
			dto.setQuantity(list.getQuantity());
			dto.setPaymentMode(list.getPaymentMode());
			if (list.getPaymentMode().equals("Cheque")) {
				dto.setChequeno(String.valueOf(list.getChequeNo()));
				dto.setBankName(list.getBankName());
			}

			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<DressPaymentDTO> getSoldUniformDetails(String from, String to)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getSoldDetails(df.parse(from),
				df.parse(to));
		int totalPaid = 0;
		int totalDues = 0;
		int due = 0;
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			totalPaid += list.getPaidAmount();

			due = 0;
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			dto.setDues(list.getDues());
			totalDues += list.getDues();
              dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setDressPaymentId(list.getDressPaymentId());
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setPaymentMode(list.getPaymentMode());
			dto.setChequeno(String.valueOf(list.getChequeNo()));
			dto.setBankName(list.getBankName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setFatherName(list.getStudents().getFatherName());
			dto.setMobile(list.getStudents().getMobile());
			dto.setTotalPaid(totalPaid);
			dto.setTotalDues(totalDues);
			listdto.add(dto);
		}
		Set<Integer> attributes = new HashSet<Integer>();
		List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
		for (DressPaymentDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public List<DressPaymentDTO> submitpaymentdetails(
			DressPaymentDTO dressPaymentDTO) {
		List<AllotDress> allotedDress = allotDressDao.findAll(AllotDress.class);
		int receiptNo = 0;
		Date date = new Date();
		DressPayment details = dao.getReceipt();
		if (details == null) {
			receiptNo = 1;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}
		for (AllotDress list : allotedDress) {
			DressPayment pay = new DressPayment();
			pay.setUniformPurchase(uniformDao.get(list.getUniformPurchase()
					.getProductPurchaseId()));
			pay.setInword(dressPaymentDTO.getInwords());
			pay.setPaidAmount(dressPaymentDTO.getPaidAmount());
			pay.setQuantity(list.getQuantity());
			pay.setStudents(studentDao.get(list.getStudents()
					.getStudentAdmissionNo()));
			pay.setTotalAmount(dressPaymentDTO.getTotalAmount());
			pay.setReceiptNo(receiptNo);
			pay.setPayDate(date);
			pay.setDiscount(dressPaymentDTO.getTotalAmount()
					- dressPaymentDTO.getNetPayableAmount());
			pay.setDues(dressPaymentDTO.getNetPayableAmount()
					- dressPaymentDTO.getPaidAmount());
			pay.setPaymentMode(dressPaymentDTO.getPaymentMode());
			pay.setDuesReceiptNo(0);
			if (dressPaymentDTO.getPaymentMode().equals("Cheque")) {
				pay.setChequeNo(Integer.parseInt(dressPaymentDTO.getChequeno()));
				pay.setPaidStatus("InHand");
				pay.setBankName(dressPaymentDTO.getBankName());
			} else {

				pay.setPaidStatus("Completed");
			}
			
				StudentFeeSubmissionDetails studentFeeSubmissionDetails = feeDao
						.findlastpayedmonthbystudentId(list.getStudents()
								.getStudentAdmissionNo(), sessionDao
								.findCurrentSession().getSessionId());
				int monthId = 0;
				if (studentFeeSubmissionDetails != null
						&& studentFeeSubmissionDetails.getMonth().getMonthId() == 12) {

				}

				else {
					if (studentFeeSubmissionDetails == null) {
						monthId = 1;
					} else
						monthId = studentFeeSubmissionDetails.getMonth().getMonthId() + 1;
					StudentWiseFee fee = new StudentWiseFee();
					Students student = admissionDao.get(list.getStudents()
							.getStudentAdmissionNo());

					fee.setMonth(monthDao.get(monthId));
					fee.setClasses(student.getClasses());

					fee.setSection(student.getSection());
					fee.setSession(student.getSession());
					fee.setActive(true);
					fee.setStudents(student);
					fee.setFeeAmount(dressPaymentDTO.getNetPayableAmount()
							- dressPaymentDTO.getPaidAmount());
					fee.setFeesCategories(feecategoryDao.get(14));

					studentWiseFeeDao.create(fee);
					pay.setStudentFee(fee);
			}
				
			
			
			dao.create(pay);
			UniformPurchase uniform = uniformDao.get(list.getUniformPurchase()
					.getProductPurchaseId());
			uniform.setRemainingQuantity(uniform.getRemainingQuantity()
					- list.getQuantity());
			allotDressDao.deleteById(list.getAllotDressId());
		}
		return getUniformReceipt(receiptNo);
	}

	@Override
	public void updatePaymentStatus(String paidStatus, int receiptno) {
		List<DressPayment> dressPayment = dao.getReceiptData(receiptno);
		for (DressPayment dress : dressPayment) {
			dress.setPaidStatus(paidStatus);
			if (paidStatus.equals("Cancel")) {
				dress.setDues(dress.getDues() + dress.getPaidAmount());
				dress.setPaidAmount(0);
			}

		}

	}

	@Override
	public List<DressPaymentDTO> getClasswiseDues(int classId, int sessionId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		List<Students> students = studentDao.getStudentbyclasssession(
				sessionId, classId);
		for (Students student : students) {
			List<DressPayment> dresslist = dao.getStudentDues(student
					.getStudentAdmissionNo());
			if (dresslist.isEmpty() || dresslist == null) {

			} else {
				int totalPaid = 0;
				int totalDues = 0;
				int due = 0;
				for (DressPayment list : dresslist) {
					List<DressPayment> dueDetails = dao.findDues(list
							.getReceiptNo());
					if (dueDetails.isEmpty() || dueDetails == null) {

					} else {
						for (DressPayment details : dueDetails) {
							due = due + details.getPaidAmount()
									+ details.getDiscount();
						}
					}
					if (list.getDues() - due == 0) {

					} else {
						totalPaid += list.getPaidAmount();
						due = 0;
						DressPaymentDTO dto = new DressPaymentDTO();
						dto.setPaidAmount(list.getPaidAmount());
						dto.setTotalAmount(list.getTotalAmount());
						dto.setDiscount(list.getDiscount());
						List<DressPayment> dueDetails1 = dao.findDues(list
								.getReceiptNo());
						if (dueDetails1.isEmpty() || dueDetails1 == null) {

						} else {
							for (DressPayment details : dueDetails1) {
								due = due + details.getPaidAmount()
										+ details.getDiscount();
							}
						}
						dto.setDues(list.getDues() - due);
						totalDues += list.getDues() - due;
						dto.setInwords(list.getInword());
						dto.setReceiptno(list.getReceiptNo());
						dto.setPayDate(df.format(list.getPayDate()));
						dto.setDressPaymentId(list.getDressPaymentId());
						dto.setClassName(list.getStudents().getClasses()
								.getClassName());
						dto.setSectionName(list.getStudents().getSection()
								.getSectionName());
						dto.setFullname(list.getStudents().getFirstName() + " "
								+ list.getStudents().getMiddleName() + " "
								+ list.getStudents().getLastName());
						dto.setPaidStatus(list.getPaidStatus());
						dto.setPaymentMode(list.getPaymentMode());
						dto.setChequeno(String.valueOf(list.getChequeNo()));
						dto.setBankName(list.getBankName());
						dto.setPaidStatus(list.getPaidStatus());
						dto.setFatherName(list.getStudents().getFatherName());
						dto.setMobile(list.getStudents().getMobile());
						dto.setTotalPaid(totalPaid);
						dto.setTotalDues(totalDues);
						listdto.add(dto);
					}
				}
			}
		}
		Set<Integer> attributes = new HashSet<Integer>();
		List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
		for (DressPaymentDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public DressPaymentDTO submitdues(DressPaymentDTO dressPaymentDTO) {

		int receiptNo = 0;
		Date date = new Date();
		DressPayment details = dao.getReceipt();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}

		DressPayment pay = new DressPayment();

		pay.setInword(dressPaymentDTO.getInwords());
		pay.setPaidAmount(dressPaymentDTO.getPaidAmount());
		pay.setTotalAmount(dressPaymentDTO.getTotalAmount());
		pay.setReceiptNo(receiptNo);
		pay.setPayDate(date);
		pay.setQuantity(0);
		pay.setDuesReceiptNo(dressPaymentDTO.getReceiptno());
		pay.setDiscount(dressPaymentDTO.getTotalAmount()
				- dressPaymentDTO.getNetPayableAmount());
		pay.setDues(dressPaymentDTO.getNetPayableAmount()
				- dressPaymentDTO.getPaidAmount());
		pay.setPaymentMode(dressPaymentDTO.getPaymentMode());
		if (dressPaymentDTO.getPaymentMode().equals("Cheque")) {
			pay.setChequeNo(Integer.parseInt(dressPaymentDTO.getChequeno()));
			pay.setPaidStatus("InHand");
			pay.setBankName(dressPaymentDTO.getBankName());
		} else {

			pay.setPaidStatus("Completed");
		}
		dao.create(pay);
		return getDuesReceipt(receiptNo);
	}

	@Override
	public DressPaymentDTO getDuesReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DressPayment list = dao.getDuesReceiptData(receiptNo);
		DressPayment stu = dao.getStudentDetails(list.getDuesReceiptNo());
		DressPaymentDTO dto = new DressPaymentDTO();
		dto.setPaidAmount(list.getPaidAmount());
		dto.setTotalAmount(list.getTotalAmount());
		dto.setDiscount(list.getDiscount());
		dto.setDues(list.getDues());
		dto.setInwords(list.getInword());
		dto.setReceiptno(list.getReceiptNo());
		dto.setPayDate(df.format(list.getPayDate()));
		dto.setClassName(stu.getStudents().getClasses().getClassName());
		dto.setSectionName(stu.getStudents().getSection().getSectionName());
		dto.setFullname(stu.getStudents().getFirstName() + " "
				+ stu.getStudents().getMiddleName() + " "
				+ stu.getStudents().getLastName());
		dto.setFirstName(stu.getStudents().getFirstName());
		dto.setMiddleName(stu.getStudents().getMiddleName());
		dto.setLastName(stu.getStudents().getLastName());

		dto.setFatherName(stu.getStudents().getFatherName());

		dto.setPaymentMode(list.getPaymentMode());
		if (list.getPaymentMode().equals("Cheque")) {
			dto.setChequeno(String.valueOf(list.getChequeNo()));
			dto.setBankName(list.getBankName());
		}
		return dto;
	}

	@Override
	public List<DressPaymentDTO> getDueDetails(int receiptno) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.findDues(receiptno);

		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());

			dto.setDues(list.getDues());
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			DressPayment stu = dao.getStudentDetails(list.getDuesReceiptNo());
			dto.setClassName(stu.getStudents().getClasses().getClassName());
			dto.setSectionName(stu.getStudents().getSection().getSectionName());
			dto.setFullname(stu.getStudents().getFirstName() + " "
					+ stu.getStudents().getMiddleName() + " "
					+ stu.getStudents().getLastName());
			dto.setFirstName(stu.getStudents().getFirstName());
			dto.setMiddleName(stu.getStudents().getMiddleName());
			dto.setLastName(stu.getStudents().getLastName());

			dto.setFatherName(stu.getStudents().getFatherName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setPaymentMode(list.getPaymentMode());
			dto.setChequeno(String.valueOf(list.getChequeNo()));
			dto.setBankName(list.getBankName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setMobile(stu.getStudents().getMobile());
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<DressPaymentDTO> getSoldUniformDetailsbysize(String name,
			String category, String size) {
		List<Integer> list = uniformDao.getProductPurchases(name, category,
				size);
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<DressPaymentDTO> dresslist = new ArrayList<DressPaymentDTO>();
		for (Integer product : list) {
			List<DressPayment> dress = dao.getSoldListbyId(product);
			for (DressPayment pay : dress) {
				DressPaymentDTO dto = new DressPaymentDTO();
				dto.setClassName(pay.getStudents().getClasses().getClassName());
				dto.setSectionName(pay.getStudents().getSection()
						.getSectionName());
				dto.setLastName(pay.getStudents().getLastName());
				dto.setMiddleName(pay.getStudents().getMiddleName());
				dto.setFatherName(pay.getStudents().getFatherName());
				dto.setFirstName(pay.getStudents().getFirstName());
				dto.setPayDate(df.format(pay.getPayDate()));
				dto.setQuantity(pay.getQuantity());
				dto.setPrice(String.valueOf(pay.getUniformPurchase()
						.getUniformPrice()));
				dresslist.add(dto);
			}

		}
		return dresslist;
	}

	@Override
	public void savesolddetails(String studentId, int total) {
		StudentFeeSubmissionDetails studentFeeSubmissionDetails = feeDao
				.findlastpayedmonthbystudentId(studentId, sessionDao
						.findCurrentSession().getSessionId());
		int monthId = 0;
		if (studentFeeSubmissionDetails != null
				&& studentFeeSubmissionDetails.getMonth().getMonthId() == 12) {

		}

		else {
			if (studentFeeSubmissionDetails == null) {
				monthId = 1;
			} else
				monthId = studentFeeSubmissionDetails.getMonth().getMonthId() + 1;
			StudentWiseFee fee = new StudentWiseFee();
			Students student = admissionDao.get(studentId);

			fee.setMonth(monthDao.get(monthId));
			fee.setClasses(student.getClasses());

			fee.setSection(student.getSection());
			fee.setSession(student.getSession());
			fee.setActive(true);
			fee.setStudents(student);
			fee.setFeeAmount(total);
			fee.setFeesCategories(feecategoryDao.get(14));

			studentWiseFeeDao.create(fee);
			List<AllotDress> allotedDress = allotDressDao
					.findAll(AllotDress.class);

			Date date = new Date();
			for (AllotDress list : allotedDress) {
				DressPayment pay = new DressPayment();
				pay.setUniformPurchase(uniformDao.get(list.getUniformPurchase()
						.getProductPurchaseId()));

				pay.setQuantity(list.getQuantity());
				pay.setStudents(studentDao.get(list.getStudents()
						.getStudentAdmissionNo()));
				pay.setPayDate(date);
				pay.setTotalAmount(total);
				pay.setStudentFee(fee);
				pay.setReceiptNo(0);
				dao.create(pay);
				UniformPurchase uniform = uniformDao.get(list
						.getUniformPurchase().getProductPurchaseId());
				uniform.setRemainingQuantity(uniform.getRemainingQuantity()
						- list.getQuantity());
				allotDressDao.deleteById(list.getAllotDressId());

			}
		}
	}

	@Override
	public List<DressPaymentDTO> getStudentUniforms(int classId, int sessionId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		List<Students> students = studentDao.getStudentbyclasssession(
				sessionId, classId);
		for (Students student : students) {
			List<DressPayment> dress = dao.getStudentUniformSold(student
					.getStudentAdmissionNo());
			if (dress.isEmpty() || dress == null) {

			} else {

				for (DressPayment list : dress) {

					DressPaymentDTO dto = new DressPaymentDTO();

					dto.setTotalAmount(list.getTotalAmount());
					if(list.getStudentFee()!=null)
					dto.setStudentwiseFeeId(list.getStudentFee()
							.getStudentWiseFeeId());
					dto.setPayDate(df.format(list.getPayDate()));

					dto.setClassName(list.getStudents().getClasses()
							.getClassName());
					dto.setSectionName(list.getStudents().getSection()
							.getSectionName());
					dto.setFullname(list.getStudents().getFirstName() + " "
							+ list.getStudents().getMiddleName() + " "
							+ list.getStudents().getLastName());

					dto.setFatherName(list.getStudents().getFatherName());

					listdto.add(dto);
				}
			}
			Set<Integer> attributes = new HashSet<Integer>();
			List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
			for (DressPaymentDTO lists : listdto) {
				if (attributes.contains(lists.getStudentwiseFeeId())) {
					duplicates.add(lists);
				}
				attributes.add(lists.getStudentwiseFeeId());
			}
			listdto.removeAll(duplicates);
		}

		return listdto;
	}

	@Override
	public List<DressPaymentDTO> geUniformSold(int feeId) {
		List<DressPayment> dresslist = dao.getUniformsSold(feeId);
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setDressName(list.getUniformPurchase().getUniformName());
			dto.setCategory(list.getUniformPurchase().getUniformCategory());
			dto.setSize(list.getUniformPurchase().getUniformSize());
			dto.setPrice(String.valueOf(list.getUniformPurchase()
					.getUniformPrice()));
			dto.setAmount(list.getUniformPurchase().getUniformPrice()
					* list.getQuantity());
			dto.setQuantity(list.getQuantity());
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<DressPaymentDTO> getStudentSoldUniforms(int classId,
			int sessionId) {

		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		List<Students> students = studentDao.getStudentbyclasssession(
				sessionId, classId);
		int total = 0;
		for (Students student : students) {
			List<DressPayment> dress = dao.getStudentUniformSold(student
					.getStudentAdmissionNo());
			if (dress.isEmpty() || dress == null) {

			} else {
				DressPaymentDTO dto = new DressPaymentDTO();
				total = 0;
				for (DressPayment list : dress) {

					total += list.getTotalAmount();
                   if(list.getStudentFee()!=null)
					dto.setStudentwiseFeeId(list.getStudentFee()
							.getStudentWiseFeeId());

					dto.setClassName(list.getStudents().getClasses()
							.getClassName());
					dto.setSectionName(list.getStudents().getSection()
							.getSectionName());
					dto.setFullname(list.getStudents().getFirstName() + " "
							+ list.getStudents().getMiddleName() + " "
							+ list.getStudents().getLastName());

					dto.setFatherName(list.getStudents().getFatherName());

				}
				dto.setTotalAmount(total);
				listdto.add(dto);
			}
			Set<Integer> attributes = new HashSet<Integer>();
			List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
			for (DressPaymentDTO lists : listdto) {
				if (attributes.contains(lists.getStudentwiseFeeId())) {
					duplicates.add(lists);
				}
				attributes.add(lists.getStudentwiseFeeId());
			}
			listdto.removeAll(duplicates);
		}

		return listdto;
	}

	@Override
	public Long findSize() {
		return dao.findSizeOfTable();
	}

	@Override
	public List<DressPaymentDTO> getOverAllUniform(int page) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getSoldDetailsbyPage(page);
		
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setStudentId(list.getStudents().getStudentId());
			dto.setPayDate(df.format(list.getPayDate()));
			
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setPaymentMode(list.getPaymentMode());
			dto.setChequeno(String.valueOf(list.getChequeNo()));
			dto.setBankName(list.getBankName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setFatherName(list.getStudents().getFatherName());
			dto.setMobile(list.getStudents().getMobile());
			
			listdto.add(dto);
	}
		Set<Integer> attributes = new HashSet<Integer>();
		List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
		for (DressPaymentDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
}

	@Override
	public List<DressPaymentDTO> getTodaySoldDetails() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getTodaysCollection(df.parse(df
				.format(new Date())));
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setStudentId(list.getStudents().getStudentId());
			dto.setPayDate(df.format(list.getPayDate()));
			
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setPaymentMode(list.getPaymentMode());
			dto.setChequeno(String.valueOf(list.getChequeNo()));
			dto.setBankName(list.getBankName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setFatherName(list.getStudents().getFatherName());
			dto.setMobile(list.getStudents().getMobile());
			
			listdto.add(dto);
	}
		Set<Integer> attributes = new HashSet<Integer>();
		List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
		for (DressPaymentDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public List<DressPaymentDTO> getDateWiseSoldDetails(String newDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<DressPayment> dresslist = dao.getTodaysCollection(df.parse(newDate));
		List<DressPaymentDTO> listdto = new ArrayList<DressPaymentDTO>();
		for (DressPayment list : dresslist) {
			
			DressPaymentDTO dto = new DressPaymentDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setStudentId(list.getStudents().getStudentId());
			dto.setPayDate(df.format(list.getPayDate()));
			
			dto.setClassName(list.getStudents().getClasses().getClassName());
			dto.setSectionName(list.getStudents().getSection().getSectionName());
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setPaymentMode(list.getPaymentMode());
			dto.setChequeno(String.valueOf(list.getChequeNo()));
			dto.setBankName(list.getBankName());
			dto.setPaidStatus(list.getPaidStatus());
			dto.setFatherName(list.getStudents().getFatherName());
			dto.setMobile(list.getStudents().getMobile());
			
			listdto.add(dto);
	}
		Set<Integer> attributes = new HashSet<Integer>();
		List<DressPaymentDTO> duplicates = new ArrayList<DressPaymentDTO>();
		for (DressPaymentDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}
}