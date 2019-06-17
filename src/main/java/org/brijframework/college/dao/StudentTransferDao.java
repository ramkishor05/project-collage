package org.brijframework.college.dao;

import org.brijframework.college.model.StudentTransfer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentTransferDao extends DAO<Integer, StudentTransfer> {

	StudentTransfer getTcfortudent(String studentId);

}
