package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.AssignClass;

public interface AssignClassDao extends DAO<Integer, AssignClass> {

	List<AssignClass> getAssignClassById(int employeeId);
	 public AssignClass getAssignClassAndSection(int employeeId);
	AssignClass checkAssignClassesForTecher(int employeeId, int classId);

}
