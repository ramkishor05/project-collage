package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.brijframework.college.dao.BookPurchaseDao;
import org.brijframework.college.dao.BookSellDao;
import org.brijframework.college.dao.FeesCategoriesDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.dao.StudentWiseFeeDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.BookPurchase;
import org.brijframework.college.model.BookSell;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.BookSellDTO;
import org.brijframework.college.service.BookSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("bookSellService")
public class BookSellServiceImpl extends
		CRUDServiceImpl<Integer, BookSell, BookSellDao> implements
		BookSellService {

	@Autowired
	public BookSellServiceImpl(BookSellDao dao) {
		super(dao);

	}

	@Autowired
	BookPurchaseDao bookPurchaseDao;
	@Autowired
	StudentsAdmissionDao studentDao;
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
	@Autowired
	SessionDao sessionDao;

	@Override
	public List<BookSellDTO> savepayment(BookSellDTO bookSellDTO) {
		int receiptNo = 0;
		Date date = new Date();
		BookSell details = dao.getReceipt();
		if (details == null) {
			receiptNo = 1;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}
		for (Integer integer : bookSellDTO.getBookPurchaseId()) {

			BookSell book = new BookSell();
			book.setTotalAmount(bookSellDTO.getTotalAmount());
			if (bookSellDTO.getPaymentMode().equals("Cheque")) {
				book.setChequeNo(Integer.parseInt(bookSellDTO.getChequeno()));
				book.setPaidStatus("InHand");
				book.setBankName(bookSellDTO.getBankName());
			} else {

				book.setPaidStatus("Completed");
			}
			book.setBookPurchase(bookPurchaseDao.get(integer));
			book.setInword(bookSellDTO.getInwords());
			book.setPaidAmount(bookSellDTO.getPaidAmount());
			book.setStudents(studentDao.get(bookSellDTO.getStudentId()));
			book.setDuesReceiptNo(0);
			book.setTotalAmount(bookSellDTO.getTotalAmount());
			book.setReceiptNo(receiptNo);
			book.setPayDate(date);
			book.setDiscount(bookSellDTO.getTotalAmount()
					- bookSellDTO.getNetPayableAmount());
			book.setDues(bookSellDTO.getNetPayableAmount()
					- bookSellDTO.getPaidAmount());
			book.setPaymentMode(bookSellDTO.getPaymentMode());
			
				StudentFeeSubmissionDetails studentFeeSubmissionDetails = feeDao
						.findlastpayedmonthbystudentId(bookSellDTO.getStudentId(),
								sessionDao.findCurrentSession().getSessionId());
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
					Students student = admissionDao.get(bookSellDTO.getStudentId());

					fee.setMonth(monthDao.get(monthId));
					fee.setClasses(student.getClasses());

					fee.setSection(student.getSection());
					fee.setSession(student.getSession());
					fee.setActive(true);
					fee.setStudents(student);
					fee.setFeeAmount(bookSellDTO.getTotalAmount()
							- bookSellDTO.getNetPayableAmount());
					fee.setFeesCategories(feecategoryDao.get(15));

					studentWiseFeeDao.create(fee);
					book.setStudentFee(fee);

			}
			
			dao.create(book);
			BookPurchase purchase = bookPurchaseDao.get(integer);
			purchase.setRemainingQuantity(purchase.getRemainingQuantity() - 1);

		}
		return getBookReceipt(receiptNo);
	}

	@Override
	public List<BookSellDTO> getSoldBookDetails(String from, String to)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> book = dao.getSoldDetails(df.parse(from), df.parse(to));
		int totalPaid = 0;
		int totalDues = 0;
		int due = 0;
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : book) {
			totalPaid += list.getPaidAmount();
			due = 0;
			BookSellDTO dto = new BookSellDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			List<BookSell> dueDetails = dao.findDues(list.getReceiptNo());
			if (dueDetails.isEmpty() || dueDetails == null) {

			} else {
				for (BookSell details : dueDetails) {
					due = due + details.getPaidAmount() + details.getDiscount();
				}
			}
			dto.setDues(list.getDues() - due);
			totalDues += list.getDues() - due;
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setBookPaymentId(list.getBookPaymentId());
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
		List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
		for (BookSellDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public List<BookSellDTO> getBookReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> booklist = dao.getReceiptData(receiptNo);
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : booklist) {
			BookSellDTO dto = new BookSellDTO();
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
			dto.setBookTitle(list.getBookPurchase().getBookTitle());
			dto.setPrice(String.valueOf(list.getBookPurchase().getBookPrice()));
			dto.setFatherName(list.getStudents().getFatherName());
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
	public void updatePaymentStatus(String paidStatus, int receiptno) {
		List<BookSell> bookSell = dao.getReceiptData(receiptno);
		for (BookSell book : bookSell) {
			book.setPaidStatus(paidStatus);
			if (paidStatus.equals("Cancel")) {
				book.setDues(book.getDues() + book.getPaidAmount());
				book.setPaidAmount(0);
			}

		}

	}

	@Override
	public List<BookSellDTO> getUnpaidBookDues(int classId, int sessionId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		List<Students> students = studentDao.getStudentbyclasssession(
				sessionId, classId);
		for (Students student : students) {
			List<BookSell> book = dao.getStudentBookDues(student
					.getStudentAdmissionNo());
			if (book.isEmpty() || book == null) {

			} else {

				int totalPaid = 0;
				int totalDues = 0;
				int due = 0;
				for (BookSell list : book) {
					List<BookSell> dueDetails = dao.findDues(list
							.getReceiptNo());
					if (dueDetails.isEmpty() || dueDetails == null) {

					} else {
						for (BookSell details : dueDetails) {
							due = due + details.getPaidAmount()
									+ details.getDiscount();
						}
					}
					if (list.getDues() - due == 0) {
					} else {
						totalPaid += list.getPaidAmount();

						due = 0;
						BookSellDTO dto = new BookSellDTO();
						dto.setPaidAmount(list.getPaidAmount());
						dto.setTotalAmount(list.getTotalAmount());
						dto.setDiscount(list.getDiscount());
						List<BookSell> dueDetails1 = dao.findDues(list
								.getReceiptNo());
						if (dueDetails1.isEmpty() || dueDetails1 == null) {

						} else {
							for (BookSell details : dueDetails1) {
								due = due + details.getPaidAmount()
										+ details.getDiscount();
							}
						}
						dto.setDues(list.getDues() - due);
						totalDues += list.getDues() - due;
						dto.setInwords(list.getInword());
						dto.setReceiptno(list.getReceiptNo());
						dto.setPayDate(df.format(list.getPayDate()));
						dto.setBookPaymentId(list.getBookPaymentId());
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
				Set<Integer> attributes = new HashSet<Integer>();
				List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
				for (BookSellDTO lists : listdto) {
					if (attributes.contains(lists.getReceiptno())) {
						duplicates.add(lists);
					}
					attributes.add(lists.getReceiptno());
				}
				listdto.removeAll(duplicates);
			}

		}
		return listdto;

	}

	@Override
	public BookSellDTO submitdues(BookSellDTO bookSellDTO) {
		int receiptNo = 0;
		Date date = new Date();
		BookSell details = dao.getReceipt();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}

		BookSell pay = new BookSell();

		pay.setInword(bookSellDTO.getInwords());
		pay.setPaidAmount(bookSellDTO.getPaidAmount());
		pay.setTotalAmount(bookSellDTO.getTotalAmount());
		pay.setReceiptNo(receiptNo);
		pay.setPayDate(date);

		pay.setDuesReceiptNo(bookSellDTO.getReceiptno());
		pay.setDiscount(bookSellDTO.getTotalAmount()
				- bookSellDTO.getNetPayableAmount());
		int discount = bookSellDTO.getTotalAmount()
				- bookSellDTO.getNetPayableAmount();
		pay.setDues(bookSellDTO.getTotalAmount() - discount
				- bookSellDTO.getPaidAmount());
		pay.setPaymentMode(bookSellDTO.getPaymentMode());
		if (bookSellDTO.getPaymentMode().equals("Cheque")) {
			pay.setChequeNo(Integer.parseInt(bookSellDTO.getChequeno()));
			pay.setPaidStatus("InHand");
			pay.setBankName(bookSellDTO.getBankName());
		} else {

			pay.setPaidStatus("Completed");
		}
		dao.create(pay);
		return getBookDuesReceipt(receiptNo);
	}

	@Override
	public BookSellDTO getBookDuesReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		BookSell list = dao.getBookDuesReceiptData(receiptNo);
		BookSell stu = dao.getBookStudentDetails(list.getDuesReceiptNo());
		BookSellDTO dto = new BookSellDTO();
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
	public List<BookSellDTO> getBookDueDetails(int receiptno) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> booklist = dao.findDues(receiptno);

		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : booklist) {
			BookSellDTO dto = new BookSellDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());

			dto.setDues(list.getDues());
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			BookSell stu = dao.getBookStudentDetails(list.getDuesReceiptNo());
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
	public void savestudentbooks(BookSellDTO bookSellDTO) {

		Date date = new Date();
		StudentFeeSubmissionDetails studentFeeSubmissionDetails = feeDao
				.findlastpayedmonthbystudentId(bookSellDTO.getStudentId(),
						sessionDao.findCurrentSession().getSessionId());
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
			Students student = admissionDao.get(bookSellDTO.getStudentId());

			fee.setMonth(monthDao.get(monthId));
			fee.setClasses(student.getClasses());

			fee.setSection(student.getSection());
			fee.setSession(student.getSession());
			fee.setActive(true);
			fee.setStudents(student);
			fee.setFeeAmount(bookSellDTO.getTotalAmount());
			fee.setFeesCategories(feecategoryDao.get(15));

			studentWiseFeeDao.create(fee);

			for (Integer integer : bookSellDTO.getBookPurchaseId()) {

				BookSell book = new BookSell();
				book.setTotalAmount(bookSellDTO.getTotalAmount());
                book.setReceiptNo(0);
				book.setBookPurchase(bookPurchaseDao.get(integer));

				book.setStudents(studentDao.get(bookSellDTO.getStudentId()));

				book.setPayDate(date);
				book.setStudentFee(fee);
				dao.create(book);
				BookPurchase purchase = bookPurchaseDao.get(integer);
				purchase.setRemainingQuantity(purchase.getRemainingQuantity() - 1);
			}
		}
	}

	@Override
	public List<BookSellDTO> getStudentBooks(int classId, int sessionId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		List<Students> students = studentDao.getStudentbyclasssession(
				sessionId, classId);
		for (Students student : students) {
			List<BookSell> book = dao.getStudentBook(student
					.getStudentAdmissionNo());
			if (book.isEmpty() || book == null) {

			} else {

				for (BookSell list : book) {

					BookSellDTO dto = new BookSellDTO();

					dto.setTotalAmount(list.getTotalAmount());
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
			List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
			for (BookSellDTO lists : listdto) {
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
	public List<BookSellDTO> getBooksSold(int feeId) {

		List<BookSell> booklist = dao.getBooksSold(feeId);
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : booklist) {
			BookSellDTO dto = new BookSellDTO();
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setBookTitle(list.getBookPurchase().getBookTitle());
			dto.setPrice(String.valueOf(list.getBookPurchase().getBookPrice()));
			listdto.add(dto);
		}
		return listdto;

	}

	@Override
	public List<BookSellDTO> getSoldBookDetails(String stringDate)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> book = dao.getSoldDetails(df.parse(stringDate));
		int totalPaid = 0;
		int totalDues = 0;
		int due = 0;
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : book) {
			totalPaid += list.getPaidAmount();
			due = 0;
			BookSellDTO dto = new BookSellDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			List<BookSell> dueDetails = dao.findDues(list.getReceiptNo());
			if (dueDetails.isEmpty() || dueDetails == null) {

			} else {
				for (BookSell details : dueDetails) {
					due = due + details.getPaidAmount() + details.getDiscount();
				}
			}
			dto.setDues(list.getDues() - due);
			totalDues += list.getDues() - due;
			dto.setInwords(list.getInword());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setBookPaymentId(list.getBookPaymentId());
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
		List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
		for (BookSellDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;

	}

	@Override
	public List<BookSellDTO> getStudentBooksbought(int classId, int sessionId) {

		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		List<Students> students = studentDao.getStudentbyclasssession(
				sessionId, classId);
		int total = 0;
		for (Students student : students) {
			List<BookSell> book = dao.getStudentBook(student
					.getStudentAdmissionNo());
			if (book.isEmpty() || book == null) {

			} else {
				BookSellDTO dto = new BookSellDTO();
				total = 0;
				for (BookSell list : book) {
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
					dto.setStudentId(list.getStudents().getStudentAdmissionNo());

				}
				dto.setTotalAmount(total);
				listdto.add(dto);
			}
			Set<Integer> attributes = new HashSet<Integer>();
			List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
			for (BookSellDTO lists : listdto) {
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
	public List<BookSellDTO> getBooksSoldtoStudent(String studentId) {
		List<BookSell> booklist = dao.getStudentBook(studentId);
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : booklist) {
			BookSellDTO dto = new BookSellDTO();
			dto.setFullname(list.getStudents().getFirstName() + " "
					+ list.getStudents().getMiddleName() + " "
					+ list.getStudents().getLastName());
			dto.setBookTitle(list.getBookPurchase().getBookTitle());
			dto.setPrice(String.valueOf(list.getBookPurchase().getBookPrice()));
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public Long findSize() {
		return dao.findSizeOfTable();
	}

	@Override
	public List<BookSellDTO> getSoldBookDetailsbypage(int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> book = dao.getSoldDetailsbypage(i);
		
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : book) {
			
			BookSellDTO dto = new BookSellDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			dto.setStudentId(list.getStudents().getStudentId());
			dto.setDues(list.getDues());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setBookPaymentId(list.getBookPaymentId());
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
		List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
		for (BookSellDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public List<BookSellDTO> getTodaySoldDetails() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> book = dao.getTodaySoldDetails(df.parse(df
				.format(new Date())));
		
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : book) {
			
			BookSellDTO dto = new BookSellDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			dto.setStudentId(list.getStudents().getStudentId());
			dto.setDues(list.getDues());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setBookPaymentId(list.getBookPaymentId());
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
		List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
		for (BookSellDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}

	@Override
	public List<BookSellDTO> getDatewiseSoldDetails(String newDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSell> book = dao.getTodaySoldDetails(df.parse(newDate));
		
		List<BookSellDTO> listdto = new ArrayList<BookSellDTO>();
		for (BookSell list : book) {
			
			BookSellDTO dto = new BookSellDTO();
			dto.setPaidAmount(list.getPaidAmount());
			dto.setTotalAmount(list.getTotalAmount());
			dto.setDiscount(list.getDiscount());
			dto.setStudentId(list.getStudents().getStudentId());
			dto.setDues(list.getDues());
			dto.setReceiptno(list.getReceiptNo());
			dto.setPayDate(df.format(list.getPayDate()));
			dto.setBookPaymentId(list.getBookPaymentId());
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
		List<BookSellDTO> duplicates = new ArrayList<BookSellDTO>();
		for (BookSellDTO lists : listdto) {
			if (attributes.contains(lists.getReceiptno())) {
				duplicates.add(lists);
			}
			attributes.add(lists.getReceiptno());
		}
		listdto.removeAll(duplicates);
		return listdto;
	}
}
