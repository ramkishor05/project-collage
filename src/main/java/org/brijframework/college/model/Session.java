package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "session")
public class Session extends AbstractPO<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "session_id")
	private Integer sessionId;
	@Column(name = "session_duration")
	private String sessionDuration;
	@Column(name = "is_active")
	private boolean active;
	@Column(name = "current_session")
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
