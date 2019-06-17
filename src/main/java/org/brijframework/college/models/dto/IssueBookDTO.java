package org.brijframework.college.models.dto;


public class IssueBookDTO 
{
	private Integer issueBookId;
	private String issueDate;
	private String studentId;
	private String bookName;
	private String subjectName;
	private String isbn;
	
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public  Integer getIssueBookId() {
		return issueBookId;
	}
	public void setIssueBookId(Integer issueBookId) {
		this.issueBookId = issueBookId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
}
