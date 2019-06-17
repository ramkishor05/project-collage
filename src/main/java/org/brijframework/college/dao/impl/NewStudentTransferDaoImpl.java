package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.NewStudentTransferDao;
import org.brijframework.college.model.NewStudentTransfer;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository("NewStudentTransferDao")
public class NewStudentTransferDaoImpl extends DAOImpl<Integer, NewStudentTransfer>implements NewStudentTransferDao{

	@Override
	public NewStudentTransfer getTCForStudent(String studentId) {
		return  (NewStudentTransfer) sessionFactory.getCurrentSession()
				.createCriteria(NewStudentTransfer.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("student.studentAdmissionNo", studentId))
				.uniqueResult();
	}



}
