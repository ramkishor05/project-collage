package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.StudentFineDao;
import org.brijframework.college.model.StudentFine;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class StudentFineDaoImpl extends DAOImpl<Integer, StudentFine> implements
		StudentFineDao {

	@Override
	public StudentFine getStudentFine(int classId, int sectionId,
			String studentAdmissionNo, Date last) {
		return (StudentFine) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFine.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.eq("lastDate", last)).uniqueResult();
	}

	@Override
	public StudentFine getStudentFineFromToDate(String studentAdmissionNo,
			int sectionId, int classId, Date fromDate, Date toDate) {
		return (StudentFine) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFine.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.between("createdAt", fromDate, toDate))
				.uniqueResult();
	}

	@Override
	public List<StudentFine> getFineAmountByMonth(Date frmDate, Date toDate) {
		return (List<StudentFine>) sessionFactory.getCurrentSession()
				.createCriteria(StudentFine.class)
				.add(Restrictions.between("createdAt", frmDate, toDate)).list();
	}

	@Override
	public StudentFine getStudentFine(int studentFeeSubDetailId) {
		return (StudentFine) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFine.class)
				.add(Restrictions
						.eq("studentFeeSubmissionDetails.studentFeeSubmissionDetailsId",
								studentFeeSubDetailId)).uniqueResult();
	}

}
