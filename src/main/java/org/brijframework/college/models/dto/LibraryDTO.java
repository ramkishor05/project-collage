package org.brijframework.college.models.dto;

import java.util.List;

public class LibraryDTO {
	public Integer libraryBookId;
	private String subjectName;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookCopyright;
	private String bookEditionNumber;
	private Integer totalBookPage;
	private String libraryName;
	private Integer shelfNo;
	
	private Integer quantity;
	private Integer remainingQuantity;
	private Integer classId;
	private String createDate;
	private boolean active;
	private List<IsbnDTO> isbnList;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public List<IsbnDTO> getIsbnList() {
		return isbnList;
	}
	public void setIsbnList(List<IsbnDTO> isbnList) {
		this.isbnList = isbnList;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getLibraryBookId() {
		return libraryBookId;
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
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	
	
	
	
	
	

}
