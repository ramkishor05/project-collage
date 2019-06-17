package org.brijframework.college.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.IsbnDao;
import org.brijframework.college.dao.IssueBookDao;
import org.brijframework.college.dao.LibraryDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.ISBN;
import org.brijframework.college.model.IssueBook;
import org.brijframework.college.model.Library;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.IssueBookDTO;
import org.brijframework.college.service.IssueBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueBookServiceImpl extends
		CRUDServiceImpl<Integer, IssueBook, IssueBookDao> implements
		IssueBookService {
	@Autowired
	public IssueBookServiceImpl(IssueBookDao dao) 
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	StudentsAdmissionDao studentDao;
	@Autowired
	IssueBookDao issueDao;
	@Autowired
	LibraryDao libraryDao;
	@Autowired
	IsbnDao isbnDao;

	@Override
	public List<IssueBookDTO> getIssuedBookByStudentId(String studentId) 
	{
		List<IssueBookDTO> dtoList = new ArrayList<IssueBookDTO>();
		List<IssueBook> list = dao.getIssuedBookByStudentId(studentId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (IssueBook issueBook : list) 
		{
			IssueBookDTO dto = new IssueBookDTO();
			dto.setIssueBookId(issueBook.getBook().getLibraryBookId());
			dto.setIssueDate(df.format(issueBook.getIssueDate()));
			Students students = studentDao.get(studentId);
			dto.setStudentId(students.getStudentId());
			dto.setBookName(issueBook.getBook().getBookName());
			dto.setSubjectName(issueBook.getBook().getSubjectName());

			ISBN isbn = isbnDao.get(issueBook.getIsbn().getIsbnId());
			// dto.setIsbn(isbn.getIsbnNo());
			dto.setIsbn(isbn.getIsbnNo());
			dtoList.add(dto);
		}

		return dtoList;
	}
	@Override
	public List<IssueBookDTO> getIssuedBookByStudentBookId(String bookId) 
	{
		List<IssueBookDTO> dtoList = new ArrayList<IssueBookDTO>();
		List<IssueBook> list = dao.getIssuedBookByStudentId(bookId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (IssueBook issueBook : list) {
			IssueBookDTO dto = new IssueBookDTO();
			dto.setIssueBookId(issueBook.getIssueBookId());
			dto.setIssueDate(df.format(issueBook.getIssueDate()));
			Students students = studentDao.get(bookId);
			dto.setStudentId(students.getStudentId());
			dto.setBookName(issueBook.getBook().getBookName());
			dto.setSubjectName(issueBook.getBook().getSubjectName());

			ISBN isbn = isbnDao.get(issueBook.getIsbn().getIsbnId());
			// dto.setIsbn(isbn.getIsbnNo());
			dto.setIsbn(isbn.getIsbnNo());
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public void issueBookToStudent(int bookId, int admissionNo,String isbn) {
		IssueBook book = new IssueBook();
		book.setActive(true);
		Library library = libraryDao.get(bookId);
		book.setBook(library);
		book.setIssueDate(new Date());
		book.setStudent(studentDao.get("" + admissionNo));
		ISBN isbn2=isbnDao.getISBNByBookIdAndISBNNO(isbn, bookId);
		book.setIsbn(isbn2);
		dao.create(book);
	
	}
	
	@Override
	public void returnBookToLibarary(int issueBookId)
	{
		IssueBook book = new IssueBook();
		
		book.setIssueBookId(issueBookId);
		Library library = libraryDao.get(issueBookId);
		book.setBook(library);
		
		dao.delete(book);
	}

	@Override
	public List<IssueBookDTO> getIssueBookToStudent(String bookName,
			int bookId, int admissionNo) {
		List<IssueBookDTO> dtoList = new ArrayList<IssueBookDTO>();
		List<ISBN> list = isbnDao.getBooksISBN_No(bookId);
		for (ISBN isbn : list) 
		{
			IssueBookDTO dto = new IssueBookDTO();
			dto.setBookName(isbn.getLibraryBookId().getBookName());
			dto.setIsbn(isbn.getIsbnNo());
			dto.setIssueDate("" + isbn.getLibraryBookId().getDate());
			dto.setSubjectName(isbn.getLibraryBookId().getSubjectName());
			dto.setStudentId("" + admissionNo);
			dto.setIssueBookId(isbn.getLibraryBookId().getLibraryBookId());
			
			dtoList.add(dto);
		}

		return dtoList;
	}
}