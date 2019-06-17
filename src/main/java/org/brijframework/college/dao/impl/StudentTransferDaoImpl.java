package org.brijframework.college.dao.impl;

import org.brijframework.college.dao.StudentTransferDao;
import org.brijframework.college.model.StudentTransfer;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("studentTransferDao")
public class StudentTransferDaoImpl extends DAOImpl<Integer, StudentTransfer>
		implements StudentTransferDao {

	@Override
	public StudentTransfer getTcfortudent(String studentId) {
		return (StudentTransfer) sessionFactory.getCurrentSession()
				.createCriteria(StudentTransfer.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("student.studentAdmissionNo", studentId))
				.uniqueResult();
	}

}
