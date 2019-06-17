package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.brijframework.college.commom.convertor.FeeDiscountEnityToDTO;
import org.brijframework.college.dao.FeeDiscountDao;
import org.brijframework.college.model.FeeDiscount;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.FeeDiscountDTO;
import org.brijframework.college.service.FeeDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeeDiscountServiceImpl extends
		CRUDServiceImpl<Integer, FeeDiscount, FeeDiscountDao> implements
		FeeDiscountService {
	@Autowired
	public FeeDiscountServiceImpl(FeeDiscountDao dao) {
		super(dao);
	}

	@Override
	public void createFeeDiscount(int classId, int sectioId,
			String studentAdmissionNo, String feeDiscountName,
			Double feeDiscountAmount, String lastDate, String fromDate,
			String toDate) {
		SimpleDateFormat Sdf = new SimpleDateFormat("yyyy-MM-dd");
		Section section = new Section();
		section.setSectionId(sectioId);
		StudentClasses studentClasses = new StudentClasses();
		studentClasses.setClassId(classId);
		Students students = new Students();
		students.setStudentAdmissionNo(studentAdmissionNo);
		FeeDiscount feeDiscount = new FeeDiscount();
		feeDiscount.setFeeDiscountAmount(feeDiscountAmount);
		feeDiscount.setFeeDiscountName(feeDiscountName);
		feeDiscount.setSection(section);
		feeDiscount.setStudentClasses(studentClasses);
		feeDiscount.setStudents(students);
		try {
			feeDiscount.setLastDate(Sdf.parse(lastDate));
			feeDiscount.setFromDate(Sdf.parse(fromDate));
			feeDiscount.setToDate(Sdf.parse(toDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.create(feeDiscount);
	}

	@Override
	public void updateFeeDiscount(int feeDiscountId, String feeDiscountName,
			Double feeDiscountAmount) {
		FeeDiscount feeDiscount = dao.get(feeDiscountId);
		feeDiscount.setFeeDiscountAmount(feeDiscountAmount);
		feeDiscount.setFeeDiscountName(feeDiscountName);
		dao.update(feeDiscount);
	}

	@Override
	public FeeDiscountDTO getFeeDiscountById(int feeDiscountId) {
		FeeDiscountEnityToDTO feeDiscountEnityToDTO = FeeDiscountEnityToDTO
				.getInstance();
		return feeDiscountEnityToDTO.convertEntityToDto(dao.get(feeDiscountId));
	}

	@Override
	public List<FeeDiscountDTO> getFeeDiscountDTOs(int classId, int sectionId,
			String studentAdmissionNo, String fromDate, String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date getFromDate = new Date();
		Date getToDate = new Date();
		try {
			getFromDate = sdf.parse(fromDate);
			getToDate = sdf.parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<FeeDiscountDTO> discountDTOs = new ArrayList<FeeDiscountDTO>();
		List<FeeDiscount> feeDiscounts = dao.getFeeDiscountDTOs(classId,
				sectionId, studentAdmissionNo, getFromDate, getToDate);
		FeeDiscountEnityToDTO feeDiscountEnityToDTO = FeeDiscountEnityToDTO
				.getInstance();
		for (FeeDiscount feeDiscount : feeDiscounts) {
			discountDTOs.add(feeDiscountEnityToDTO
					.convertEntityToDto(feeDiscount));
		}
		return discountDTOs;
	}

}
