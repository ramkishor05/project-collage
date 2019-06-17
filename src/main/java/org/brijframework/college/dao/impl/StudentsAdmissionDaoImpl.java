package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.StudentAdmission;
import org.brijframework.college.model.Students;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("unchecked")
public class StudentsAdmissionDaoImpl extends DAOImpl<String, Students>
		implements StudentsAdmissionDao {

	public void ceateAdmission(StudentAdmission studentAdmission) {
		sessionFactory.getCurrentSession().save(studentAdmission);
	}

	public StudentAdmission getStudent(int id) {
		return (StudentAdmission) sessionFactory.getCurrentSession()
				.createCriteria(StudentAdmission.class)
				.add(Restrictions.eq("studentId", id)).uniqueResult();
	}

	public List<StudentAdmission> getAllApplication() {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAdmission.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("delete", true))
				.add(Restrictions.eq("transferStatus", "no")).list();
	}

	@Override
	public List<Students> getStudentListById(int courseId, int branchId,
			int batchId) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("course.courseId", courseId))
				.add(Restrictions.eq("branch.branchId", branchId))
				.add(Restrictions.eq("batches.batchesId", batchId))).list();
	}

	@Override
	public List<Students> getStudent(String firstName) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true)).add(Restrictions.like(
				"firstName", firstName + "%"))).list();
	}

	public List<Students> getStudentByClassIdAndName(int classId,
			String firstName, int sessionId) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("active", true)).add(Restrictions.like(
				"firstName", firstName + "%"))).list();
	}

	@Transactional
	public Students getActiveStudentById(String id) {
		return (Students) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("studentAdmissionNo", id)).uniqueResult());
	}

	public Students getInActiveStudentById(String id) {
		return (Students) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", false))
				.add(Restrictions.eq("studentAdmissionNo", id)).uniqueResult());
	}

	@Override
	public List<Students> getStudentsbyClassSectionId(int classId,
			int sectionId, int sessionId) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true)).add(Restrictions.eq(
				"classes.classId", classId)))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.addOrder(Order.asc("rollNo"))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<Students> getStudentbyFatherName(String fatherName,
			int sessionId) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("active", true)).add(Restrictions.like(
				"fatherName", fatherName + "%"))).list();
	}

	@Override
	public List<Students> getStudentByMatchFromName(int classId, int sessionId,
			String studentName) {
		return sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.like("firstName", studentName + "%")).list();
	}

	@Override
	public List<Students> getStudentByClassId(Integer classId) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("classes.classId", classId)).list();
	}

	@Override
	public List<Students> getCancelledList(int sessionId) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", false))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public int getMaxLfno(int sessionId, int classId) {
		return (Integer) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.setProjection(Projections.max("lfNo")).uniqueResult();

	}

	@Override
	public List<Students> getStudentbyclasssession(int sessionId, int classId) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId)).list();
	}

	@Override
	public List<Students> getStudentsByClassId(int classId) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("classes.classId", classId)).list();
	}

	@Override
	public List<Students> getStudentsbysession(Integer sessionId) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	@Transactional
	public List<Students> getStudentsbysessionandpageno(Integer sessionId, int p) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.setMaxResults(10).setFirstResult(p * 10).list();
	}

	@Override
	public List<Students> getStudentbyAdmNo(String admissionNo) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("studentId", admissionNo)).list();
	}

	@Override
	public List<Students> getStudentsByClassIdandSession(int classId,
			int sessionId) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("classes.classId", classId))
				.addOrder(Order.asc("rollNo"))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<Students> getStudentsByClassIdandSessionpageno(int classId,
			int sessionId, int pageno) {
		return (List<Students>) sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.setMaxResults(10).setFirstResult(pageno * 10).list();
	}

	@Override
	public Students getStudentbyuserId(Integer id) {
		return (Students) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("user.id", id)).uniqueResult());
	}

	@Override
	public Students getAdmissionNo() {
		return (Students) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.addOrder(Order.desc("uniqueId")).setMaxResults(1))
				.uniqueResult();
	}

	@Override
	public Students checkExistStudent(String studentId, int sessionId) {
		return (Students) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("studentId", studentId)).uniqueResult());
	}

	@Override
	public List<Students> getStudentbyFatherName(int categoryId, int sessionId) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("active", true)).add(Restrictions.eq(
				"studentCategory.studentCategoriesId", categoryId))).list();
	}

	@Override
	public List<Students> getStudentbyCategory(int categoryId, int sessionId,
			int classId) {
		return (List<Students>) (sessionFactory.getCurrentSession()
				.createCriteria(Students.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("classes.classId", classId))
				.add(Restrictions.eq("active", true)).add(Restrictions.eq(
				"studentCategory.studentCategoriesId", categoryId))).list();
	}

	@Override
	public List<StudentAdmission> getCancelledAllApplication() {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAdmission.class)
				.add(Restrictions.eq("active", false))
				.add(Restrictions.eq("delete", true))
				.add(Restrictions.eq("transferStatus", "no")).list();
	}

	@Override
	public List<StudentAdmission> getTransferredAllApplication() {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentAdmission.class)
				.add(Restrictions.eq("active", true))
				.add(Restrictions.eq("delete", true))
				.add(Restrictions.eq("transferStatus", "yes")).list();
	}
}
