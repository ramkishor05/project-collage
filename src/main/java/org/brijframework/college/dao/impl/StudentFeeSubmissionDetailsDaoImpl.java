package org.brijframework.college.dao.impl;

import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class StudentFeeSubmissionDetailsDaoImpl extends
		DAOImpl<Integer, StudentFeeSubmissionDetails> implements
		StudentFeeSubmissionDetailsDao {

	@Override
	public List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetail(
			String studentAdmissionNo, int sectionId, int classId,
			Date fromDate, Date toDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.ge("fromDate", fromDate))
				.add(Restrictions.le("toDate", toDate)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetails(
			String studentAdmissionNo, int sectionId, int classId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId)).list();
	}

	@Override
	public StudentFeeSubmissionDetails getLastRowStudentFeeSubmissionDetails() {
		return (StudentFeeSubmissionDetails) sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setMaxResults(1)
				.addOrder(Order.desc("studentFeeSubmissionDetailsId"))
				.uniqueResult();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetail(
			String studentAdmissionNo, Date fromDate, Date toDate) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.between("feePaidDate", fromDate, toDate))
				.list();
	}

	@Override
	public StudentFeeSubmissionDetails getStudentFeeSubmissionDetails(
			int classId, int sectionId, String studentAdmissionNo, Date date) {
		return (StudentFeeSubmissionDetails) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("lastDate", date)).uniqueResult();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getallsubmissiondetails(
			String admissionNo, int sessionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions
						.eq("students.studentAdmissionNo", admissionNo))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getallfeesubmissiondetailsBySlipNo(
			int slipNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("recieptNo", slipNo)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getallfeesubmissiondetailsByDate(
			Date date) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidDate", date)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getDateWiseCollectionsDetailsFromToDate(
			int sectionId, int classId, Date fromDate, Date toDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.ge("fromDate", fromDate))
				.add(Restrictions.le("toDate", toDate)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getOverallCollectionsDetails() {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed")).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getOverallCollectionsDetailsByPageNo(
			int pageno) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.setMaxResults(8).setFirstResult(pageno * 8).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getTodaysCollectionsDetails(
			Date date) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.eq("feePaidDate", date)).list();
	}

	public List<StudentFeeSubmissionDetails> getTodaysCollectionsDetailsByPageNo(
			Date date, int pageno) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.eq("feePaidDate", date)).setMaxResults(8)
				.setFirstResult(pageno * 8).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getDateWiseCollectionsDetails(
			Date fromDate, Date toDate) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.between("feePaidDate", fromDate, toDate))
				.list();
	}

	public List<StudentFeeSubmissionDetails> getDateWiseCollectionsDetailsByPageNo(
			Date fromDate, Date toDate, int pageNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.between("feePaidDate", fromDate, toDate))
				.setMaxResults(8).setFirstResult(pageNo * 8).list();
	}

	@Override
	public StudentFeeSubmissionDetails getDefaulters(int sessionId,
			int classId, int sectionId, int monthId, String admissionNo) {
		return (StudentFeeSubmissionDetails) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions
						.eq("students.studentAdmissionNo", admissionNo))
				.uniqueResult();
	}

	@Override
	public StudentFeeSubmissionDetails getLfNo() {
		return (StudentFeeSubmissionDetails) sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.addOrder(Order.desc("recieptNo")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getallfeesubmissiondetailsByLfNo(
			Integer slipNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("recieptNo", slipNo)).list();
	}

	@Override
	public List<Integer> getallfeesubmissiondetailsLfnoBySlipNo(int slipNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("recieptNo", slipNo)).list();
	}

	@Override
	public List<Integer> getallfeesubmissiondetailsLfnoByDate(Date fromDate,
			Date todate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.between("feePaidDate", fromDate, todate))
				.list();
	}

	@Override
	public List<Integer> getallsubmissiondetailsLfnoByAdmissionNo(
			String admissionNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions
						.eq("students.studentAdmissionNo", admissionNo)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getdetailsbystudentId(String id) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo", id)).list();

	}

	@Override
	public List<StudentFeeSubmissionDetails> getfeedetailsbymonth(
			int sessionId, String studentAdmissionNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feePaidStatus", "completed")).list();
	}

	@Override
	public List<Integer> getStudentFeeSubmissionDetails(
			String studentAdmissionNo, int sectionId, int classId, int sessionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getPrevsYearFeeSubmissionDetails(
			int sessionId, int monthId, int admissionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						String.valueOf(admissionId))).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getallsubmissiondetailWithFeeStatus(
			String admissionNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions
						.eq("students.studentAdmissionNo", admissionNo)).list();
	}

	@Override
	public List<Integer> getallDistinctSlipNo() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "completed")).list();
	}

	public List<Integer> getallDistinctSlipNoByMonth(Date fromDate, Date toDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.ge("feePaidDate", fromDate))
				.add(Restrictions.le("feePaidDate", toDate)).list();
	}

	public List<StudentFeeSubmissionDetails> getMonthlyFeesSubmissionById(
			int monthId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("recieptNo", monthId))
				.add(Restrictions.eq("feePaidStatus", "completed")).list();
	}

	public List<StudentFeeSubmissionDetails> getYearlyFeeSubmossionById(
			int sessionId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feePaidStatus", "completed")).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSingleFeeSubmissionBySlipNo(
			int slipNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("recieptNo", slipNo)).setMaxResults(1)
				.list();
	}

	@Override
	public List<Integer> getallDistinctSlipNo(int pageNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.setMaxResults(8).setFirstResult(pageNo * 8).list();
	}

	@Override
	public List<Integer> getallDistinctSlipNo(Date date) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.eq("feePaidDate", date)).list();
	}

	@Override
	public List<Integer> getallDistinctSlipNo(Date fromDate, Date toDate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.between("feePaidDate", fromDate, toDate))
				.list();
	}

	@Override
	public List<Integer> getallDistinctSlipNo(int sessionId, String studentId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<Integer> getallInprogressDistinctSlipNo() {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "inprogress")).list();
	}

	@Override
	public List<Integer> getMonthIds(int sessionId, String studentId) {
		Criterion post = Restrictions.eq("feePaidStatus", "inprogress");
		Criterion pre = Restrictions.eq("feePaidStatus", "completed");
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.or(pre, post))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<Integer> getMonthIdsBySlipNo(int slipNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("recieptNo", slipNo)).list();
	}

	@Override
	public List<Integer> getallDistinctSlipNoBySessionId(int sessionId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<Integer> getReceiptformonth(int monthId, int sessionId,
			Date paydate) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("feePaidStatus", "completed"))
				.add(Restrictions.eq("month.monthId", monthId))
				.add(Restrictions.ge("feePaidDate", paydate))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<Integer> getMonthsId(int sessionId, String studentId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.ne("feePaidStatus", "cancel")).list();
	}

	@Override
	public List<Integer> getInprogressMonthsId(int sessionId, String studentId) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("feePaidStatus", "inprogress")).list();
	}

	@Override
	public Integer getLastMonthIdOfReciept(int slipNo) {
		return (Integer) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("recieptNo", slipNo))
				.addOrder(Order.desc("month.monthId")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public StudentFeeSubmissionDetails findlastpayedmonthbystudentId(
			String studentId, Integer sessionId) {
		return (StudentFeeSubmissionDetails) sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.setMaxResults(1)
				.addOrder(Order.desc("studentFeeSubmissionDetailsId"))
				.add(Restrictions.eq("students.studentAdmissionNo", studentId))
				.uniqueResult();
	}

	@Override
	public StudentFeeSubmissionDetails getPaymentStatusDetails(String slipNo) {
		return (StudentFeeSubmissionDetails) sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("recieptNo", Integer.parseInt(slipNo)))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSubmissioninMonth(
			int sessionId, int classId, int monthId) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("month.monthId", monthId)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSubmissionforsectioninMonth(
			int sessionId, int classId, int sectionId, int monthId) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("month.monthId", monthId)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSubmissionforsstudentinMonth(
			int sessionId, int classId, int sectionId,
			String studentAdmissionNo, int monthId) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.eq("month.monthId", monthId)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSubmissionforsstudent(
			int sessionId, int classId, int sectionId, String studentAdmissionNo) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSubmissionforsection(
			int sessionId, int classId, int sectionId) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("section.sectionId", sectionId)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getSubmissioninClass(
			int sessionId, int classId) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public StudentFeeSubmissionDetails getLastCommonRecieptNo() {
		return (StudentFeeSubmissionDetails) sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.addOrder(Order.desc("commonRecieptNo")).setMaxResults(1)
				.uniqueResult();
	}

	@Override
	public List<String> getByCommonRecieptNo(int commonRecieptNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("students.studentAdmissionNo")))
				.add(Restrictions.eq("commonRecieptNo", commonRecieptNo))
				.list();
	}

	@Override
	public List<Integer> getByCommonRecieptNoAndStudentAddNo(
			int commonRecieptNo, String studentAddmissionNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections
								.property("month.monthId")))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAddmissionNo))
				.add(Restrictions.eq("commonRecieptNo", commonRecieptNo))
				.add(Restrictions.ne("feePaidStatus", "cancel")).list();
	}

	@Override
	public StudentFeeSubmissionDetails getByCommonRecieptNoStudentAddNo(
			int commonRecieptNo, String studentAddmissionNo) {
		return (StudentFeeSubmissionDetails) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAddmissionNo))
				.add(Restrictions.eq("commonRecieptNo", commonRecieptNo))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getByCommonRecieptNoAndStudentAdd(
			int commonRecieptNo) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("commonRecieptNo", commonRecieptNo))
				.add(Restrictions.ne("feePaidStatus", "cancel")).list();
	}

	@Override
	public List<Integer> getallfeesubmissiondetailsLfnoByCommonSlipNo(int slipNo) {
		return sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.setProjection(
						Projections.distinct(Projections.property("recieptNo")))
				.add(Restrictions.eq("commonRecieptNo", slipNo)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getBySessionId(int sessionId) {
		return sessionFactory.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getFeeSubmissionStatus(
			Integer sessionId, int classId, Integer[] months) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.in("month.monthId", months)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getFeeSubmissionStatus(
			Integer sessionId, int classId, int sectionId, Integer[] months) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.in("month.monthId", months)).list();
	}

	@Override
	public List<StudentFeeSubmissionDetails> getFeeSubmissionStatus(
			Integer sessionId, int classId, int sectionId, Integer[] months,
			String studentAdmissionNo) {
		return (List<StudentFeeSubmissionDetails>) sessionFactory
				.getCurrentSession()
				.createCriteria(StudentFeeSubmissionDetails.class)
				.add(Restrictions.eq("session.sessionId", sessionId))
				.add(Restrictions.eq("studentClasses.classId", classId))
				.add(Restrictions.eq("students.studentAdmissionNo",
						studentAdmissionNo))
				.add(Restrictions.in("month.monthId", months)).list();
	}

}
