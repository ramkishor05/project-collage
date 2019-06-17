package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.StudentFeeSubmissionDetails;

public interface StudentFeeSubmissionDetailsDao extends
		DAO<Integer, StudentFeeSubmissionDetails> {
	List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetail(
			String studentAdmissionNo, int sectionId, int classId,
			Date fromDate, Date toDate);

	List<Integer> getallDistinctSlipNoByMonth(Date fromDate, Date toDate);

	List<StudentFeeSubmissionDetails> getMonthlyFeesSubmissionById(int monthId);

	List<StudentFeeSubmissionDetails> getYearlyFeeSubmossionById(int sessionId);

	List<StudentFeeSubmissionDetails> getDateWiseCollectionsDetailsByPageNo(
			Date fromDate, Date toDate, int pageNo);

	List<StudentFeeSubmissionDetails> getTodaysCollectionsDetailsByPageNo(
			Date date, int pageno);

	List<StudentFeeSubmissionDetails> getOverallCollectionsDetailsByPageNo(
			int pageNo);

	public List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetails(
			String studentAdmissionNo, int sectionId, int classId);

	public StudentFeeSubmissionDetails getLastRowStudentFeeSubmissionDetails();

	public List<StudentFeeSubmissionDetails> getStudentFeeSubmissionDetail(
			String studentAdmissionNo, Date fromDate, Date toDate);

	public StudentFeeSubmissionDetails getStudentFeeSubmissionDetails(
			int classId, int sectionId, String studentAdmissionNo, Date date);

	List<StudentFeeSubmissionDetails> getallsubmissiondetails(
			String admissionNo, int sessionId);

	List<Integer> getallsubmissiondetailsLfnoByAdmissionNo(String admissionNo);

	public List<Integer> getallfeesubmissiondetailsLfnoBySlipNo(int slipNo);

	public List<StudentFeeSubmissionDetails> getallfeesubmissiondetailsBySlipNo(
			int slipNo);

	public List<Integer> getallfeesubmissiondetailsLfnoByDate(Date fromDate,
			Date todate);

	public List<StudentFeeSubmissionDetails> getallfeesubmissiondetailsByDate(
			Date date);

	List<StudentFeeSubmissionDetails> getDateWiseCollectionsDetailsFromToDate(
			int sectionId, int classId, Date fromDate, Date toDate);

	List<StudentFeeSubmissionDetails> getOverallCollectionsDetails();

	List<StudentFeeSubmissionDetails> getTodaysCollectionsDetails(Date date);

	List<StudentFeeSubmissionDetails> getDateWiseCollectionsDetails(
			Date fromDate, Date toDate);

	StudentFeeSubmissionDetails getDefaulters(int sessionId, int classId,
			int sectionId, int monthId, String admissionNo);

	StudentFeeSubmissionDetails getLfNo();

	public List<StudentFeeSubmissionDetails> getallfeesubmissiondetailsByLfNo(
			Integer lfNo);

	List<StudentFeeSubmissionDetails> getdetailsbystudentId(String id);

	List<StudentFeeSubmissionDetails> getfeedetailsbymonth(int sessionId,
			String studentAdmissionNo);

	public List<Integer> getStudentFeeSubmissionDetails(
			String studentAdmissionNo, int sectionId, int classId, int sessionId);

	List<StudentFeeSubmissionDetails> getPrevsYearFeeSubmissionDetails(
			int sessionId, int months, int admissionId);

	List<StudentFeeSubmissionDetails> getallsubmissiondetailWithFeeStatus(
			String admissionNo);

	public List<Integer> getallDistinctSlipNo();

	public List<Integer> getallDistinctSlipNo(int pageNo);

	public List<Integer> getallDistinctSlipNo(Date date);

	public List<Integer> getallDistinctSlipNo(Date fromDate, Date toDate);

	public List<Integer> getallDistinctSlipNo(int sessionId, String studentId);

	public List<StudentFeeSubmissionDetails> getSingleFeeSubmissionBySlipNo(
			int slipNo);

	public List<Integer> getallInprogressDistinctSlipNo();

	public List<Integer> getMonthIds(int sessionid, String studentId);

	public List<Integer> getMonthIdsBySlipNo(int slipNo);

	public List<Integer> getallDistinctSlipNoBySessionId(int sessionId);

	public List<Integer> getMonthsId(int sessionId, String studentId);

	public List<Integer> getInprogressMonthsId(int sessionId, String studentId);

	List<Integer> getReceiptformonth(int monthId, int sessionId, Date date);

	public Integer getLastMonthIdOfReciept(int slipNo);

	StudentFeeSubmissionDetails findlastpayedmonthbystudentId(String studentId,
			Integer sessionId);

	StudentFeeSubmissionDetails getPaymentStatusDetails(String slipNo);

	List<StudentFeeSubmissionDetails> getSubmissioninMonth(int sessionId,
			int classId, int monthId);

	List<StudentFeeSubmissionDetails> getSubmissionforsectioninMonth(
			int sessionId, int classId, int sectionId, int monthId);

	List<StudentFeeSubmissionDetails> getSubmissionforsstudentinMonth(
			int sessionId, int classId, int sectionId,
			String studentAdmissionNo, int monthId);

	List<StudentFeeSubmissionDetails> getSubmissionforsstudent(int sessionId,
			int classId, int sectionId, String studentAdmissionNo);

	List<StudentFeeSubmissionDetails> getSubmissionforsection(int sessionId,
			int classId, int sectionId);

	List<StudentFeeSubmissionDetails> getSubmissioninClass(int sessionId,
			int classId);
	StudentFeeSubmissionDetails getLastCommonRecieptNo();
	List<String> getByCommonRecieptNo(int commonRecieptNo);
	List<Integer> getByCommonRecieptNoAndStudentAddNo(int commonRecieptNo,String studentAddmissionNo);
	StudentFeeSubmissionDetails getByCommonRecieptNoStudentAddNo(int commonRecieptNo,String studentAddmissionNo);
	List<StudentFeeSubmissionDetails> getByCommonRecieptNoAndStudentAdd(int commonRecieptNo);

	List<Integer> getallfeesubmissiondetailsLfnoByCommonSlipNo(int slipNo);
	public List<StudentFeeSubmissionDetails> getBySessionId(int sessionId);

	List<StudentFeeSubmissionDetails> getFeeSubmissionStatus(Integer sessionId,
			int classId, Integer[] months);

	List<StudentFeeSubmissionDetails> getFeeSubmissionStatus(Integer sessionId,
			int classId, int sectionId, Integer[] months);

	List<StudentFeeSubmissionDetails> getFeeSubmissionStatus(Integer sessionId,
			int classId, int sectionId, Integer[] months,
			String studentAdmissionNo);

	
}
