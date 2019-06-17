package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.brijframework.college.dao.EditFeeAmountDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.model.EditFeeAmount;
import org.brijframework.college.model.FeesCategories;
import org.brijframework.college.model.Month;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.EditFeeAmountDTO;
import org.brijframework.college.service.EditFeeAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EditFeeAmountServiceImpl extends
		CRUDServiceImpl<Integer, EditFeeAmount, EditFeeAmountDao> implements
		EditFeeAmountService {
	@Autowired
	public EditFeeAmountServiceImpl(EditFeeAmountDao dao) {
		super(dao);
	}

	@Autowired
	private StudentFeeSubmissionDetailsDao studentFeeSubmissionDetailsDao;
	@Autowired
	private MonthDao monthDao;

	@SuppressWarnings("unused")
	@Override
	public List<EditFeeAmountDTO> getEditFeeAmountDTOs(int classId,
			int sectionId, int sessionId, int feeCategoryId, int monthId,
			String studentId, double allottedAmount) {
		/* Map map = new HashMap(); */
		List<EditFeeAmountDTO> editFeeAmountDTOs = new ArrayList<EditFeeAmountDTO>();
		List<Integer> submittedId = studentFeeSubmissionDetailsDao.getMonthIds(
				sessionId, studentId);
		List<EditFeeAmount> editFeeAmounts = dao.getEditFeeAmountDTOsLast(
				classId, sectionId, sessionId, feeCategoryId, studentId);
		List<Integer> changeFeeIds = dao.getEditFeeAmountDTOs(classId,
				sectionId, sessionId, feeCategoryId, studentId);
		List<Month> months = monthDao.findAll(Month.class);
		if (monthId == 13) {
			for (Month month : months) {
				if (month.getMonthId() != 13) {
					EditFeeAmount editedFeeAmounts = dao.getEditFeeAmountDTOs(
							classId, sectionId, sessionId, feeCategoryId,
							studentId, month.getMonthId());
					double discountAmount = 0.0;
					if (editedFeeAmounts != null) {
						discountAmount = allottedAmount
								- editedFeeAmounts.getDiscount();
					} else {
						discountAmount = allottedAmount;
					}
					EditFeeAmountDTO editFeeAmountDTO = new EditFeeAmountDTO();
					if (submittedId.contains(month.getMonthId())) {
						editFeeAmountDTO.setMonthId(month.getMonthId());
						editFeeAmountDTO.setMonthName(month.getMonthName());
						editFeeAmountDTO.setMonthSerialNo(month.getSerialNo());
						editFeeAmountDTO.setCurrentAmount(allottedAmount);
						editFeeAmountDTO.setDiscount(discountAmount);
						editFeeAmountDTO.setStatusVarible("complete");
					} else if (changeFeeIds.contains(month.getMonthId())) {
						editFeeAmountDTO.setMonthId(month.getMonthId());
						editFeeAmountDTO.setMonthName(month.getMonthName());
						editFeeAmountDTO.setMonthSerialNo(month.getSerialNo());
						editFeeAmountDTO.setCurrentAmount(allottedAmount);
						editFeeAmountDTO.setDiscount(discountAmount);
						editFeeAmountDTO.setStatusVarible("inprogress");

					} else {
						editFeeAmountDTO.setMonthId(month.getMonthId());
						editFeeAmountDTO.setMonthName(month.getMonthName());
						editFeeAmountDTO.setMonthSerialNo(month.getSerialNo());
						editFeeAmountDTO.setCurrentAmount(allottedAmount);
						editFeeAmountDTO.setDiscount(discountAmount);
						editFeeAmountDTO.setStatusVarible("cancel");
					}
					editFeeAmountDTOs.add(editFeeAmountDTO);
				}
			}
		} else {
			EditFeeAmountDTO editFeeAmountDTO = new EditFeeAmountDTO();
			Month month = monthDao.get(monthId);
			EditFeeAmount editedFeeAmounts = dao.getEditFeeAmountDTOs(classId,
					sectionId, sessionId, feeCategoryId, studentId, monthId);
			double discountAmount = 0.0;
			if (editedFeeAmounts != null) {
				discountAmount = allottedAmount
						- editedFeeAmounts.getDiscount();
			} else {
				discountAmount = allottedAmount;
			}
			if (submittedId.contains(month.getMonthId())) {
				editFeeAmountDTO.setMonthId(month.getMonthId());
				editFeeAmountDTO.setMonthName(month.getMonthName());
				editFeeAmountDTO.setMonthSerialNo(month.getSerialNo());
				editFeeAmountDTO.setCurrentAmount(allottedAmount);
				editFeeAmountDTO.setDiscount(discountAmount);
				editFeeAmountDTO.setStatusVarible("complete");
			} else if (changeFeeIds.contains(month.getMonthId())) {
				editFeeAmountDTO.setMonthId(month.getMonthId());
				editFeeAmountDTO.setMonthName(month.getMonthName());
				editFeeAmountDTO.setMonthSerialNo(month.getSerialNo());
				editFeeAmountDTO.setCurrentAmount(allottedAmount);
				editFeeAmountDTO.setDiscount(discountAmount);
				editFeeAmountDTO.setStatusVarible("inprogress");
			} else {
				editFeeAmountDTO.setMonthId(month.getMonthId());
				editFeeAmountDTO.setMonthName(month.getMonthName());
				editFeeAmountDTO.setMonthSerialNo(month.getSerialNo());
				editFeeAmountDTO.setCurrentAmount(allottedAmount);
				editFeeAmountDTO.setDiscount(discountAmount);
				editFeeAmountDTO.setStatusVarible("cancel");
			}
			editFeeAmountDTOs.add(editFeeAmountDTO);
		}
		if (editFeeAmountDTOs.size() > 0) {
			Collections.sort(editFeeAmountDTOs,
					new Comparator<EditFeeAmountDTO>() {
						@Override
						public int compare(final EditFeeAmountDTO object1,
								final EditFeeAmountDTO object2) {
							return object1.getMonthSerialNo().compareTo(
									object2.getMonthSerialNo());
						}
					});
		}
		return editFeeAmountDTOs;
	}

	@Override
	public void createEditFeeAmount(EditFeeAmountDTO editFeeAmountDTO) {
		if (editFeeAmountDTO.getMonthIds() == null
				|| editFeeAmountDTO.getMonthIds().isEmpty()) {
			EditFeeAmount editFeeAmount = dao.getEditFeeAmountDTOs(
					editFeeAmountDTO.getClassId(),
					editFeeAmountDTO.getSectionId(),
					editFeeAmountDTO.getSessionId(),
					editFeeAmountDTO.getFeesCategoriesId(),
					editFeeAmountDTO.getStudentAdmissionNo(),
					editFeeAmountDTO.getMonthId());
			if (editFeeAmount == null) {
				editFeeAmountDTO.setMonthId(editFeeAmountDTO.getMonthId());
				createEditFeeAmountPrivate(editFeeAmountDTO);
			} else {
				editFeeAmount.setDiscount(editFeeAmountDTO.getCurrentAmount()
						- editFeeAmountDTO.getDiscount());
				dao.update(editFeeAmount);
			}
		} else {
			for (Integer integer : editFeeAmountDTO.getMonthIds()) {
				EditFeeAmount editFeeAmount = dao.getEditFeeAmountDTOs(
						editFeeAmountDTO.getClassId(),
						editFeeAmountDTO.getSectionId(),
						editFeeAmountDTO.getSessionId(),
						editFeeAmountDTO.getFeesCategoriesId(),
						editFeeAmountDTO.getStudentAdmissionNo(), integer);
				if (editFeeAmount == null) {
					editFeeAmountDTO.setMonthId(integer);
					createEditFeeAmountPrivate(editFeeAmountDTO);
				} else {
					editFeeAmount.setDiscount(editFeeAmountDTO
							.getCurrentAmount()
							- editFeeAmountDTO.getDiscount());
					dao.update(editFeeAmount);
				}
			}
		}
	}

	private void createEditFeeAmountPrivate(EditFeeAmountDTO editFeeAmountDTO) {
		EditFeeAmount newEditFeeAmount = new EditFeeAmount();
		StudentClasses studentClasses = new StudentClasses();
		studentClasses.setClassId(editFeeAmountDTO.getClassId());
		Session session = new Session();
		session.setSessionId(editFeeAmountDTO.getSessionId());
		Section section = new Section();
		section.setSectionId(editFeeAmountDTO.getSectionId());
		FeesCategories feesCategories = new FeesCategories();
		feesCategories.setFeeCategoryId(editFeeAmountDTO.getFeesCategoriesId());
		Students students = new Students();
		students.setStudentAdmissionNo(editFeeAmountDTO.getStudentAdmissionNo());
		Month month = new Month();
		month.setMonthId(editFeeAmountDTO.getMonthId());
		newEditFeeAmount.setDiscount(editFeeAmountDTO.getCurrentAmount()
				- editFeeAmountDTO.getDiscount());
		newEditFeeAmount.setFeesCategories(feesCategories);
		newEditFeeAmount.setMonth(month);
		newEditFeeAmount.setSection(section);
		newEditFeeAmount.setStudents(students);
		newEditFeeAmount.setStudentClasses(studentClasses);
		newEditFeeAmount.setSession(session);
		dao.create(newEditFeeAmount);
	}

}
