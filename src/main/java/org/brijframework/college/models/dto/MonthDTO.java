package org.brijframework.college.models.dto;

public class MonthDTO {

	private Integer monthId;
	private String monthName;
	private boolean active;
	private String feePaymentStatus;
	private Integer monthSerialNo;

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFeePaymentStatus() {
		return feePaymentStatus;
	}

	public void setFeePaymentStatus(String feePaymentStatus) {
		this.feePaymentStatus = feePaymentStatus;
	}

	public Integer getMonthSerialNo() {
		return monthSerialNo;
	}

	public void setMonthSerialNo(Integer monthSerialNo) {
		this.monthSerialNo = monthSerialNo;
	}

}
