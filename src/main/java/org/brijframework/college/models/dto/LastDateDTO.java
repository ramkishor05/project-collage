package org.brijframework.college.models.dto;

public class LastDateDTO {

	private Integer lastdateId;
	private int monthId;
	private String lastdate;
	private String monthName;
	private int sessionId;

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public Integer getLastdateId() {
		return lastdateId;
	}

	public void setLastdateId(Integer lastdateId) {
		this.lastdateId = lastdateId;
	}

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

}
