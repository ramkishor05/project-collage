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
@Table(name = "employee_document")
public class EmployeeDocument extends AbstractPO<Integer>{

	@Id
	@Column(name = "employee_document_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeDocumentId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employees employees;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_category_id")
	private DocumentsCategory document;
	@Column(name="is_active")
	private Boolean active;
	@Column(name="uploaded_on")
	private Date uploadedOn;
	@Column(name="file_name")
	private String fileName;
	
	
	
	public Date getUploadedOn() {
		return uploadedOn;
	}
	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getEmployeeDocumentId() {
		return employeeDocumentId;
	}
	public void setEmployeeDocumentId(Integer employeeDocumentId) {
		this.employeeDocumentId = employeeDocumentId;
	}
	public Employees getEmployees() {
		return employees;
	}
	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
	public DocumentsCategory getDocument() {
		return document;
	}
	public void setDocument(DocumentsCategory document) {
		this.document = document;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
