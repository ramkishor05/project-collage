package org.brijframework.college.model;

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
@Table(name = "library_settings")
public class LibraraySettings extends AbstractPO<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="library_setting_id")
	private int librarySettingId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@Column(name="maximum_no_books")
	private int maxBooks;
	@Column(name="maximum_no_days")
	private int maxDays;
	@Column(name="fine_amount")
	private double fineAmount;
	@Column(name="max_fine")
	private double maxFine;
	@Column(name="active")
	private boolean active;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getLibrarySettingId() {
		return librarySettingId;
	}
	public void setLibrarySettingId(int librarySettingId) {
		this.librarySettingId = librarySettingId;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public int getMaxBooks() {
		return maxBooks;
	}
	public void setMaxBooks(int maxBooks) {
		this.maxBooks = maxBooks;
	}
	public int getMaxDays() {
		return maxDays;
	}
	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	public double getMaxFine() {
		return maxFine;
	}
	public void setMaxFine(double maxFine) {
		this.maxFine = maxFine;
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
