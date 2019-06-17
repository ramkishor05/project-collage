package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.commom.convertor.ClassWiseFeeEntityToDTO;
import org.brijframework.college.dao.ClassWiseFeeDao;
import org.brijframework.college.dao.FeesCategoriesDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.model.ClassWiseFee;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.models.dto.ClassWiseFeeDTO;
import org.brijframework.college.service.ClassWiseFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("classWiseFeeService")
public class ClassWiseFeeServiceImpl extends
		CRUDServiceImpl<Integer, ClassWiseFee, ClassWiseFeeDao> implements
		ClassWiseFeeService {
	@Autowired
	public ClassWiseFeeServiceImpl(ClassWiseFeeDao dao) {
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
	StudentFeeSubmissionDetailsDao studentFeeSubmissionDetailsDao;

	@Override
	public String saveClassWiseFee(ClassWiseFeeDTO classWiseFeeDTO) {
		String status = "no";
		List<StudentFeeSubmissionDetails> fees = new ArrayList<StudentFeeSubmissionDetails>();
		if (classWiseFeeDTO.getMonthId() == 13) {
			fees = studentFeeSubmissionDetailsDao.getSubmissioninClass(
					classWiseFeeDTO.getSessionId(),
					classWiseFeeDTO.getClassId());
		} else {
			fees = studentFeeSubmissionDetailsDao.getSubmissioninMonth(
					classWiseFeeDTO.getSessionId(),
					classWiseFeeDTO.getClassId(), classWiseFeeDTO.getMonthId());
		}
		if (fees.isEmpty()) {
			ClassWiseFee fee = new ClassWiseFee();
			fee.setActive(true);
			fee.setFeeAmount(Double.parseDouble(classWiseFeeDTO.getFeeAmount()));
			fee.setFeesCategories(feesCategoriesDao.get((classWiseFeeDTO
					.getFeesCategoriesId())));
			fee.setMonth(monthDao.get(classWiseFeeDTO.getMonthId()));
			fee.setSession(sessionDao.get(classWiseFeeDTO.getSessionId()));
			fee.setClasses(classDao.get(classWiseFeeDTO.getClassId()));
			dao.create(fee);
			status = "yes";
		}

		return status;

	}

	@Override
	public List<ClassWiseFeeDTO> getClassWiseFee(int sessionId, int classId,
			Integer[] monthId, int categoryId) {
		ClassWiseFeeEntityToDTO classWiseFeeEntityToDTO = ClassWiseFeeEntityToDTO
				.getInstance();
		List<ClassWiseFeeDTO> classWiseFeeDTOs = new ArrayList<ClassWiseFeeDTO>();
		for (ClassWiseFee classWiseFee : dao.getClassWiseFee(sessionId,
				classId, monthId, categoryId)) {
			classWiseFeeDTOs.add(classWiseFeeEntityToDTO
					.convertEntityToDTO(classWiseFee));
		}
		return classWiseFeeDTOs;
	}

	@Override
	public List<ClassWiseFeeDTO> getAllClassFees() {
		List<ClassWiseFee> fee = dao.findAllByIsActiveTrue(ClassWiseFee.class);
		List<ClassWiseFeeDTO> lists = new ArrayList<ClassWiseFeeDTO>();
		for (ClassWiseFee fees : fee) {
			ClassWiseFeeDTO dto = new ClassWiseFeeDTO();
			dto.setClassWiseFeeId(fees.getClassWiseFeeId());
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
	public void setActiveById(int classWiseFeeId) {
		dao.get(classWiseFeeId).setActive(false);

	}

	@Override
	public List<ClassWiseFeeDTO> getAllotedFeesByClassId(int classId,
			int sessionId) {
		List<ClassWiseFeeDTO> classWiseFeeDTOs = new ArrayList<ClassWiseFeeDTO>();
		ClassWiseFeeEntityToDTO classWiseFeeEntityToDTO = new ClassWiseFeeEntityToDTO();
		for (ClassWiseFee classWiseFee : dao.getAllotedFeesByClassId(classId,
				sessionId)) {
			classWiseFeeDTOs.add(classWiseFeeEntityToDTO
					.convertEntityToDTO(classWiseFee));
		}
		return classWiseFeeDTOs;
	}

	@Override
	public String deleteAllotedFeesCategory(int classId, int monthId,
			int classWiseFeeId) {
		Session current = sessionDao.findCurrentSession();
		List<StudentFeeSubmissionDetails> feeSubmissionDetails;

		if (monthId == 13) {
			Integer[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
			feeSubmissionDetails = studentFeeSubmissionDetailsDao
					.getFeeSubmissionStatus(current.getSessionId(), classId,
							months);

		} else {

			Integer[] months = { monthId };
			feeSubmissionDetails = studentFeeSubmissionDetailsDao
					.getFeeSubmissionStatus(current.getSessionId(), classId,
							months);
		}
		if (feeSubmissionDetails == null || feeSubmissionDetails.isEmpty())

		{
			dao.deleteById(classWiseFeeId);
			return "true";
		} else
			return "false";

	}
}