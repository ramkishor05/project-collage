package org.brijframework.college.models.dto;


public class HolidayDTO {
	private int holidayId;
	private String sessionId;
	private int sessionName;
	private String description;
	private String holidayDate;
	private String holidayCount;
	private String des;

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getSessionName() {
		return sessionName;
	}

	public void setSessionName(int sessionName) {
		this.sessionName = sessionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayCount() {
		return holidayCount;
	}

	public void setHolidayCount(String holidayCount) {
		this.holidayCount = holidayCount;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
