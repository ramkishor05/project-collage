package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.dao.BookPurchaseDao;
import org.brijframework.college.dao.BookSupplierDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.model.BookPurchase;
import org.brijframework.college.model.BookSupplier;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.BookPurchaseDTO;
import org.brijframework.college.service.BookPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("boolPurchaseService")
public class BookPurchaseServiceImpl extends
CRUDServiceImpl<Integer, BookPurchase, BookPurchaseDao> implements
BookPurchaseService {

	@Autowired
	public BookPurchaseServiceImpl(BookPurchaseDao dao) {
		super(dao);
		
	}
	@Autowired
	SessionDao sessionDao;
	@Autowired
	StudentClassesDao classDao;
	@Autowired
	BookSupplierDao bookSupplierDao;

	@Override
	public int getProductNo() {
		int count = dao.findAll(BookPurchase.class).size() + 1;
		return count;
	}

	@Override
	public List<BookPurchaseDTO> getPurchaseDetails() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Session current = sessionDao.findCurrentSession();
		List<BookPurchase> purchaselist = dao
				.findCurrentSessionPurchases(current.getSessionId());
		List<BookPurchaseDTO> list = new ArrayList<BookPurchaseDTO>();
		for (BookPurchase book : purchaselist) {
			BookPurchaseDTO dto = new BookPurchaseDTO();
			dto.setAmount(book.getAmount());
			dto.setDateOfPurchase(df.format(book.getDateOfPurchase()));
			dto.setProductNo(book.getProductNo());
			dto.setBookPurchaseId(book.getBookPurchaseId());
			dto.setBoughtQuantity(book.getBoughtQuantity());
			dto.setSupplierName(book.getSupplierName());
			dto.setBookPrice(book.getBookPrice());
			dto.setBookTitle(book.getBookTitle());
			dto.setClassName(book.getClasses().getClassName());
			list.add(dto);

		}
		return list;
		
	}

	@Override
	public BookPurchaseDTO getPurchaseDetailsbyId(int bookPurchaseId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		BookPurchase book = dao.get(bookPurchaseId);
		BookPurchaseDTO dto = new BookPurchaseDTO();
		dto.setAmount(book.getAmount());
		dto.setDateOfPurchase(df.format(book.getDateOfPurchase()));
		dto.setProductNo(book.getProductNo());
		dto.setBookPurchaseId(book.getBookPurchaseId());
		dto.setBoughtQuantity(book.getBoughtQuantity());
		dto.setSupplierName(book.getSupplierName());
		dto.setBookPrice(book.getBookPrice());
		dto.setBookTitle(book.getBookTitle());
		dto.setClassName(book.getClasses().getClassName());
		dto.setClassId(book.getClasses().getClassId());
		dto.setSessionId(book.getSession().getSessionId());
		return dto;
	}

	@Override
	public void savepurchasedetals(BookPurchaseDTO bookPurchaseDTO) throws ParseException {
		BookPurchase book=new BookPurchase();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		book.setActive(true);
		book.setAmount(bookPurchaseDTO.getAmount());
		book.setBookPrice(bookPurchaseDTO.getBookPrice());
		book.setBookTitle(bookPurchaseDTO.getBookTitle());
		book.setBookPrice(bookPurchaseDTO.getBookPrice());
		book.setBoughtQuantity(bookPurchaseDTO.getBoughtQuantity());
		book.setClasses(classDao.get(bookPurchaseDTO.getClassId()));
		book.setDateOfPurchase(df.parse(bookPurchaseDTO.getDateOfPurchase()));
		book.setProductNo(bookPurchaseDTO.getProductNo());
		book.setRemainingQuantity(bookPurchaseDTO.getBoughtQuantity());
		book.setSupplierName(bookPurchaseDTO.getSupplierName());
		book.setSession(sessionDao.get(bookPurchaseDTO.getSessionId()));
		dao.create(book);
		
		
		
	}

	@Override
	public String updatepurchases(BookPurchaseDTO bookPurchaseDTO) throws ParseException {
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		BookPurchase book = dao.get(bookPurchaseDTO.getBookPurchaseId());
	 {
		 if((book.getBookTitle().equals(bookPurchaseDTO.getBookTitle())) && (book.getClasses().getClassId()==bookPurchaseDTO.getClassId()) && (book.getBookPrice()==bookPurchaseDTO.getBookPrice()) &&(book.getSession().getSessionId()==bookPurchaseDTO.getSessionId()))
				 {
			if (book.getBoughtQuantity() <= bookPurchaseDTO
					.getBoughtQuantity()) {
				book.setAmount(bookPurchaseDTO.getAmount());
				book.setDateOfPurchase(df.parse(bookPurchaseDTO.getDateOfPurchase()));
				book.setRemainingQuantity(bookPurchaseDTO
						.getBoughtQuantity()
						- book.getBoughtQuantity()
						+ book.getRemainingQuantity());
				book.setBoughtQuantity(bookPurchaseDTO.getBoughtQuantity());
				book.setSupplierName(bookPurchaseDTO.getSupplierName());
				
				result="*Successfully Updated";
			} else {
				int restquantity = book.getRemainingQuantity();
				int returnquantity = book.getBoughtQuantity()
						- bookPurchaseDTO.getBoughtQuantity();
				if (restquantity < returnquantity) {
					result = "*Stock is less";
				} else {
					book.setAmount(bookPurchaseDTO.getAmount());
					book.setDateOfPurchase(df.parse(bookPurchaseDTO.getDateOfPurchase()));
					book.setBoughtQuantity(bookPurchaseDTO.getBoughtQuantity());
					book.setSupplierName(bookPurchaseDTO.getSupplierName());
					book.setRemainingQuantity(book.getRemainingQuantity()
							- returnquantity);
					result="*Successfully Updated";
					
				}
			}

		} else {
			if (book.getRemainingQuantity() < book.getBoughtQuantity()) {
				result = "*Book has been sold out from this stock";
			} else {
				book.setAmount(bookPurchaseDTO.getAmount());
				book.setBookPrice(bookPurchaseDTO.getBookPrice());
				book.setBookTitle(bookPurchaseDTO.getBookTitle());
				book.setBookPrice(bookPurchaseDTO.getBookPrice());
				book.setBoughtQuantity(bookPurchaseDTO.getBoughtQuantity());
				book.setClasses(classDao.get(bookPurchaseDTO.getClassId()));
				book.setDateOfPurchase(df.parse(bookPurchaseDTO.getDateOfPurchase()));
				book.setProductNo(bookPurchaseDTO.getProductNo());
				book.setRemainingQuantity(bookPurchaseDTO.getBoughtQuantity());
				book.setSupplierName(bookPurchaseDTO.getSupplierName());
				book.setSession(sessionDao.get(bookPurchaseDTO.getSessionId()));
				result = "Successfully Updated";
			}
		}
		return result;
	}
	}

	@Override
	public String cancelPurchase(int bookPurchaseId) {
		String result = "";
		BookPurchase book = dao.get(bookPurchaseId);
		if (book.getRemainingQuantity() < book.getBoughtQuantity()) {
			result = "*Book has been sold out from this stock";
		} else {
			result = "Successfully deleted";
			book.setActive(false);
		}
		return result;
	}

	@Override
	public List<BookPurchaseDTO> getClasswiseBookList(int id) {
		Session current = sessionDao.findCurrentSession();
		List<String> purchaselist = dao
				.findBookList(current.getSessionId(),id);
		List<BookPurchaseDTO> list=new ArrayList<BookPurchaseDTO>();
		for(String name:purchaselist)
		{
			System.out.println(name);
			BookPurchase book=dao.findbookDetails(name,current.getSessionId(),id);
			if(book==null)
			{
			 book=dao.findoutofStockBook(name,current.getSessionId(),id);
			}
		
		BookPurchaseDTO dto=new BookPurchaseDTO();
			dto.setBookPrice(book.getBookPrice());
			dto.setBookPurchaseId(book.getBookPurchaseId());
			dto.setBookTitle(book.getBookTitle());
			dto.setRemainingQuantity(book.getRemainingQuantity());
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<BookPurchaseDTO> getBookStockDetails(int classId, int sessionId) {
		List<String> booknames = dao.findBookList(sessionId, classId);
		List<BookPurchaseDTO> list = new ArrayList<BookPurchaseDTO>();
		int totalQuantity = 0;
		int remainingQuantity = 0;
		for (String name : booknames) {
			totalQuantity = 0;
			remainingQuantity = 0;
			BookPurchaseDTO dto = new BookPurchaseDTO();
			dto.setBookTitle(name);

			List<BookPurchase> bookStock = dao.findbookstock(sessionId,classId,name);
			for (BookPurchase stock : bookStock) {

				totalQuantity += stock.getBoughtQuantity();
				remainingQuantity += stock.getRemainingQuantity();
			}
			dto.setBoughtQuantity(totalQuantity);
			dto.setRemainingQuantity(remainingQuantity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<BookPurchaseDTO> getBookbySubject(int subjectId,
			Integer sessionId) {
		List<String> lists=dao.findbookbysubject(subjectId,sessionId);
		List<BookPurchaseDTO> listdto=new ArrayList<BookPurchaseDTO>();
		int stock=0;
		String subject="";
		for(String name:lists)
		{
			stock=0;
			List<BookPurchase> booklist=dao.findbookbysubject(subjectId,sessionId,name);
			for(BookPurchase book:booklist)
			{
				stock+=book.getRemainingQuantity();
				subject=book.getSubject().getSubjectName();
			}
			BookPurchaseDTO dto=new BookPurchaseDTO();
			dto.setBookTitle(name);
			dto.setRemainingQuantity(stock);
			dto.setSubjectName(subject);
			listdto.add(dto);
		}
		
		
		return listdto;
	}

	@Override
	public List<BookPurchaseDTO> getBoughtDetails(int stockPurchaseId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<BookPurchase> purchaselist=dao.getbookBoughtDetails(stockPurchaseId);
		BookSupplier supply=bookSupplierDao.get(stockPurchaseId);
		
		List<BookPurchaseDTO> list = new ArrayList<BookPurchaseDTO>();
		for (BookPurchase book : purchaselist) {
			BookPurchaseDTO dto = new BookPurchaseDTO();
			dto.setAmount(book.getAmount());
			dto.setDateOfPurchase(df.format(book.getDateOfPurchase()));
		    dto.setBoughtQuantity(book.getBoughtQuantity());
		    dto.setNetAmount(book.getNetAmount());
			dto.setBookPrice(book.getBookPrice());
			dto.setClassName(book.getClasses().getClassName());
			dto.setSubjectName(book.getSubject().getSubjectName());
			dto.setSupplierName(book.getStocksupplier().getSupplierName());
			dto.setPaymentMode(supply.getPaymentMode());
			dto.setBookTitle(book.getBookTitle());
			dto.setSectionName(book.getSection().getSectionName());
			if(supply.getPaymentMode()=="Cheque" || supply.getPaymentMode().equals("Cheque"))
			{
				dto.setBankName(supply.getBankName());
				dto.setChequeno(supply.getChequeNo());
				
			}
			list.add(dto);

		}
		return list;
	}

	@Override
	public List<BookPurchaseDTO> getClassWiseBooks(int classId, int sessionId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<BookPurchase> booklist=dao.findBooksbyclass(classId,sessionId);
		List<BookPurchaseDTO> listdto=new ArrayList<BookPurchaseDTO>();
		for(BookPurchase book:booklist)
		{
			BookPurchaseDTO dto=new BookPurchaseDTO();
			dto.setSubjectName(book.getSubject().getSubjectName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookPrice(book.getBookPrice());
			dto.setBoughtQuantity(book.getBoughtQuantity());
			dto.setAmount(book.getAmount());
			dto.setSupplierName(book.getSupplierName());
			dto.setNetAmount(book.getNetAmount());
			dto.setDateOfPurchase(df.format(book.getDateOfPurchase()));
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<BookPurchaseDTO> getClassSectionBookList(int classId,
			int sectionId) {
		Session current = sessionDao.findCurrentSession();
		List<String> purchaselist = dao
				.findBookListclassSection(current.getSessionId(),classId,sectionId);
		List<BookPurchaseDTO> list=new ArrayList<BookPurchaseDTO>();
		for(String name:purchaselist)
		{
			
			BookPurchase book=dao.findbookDetailsnew(name,current.getSessionId(),classId,sectionId);
			if(book==null)
			{
			 book=dao.findoutofStockBooknew(name,current.getSessionId(),classId,sectionId);
			}
		
		BookPurchaseDTO dto=new BookPurchaseDTO();
			dto.setBookPrice(book.getBookPrice());
			dto.setBookPurchaseId(book.getBookPurchaseId());
			dto.setBookTitle(book.getBookTitle());
			dto.setRemainingQuantity(book.getRemainingQuantity());
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<BookPurchaseDTO> getClasssecttionWiseBooks(int sessionId,
			int classId, int sectionId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<BookPurchase> booklist=dao.findBooksbyclasssection(sessionId,classId,sectionId);
		List<BookPurchaseDTO> listdto=new ArrayList<BookPurchaseDTO>();
		for(BookPurchase book:booklist)
		{
			BookPurchaseDTO dto=new BookPurchaseDTO();
			dto.setSubjectName(book.getSubject().getSubjectName());
			dto.setBookTitle(book.getBookTitle());
			dto.setBookPrice(book.getBookPrice());
			dto.setBoughtQuantity(book.getBoughtQuantity());
			dto.setAmount(book.getAmount());
			dto.setSupplierName(book.getSupplierName());
			dto.setNetAmount(book.getNetAmount());
			dto.setDateOfPurchase(df.format(book.getDateOfPurchase()));
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<BookPurchaseDTO> getBookStockDetailsnew(int classId,
			int sessionId, int sectionId) {
		List<String> booknames = dao.findBookListclassSection(sessionId, classId, sectionId);
		List<BookPurchaseDTO> list = new ArrayList<BookPurchaseDTO>();
		int totalQuantity = 0;
		int remainingQuantity = 0;
		for (String name : booknames) {
			totalQuantity = 0;
			remainingQuantity = 0;
			BookPurchaseDTO dto = new BookPurchaseDTO();
			dto.setBookTitle(name);

			List<BookPurchase> bookStock = dao.findbookstocknew(sessionId, classId,sectionId, name);
			for (BookPurchase stock : bookStock) {

				totalQuantity += stock.getBoughtQuantity();
				remainingQuantity += stock.getRemainingQuantity();
			}
			dto.setBoughtQuantity(totalQuantity);
			dto.setRemainingQuantity(remainingQuantity);
			list.add(dto);
		}
		return list;
	}

}
