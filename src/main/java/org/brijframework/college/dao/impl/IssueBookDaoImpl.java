package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.IssueBookDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.IssueBook;
import org.brijframework.college.models.dto.IssueBookDTO;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

@Transactional
@Repository("issueBookDao")
public class IssueBookDaoImpl extends DAOImpl<Integer, IssueBook> implements
		IssueBookDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IssueBook> getIssuedBookByStudentId(String studentId) {
		
		
		return sessionFactory.getCurrentSession()
				.createCriteria(IssueBook.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("student.studentAdmissionNo", studentId))
				.list();
	}

	

}
