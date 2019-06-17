package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;

public interface StudentFeeSubmissionDetailsService extends
		CRUDService<Integer, StudentFeeSubmissionDetails> {

	public CommonDTO createStudentFeeSubmissionDetails(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO);

	List<StudentFeeSubmissionDetailsDTO> getOverallCollectionsDetailsByPageNo(
			int pageno);

	List<StudentFeeSubmissionDetailsDTO> getDateWiseCollectionsDetailsByPageNo(
			String fromDate, String toDate, int pageNo) throws ParseException;

	public List<StudentFeeSubmissionDetailsDTO> getStudentFeeSubmissionDetails(
			String studentAdmissionNo, int sectionId, int classId);

	StudentFeeSubmissionDetailsDTO getLastRowStudentFeeSubmissionDetails();

	public List<StudentDTO> getStudentFeeSubmissionDetails(int classId,
			int sectionId, String fromDate, String toDate, int sessionId)
			throws ParseException;

	public void updateStatusById(String paidStatus, int lFno);

	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetails(
			String admissionNo, int sessionId);

	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsAdmissionNo(
			String admissionNo);

	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsBySlipNo(
			int slipNo);

	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsByDate(
			String fromDate, String toDate);

	List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsByStudentName(
			int classId, int sectionId, String studentName);

	public List<StudentFeeSubmissionDetailsDTO> getOverallCollectionsDetails();

	public List<StudentFeeSubmissionDetailsDTO> getTodaysCollectionsDetails()
			throws ParseException;

	public List<StudentFeeSubmissionDetailsDTO> getDateWiseCollectionsDetails(
			String fromDate, String toDate) throws ParseException;

	public Object getYearlyCollectionsDetails(String year);

	public Map<String, Double> getAvailableMonthlyCollectionsDetails(int monthId)
			throws ParseException;

	List<StudentFeeSubmissionDetailsDTO> getStudentFeeSubmissionDetailsByFromToDate(
			String studentAdmissionNo, int sectionId, int classId,
			String fromDate, String toDate);

	CommonDTO getDataForFeeReceiptPDFGeneration(String studentAdmissionNo,
			int slipNo);

	public Map<String, Double> getAvailableyearlyCollectionsDetails(int year)
			throws ParseException;

	public List<StudentDTO> getDefaulterList(int sessionId, int classId,
			int sectionId, int monthId);

	public CommonDTO getStudentFeePaymentDetails(int admissionId, int sessionId);

	public Object getStudentFeePaymentAllotment(int sessionId, int classId,
			int sectionId, int admissionNo, int monthId) throws ParseException;

	public List<StudentDTO> getPaidList(int sessionId, int classId,
			int sectionId, int monthId);

	public List<StudentDTO> getAllDefaulters();

	public List<StudentDTO> getAllDefaultersbyPageNo(int pageNo);

	public List<StudentDTO> getClassWiseDefaulterList(int classId);

	public List<StudentFeeSubmissionDetailsDTO> getInprogressList();

	public void changeFeeStatus(int slipNo, String feePaidStatus);

	public List<StudentFeeSubmissionDetailsDTO> getStudentFeePaymentDetail(
			String studentAdmissionNo, int sessionId);

	public List<StudentFeeSubmissionDetailsDTO> getStudentFeePaymentDetail(
			String studentAdmissionNo);

	public List<FeesCategoriesDTO> getNextMonthFeedetailsbystudentId(
			String studentId);

	public StudentFeeSubmissionDetailsDTO getPaymentStatusDetails(
			String studentId, String slipNo);

	public List<StudentFeeSubmissionDetailsDTO> getTodaysCollectionsDetails(
			String stringDate) throws ParseException;
	public CommonDTO createStudentFeeSubmissionDetailsNew(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO);

	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsByCommonSlipNo(int slipNo);

	public List<StudentFeeSubmissionDetailsDTO> getBySessionId(Integer sessionId);
}
