package org.brijframework.college.dao;

import org.brijframework.college.model.NewStudentTransfer;
public interface NewStudentTransferDao extends DAO<Integer, NewStudentTransfer>{

	NewStudentTransfer getTCForStudent(String studentId);

	
	

}
