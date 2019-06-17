package org.brijframework.college.models.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeDocumentDTO {
	
	private int employeeDocumentId;
	private int employeeId;
	private int  documentCategoryId;
	private String documentName;
	private MultipartFile documentFile;
	private boolean active;
	private String uploadedOn;
	private String fileName;
	private List<EmployeeDocumentDTO> documentList;
	
	
	
	
	public List<EmployeeDocumentDTO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<EmployeeDocumentDTO> documentList) {
		this.documentList = documentList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getEmployeeDocumentId() {
		return employeeDocumentId;
	}
	public void setEmployeeDocumentId(int employeeDocumentId) {
		this.employeeDocumentId = employeeDocumentId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public int getDocumentCategoryId() {
		return documentCategoryId;
	}
	public void setDocumentCategoryId(int documentCategoryId) {
		this.documentCategoryId = documentCategoryId;
	}
	public String getUploadedOn() {
		return uploadedOn;
	}
	public void setUploadedOn(String uploadedOn) {
		this.uploadedOn = uploadedOn;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public MultipartFile getDocumentFile() {
		return documentFile;
	}
	public void setDocumentFile(MultipartFile documentFile) {
		this.documentFile = documentFile;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
