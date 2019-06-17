package org.brijframework.college.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="library")

public class Library extends AbstractPO<Integer>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="library_book_id")
	public Integer libraryBookId;
	
	@Column(name="book_name")	
	private String bookName;
	
	@Column(name="subject_name")
	private String  subjectName;
	
	@Column(name="book_author")
	private String  bookAuthor;
	
	@Column(name="book_Publisher")
	private String  bookPublisher;
	
	@Column(name="book_Copyright")
	private String  bookCopyright;
	
	@Column(name="book_Edition_Number")
	private String  bookEditionNumber;
	
	@Column(name="total_Book_Page")
	private Integer  totalBookPage;
	
	@Column(name="library_Name")
	private String  libraryName;
	
	@Column(name="shelf_No")
	private Integer  shelfNo;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="remaining_quantity")
	private Integer remainingQuantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="class_id")
	
	private StudentClasses classId;
	@Column(name="created_date")
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getLibraryBookId() {
		return libraryBookId;
	}

	public void setLibraryBookId(Integer libraryBookId) {
		this.libraryBookId = libraryBookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public StudentClasses getClassId() {
		return classId;
	}

	public void setClassId(StudentClasses classId) {
		this.classId = classId;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookCopyright() {
		return bookCopyright;
	}

	public void setBookCopyright(String bookCopyright) {
		this.bookCopyright = bookCopyright;
	}

	public String getBookEditionNumber() {
		return bookEditionNumber;
	}

	public void setBookEditionNumber(String bookEditionNumber) {
		this.bookEditionNumber = bookEditionNumber;
	}

	public Integer getTotalBookPage() {
		return totalBookPage;
	}

	public void setTotalBookPage(Integer totalBookPage) {
		this.totalBookPage = totalBookPage;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public Integer getShelfNo() {
		return shelfNo;
	}

	public void setShelfNo(Integer shelfNo) {
		this.shelfNo = shelfNo;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
