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
@Table(name = "student_document")
public class StudentDocument extends AbstractPO<Integer>{

	@Id
	@Column(name = "student_document_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentDocumentId;
	@Column(name = "student_id")
	private String student;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_category_id")
	private DocumentsCategory document;
	@Column(name="is_active")
	private Boolean active;
	@Column(name="file_name")
	private String fileName;
	@Column(name="uploaded_on")
	private Date uploadedOn;
	
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
	public Integer getStudentDocumentId() {
		return studentDocumentId;
	}
	public void setStudentDocumentId(Integer studentDocumentId) {
		this.studentDocumentId = studentDocumentId;
	}
	
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
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
