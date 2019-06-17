package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "fees_categories")
public class FeesCategories extends AbstractPO<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fee_category_id")
	private Integer feeCategoryId;
	@Column(name = "fee_category_name")
	private String feeCategoryName;
	@Column(name = "is_active")
	private boolean active;
	public Integer getFeeCategoryId() {
		return feeCategoryId;
	}
	public void setFeeCategoryId(Integer feeCategoryId) {
		this.feeCategoryId = feeCategoryId;
	}
	public String getFeeCategoryName() {
		return feeCategoryName;
	}
	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
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
