package org.brijframework.college.models.dto;

import java.util.List;

public class DressDTO {
	
	private int dressId;
	private String dressName;
	private String category;
	private String size;
	private String price;
	private List<String> categoryList;
	private int status;
	private List<AllotDressDTO> allotedList;
	private String studentId;
	
	
	
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public List<AllotDressDTO> getAllotedList() {
		return allotedList;
	}
	public void setAllotedList(List<AllotDressDTO> allotedList) {
		this.allotedList = allotedList;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}
	public int getDressId() {
		return dressId;
	}
	public void setDressId(int dressId) {
		this.dressId = dressId;
	}
	public String getDressName() {
		return dressName;
	}
	public void setDressName(String dressName) {
		this.dressName = dressName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	

}
