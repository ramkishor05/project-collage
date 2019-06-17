package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.Employees;

public interface EmployeeDao extends DAO<Integer, Employees> {

	Employees finddetailsbyUserId(Integer id);

	List<Employees> getEmployee(String firstName);

	List<Employees> getEmployee(Boolean Active);

}
