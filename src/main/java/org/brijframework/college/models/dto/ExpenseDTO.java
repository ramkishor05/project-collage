package org.brijframework.college.models.dto;

public class ExpenseDTO {
	private Integer id;
	private String roleName;
	private String employeeName;
	private String expenseAmount;
	private String purposeDetails;
	private String dateofexpense;
	private String active;
	private double total;
	
	

	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDateofexpense() {
		return dateofexpense;
	}

	public void setDateofexpense(String dateofexpense) {
		this.dateofexpense = dateofexpense;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	

	public String getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getPurposeDetails() {
		return purposeDetails;
	}

	public void setPurposeDetails(String purposeDetails) {
		this.purposeDetails = purposeDetails;
	}

}
