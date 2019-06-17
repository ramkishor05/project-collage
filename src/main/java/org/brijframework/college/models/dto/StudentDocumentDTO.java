package org.brijframework.college.models.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class StudentDocumentDTO {

	private int studentDocumentId;
	private String studentId;
	private String studentName;
	private int documentCategoryId;
	private String documentName;
	private boolean active;
	private MultipartFile documentFile;
	private String fileName;
	private List<StudentDocumentDTO> documentList;
	private String uploadedOn;
	
	
	public String getUploadedOn() {
		return uploadedOn;
	}
	public void setUploadedOn(String uploadedOn) {
		this.uploadedOn = uploadedOn;
	}
	public List<StudentDocumentDTO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<StudentDocumentDTO> documentList) {
		this.documentList = documentList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getStudentDocumentId() {
		return studentDocumentId;
	}
	public void setStudentDocumentId(int studentDocumentId) {
		this.studentDocumentId = studentDocumentId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getDocumentCategoryId() {
		return documentCategoryId;
	}
	public void setDocumentCategoryId(int documentCategoryId) {
		this.documentCategoryId = documentCategoryId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public MultipartFile getDocumentFile() {
		return documentFile;
	}
	public void setDocumentFile(MultipartFile documentFile) {
		this.documentFile = documentFile;
	}
	
	
	
	
	
}
