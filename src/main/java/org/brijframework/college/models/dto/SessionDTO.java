package org.brijframework.college.models.dto;


public class SessionDTO {

	private Integer sessionId;
	private String sessionDuration;
	private boolean active;
	private boolean currentSession;
	
	
	public boolean isCurrentSession() {
		return currentSession;
	}
	public void setCurrentSession(boolean currentSession) {
		this.currentSession = currentSession;
	}
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionDuration() {
		return sessionDuration;
	}
	public void setSessionDuration(String sessionDuration) {
		this.sessionDuration = sessionDuration;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
