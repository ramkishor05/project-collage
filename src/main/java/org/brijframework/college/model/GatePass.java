package org.brijframework.college.model;

import java.sql.Time;
import java.util.Date;

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
@Table(name = "gate_pass")
public class GatePass extends AbstractPO<Integer> {
	@Id
	@Column(name = "gate_pass_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer gatePassId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_no")
	private Students students;
	@Column(name = "leaving_date")
	private Date leavingDate;
	@Column(name = "leaving_time")
	private Time leavingTime;
	@Column(name = "reason")
	private String reason;
	@Column(name = "leaving_with")
	private String leavingWith;
	@Column(name = "is_active")
	private boolean active;
	public Integer getGatePassId() {
		return gatePassId;
	}
	public void setGatePassId(Integer gatePassId) {
		this.gatePassId = gatePassId;
	}
	public Students getStudents() {
		return students;
	}
	public void setStudents(Students students) {
		this.students = students;
	}
	public Date getLeavingDate() {
		return leavingDate;
	}
	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}
	
	public Time getLeavingTime() {
		return leavingTime;
	}
	public void setLeavingTime(Time leavingTime) {
		this.leavingTime = leavingTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLeavingWith() {
		return leavingWith;
	}
	public void setLeavingWith(String leavingWith) {
		this.leavingWith = leavingWith;
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
