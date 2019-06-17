package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "document_category")
public class DocumentsCategory extends AbstractPO<Integer>{

	@Id
	@Column(name = "document_category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer documentCategoryId;
	@Column(name="document_category_name")
	private String documentCategoryName;
	@Column(name="document_type")
	private String documentType;
	@Column(name="is_active")
	private Boolean active;
	
	
	public Integer getDocumentCategoryId() {
		return documentCategoryId;
	}
	public void setDocumentCategoryId(Integer documentCategoryId) {
		this.documentCategoryId = documentCategoryId;
	}
	public String getDocumentCategoryName() {
		return documentCategoryName;
	}
	public void setDocumentCategoryName(String documentCategoryName) {
		this.documentCategoryName = documentCategoryName;
	}
	
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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
