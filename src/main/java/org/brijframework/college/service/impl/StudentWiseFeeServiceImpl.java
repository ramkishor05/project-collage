package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.commom.convertor.StudentWiseFeeEntityToDTO;
import org.brijframework.college.dao.FeesCategoriesDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.dao.StudentWiseFeeDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.models.dto.StudentWiseFeeDTO;
import org.brijframework.college.service.StudentWiseFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("studentWiseFeeService")
public class StudentWiseFeeServiceImpl extends
		CRUDServiceImpl<Integer, StudentWiseFee, StudentWiseFeeDao> implements
		StudentWiseFeeService {
	@Autowired
	public StudentWiseFeeServiceImpl(StudentWiseFeeDao dao) {
		super(dao);
	}

	@Autowired
	FeesCategoriesDao feesCategoriesDao;
	@Autowired
	MonthDao monthDao;
	@Autowired
	SessionDao sessionDao;
	@Autowired
	StudentClassesDao classDao;
	@Autowired
	SectionDao sectionDao;
	@Autowired
	StudentsAdmissionDao studentDao;
	@Autowired
	StudentFeeSubmissionDetailsDao studentFeeSubmissionDetailsDao;

	@Override
	public String saveStudentWiseFee(StudentWiseFeeDTO studentWiseFeeDTO) {
		String status="no";
		List<StudentFeeSubmissionDetails> fees=new ArrayList<StudentFeeSubmissionDetails>();
		if(studentWiseFeeDTO.getMonthId()==13)
		{
			 fees=studentFeeSubmissionDetailsDao.getSubmissionforsstudent(studentWiseFeeDTO.getSessionId(),studentWiseFeeDTO.getClassId(),studentWiseFeeDTO.getSectionId(),studentWiseFeeDTO.getStudentAdmissionNo());
		}
		else
		{
		fees=studentFeeSubmissionDetailsDao.getSubmissionforsstudentinMonth(studentWiseFeeDTO.getSessionId(),studentWiseFeeDTO.getClassId(),studentWiseFeeDTO.getSectionId(),studentWiseFeeDTO.getStudentAdmissionNo(),studentWiseFeeDTO.getMonthId());
		}
		if(fees.isEmpty())
		{
		StudentWiseFee fee = new StudentWiseFee();
		fee.setActive(true);
		fee.setFeeAmount(Double.parseDouble(studentWiseFeeDTO.getFeeAmount()));
		fee.setFeesCategories(feesCategoriesDao.get((studentWiseFeeDTO
				.getFeesCategoriesId())));
		fee.setMonth(monthDao.get(studentWiseFeeDTO.getMonthId()));
		fee.setSession(sessionDao.get(studentWiseFeeDTO.getSessionId()));
		fee.setClasses(classDao.get(studentWiseFeeDTO.getClassId()));
		fee.setSection(sectionDao.get(studentWiseFeeDTO.getSectionId()));
		fee.setStudents(studentDao.get(studentWiseFeeDTO
				.getStudentAdmissionNo()));
		dao.create(fee);
		status="yes";
		}
		return status;

	}

	@Override
	public List<StudentWiseFeeDTO> getStudentWiseFee(int sessionId,
			int classId, Integer[] monthId, int categoryId, int sectionId,
			String studentId) {
		List<StudentWiseFee> studentWiseFee = dao.getStudentWiseFee(sessionId,
				classId, monthId, categoryId, sectionId, studentId);
		List<StudentWiseFeeDTO> studentWiseFeeDTOs = new ArrayList<StudentWiseFeeDTO>();
		StudentWiseFeeEntityToDTO studentWiseFeeEntityToDTO = new StudentWiseFeeEntityToDTO();
		for (StudentWiseFee studentWiseFee2 : studentWiseFee) {
			studentWiseFeeDTOs.add(studentWiseFeeEntityToDTO
					.convertEntityToDTO(studentWiseFee2));
		}
		return studentWiseFeeDTOs;
	}

	@Override
	public List<StudentWiseFeeDTO> getAllStudentFees() {
		List<StudentWiseFee> fee = dao
				.findAllByIsActiveTrue(StudentWiseFee.class);
		List<StudentWiseFeeDTO> lists = new ArrayList<StudentWiseFeeDTO>();
		for (StudentWiseFee fees : fee) {
			StudentWiseFeeDTO dto = new StudentWiseFeeDTO();
			dto.setStudentWiseFeeId(fees.getStudentWiseFeeId());
			dto.setFirstName(studentDao.get(
					fees.getStudents().getStudentAdmissionNo()).getFirstName());
			dto.setMiddleName(studentDao.get(
					fees.getStudents().getStudentAdmissionNo()).getMiddleName());
			dto.setLastName(studentDao.get(
					fees.getStudents().getStudentAdmissionNo()).getLastName());
			dto.setSectionName(sectionDao.get(fees.getSection().getSectionId())
					.getSectionName());
			dto.setClassName(classDao.get(fees.getClasses().getClassId())
					.getClassName());
			dto.setSessionName(sessionDao.get(fees.getSession().getSessionId())
					.getSessionDuration());
			dto.setFeeAmount(String.valueOf(fees.getFeeAmount()));
			dto.setFeesCategoriesName(feesCategoriesDao.get(
					fees.getFeesCategories().getFeeCategoryId())
					.getFeeCategoryName());
			dto.setMonthName(monthDao.get(fees.getMonth().getMonthId())
					.getMonthName());
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public void setActiveById(int id) {
		dao.get(id).setActive(false);

	}

	public List<StudentWiseFeeDTO> getFeeAllotementByStudentId(int sessionId,
			int classId, int sectionId, String studentId) {
		List<StudentWiseFee> studentWiseFees = dao.getAllotedFeeByStudentId(
				sessionId, classId, sectionId, studentId);

		List<StudentWiseFeeDTO> list = new ArrayList<StudentWiseFeeDTO>();
		StudentWiseFeeEntityToDTO entityToDTO = new StudentWiseFeeEntityToDTO();
		for (StudentWiseFee fee : studentWiseFees) {
			list.add(entityToDTO.convertEntityToDTO(fee));
		}
		return list;
	}

	@Override
	public List<StudentWiseFee> getStudentWiseFee(Integer sessionId,
			int monthId, int categoryId, int studentId) {
		return dao.getStudentWiseFee(sessionId, monthId, categoryId, studentId);
	}

	@Override
	public String deleteAllotedFeesCategory(int classId, int monthId,
			int sectionId, int studentWiseFeeId, String studentAdmissionNo) {
		Session current = sessionDao.findCurrentSession();
		List<StudentFeeSubmissionDetails> feeSubmissionDetails;

		if (monthId == 13) {
			Integer[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			feeSubmissionDetails =  studentFeeSubmissionDetailsDao
					.getFeeSubmissionStatus(current.getSessionId(), classId,
							sectionId, months,studentAdmissionNo);

		} else {

			Integer[] months = { monthId };
			feeSubmissionDetails = studentFeeSubmissionDetailsDao
					.getFeeSubmissionStatus(current.getSessionId(), classId,
							sectionId, months,studentAdmissionNo);
		}
		if (feeSubmissionDetails == null || feeSubmissionDetails.isEmpty())

		{
			dao.deleteById(studentWiseFeeId);
			return "true";
		} else
			return "false";

	}
	}
