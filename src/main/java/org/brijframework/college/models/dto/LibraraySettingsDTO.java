package org.brijframework.college.models.dto;

public class LibraraySettingsDTO {
	private int librarySettingId;
	private int sessionId;
	private String sessionName;
	private int maxBooks;
	private int maxDays;
	private double fineAmount;
	private double maxFine;
	public int getLibrarySettingId() {
		return librarySettingId;
	}
	public void setLibrarySettingId(int librarySettingId) {
		this.librarySettingId = librarySettingId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
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
	
}
