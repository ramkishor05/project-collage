package org.brijframework.college.models.dto;

public class FineDTO {

	private int fineId;
	private String fineName;
	private int sessionId;
	private int maxFineAmount;
	private int fineAmount;
	public int getFineId() {
		return fineId;
	}
	public void setFineId(int fineId) {
		this.fineId = fineId;
	}
	public String getFineName() {
		return fineName;
	}
	public void setFineName(String fineName) {
		this.fineName = fineName;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public int getMaxFineAmount() {
		return maxFineAmount;
	}
	public void setMaxFineAmount(int maxFineAmount) {
		this.maxFineAmount = maxFineAmount;
	}
	public int getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}
	
}
