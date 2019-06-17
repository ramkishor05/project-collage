package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.brijframework.college.dao.AllotDressDao;
import org.brijframework.college.dao.BookPurchaseDao;
import org.brijframework.college.dao.BookSupplierDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.AllotDress;
import org.brijframework.college.model.BookPurchase;
import org.brijframework.college.model.BookSupplier;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.BookPurchaseDTO;
import org.brijframework.college.models.dto.BookSupplierDTO;
import org.brijframework.college.service.BookSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("bookSupplierService")
public class BookSupplierServiceImpl extends
		CRUDServiceImpl<Integer, BookSupplier, BookSupplierDao> implements
		BookSupplierService {

	@Autowired
	public BookSupplierServiceImpl(BookSupplierDao dao) {
		super(dao);

	}

	@Autowired
	SessionDao sessionDao;
	@Autowired
	AllotDressDao allotDressDao;
	@Autowired
	BookPurchaseDao bookPurchaseDao;

	@Override
	public List<String> getSupplierNames() {
		List<String> lists = dao.getSuppliersId();
		return lists;
	}

	@Override
	public List<BookSupplierDTO> getSupplierDetails(String supplierName)
			throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<BookSupplier> purchases = dao.getDetailsbySupplier(supplierName,
				frmDate);
		List<BookSupplierDTO> dtolist = new ArrayList<BookSupplierDTO>();
		for (BookSupplier purchase : purchases) {
			BookSupplierDTO dto = new BookSupplierDTO();
			dto.setGrossAmount(purchase.getGrossAmount());
			dto.setBalanceAmount(purchase.getDues());
			dto.setPaidAmount(purchase.getPaidAmount());
			dto.setDiscount(purchase.getDiscount());
			dto.setDateOfPurchase(dfm.format(purchase.getDateOfPurchase()));
			dto.setStockPurchaseId(purchase.getStockPurchaseId());
			dto.setSupplierNo(purchase.getSupplierNo());
			dto.setPaidStatus(purchase.getPaidStatus());
			dto.setPaymentMode(purchase.getPaymentMode());
			if (purchase.getPaymentMode().equals("Cheque")) {
				dto.setChequeno(String.valueOf(purchase.getChequeNo()));
				dto.setBankName(purchase.getBankName());

			} else {
				dto.setChequeno("no");
				dto.setBankName("no");
			}
			dtolist.add(dto);
		}

		return dtolist;
	}

	@Override
	public BookSupplierDTO savePurchasedBook(BookSupplierDTO bookSupplierDTO)
			throws ParseException {
		List<AllotDress> allotedDress = allotDressDao.findAll(AllotDress.class);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int receiptNo = 0;
		BookSupplier details = dao.getReceipt();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}
		BookSupplier pay = new BookSupplier();
		pay.setActive(true);
		pay.setSupplierName(bookSupplierDTO.getSupplierName());
		pay.setInword(bookSupplierDTO.getInwords());
		pay.setPaidAmount(bookSupplierDTO.getPaidAmount());
		pay.setGrossAmount(bookSupplierDTO.getGrossAmount());
		pay.setReceiptNo(receiptNo);
		pay.setDateOfPurchase(df.parse(bookSupplierDTO.getDateOfPurchase()));
		pay.setDiscount(bookSupplierDTO.getGrossAmount()
				- bookSupplierDTO.getNetPayableAmount());
		pay.setDues(bookSupplierDTO.getNetPayableAmount()
				- bookSupplierDTO.getPaidAmount());
		pay.setPaymentMode(bookSupplierDTO.getPaymentMode());
		pay.setBalanceAmount(0);
		pay.setSupplierNo(bookSupplierDTO.getSupplierNo());

		if (bookSupplierDTO.getPaymentMode().equals("Cheque")) {
			pay.setChequeNo(Integer.parseInt(bookSupplierDTO.getChequeno()));
			pay.setPaidStatus("Inhand");
			pay.setBankName(bookSupplierDTO.getBankName());
		} else {

			pay.setPaidStatus("Completed");
		}
		dao.create(pay);
		for (AllotDress list : allotedDress) {

			BookPurchase book = new BookPurchase();
			book.setActive(true);
			book.setAmount(list.getAmount());
			book.setBoughtQuantity(list.getQuantity());
			book.setNetAmount(list.getNetamount());
			book.setRemainingQuantity(list.getQuantity());
			book.setSupplierName(bookSupplierDTO.getSupplierName());
			book.setBookPrice(list.getPrice());
			book.setBookTitle(list.getType());
			book.setClasses(list.getClasses());
			book.setSubject(list.getSubject());
			book.setStocksupplier(pay);
			book.setSession(list.getSession());
			book.setSection(list.getSection());
			book.setDateOfPurchase(df.parse(bookSupplierDTO.getDateOfPurchase()));
			bookPurchaseDao.create(book);
			allotDressDao.deleteById(list.getAllotDressId());
		}
		return getBookBoughtReceipt(receiptNo);
	}

	@Override
	public BookSupplierDTO getBookBoughtReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		BookSupplier supplier = dao.getReceiptData(receiptNo);
		BookSupplierDTO supdto = new BookSupplierDTO();
		supdto.setBalanceAmount(supplier.getDues());
		if (supplier.getPaymentMode().equals("Cheque")) {
			supdto.setChequeno(String.valueOf(supplier.getChequeNo()));
			supdto.setBankName(supplier.getBankName());

		}
		supdto.setPaymentMode(supplier.getPaymentMode());
		supdto.setDateOfPurchase(df.format(supplier.getDateOfPurchase()));
		supdto.setDiscount(supplier.getDiscount());
		supdto.setDues(supplier.getDues());
		supdto.setGrossAmount(supplier.getGrossAmount());
		supdto.setInwords(supplier.getInword());
		supdto.setPaidAmount(supplier.getPaidAmount());
		supdto.setSupplierName(supplier.getSupplierName());
		supdto.setReceiptNo(receiptNo);
		supdto.setSupplierNo(supplier.getSupplierNo());

		List<BookPurchase> booklist = bookPurchaseDao
				.getbookBoughtDetails(supplier.getStockPurchaseId());
		List<BookPurchaseDTO> listdto = new ArrayList<BookPurchaseDTO>();
		for (BookPurchase list : booklist) {
			BookPurchaseDTO dto = new BookPurchaseDTO();
			dto.setAmount(list.getAmount());
			dto.setBoughtQuantity(list.getBoughtQuantity());
			dto.setNetAmount(list.getNetAmount());
			dto.setClassName(list.getClasses().getClassName());
			dto.setSubjectName(list.getSubject().getSubjectName());
			dto.setBookPrice(list.getBookPrice());
			dto.setBookTitle(list.getBookTitle());
			listdto.add(dto);
		}
		supdto.setBookPurchase(listdto);
		return supdto;
	}

	@Override
	public List<BookSupplierDTO> getBookBoughtDetails() throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<BookSupplier> purchases = dao.getBoughtDetails(frmDate);
		List<BookSupplierDTO> dtolist = new ArrayList<BookSupplierDTO>();
		for (BookSupplier purchase : purchases) {
			BookSupplierDTO dto = new BookSupplierDTO();
			dto.setGrossAmount(purchase.getGrossAmount());
			dto.setBalanceAmount(purchase.getDues());
			dto.setPaidAmount(purchase.getPaidAmount());
			dto.setDateOfPurchase(dfm.format(purchase.getDateOfPurchase()));
			dto.setStockPurchaseId(purchase.getStockPurchaseId());
			dto.setPaidStatus(purchase.getPaidStatus());
			dto.setPaymentMode(purchase.getPaymentMode());
			dto.setReceiptNo(purchase.getReceiptNo());
			dto.setSupplierName(purchase.getSupplierName());
			dto.setDiscount(purchase.getDiscount());
			dtolist.add(dto);
		}
		return dtolist;
	}

	@Override
	public void updateStatus(int id, String status) throws ParseException {
		BookSupplier supplier = dao.getReceiptData(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (status == "Completed" || status.equals("Completed"))
			supplier.setPaidStatus("Completed");
		else {
			supplier.setPaidStatus("Cancel");
			BookSupplier last = dao.getLastpaymenttoSupplier(supplier.getSupplierName(),supplier.getSupplierNo());
			
			BookSupplier sup=new BookSupplier();
			sup.setBalanceAmount(supplier.getPaidAmount()+last.getDues());
			sup.setDues(supplier.getPaidAmount()+last.getDues());
			sup.setActive(true);
			sup.setDateOfPurchase(df.parse(df.format(new Date())));
			sup.setPaidAmount(0);
			sup.setDiscount(0);
			sup.setGrossAmount(supplier.getPaidAmount()+last.getDues());
			sup.setSupplierName(supplier.getSupplierName());
			sup.setSupplierNo(supplier.getSupplierNo());
			sup.setReceiptNo(0);
			sup.setPaidStatus("None");
			sup.setPaymentMode("Cancelled");
			dao.create(sup);
		}

	}

	@Override
	public List<BookSupplierDTO> getBookBoughtDetails(String stringDate)
			throws ParseException {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		if (stringDate.equals("")) {
			date = df.parse(df.format(date));
		} else {
			date = df.parse(stringDate);
		}
		List<BookSupplier> purchases = dao.getBoughtDetailsByDate(date);
		List<BookSupplierDTO> dtolist = new ArrayList<BookSupplierDTO>();
		for (BookSupplier purchase : purchases) {
			BookSupplierDTO dto = new BookSupplierDTO();
			dto.setGrossAmount(purchase.getGrossAmount());
			dto.setBalanceAmount(purchase.getDues());
			dto.setPaidAmount(purchase.getPaidAmount());
			dto.setDateOfPurchase(dfm.format(purchase.getDateOfPurchase()));
			dto.setStockPurchaseId(purchase.getStockPurchaseId());
			dto.setPaidStatus(purchase.getPaidStatus());
			dto.setPaymentMode(purchase.getPaymentMode());
			dto.setReceiptNo(purchase.getReceiptNo());
			dto.setSupplierName(purchase.getSupplierName());
			dto.setDiscount(purchase.getDiscount());
			dtolist.add(dto);
		}
		return dtolist;
	}

	@Override
	public List<BookSupplierDTO> findOverallByPageNo(int pageNo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSupplierDTO> supplierDTOs = new ArrayList<BookSupplierDTO>();
		for (BookSupplier bookSupplier : dao.getOverallExpenseByPageNo(pageNo)) {
			BookSupplierDTO dto = new BookSupplierDTO();
			dto.setGrossAmount(bookSupplier.getGrossAmount());
			dto.setBalanceAmount(bookSupplier.getDues());
			dto.setPaidAmount(bookSupplier.getPaidAmount());
			dto.setDateOfPurchase(df.format(bookSupplier.getDateOfPurchase()));
			dto.setStockPurchaseId(bookSupplier.getStockPurchaseId());
			dto.setPaidStatus(bookSupplier.getPaidStatus());
			dto.setPaymentMode(bookSupplier.getPaymentMode());
			dto.setReceiptNo(bookSupplier.getReceiptNo());
			dto.setSupplierName(bookSupplier.getSupplierName());
			dto.setDiscount(bookSupplier.getDiscount());
			supplierDTOs.add(dto);
		}
		return supplierDTOs;
	}

	@Override
	public List<BookSupplierDTO> findtodaysexpense() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSupplierDTO> supplierDTOs = new ArrayList<BookSupplierDTO>();
		for (BookSupplier bookSupplier : dao.getTodaysExpenses(df.parse(df
				.format(new Date())))) {
			BookSupplierDTO dto = new BookSupplierDTO();
			dto.setGrossAmount(bookSupplier.getGrossAmount());
			dto.setBalanceAmount(bookSupplier.getDues());
			dto.setPaidAmount(bookSupplier.getPaidAmount());
			dto.setDateOfPurchase(df.format(bookSupplier.getDateOfPurchase()));
			dto.setStockPurchaseId(bookSupplier.getStockPurchaseId());
			dto.setPaidStatus(bookSupplier.getPaidStatus());
			dto.setPaymentMode(bookSupplier.getPaymentMode());
			dto.setReceiptNo(bookSupplier.getReceiptNo());
			dto.setSupplierName(bookSupplier.getSupplierName());
			dto.setDiscount(bookSupplier.getDiscount());
			supplierDTOs.add(dto);
		}
		return supplierDTOs;
	}

	@Override
	public List<BookSupplierDTO> findDatewiseExpense(String fromDate,
			String toDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<BookSupplierDTO> supplierDTOs = new ArrayList<BookSupplierDTO>();
		for (BookSupplier bookSupplier : dao.findDatewiseExpense(
				df.parse(fromDate), df.parse(toDate))) {
			BookSupplierDTO dto = new BookSupplierDTO();
			dto.setGrossAmount(bookSupplier.getGrossAmount());
			dto.setBalanceAmount(bookSupplier.getDues());
			dto.setPaidAmount(bookSupplier.getPaidAmount());
			dto.setDateOfPurchase(df.format(bookSupplier.getDateOfPurchase()));
			dto.setStockPurchaseId(bookSupplier.getStockPurchaseId());
			dto.setPaidStatus(bookSupplier.getPaidStatus());
			dto.setPaymentMode(bookSupplier.getPaymentMode());
			dto.setReceiptNo(bookSupplier.getReceiptNo());
			dto.setSupplierName(bookSupplier.getSupplierName());
			dto.setDiscount(bookSupplier.getDiscount());
			supplierDTOs.add(dto);
		}
		return supplierDTOs;
	}
}
