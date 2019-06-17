package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "dress")
public class Dress extends AbstractPO<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dress_id")
	private Integer dressId;
	@Column(name = "dress_name")
	private String dressName;
	@Column(name = "category")
	private String category;
	@Column(name = "size")
	private String size;
	@Column(name = "price")
	private int price;
	
	
	

	public Integer getDressId() {
		return dressId;
	}

	public void setDressId(Integer dressId) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
