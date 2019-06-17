package org.brijframework.college.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.brijframework.college.commom.convertor.StudentEntityToDTO;
import org.brijframework.college.commom.convertor.StudentFeeSubmissionDetailsEntityToDTO;
import org.brijframework.college.commom.convertor.StudentFineEntityToDTO;
import org.brijframework.college.dao.AdvanceSalaryDao;
import org.brijframework.college.dao.BookSellDao;
import org.brijframework.college.dao.BookSupplierDao;
import org.brijframework.college.dao.CityDao;
import org.brijframework.college.dao.ClassWiseFeeDao;
import org.brijframework.college.dao.DiscountDao;
import org.brijframework.college.dao.DressPaymentDao;
import org.brijframework.college.dao.EditFeeAmountDao;
import org.brijframework.college.dao.EmployeeSalaryDao;
import org.brijframework.college.dao.ExpenseDao;
import org.brijframework.college.dao.FeesCategoriesDao;
import org.brijframework.college.dao.FineDao;
import org.brijframework.college.dao.LastDateDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.dao.SectionWiseFeeDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.dao.StudentFineDao;
import org.brijframework.college.dao.StudentWiseFeeDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.UniformSupplierDao;
import org.brijframework.college.model.AdvanceSalary;
import org.brijframework.college.model.BookSell;
import org.brijframework.college.model.BookSupplier;
import org.brijframework.college.model.ClassWiseFee;
import org.brijframework.college.model.Discount;
import org.brijframework.college.model.DressPayment;
import org.brijframework.college.model.EditFeeAmount;
import org.brijframework.college.model.EmployeeSalary;
import org.brijframework.college.model.Expense;
import org.brijframework.college.model.FeesCategories;
import org.brijframework.college.model.Fine;
import org.brijframework.college.model.LastDate;
import org.brijframework.college.model.Month;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.SectionWiseFee;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.model.StudentFine;
import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.UniformSupplier;
import org.brijframework.college.models.dto.ClassWiseFeeDTO;
import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.FeecategoryAmountDTO;
import org.brijframework.college.models.dto.FeesCategoriesDTO;
import org.brijframework.college.models.dto.MonthDTO;
import org.brijframework.college.models.dto.SectionWiseFeeDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.StudentFeeSubmissionDetailsDTO;
import org.brijframework.college.models.dto.StudentWiseFeeDTO;
import org.brijframework.college.service.MonthService;
import org.brijframework.college.service.StudentFeeSubmissionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentFeeSubmissionDetailsServiceImpl
		extends
		CRUDServiceImpl<Integer, StudentFeeSubmissionDetails, StudentFeeSubmissionDetailsDao>
		implements StudentFeeSubmissionDetailsService {
	@Autowired
	public StudentFeeSubmissionDetailsServiceImpl(
			StudentFeeSubmissionDetailsDao dao) {
		super(dao);
	}

	@Autowired
	private StudentFineDao studentFineDao;
	@Autowired
	private StudentsAdmissionDao studentsAdmissionDao;
	@Autowired
	StudentsAdmissionDao admissionDao;
	@Autowired
	ExpenseDao expenseDao;
	@Autowired
	MonthService monthService;
	@Autowired
	LastDateDao lastDateDao;
	@Autowired
	ClassWiseFeeDao classWiseFeeDao;
	@Autowired
	SectionWiseFeeDao sectionWiseFeeDao;
	@Autowired
	StudentWiseFeeDao studentWiseFeeDao;
	@Autowired
	MonthDao monthDao;
	@Autowired
	SessionDao sessionDao;
	@Autowired
	DiscountDao discountDao;
	@Autowired
	CityDao cityDao;
	@Autowired
	StudentClassesDao classDao;
	@Autowired
	SectionDao sectionDao;
	@Autowired
	FeesCategoriesDao feescategoriesDao;
	@Autowired
	EditFeeAmountDao editFeeAmountDao;
	@Autowired
	private UniformSupplierDao uniformSupplierDao;
	@Autowired
	private BookSupplierDao bookSupplierDao;
	@Autowired
	private EmployeeSalaryDao salaryDao;
	@Autowired
	AdvanceSalaryDao advanceSalaryDao;
	@Autowired
	private FineDao fineDao;
	@Autowired
	DressPaymentDao dressPaymentDao;
	@Autowired
	BookSellDao bookSellDao;

	@Override
	public CommonDTO createStudentFeeSubmissionDetails(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO) {
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", studentFeeSubmissionDetailsDTO.getSessionId());
		Session session = new Session();
		Date date = new Date();
		int monthIds = 0;
		int lastmonthId = 0;
		CommonDTO commonDTO = new CommonDTO();
		int receiptNo = 0;
		int lfNo = 0;
		int totalfine = 0;
		double totalAmount = 0.0;
		String months = "";

		StudentFeeSubmissionDetails details = dao.getLfNo();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getRecieptNo() + 1;
		}

		session.setSessionId(studentFeeSubmissionDetailsDTO.getSessionId());
		StudentClasses studentClasses = new StudentClasses();
		studentClasses.setClassId(studentFeeSubmissionDetailsDTO.getClassId());
		Section section = new Section();
		section.setSectionId(studentFeeSubmissionDetailsDTO.getSectionId());
		Students students = admissionDao.get(studentFeeSubmissionDetailsDTO
				.getStudentAdmissionNo());
		lfNo = students.getLfNo();
		// Student detail
		StudentEntityToDTO studentEntityToDTO = StudentEntityToDTO
				.getInstance();
		commonDTO.setStudentDTO(studentEntityToDTO
				.getConvertEntityToDTO(students));
		List<FeesCategoriesDTO> feeslist = new ArrayList<FeesCategoriesDTO>();
		double feecategoryamount = 0;
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		List<StudentFeeSubmissionDetails> submissionDetails = new ArrayList<StudentFeeSubmissionDetails>();
		for (int monthId : studentFeeSubmissionDetailsDTO.getMonthsId()) {
			monthIds = monthId;
			StudentFeeSubmissionDetails studentFeeSubmissionDetails = new StudentFeeSubmissionDetails();
			if (studentFeeSubmissionDetailsDTO.getPaidBy().equals("cheque")) {
				studentFeeSubmissionDetails.setPaidBy("cheque");
				studentFeeSubmissionDetails
						.setChequeNo(studentFeeSubmissionDetailsDTO
								.getChequeNo());
				studentFeeSubmissionDetails.setFeePaidStatus("inprogress");
				studentFeeSubmissionDetails
						.setBankName(studentFeeSubmissionDetailsDTO
								.getBankName());
			} else {
				studentFeeSubmissionDetails
						.setPaidBy(studentFeeSubmissionDetailsDTO.getPaidBy());
				studentFeeSubmissionDetails.setFeePaidStatus("completed");
			}
			studentFeeSubmissionDetails.setFeePaidDate(new Date());
			double feePaidAmount = 0.0;
			List<FeecategoryAmountDTO> feecategoryAmountDTOs = new ArrayList<FeecategoryAmountDTO>();
			for (ClassWiseFee classWiseFees : classWiseFeeDao.getFeeAllotement(
					studentFeeSubmissionDetailsDTO.getSessionId(),
					studentFeeSubmissionDetailsDTO.getClassId(), monthId)) {
				if (classWiseFees != null) {
					FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
					feecategoryAmountDTO.setFeeCategoryName(classWiseFees
							.getFeesCategories().getFeeCategoryName());
					feecategoryAmountDTO.setFeeCatgoryAmount(classWiseFees
							.getFeeAmount());
					feecategoryAmountDTOs.add(feecategoryAmountDTO);

					EditFeeAmount editFeeAmount = editFeeAmountDao
							.getDiscountAmountBySessionMonthCategoryId(
									studentFeeSubmissionDetailsDTO
											.getSessionId(),
									monthId,
									classWiseFees.getFeesCategories()
											.getFeeCategoryId(),
									Integer.parseInt(studentFeeSubmissionDetailsDTO
											.getStudentAdmissionNo()));
					if (editFeeAmount != null) {
						double editedAmount = classWiseFees.getFeeAmount()
								- editFeeAmount.getDiscount();
						feePaidAmount += editedAmount;
					} else {
						feePaidAmount += classWiseFees.getFeeAmount();
					}

				}
			}
			for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
					.getFeeAllotement(
							studentFeeSubmissionDetailsDTO.getSessionId(),
							studentFeeSubmissionDetailsDTO.getClassId(),
							studentFeeSubmissionDetailsDTO.getSectionId(),
							monthId)) {
				if (sectionWiseFee != null) {
					FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
					feecategoryAmountDTO.setFeeCategoryName(sectionWiseFee
							.getFeesCategories().getFeeCategoryName());
					feecategoryAmountDTO.setFeeCatgoryAmount(sectionWiseFee
							.getFeeAmount());
					feecategoryAmountDTOs.add(feecategoryAmountDTO);
					EditFeeAmount editFeeAmount = editFeeAmountDao
							.getDiscountAmountBySessionMonthCategoryId(
									studentFeeSubmissionDetailsDTO
											.getSessionId(),
									monthId,
									sectionWiseFee.getFeesCategories()
											.getFeeCategoryId(),
									Integer.parseInt(studentFeeSubmissionDetailsDTO
											.getStudentAdmissionNo()));
					if (editFeeAmount != null) {
						double editedAmount = sectionWiseFee.getFeeAmount()
								- editFeeAmount.getDiscount();
						feePaidAmount += editedAmount;
					} else {
						feePaidAmount += sectionWiseFee.getFeeAmount();
					}
				}
			}
			for (StudentWiseFee studentWiseFee : studentWiseFeeDao
					.getFeeAllotement(studentFeeSubmissionDetailsDTO
							.getSessionId(), studentFeeSubmissionDetailsDTO
							.getClassId(), studentFeeSubmissionDetailsDTO
							.getSectionId(), monthId,
							studentFeeSubmissionDetailsDTO
									.getStudentAdmissionNo())) {
				if (studentWiseFee != null) {
					FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
					feecategoryAmountDTO.setFeeCategoryName(studentWiseFee
							.getFeesCategories().getFeeCategoryName());
					feecategoryAmountDTO.setFeeCatgoryAmount(studentWiseFee
							.getFeeAmount());
					feecategoryAmountDTOs.add(feecategoryAmountDTO);
					EditFeeAmount editFeeAmount = editFeeAmountDao
							.getDiscountAmountBySessionMonthCategoryId(
									studentFeeSubmissionDetailsDTO
											.getSessionId(),
									monthId,
									studentWiseFee.getFeesCategories()
											.getFeeCategoryId(),
									Integer.parseInt(studentFeeSubmissionDetailsDTO
											.getStudentAdmissionNo()));
					if (editFeeAmount != null) {
						double editedAmount = studentWiseFee.getFeeAmount()
								- editFeeAmount.getDiscount();
						feePaidAmount += editedAmount;
					} else {
						feePaidAmount += studentWiseFee.getFeeAmount();
					}
				}

			}

			Month month = monthDao.get(monthId);
			months += " " + month.getMonthName();
			studentFeeSubmissionDetails.setSession(session);
			studentFeeSubmissionDetails
					.setPaidAmountInWord(studentFeeSubmissionDetailsDTO
							.getPaidAmountInWord());

			studentFeeSubmissionDetails.setlFNo(lfNo);
			studentFeeSubmissionDetails.setRecieptNo(receiptNo);
			studentFeeSubmissionDetails.setStudentClasses(studentClasses);
			studentFeeSubmissionDetails.setSection(section);
			studentFeeSubmissionDetails.setStudents(students);
			studentFeeSubmissionDetails.setMonth(month);
			studentFeeSubmissionDetails.setPaidAmount(Float
					.parseFloat(studentFeeSubmissionDetailsDTO
							.getRecentPaidAmount()));
			dao.create(studentFeeSubmissionDetails);
			submissionDetails.add(studentFeeSubmissionDetails);

			// Discount Amount Save
			Discount discountdetails = new Discount();
			discountdetails.setActive(true);
			if (studentFeeSubmissionDetailsDTO.getDiscount().equals("")) {
				discountdetails.setDiscountAmount(Double.parseDouble("0"));
			} else {
				discountdetails.setDiscountAmount(Double
						.parseDouble(studentFeeSubmissionDetailsDTO
								.getDiscount()));
			}
			discountdetails
					.setStudentFeeSubmissionDetails(studentFeeSubmissionDetails);
			discountdetails.setStudents(students);
			discountdetails.setCratedAt(date);
			discountDao.create(discountdetails);

			// Get Last Date and calculate the fine
			LastDate lastDate = lastDateDao.getLastDatebyMonth(monthId,
					studentFeeSubmissionDetailsDTO.getSessionId());
			Date dueDate = lastDate.getLastdate();
			Date currentDate = new Date();
			int fine = 0;
			long diff = currentDate.getTime() - dueDate.getTime();
			long differDays = TimeUnit.DAYS
					.convert(diff, TimeUnit.MILLISECONDS);
			
			if (differDays > 0) {

				if (fineEntity == null) {
					fine = 0;
				} else {
					fine = (int) (differDays * fineEntity.getFineAmount());
					if (fine > fineEntity.getMaxFineAmount()) {
						fine = fineEntity.getMaxFineAmount();
					}
				}
			}
			totalAmount += fine + feePaidAmount;
			totalfine += fine;
			StudentFine studentFine = new StudentFine();
			studentFine.setCreatedAt(new Date());
			studentFine.setFineAmount(String
					.valueOf(studentFeeSubmissionDetailsDTO.getFineAmount()));
			studentFine
					.setStudentFeeSubmissionDetails(studentFeeSubmissionDetails);
			studentFine.setFineName("Fine");
			studentFine.setStudentClasses(studentClasses);
			studentFine.setSection(section);
			studentFine.setStudents(students);
			studentFine.setPaid(true);
			studentFineDao.create(studentFine);

			StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
					.getInstance();
			StudentFeeSubmissionDetailsDTO feeSubmissionDetailsDTO = studentFeeSubmissionDetailsEntityToDTO
					.convertEntityToDTO(studentFeeSubmissionDetails);
			StudentFineEntityToDTO studentFineEntityToDTO = StudentFineEntityToDTO
					.getInstance();
			feeSubmissionDetailsDTO.setStudentFineDTO(studentFineEntityToDTO
					.convertEntityToDTO(studentFine));
			feeSubmissionDetailsDTO
					.setFeecategoryAmountDTOs(feecategoryAmountDTOs);
			studentFeeSubmissionDetailsDTOs.add(feeSubmissionDetailsDTO);

		}
		// Get due Amount and alert student that amount for next month
		if (Double.parseDouble(studentFeeSubmissionDetailsDTO.getDueAmount()) > 0) {
			StudentWiseFee changeStudentWiseFee = new StudentWiseFee();
			changeStudentWiseFee.setActive(true);
			changeStudentWiseFee.setFeeAmount(Double
					.parseDouble(studentFeeSubmissionDetailsDTO
							.getNetPayableAmount())
					- Double.parseDouble(studentFeeSubmissionDetailsDTO
							.getRecentPaidAmount()));
			changeStudentWiseFee.setFeesCategories(feescategoriesDao.get((13)));
			changeStudentWiseFee.setMonth(monthDao.get(monthIds + 1));
			changeStudentWiseFee.setSession(sessionDao
					.get(studentFeeSubmissionDetailsDTO.getSessionId()));
			changeStudentWiseFee.setClasses(classDao
					.get(studentFeeSubmissionDetailsDTO.getClassId()));
			changeStudentWiseFee.setSection(sectionDao
					.get(studentFeeSubmissionDetailsDTO.getSectionId()));
			changeStudentWiseFee
					.setStudents(studentsAdmissionDao
							.get(studentFeeSubmissionDetailsDTO
									.getStudentAdmissionNo()));
			studentWiseFeeDao.create(changeStudentWiseFee);
		}

		for (FeesCategories feescatg : feescategoriesDao
				.findAllByIsActiveTrue(FeesCategories.class)) {
			String fineType = "Fine";
			FeesCategoriesDTO feescatgdto = new FeesCategoriesDTO();
			feecategoryamount = 0;
			if (feescatg.getFeeCategoryName().equals(fineType))
				feecategoryamount = totalfine;
			feescatgdto.setFeeCategoryName(feescatg.getFeeCategoryName());
			for (int monthId : studentFeeSubmissionDetailsDTO.getMonthsId()) {
				for (ClassWiseFee classWiseFees : classWiseFeeDao
						.getFeeAllotement(
								studentFeeSubmissionDetailsDTO.getSessionId(),
								studentFeeSubmissionDetailsDTO.getClassId(),
								monthId)) {
					if (classWiseFees != null) {
						if (feescatg.getFeeCategoryName() == classWiseFees
								.getFeesCategories().getFeeCategoryName()) {
							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											studentFeeSubmissionDetailsDTO
													.getSessionId(),
											monthId,
											classWiseFees.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(studentFeeSubmissionDetailsDTO
													.getStudentAdmissionNo()));
							if (editFeeAmount != null) {
								double editedAmount = classWiseFees
										.getFeeAmount()
										- editFeeAmount.getDiscount();
								feecategoryamount += editedAmount;
							} else {
								feecategoryamount += classWiseFees
										.getFeeAmount();
							}

						}
					}

				}
				for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
						.getFeeAllotement(
								studentFeeSubmissionDetailsDTO.getSessionId(),
								studentFeeSubmissionDetailsDTO.getClassId(),
								studentFeeSubmissionDetailsDTO.getSectionId(),
								monthId)) {
					if (sectionWiseFee != null) {
						if (feescatg.getFeeCategoryName() == sectionWiseFee
								.getFeesCategories().getFeeCategoryName()) {
							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											studentFeeSubmissionDetailsDTO
													.getSessionId(),
											monthId,
											sectionWiseFee.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(studentFeeSubmissionDetailsDTO
													.getStudentAdmissionNo()));
							if (editFeeAmount != null) {
								double editedAmount = sectionWiseFee
										.getFeeAmount()
										- editFeeAmount.getDiscount();
								feecategoryamount += editedAmount;
							} else {
								feecategoryamount += sectionWiseFee
										.getFeeAmount();
							}
						}
					}
				}
				for (StudentWiseFee studentWiseFee : studentWiseFeeDao
						.getFeeAllotement(studentFeeSubmissionDetailsDTO
								.getSessionId(), studentFeeSubmissionDetailsDTO
								.getClassId(), studentFeeSubmissionDetailsDTO
								.getSectionId(), monthId,
								studentFeeSubmissionDetailsDTO
										.getStudentAdmissionNo())) {
					if (studentWiseFee != null) {
						if (feescatg.getFeeCategoryName() == studentWiseFee
								.getFeesCategories().getFeeCategoryName()) {
							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											studentFeeSubmissionDetailsDTO
													.getSessionId(),
											monthId,
											studentWiseFee.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(studentFeeSubmissionDetailsDTO
													.getStudentAdmissionNo()));
							if (editFeeAmount != null) {
								double editedAmount = studentWiseFee
										.getFeeAmount()
										- editFeeAmount.getDiscount();
								feecategoryamount += editedAmount;
							} else {
								feecategoryamount += studentWiseFee
										.getFeeAmount();
							}
						}
					}
				}
				lastmonthId = monthId;

			}
			if (feecategoryamount == 0.0 || feecategoryamount == 0)
				feescatgdto.setAmounts("");
			else
				feescatgdto.setAmounts(String.valueOf(feecategoryamount));
			feeslist.add(feescatgdto);
		}
		lastmonthId = lastmonthId + 1;
		StudentWiseFee stufee = studentWiseFeeDao.getDues(
				studentFeeSubmissionDetailsDTO.getSessionId(),
				studentFeeSubmissionDetailsDTO.getClassId(),
				studentFeeSubmissionDetailsDTO.getSectionId(), lastmonthId,
				studentFeeSubmissionDetailsDTO.getStudentAdmissionNo());
		if (stufee != null) {
			commonDTO.setDue(String.valueOf(stufee.getFeeAmount()));
			commonDTO.setPaidAmount(String.valueOf(totalAmount
					- stufee.getFeeAmount()));
		} else {
			commonDTO.setDue("0");
			commonDTO.setPaidAmount(String.valueOf(totalAmount));
		}
		commonDTO.setFeecategoriesDTOs(feeslist);
		if (studentFeeSubmissionDetailsDTO.getDiscount() == "")
			commonDTO.setDiscount("0");
		else
			commonDTO.setDiscount(studentFeeSubmissionDetailsDTO.getDiscount());
		commonDTO.setMonths(months);
		commonDTO.setReceiptNo(receiptNo);
		commonDTO.setlFNo(lfNo);
		commonDTO.setTotalFee(Float.parseFloat(String.valueOf(totalAmount)));
		commonDTO.setCurrentPaidAmountDate(new SimpleDateFormat("dd-MM-yyyy")
				.format(new Date()));
		commonDTO
				.setStudentFeeSubmissionDetailsDTOs(studentFeeSubmissionDetailsDTOs);
		return commonDTO;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getStudentFeeSubmissionDetails(
			String studentAdmissionNo, int sectionId, int classId) {
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailsList = dao
				.getStudentFeeSubmissionDetails(studentAdmissionNo, sectionId,
						classId);
		StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
				.getInstance();
		for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailsList) {
			studentFeeSubmissionDetailsDTOs
					.add(studentFeeSubmissionDetailsEntityToDTO
							.convertEntityToDTO(studentFeeSubmissionDetails));
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public StudentFeeSubmissionDetailsDTO getLastRowStudentFeeSubmissionDetails() {
		StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
				.getInstance();
		return studentFeeSubmissionDetailsEntityToDTO.convertEntityToDTO(dao
				.getLastRowStudentFeeSubmissionDetails());
	}

	@Override
	public List<StudentDTO> getStudentFeeSubmissionDetails(int classId,
			int sectionId, String from, String to, int sessionId)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = dateFormat.parse(from);
		Date toDate = dateFormat.parse(to);
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for (Students student : studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId, sessionId)) {

			StudentDTO studentDTO = new StudentDTO();
			List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTO = new ArrayList<StudentFeeSubmissionDetailsDTO>();
			/*
			 * for (StudentFeeSubmissionDetails feeSubmissionDetail : dao
			 * .getStudentFeeSubmissionDetail( student.getStudentAdmissionNo(),
			 * fromDate, toDate)) { StudentFeeSubmissionDetailsDTO detailsDTO =
			 * new StudentFeeSubmissionDetailsDTO();
			 * detailsDTO.setPaidAmount(String.valueOf(feeSubmissionDetail
			 * .getPaidAmount()));
			 * detailsDTO.setFeePaidStatus(feeSubmissionDetail
			 * .getFeePaidStatus());
			 * detailsDTO.setFeePaidDate(dateFormat.format(feeSubmissionDetail
			 * .getFeePaidDate()));
			 * detailsDTO.setPaidBy(feeSubmissionDetail.getPaidBy());
			 * detailsDTO.setStudentFeeSubmissionDetailsId(feeSubmissionDetail
			 * .getStudentFeeSubmissionDetailsId());
			 * detailsDTO.setMonth(feeSubmissionDetail.getMonth()
			 * .getMonthName()); feeSubmissionDetailsDTO.add(detailsDTO);
			 * 
			 * }
			 */
			Set<Integer> integers = new HashSet<Integer>();
			for (StudentFeeSubmissionDetails feeSubmissionDetail : dao
					.getStudentFeeSubmissionDetail(
							student.getStudentAdmissionNo(), fromDate, toDate)) {
				integers.add(feeSubmissionDetail.getRecieptNo());

			}
			List<Integer> integersList = new ArrayList<Integer>();
			for (Integer integer : integers) {
				integersList.add(integer);
			}
			for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : getData(integersList)) {
				feeSubmissionDetailsDTO.add(studentFeeSubmissionDetailsDTO);
			}

			studentDTO.setFullName(student.getFirstName() + " "
					+ student.getMiddleName() + " " + student.getLastName());
			studentDTO.setClassName(student.getClasses().getClassName());
			studentDTO.setSectionName(student.getSection().getSectionName());
			studentDTO.setFeeSubmissionDetailsDTOs(feeSubmissionDetailsDTO);
			studentDTO.setMobile(student.getMobile());
			studentDTO.setAddressLine1(student.getAddressLine1());
			studentDTO.setCity(cityDao.get(student.getCity().getCityId())
					.getCityName());
			studentDTOs.add(studentDTO);
		}
		return studentDTOs;
	}

	@Override
	public void updateStatusById(String paidStatus, int receiptno) {
		String studentId = "";
		int monthId = 0;
		int sessionId = 0;
		List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailsList = dao
				.getallfeesubmissiondetailsByLfNo(receiptno);
		for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailsList) {
			studentFeeSubmissionDetails.setFeePaidStatus(paidStatus);
			studentId = studentFeeSubmissionDetails.getStudents()
					.getStudentAdmissionNo();
			monthId = studentFeeSubmissionDetails.getMonth().getMonthId();
			sessionId = studentFeeSubmissionDetails.getSession().getSessionId();
			dao.update(studentFeeSubmissionDetails);
		}
		if (monthId == 12) {
			monthId = 0;
		}
		if(paidStatus.equals("Cancel"))
		{
		StudentWiseFee stufee = studentWiseFeeDao.getduesbyMonth(studentId,
				monthId + 1, sessionId);
		stufee.setActive(false);
		}
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsBySlipNo(
			int slipNo) {
		List<Integer> integers = dao
				.getallfeesubmissiondetailsLfnoBySlipNo(slipNo);
		return getData(integers);
	}

	private List<StudentFeeSubmissionDetailsDTO> getData(List<Integer> integers) {
		StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
				.getInstance();
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : integers) {
			Float paidAmount = 0.0f;
			String month = "";
			// Double discountAmount = 0.0;
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				paidAmount += studentFeeSubmissionDetails.getPaidAmount();
				/*
				 * paidAmount += Float.parseFloat(studentFeeSubmissionDetails
				 * .getStudentFine().getFineAmount()); Discount dis =
				 * discountDao
				 * .getDiscountbyFeeSubId(studentFeeSubmissionDetails
				 * .getStudentFeeSubmissionDetailsId()); discountAmount =
				 * dis.getDiscountAmount();
				 */

				StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = studentFeeSubmissionDetailsEntityToDTO
						.convertEntityToDTO(studentFeeSubmissionDetails);
				for (Integer monthId : dao.getMonthIdsBySlipNo(integer)) {
					month += " " + monthDao.get(monthId).getMonthName();
				}
				studentFeeSubmissionDetailsDTO.setMonth(month);
				studentFeeSubmissionDetailsDTO.setPaidAmount(String
						.valueOf(paidAmount));
				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsDTO);
			}
			/* paidAmount = (float) (paidAmount - discountAmount); */
			/*
			 * StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO =
			 * remaoveDuplicate( studentFeeSubmissionDetailsDTOsNew).get(0);
			 */
			/*
			 * studentFeeSubmissionDetailsDTO.setMonth(month);
			 * studentFeeSubmissionDetailsDTO.setPaidAmount(String
			 * .valueOf(paidAmount));
			 */

			// studentFeeSubmissionDetailsDTOs.add(studentFeeSubmissionDetailsDTO);
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	private List<StudentFeeSubmissionDetailsDTO> remaoveDuplicate(
			List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs) {
		Set<Integer> attributes = new HashSet<Integer>();
		List<StudentFeeSubmissionDetailsDTO> duplicates = new ArrayList<StudentFeeSubmissionDetailsDTO>();

		for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : studentFeeSubmissionDetailsDTOs) {
			if (attributes.contains(studentFeeSubmissionDetailsDTO
					.getRecieptNo())) {
				duplicates.add(studentFeeSubmissionDetailsDTO);
			}
			attributes.add(studentFeeSubmissionDetailsDTO.getRecieptNo());
		}
		studentFeeSubmissionDetailsDTOs.removeAll(duplicates);
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsByDate(
			String fromDate, String toDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> integers = new ArrayList<Integer>();
		try {
			integers = dao.getallfeesubmissiondetailsLfnoByDate(
					dateFormat.parse(fromDate), dateFormat.parse(toDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return getData(integers);
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsByStudentName(
			int classId, int sessionId, String studentName) {
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		List<Students> studentList = studentsAdmissionDao
				.getStudentByMatchFromName(classId, sessionId, studentName);
		for (Students students : studentList) {
			Set<Integer> integers = new HashSet<Integer>();
			List<Integer> integersList = new ArrayList<Integer>();
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : students
					.getStudentFeeSubmissionDetailsList()) {
				integers.add(studentFeeSubmissionDetails.getRecieptNo());
			}
			for (Integer integer : integers) {
				integersList.add(integer);
			}
			for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : getData(integersList)) {
				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsDTO);
			}
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsAdmissionNo(
			String admissionNo) {
		List<Integer> all = new ArrayList<Integer>();
		List<Students> student = studentsAdmissionDao
				.getStudentbyAdmNo(admissionNo);
		for (Students studentslist : student) {
			List<Integer> integers = dao
					.getallsubmissiondetailsLfnoByAdmissionNo(studentslist
							.getStudentAdmissionNo());
			for (Integer intg : integers) {
				all.add(intg);
			}
		}
		return getData(all);

	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetails(
			String admissionNo, int sessionId) {

		Float total = 0.0f;
		String month = "";
		List<StudentFeeSubmissionDetails> list = dao.getallsubmissiondetails(
				admissionNo, sessionId);

		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
				.getInstance();
		for (StudentFeeSubmissionDetails fees : list) {
			total = 0.0f;
			month = "";
			StudentFeeSubmissionDetailsDTO dto = studentFeeSubmissionDetailsEntityToDTO
					.convertEntityToDTO(fees);
			int r = fees.getRecieptNo();

			for (StudentFeeSubmissionDetails cfees : list) {

				if (cfees.getRecieptNo() == r) {
					month += " " + cfees.getMonth().getMonthName();
					total = cfees.getPaidAmount();

				}
			}
			dto.setMonth(month);
			dto.setPaidAmount(String.valueOf(total));
			studentFeeSubmissionDetailsDTOs.add(dto);
		}
		studentFeeSubmissionDetailsDTOs = remaoveDuplicate(studentFeeSubmissionDetailsDTOs);
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getOverallCollectionsDetails() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> recieptNos = dao.getallDistinctSlipNo();
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : recieptNos) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
				detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
						.getStudentAdmissionNo());
				detailsDTO.setStudentName(feeDetails.getStudents()
						.getFirstName()
						+ " "
						+ feeDetails.getStudents().getMiddleName()
						+ " "
						+ feeDetails.getStudents().getLastName());
				detailsDTO.setPaidAmount(String.valueOf(feeDetails
						.getPaidAmount()));
				detailsDTO.setFineAmount(feeDetails.getStudentFine()
						.getFineAmount());
				detailsDTO.setDiscountAmount(String.valueOf(feeDetails
						.getDiscount().getDiscountAmount()));
				detailsDTO.setlFno(feeDetails.getlFNo());
				detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
				detailsDTO
						.setFeePaidDate(df.format(feeDetails.getFeePaidDate()));
				feeSubmissionDetailsDTOs.add(detailsDTO);
			}
		}
		return feeSubmissionDetailsDTOs;
	}

	public List<StudentFeeSubmissionDetailsDTO> getOverallCollectionsDetailsByPageNo(
			int pageno) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> integers = dao.getallDistinctSlipNo(pageno);
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : integers) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
				detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
						.getStudentAdmissionNo());
				detailsDTO.setStudentName(feeDetails.getStudents()
						.getFirstName()
						+ " "
						+ feeDetails.getStudents().getMiddleName()
						+ " "
						+ feeDetails.getStudents().getLastName());
				detailsDTO.setPaidAmount(String.valueOf(feeDetails
						.getPaidAmount()));
				detailsDTO.setFineAmount(feeDetails.getStudentFine()
						.getFineAmount());
				detailsDTO.setDiscountAmount(String.valueOf(feeDetails
						.getDiscount().getDiscountAmount()));
				detailsDTO.setlFno(feeDetails.getlFNo());
				detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
				detailsDTO
						.setFeePaidDate(df.format(feeDetails.getFeePaidDate()));
				detailsDTO
						.setStudentId(feeDetails.getStudents().getStudentId());
				feeSubmissionDetailsDTOs.add(detailsDTO);
			}
		}
		return feeSubmissionDetailsDTOs;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getTodaysCollectionsDetails()
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> integers = dao.getallDistinctSlipNo(df.parse(df
				.format(new Date())));
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : integers) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
				detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
						.getStudentAdmissionNo());
				detailsDTO.setStudentName(feeDetails.getStudents()
						.getFirstName()
						+ " "
						+ feeDetails.getStudents().getMiddleName()
						+ " "
						+ feeDetails.getStudents().getLastName());
				detailsDTO.setPaidAmount(String.valueOf(feeDetails
						.getPaidAmount()));
				detailsDTO.setFineAmount(feeDetails.getStudentFine()
						.getFineAmount());
				detailsDTO.setDiscountAmount(String.valueOf(feeDetails
						.getDiscount().getDiscountAmount()));
				detailsDTO.setlFno(feeDetails.getlFNo());
				detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
				detailsDTO
						.setFeePaidDate(df.format(feeDetails.getFeePaidDate()));
				detailsDTO
						.setStudentId(feeDetails.getStudents().getStudentId());
				feeSubmissionDetailsDTOs.add(detailsDTO);
			}
		}
		return feeSubmissionDetailsDTOs;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getDateWiseCollectionsDetailsByPageNo(
			String fromDate, String toDate, int pageNo) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<StudentFeeSubmissionDetails> feeSubmissionDetails = dao
				.getDateWiseCollectionsDetailsByPageNo(df.parse(fromDate),
						df.parse(toDate), pageNo);
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (StudentFeeSubmissionDetails feeDetails : feeSubmissionDetails) {
			StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
			detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
					.getStudentAdmissionNo());
			detailsDTO.setStudentName(feeDetails.getStudents().getFirstName()
					+ " " + feeDetails.getStudents().getMiddleName() + " "
					+ feeDetails.getStudents().getLastName());
			detailsDTO
					.setPaidAmount(String.valueOf(feeDetails.getPaidAmount()));
			detailsDTO.setFineAmount(feeDetails.getStudentFine()
					.getFineAmount());
			detailsDTO.setDiscountAmount(String.valueOf(feeDetails
					.getDiscount().getDiscountAmount()));
			detailsDTO.setlFno(feeDetails.getlFNo());
			detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
			detailsDTO.setFeePaidDate(df.format(feeDetails.getFeePaidDate()));
			feeSubmissionDetailsDTOs.add(detailsDTO);
		}
		return feeSubmissionDetailsDTOs;
	}

	public List<StudentFeeSubmissionDetailsDTO> getDateWiseCollectionsDetails(
			String fromDate, String toDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> integers = dao.getallDistinctSlipNo(df.parse(fromDate),
				df.parse(toDate));
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : integers) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
				detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
						.getStudentAdmissionNo());
				detailsDTO.setStudentName(feeDetails.getStudents()
						.getFirstName()
						+ " "
						+ feeDetails.getStudents().getMiddleName()
						+ " "
						+ feeDetails.getStudents().getLastName());
				detailsDTO.setPaidAmount(String.valueOf(feeDetails
						.getPaidAmount()));
				detailsDTO.setFineAmount(feeDetails.getStudentFine()
						.getFineAmount());
				detailsDTO.setDiscountAmount(String.valueOf(feeDetails
						.getDiscount().getDiscountAmount()));
				detailsDTO.setlFno(feeDetails.getlFNo());
				detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
				detailsDTO
						.setFeePaidDate(df.format(feeDetails.getFeePaidDate()));
				detailsDTO
						.setStudentId(feeDetails.getStudents().getStudentId());
				feeSubmissionDetailsDTOs.add(detailsDTO);
			}
		}
		return feeSubmissionDetailsDTOs;
	}

	@Override
	public CommonDTO getDataForFeeReceiptPDFGeneration(
			String studentAdmissionNo, int slipNo) {
		CommonDTO commonDTO = new CommonDTO();
		String months = "";
		int lfNo = 0;
		String feePaidDate = "";
		int totalfine = 0;
		int lastmonthId = 0;
		Float totalfee = 0f;
		int commonRecieptNo = 0;
		Students students = admissionDao.get(studentAdmissionNo);
		StudentEntityToDTO studentEntityToDTO = StudentEntityToDTO
				.getInstance();
		commonDTO.setStudentDTO(studentEntityToDTO
				.getConvertEntityToDTO(students));

		List<FeesCategoriesDTO> feeslist = new ArrayList<FeesCategoriesDTO>();
		double feecategoryamount = 0;
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();

		List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailsList = dao
				.getSingleFeeSubmissionBySlipNo(slipNo);
		for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailsList) {
			if (studentFeeSubmissionDetails.getCommonRecieptNo() == null) {
			} else {
				commonRecieptNo = studentFeeSubmissionDetails
						.getCommonRecieptNo();
			}
		}
		if (commonRecieptNo == 0) {

			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailsList) {

				StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
						.getInstance();
				StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = studentFeeSubmissionDetailsEntityToDTO
						.convertEntityToDTO(studentFeeSubmissionDetails);
				for (Integer integer : dao.getMonthIdsBySlipNo(slipNo)) {

					months += " " + monthDao.get(integer).getMonthName();

				}
				Discount discount = discountDao
						.getDiscountbyFeeSubId(studentFeeSubmissionDetails
								.getStudentFeeSubmissionDetailsId());

				feePaidDate = studentFeeSubmissionDetailsDTO.getFeePaidDate();
				lfNo = studentFeeSubmissionDetailsDTO.getlFno();

				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsDTO);
				commonDTO.setDiscount(String.valueOf(discount
						.getDiscountAmount()));
			
				commonDTO.setPaidAmount(String
						.valueOf(studentFeeSubmissionDetails.getPaidAmount()));
				totalfee = studentFeeSubmissionDetails.getPaidAmount();
			}
			List<StudentFeeSubmissionDetails> studentFeeSubmissionList = dao
					.getallfeesubmissiondetailsBySlipNo(slipNo);
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionList) {
				StudentFine studentFine = studentFineDao
						.getStudentFine(studentFeeSubmissionDetails
								.getStudentFeeSubmissionDetailsId());
				totalfine += Integer.parseInt(studentFine.getFineAmount());

			}
			for (FeesCategories feescatg : feescategoriesDao
					.findAllByIsActiveTrue(FeesCategories.class)) {
				String fine = "Fine";
				FeesCategoriesDTO feescatgdto = new FeesCategoriesDTO();
				feecategoryamount = 0;
				if (feescatg.getFeeCategoryName().equals(fine))
					feecategoryamount = totalfine;
				feescatgdto.setFeeCategoryName(feescatg.getFeeCategoryName());
				for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailsList) {
					int sectionId = studentFeeSubmissionDetails.getSection()
							.getSectionId();
					int classId = studentFeeSubmissionDetails
							.getStudentClasses().getClassId();
					int sessionId = studentFeeSubmissionDetails.getSession()
							.getSessionId();
					for (Integer integer : dao.getMonthIdsBySlipNo(slipNo)) {
						for (ClassWiseFee classWiseFees : classWiseFeeDao
								.getFeeAllotement(sessionId, classId, integer)) {
							if (classWiseFees != null) {
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												sessionId,
												integer,
												classWiseFees
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(studentAdmissionNo));
								if (feescatg.getFeeCategoryName() == classWiseFees
										.getFeesCategories()
										.getFeeCategoryName()) {
									if (editFeeAmount == null) {
										feecategoryamount += classWiseFees
												.getFeeAmount();
									} else {
										feecategoryamount += (classWiseFees
												.getFeeAmount() - editFeeAmount
												.getDiscount());
									}
								}
							}

						}
						for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
								.getFeeAllotement(sessionId, classId,
										sectionId, integer)) {
							if (sectionWiseFee != null) {
								if (feescatg.getFeeCategoryName() == sectionWiseFee
										.getFeesCategories()
										.getFeeCategoryName()) {
									EditFeeAmount editFeeAmount = editFeeAmountDao
											.getDiscountAmountBySessionMonthCategoryId(
													sessionId,
													integer,
													sectionWiseFee
															.getFeesCategories()
															.getFeeCategoryId(),
													Integer.parseInt(studentAdmissionNo));
									if (editFeeAmount == null) {
										feecategoryamount += sectionWiseFee
												.getFeeAmount();
									} else {
										feecategoryamount += (sectionWiseFee
												.getFeeAmount() - editFeeAmount
												.getDiscount());
									}
								}
							}
						}
						for (StudentWiseFee studentWiseFee : studentWiseFeeDao
								.getFeeAllotement(sessionId, classId,
										sectionId, integer, studentAdmissionNo)) {
							if (studentWiseFee != null) {
								if (feescatg.getFeeCategoryName() == studentWiseFee
										.getFeesCategories()
										.getFeeCategoryName()) {
									EditFeeAmount editFeeAmount = editFeeAmountDao
											.getDiscountAmountBySessionMonthCategoryId(
													sessionId,
													integer,
													studentWiseFee
															.getFeesCategories()
															.getFeeCategoryId(),
													Integer.parseInt(studentAdmissionNo));
									if (editFeeAmount == null) {
										feecategoryamount += studentWiseFee
												.getFeeAmount();
									} else {
										feecategoryamount += (studentWiseFee
												.getFeeAmount() - editFeeAmount
												.getDiscount());
									}
								}
							}
						}
						lastmonthId = integer;
					}
				}
				if (feecategoryamount == 0.0 || feecategoryamount == 0)
					feescatgdto.setAmounts("");
				else
					feescatgdto.setAmounts(String.valueOf(feecategoryamount));
				feeslist.add(feescatgdto);

			}
			lastmonthId = lastmonthId + 1;
			StudentWiseFee stufee = studentWiseFeeDao.getDues(students
					.getSession().getSessionId(), students.getClasses()
					.getClassId(), students.getSection().getSectionId(),
					lastmonthId, students.getStudentAdmissionNo());
			if (stufee != null) {
				commonDTO.setDue(String.valueOf(stufee.getFeeAmount()));
				totalfee = (float) (totalfee + stufee.getFeeAmount());

			} else {
				commonDTO.setDue("0");

			}
			commonDTO.setFeecategoriesDTOs(feeslist);
			commonDTO.setMonths(months);
			commonDTO.setReceiptNo(slipNo);
			commonDTO.setlFNo(lfNo);
			commonDTO.setTotalFee(Float.parseFloat(String.valueOf(totalfee)));
			commonDTO.setCurrentPaidAmountDate(feePaidDate);
			commonDTO
					.setStudentFeeSubmissionDetailsDTOs(studentFeeSubmissionDetailsDTOs);
		} else {
			commonDTO = getDataForCommonReciept(commonRecieptNo);
		}
		return commonDTO;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getStudentFeeSubmissionDetailsByFromToDate(
			String studentAdmissionNo, int sectionId, int classId,
			String fromDate, String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date getFromDate = new Date();
		Date getToDate = new Date();
		try {
			getFromDate = sdf.parse(fromDate);
			getToDate = sdf.parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<StudentFeeSubmissionDetails> studentFeeSubmissionDetails = dao
				.getStudentFeeSubmissionDetail(studentAdmissionNo, sectionId,
						classId, getFromDate, getToDate);
		StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
				.getInstance();
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (StudentFeeSubmissionDetails feeSubmissionDetails : studentFeeSubmissionDetails) {
			studentFeeSubmissionDetailsDTOs
					.add(studentFeeSubmissionDetailsEntityToDTO
							.convertEntityToDTO(feeSubmissionDetails));
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public Object getYearlyCollectionsDetails(String year) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Integer> integers = dao.getallDistinctSlipNo();
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : integers) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				Date paidDate = feeDetails.getFeePaidDate();
				String getYear[] = (df.format(paidDate)).split("-");
				if (getYear[0].equalsIgnoreCase(year)) {
					StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
					detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
							.getStudentAdmissionNo());
					detailsDTO.setStudentName(feeDetails.getStudents()
							.getFirstName()
							+ " "
							+ feeDetails.getStudents().getMiddleName()
							+ " "
							+ feeDetails.getStudents().getLastName());
					detailsDTO.setPaidAmount(String.valueOf(feeDetails
							.getPaidAmount()));
					detailsDTO.setlFno(feeDetails.getlFNo());
					detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
					detailsDTO.setFeePaidDate(df.format(feeDetails
							.getFeePaidDate()));
					feeSubmissionDetailsDTOs.add(detailsDTO);
				}
			}
		}
		return feeSubmissionDetailsDTOs;
	}

	@Override
	public Map<String, Double> getAvailableMonthlyCollectionsDetails(int monthId)
			throws ParseException {

		if (monthId > 9)
			monthId = monthId - 9;
		else
			monthId = monthId + 3;

		Double totalExpectedAmount;
		Double totalCollectionAmount = 0.0;
		Double extraCollectionAmount = 0.0;
		Double totalDiscountAmount = 0.0;
		Double totalExpensesAmount = 0.0;
		Double perClassExpectedAmount = 0.0;
		Double perStudentFeeAmount = 0.0;
		Double perSectionExpectedAmount = 0.0;
		int totalStudent = 0;
		Session session = sessionDao.findCurrentSession();
		String currentSession = session.getSessionDuration();
		String year[] = currentSession.split("-");
		int currentYear;
		if (monthId < 5) {
			currentYear = Integer.parseInt(year[1]);
		} else {
			currentYear = Integer.parseInt(year[0]);
		}
		GregorianCalendar gc = new GregorianCalendar(currentYear, monthId - 1,
				1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(currentYear, monthId - 1, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		Date toDate = df.parse(df.format(calendar.getTime()));

		for (StudentWiseFee feeAllotement : studentWiseFeeDao
				.getStudentByAdmissionNoFromDateToDate(monthId)) {
			if (feeAllotement.getFeesCategories().getFeeCategoryId() != 13) {
				perStudentFeeAmount += feeAllotement.getFeeAmount();
			}
		}
		int totalDiscount = 0;
		for (EditFeeAmount editFeeAmount : classWiseFeeDao
				.getDiscountAfterEdit(monthId)) {
			totalDiscount += editFeeAmount.getDiscount();
		}
		for (ClassWiseFee classWiseFee : classWiseFeeDao
				.getClassWiseFeeAllotementByMonth(monthId)) {

			int noOfStudentsPerClass = 0;
			for (Students students : admissionDao
					.getStudentByClassId(classWiseFee.getClasses().getClassId())) {
				noOfStudentsPerClass++;
			}

			totalStudent += noOfStudentsPerClass;
			Double feeAmount = classWiseFee.getFeeAmount();
			perClassExpectedAmount += feeAmount * noOfStudentsPerClass;
		}

		for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
				.getSectionWiseFeeAllotmentByMonth(monthId)) {
			int noOfStudentsPerClass = 0;
			for (Students students : admissionDao.getStudentsbyClassSectionId(
					sectionWiseFee.getClasses().getClassId(), sectionWiseFee
							.getSection().getSectionId(), sectionWiseFee
							.getSession().getSessionId())) {
				noOfStudentsPerClass++;
			}
			Double feeAmount = sectionWiseFee.getFeeAmount();
			perSectionExpectedAmount += feeAmount * noOfStudentsPerClass;

		}

		totalExpectedAmount = perClassExpectedAmount + perStudentFeeAmount
				+ perSectionExpectedAmount - totalDiscount;
		for (Integer integer : dao.getallDistinctSlipNoByMonth(frmDate, toDate)) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				totalCollectionAmount += feeDetails.getPaidAmount();
				totalDiscountAmount += feeDetails.getDiscount()
						.getDiscountAmount();
				extraCollectionAmount += Float.parseFloat(feeDetails
						.getStudentFine().getFineAmount());
			}
		}
		for(DressPayment dress:dressPaymentDao.getSoldDetails(frmDate, toDate))
		{
			totalCollectionAmount += dress.getPaidAmount();
		}
		for(BookSell book:bookSellDao.getSoldDetails(frmDate, toDate))
		{
			totalCollectionAmount += book.getPaidAmount();
		}
		for (Expense expense : expenseDao.finddatewiseexpense(frmDate, toDate)) {
			totalExpensesAmount += expense.getExpenseAmount();
		}
		List<UniformSupplier> uniformSuppliers = uniformSupplierDao
				.findDatewiseExpense(frmDate, toDate);
		for (UniformSupplier uniformSupplier : uniformSuppliers) {
			totalExpensesAmount += uniformSupplier.getPaidAmount();
		}
		List<BookSupplier> bookSuppliers = bookSupplierDao.findDatewiseExpense(
				frmDate, toDate);
		for (BookSupplier bookSupplier : bookSuppliers) {
			totalExpensesAmount += bookSupplier.getPaidAmount();
		}
		List<Integer> integers = salaryDao.getallDistinctSlipNoByDate(frmDate,
				toDate);
		for (Integer integer : integers) {
			EmployeeSalary salary = salaryDao.findSingleSlipData(integer);
			totalExpensesAmount += salary.getPaidAmount();
		}
		List<AdvanceSalary> advanceSalary = advanceSalaryDao.getTodaysAdvance(
				frmDate, toDate);
		for (AdvanceSalary advances : advanceSalary) {
			totalExpensesAmount += advances.getPaidAmount();
		}
		Double restAmount = (((totalExpectedAmount - totalCollectionAmount) - totalDiscountAmount) + extraCollectionAmount);
		Map<String, Double> collectionMap = new HashMap<String, Double>();
		collectionMap.put("totalExpectedAmount", totalExpectedAmount);
		collectionMap.put("totalCollectionAmount", totalCollectionAmount);
		collectionMap.put("totalDiscountAmount", totalDiscountAmount);
		collectionMap.put("extraCollectionAmount", extraCollectionAmount);
		collectionMap.put("totalExpensesAmount", totalExpensesAmount);
		collectionMap.put("restAmount", restAmount);
		collectionMap.put("availabelAmount", (totalCollectionAmount)
				- totalExpensesAmount);

		return collectionMap;
	}

	@Override
	public Map<String, Double> getAvailableyearlyCollectionsDetails(
			int sessionId) throws ParseException {
		Double totalExpectedAmount;
		Double totalCollectionAmount = 0.0;
		Double extraCollectionAmount = 0.0;
		Double totalDiscountAmount = 0.0;
		Double totalExpensesAmount = 0.0;
		Double perClassExpectedAmount = 0.0;
		Double perStudentFeeAmount = 0.0;
		Double perSectionExpectedAmount = 0.0;
		int totalStudent = 0;
		Session session = sessionDao.get(sessionId);
		String sessionDuration = session.getSessionDuration();
		String[] years = sessionDuration.split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(years[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(years[1]), 4 - 1, 31);
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		Date toDate = df.parse(df.format(calendar.getTime()));

		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

		for (StudentWiseFee feeAllotement : studentWiseFeeDao
				.getStudentFeeAllotementBySessionId(sessionId)) {
			if (feeAllotement.getFeesCategories().getFeeCategoryId() != 13) {
				if (feeAllotement.getMonth().getMonthId() == 13) {
					Double completeYearFee = feeAllotement.getFeeAmount() * 12;
					perStudentFeeAmount += completeYearFee;
				} else {
					perStudentFeeAmount += feeAllotement.getFeeAmount();
				}
				
			}
		}
		int totalDiscount = 0;
		for (EditFeeAmount editFeeAmount : classWiseFeeDao
				.getDiscountAfterEditBySessionId(sessionId)) {
			totalDiscount += editFeeAmount.getDiscount();
		}

		for (ClassWiseFee classWiseFee : classWiseFeeDao
				.getClassWiseFeeAllotementBySessionId(sessionId)) {
			int noOfStudentsPerClass = 0;
			for (Students students : admissionDao
					.getStudentByClassId(classWiseFee.getClasses().getClassId())) {
				noOfStudentsPerClass++;
			}

			totalStudent += noOfStudentsPerClass;
			if (classWiseFee.getMonth().getMonthId() == 13) {
				Double completeYearFee = classWiseFee.getFeeAmount() * 12;
				perClassExpectedAmount += completeYearFee
						* noOfStudentsPerClass;
} else {
				Double feeAmount = classWiseFee.getFeeAmount();
				perClassExpectedAmount += feeAmount * noOfStudentsPerClass;
			}
		}

		for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
				.getSectionWiseFeeAllotmentBysessionId(sessionId)) {
			int noOfStudentsPerSection = 0;
			for (Students students : admissionDao.getStudentsbyClassSectionId(
					sectionWiseFee.getClasses().getClassId(), sectionWiseFee
							.getSection().getSectionId(), sectionWiseFee
							.getSession().getSessionId())) {
				noOfStudentsPerSection++;
			}
			if (sectionWiseFee.getMonth().getMonthId() == 13) {
				Double completeYearFee = sectionWiseFee.getFeeAmount() * 12;
				perClassExpectedAmount += completeYearFee
						* noOfStudentsPerSection;
			} else {
				Double feeAmount = sectionWiseFee.getFeeAmount();
				perClassExpectedAmount += feeAmount * noOfStudentsPerSection;
			}
			Double feeAmount = sectionWiseFee.getFeeAmount();
			perSectionExpectedAmount += feeAmount * noOfStudentsPerSection;

		}

		totalExpectedAmount = perClassExpectedAmount + perStudentFeeAmount
				+ perSectionExpectedAmount - totalDiscount;
		for (Integer integer : dao.getallDistinctSlipNoBySessionId(sessionId)) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				totalCollectionAmount += feeDetails.getPaidAmount();
				totalDiscountAmount += feeDetails.getDiscount()
						.getDiscountAmount();
				extraCollectionAmount += Float.parseFloat(feeDetails
						.getStudentFine().getFineAmount());
			}
		}
		for(DressPayment dress:dressPaymentDao.getSoldDetails(frmDate, toDate))
		{
			totalCollectionAmount += dress.getPaidAmount();
		}
		for(BookSell book:bookSellDao.getSoldDetails(frmDate, toDate))
		{
			totalCollectionAmount += book.getPaidAmount();
		}
		for (Expense expense : expenseDao.finddatewiseexpense(frmDate, toDate)) {
			totalExpensesAmount += expense.getExpenseAmount();
		}
		List<UniformSupplier> uniformSuppliers = uniformSupplierDao
				.findDatewiseExpense(frmDate, toDate);
		for (UniformSupplier uniformSupplier : uniformSuppliers) {
			totalExpensesAmount += uniformSupplier.getPaidAmount();
		}
		List<BookSupplier> bookSuppliers = bookSupplierDao.findDatewiseExpense(
				frmDate, toDate);
		for (BookSupplier bookSupplier : bookSuppliers) {
			totalExpensesAmount += bookSupplier.getPaidAmount();
		}
		List<Integer> integers = salaryDao.getallDistinctSlipNoByDate(frmDate,
				toDate);
		for (Integer integer : integers) {
			EmployeeSalary salary = salaryDao.findSingleSlipData(integer);
			totalExpensesAmount += salary.getPaidAmount();
		}
		List<AdvanceSalary> advanceSalary = advanceSalaryDao.getTodaysAdvance(
				frmDate, toDate);
		for (AdvanceSalary advances : advanceSalary) {
			totalExpensesAmount += advances.getPaidAmount();
		}
		Double restAmount = (((totalExpectedAmount - totalCollectionAmount) - totalDiscountAmount) + extraCollectionAmount);
		Map<String, Double> collectionMap = new HashMap<String, Double>();
		collectionMap.put("totalExpectedAmount", totalExpectedAmount);
		collectionMap.put("totalCollectionAmount", totalCollectionAmount);
		collectionMap.put("totalDiscountAmount", totalDiscountAmount);
		collectionMap.put("extraCollectionAmount", extraCollectionAmount);
		collectionMap.put("totalExpensesAmount", totalExpensesAmount);
		collectionMap.put("restAmount", restAmount);
		collectionMap.put("availabelAmount",
				(totalCollectionAmount - totalExpensesAmount));

		return collectionMap;
	}

	@Override
	public List<StudentDTO> getDefaulterList(int sessionId, int classId,
			int sectionId, int monthId) {

		List<Students> student = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId, sessionId);
		List<StudentDTO> dto = new ArrayList<StudentDTO>();
		for (Students stu : student) {
			double feePaidAmount = 0;
			StudentDTO stud = new StudentDTO();

			StudentFeeSubmissionDetails fee = dao.getDefaulters(sessionId,
					classId, sectionId, monthId, stu.getStudentAdmissionNo());
			if (fee == null) {

				for (ClassWiseFee classWiseFees : classWiseFeeDao
						.getFeeAllotement(sessionId, classId, monthId)) {
					if (classWiseFees != null) {
						feePaidAmount += classWiseFees.getFeeAmount();

					}
				}
				for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
						.getFeeAllotement(sessionId, classId, sectionId,
								monthId)) {
					if (sectionWiseFee != null) {
						feePaidAmount += sectionWiseFee.getFeeAmount();
					}
				}
				for (StudentWiseFee studentWiseFee : studentWiseFeeDao
						.getFeeAllotement(sessionId, classId, sectionId,
								monthId, stu.getStudentAdmissionNo())) {
					if (studentWiseFee != null) {
						feePaidAmount += studentWiseFee.getFeeAmount();
					}

				}
				stud.setFirstName(stu.getFirstName());
				stud.setMiddleName(stu.getMiddleName());
				stud.setLastName(stu.getLastName());
				stud.setBalanceAmount(String.valueOf(feePaidAmount));
				stud.setMobile(stu.getMobile());
				stud.setAddressLine1(stu.getAddressLine1());
				stud.setCityName(cityDao.get(stu.getCity().getCityId())
						.getCityName());
				stud.setClassName(classDao.get(stu.getClasses().getClassId())
						.getClassName());
				stud.setSectionName(sectionDao.get(
						stu.getSection().getSectionId()).getSectionName());
				stud.setSessionDuration(sessionDao.get(
						stu.getSession().getSessionId()).getSessionDuration());
				stud.setMonthName(monthDao.get(monthId).getMonthName());
				dto.add(stud);
			}
		}
		return dto;
	}

	private List<FeecategoryAmountDTO> feePaymentData(int admissionId,
			int sessionId) {
		List<FeecategoryAmountDTO> feecategoryAmountDTOs = new ArrayList<FeecategoryAmountDTO>();
		List<Integer> integers = dao.getallDistinctSlipNo(sessionId,
				String.valueOf(admissionId));
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		for (Integer integer : integers) {
			String monthName = "";
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : dao
					.getallfeesubmissiondetailsBySlipNo(integer)) {
				monthName += studentFeeSubmissionDetails.getMonth()
						.getMonthName();
			}
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFine fine = studentFineDao
						.getStudentFine(studentFeeSubmissionDetails
								.getStudentFeeSubmissionDetailsId());
				FeecategoryAmountDTO detailsDTO = new FeecategoryAmountDTO();
				detailsDTO.setMonth(monthName);
				detailsDTO.setPaidAmount(String
						.valueOf(studentFeeSubmissionDetails.getPaidAmount()));
				detailsDTO.setFeePaidDate(df.format(studentFeeSubmissionDetails
						.getFeePaidDate()));
				detailsDTO.setFeePaidStatus(studentFeeSubmissionDetails
						.getFeePaidStatus());
				detailsDTO.setFineAmount(fine.getFineAmount());
				detailsDTO.setSessionId(studentFeeSubmissionDetails
						.getSession().getSessionId());
				detailsDTO.setStudentAdmissionNo(studentFeeSubmissionDetails
						.getStudents().getStudentAdmissionNo());
				detailsDTO
						.setSlipNo(studentFeeSubmissionDetails.getRecieptNo());
				feecategoryAmountDTOs.add(detailsDTO);
			}
		}
		return feecategoryAmountDTOs;
	}

	@Override
	public CommonDTO getStudentFeePaymentDetails(int admissionId, int sessionId) {
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", sessionId);
		CommonDTO commonDTO = new CommonDTO();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> prvList = new ArrayList<Object>();
		List<MonthDTO> monthDTOs = new ArrayList<MonthDTO>();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<Integer> monthsId = new ArrayList<Integer>();
		List<Integer> inprogressMonthsId = new ArrayList<Integer>();
		Students students = studentsAdmissionDao.getActiveStudentById(String
				.valueOf(admissionId));
		List<StudentFeeSubmissionDetailsDTO> detailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		List<MonthDTO> dtos = new ArrayList<MonthDTO>();
		Integer months[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		int prvSessionId = sessionId - 1;
		double totalAmount = 0.0;
		int lateFine = 0;
		commonDTO.setFeecategoryAmountDTOs(feePaymentData(admissionId,
				sessionId));
		for (int monthId : months) {

			if ((sessionDao.get(prvSessionId)) == null) {
				prvList.add("");
			} else if ((dao.getPrevsYearFeeSubmissionDetails(prvSessionId,
					monthId, admissionId)).isEmpty()) {
				List<String> list = new ArrayList<String>();
				for (ClassWiseFee classWiseFee : classWiseFeeDao
						.getFeeAllotement(prvSessionId, students.getClasses()
								.getClassId(), monthId)) {
					totalAmount += classWiseFee.getFeeAmount();
				}
				for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
						.getFeeAllotement(prvSessionId, students.getClasses()
								.getClassId(), students.getSection()
								.getSectionId(), monthId)) {
					totalAmount += sectionWiseFee.getFeeAmount();
				}
				for (StudentWiseFee studentWiseFee : studentWiseFeeDao
						.getFeeAllotement(prvSessionId, students.getClasses()
								.getClassId(), students.getSection()
								.getSectionId(), monthId, String
								.valueOf(admissionId))) {
					totalAmount += studentWiseFee.getFeeAmount();
				}
				LastDate lastDate;
				if ((lastDate = lastDateDao.getLastDatebyMonth(monthId,
						prvSessionId)) != null) {
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					if (differDays > 0) {

						if (fineEntity == null) {
							lateFine = 0;
						} else {
							lateFine = (int) (differDays * fineEntity.getFineAmount());
							if (lateFine > fineEntity.getMaxFineAmount()) {
								lateFine = fineEntity.getMaxFineAmount();
							}
						}
					}
					
					
				} else {
					lateFine = 0;
				}
				list.add(String.valueOf(totalAmount));
				list.add(String.valueOf(lateFine));
				list.add(String.valueOf(monthId));
				list.add(sessionDao.get(prvSessionId).getSessionDuration());
				prvList.add(list);
			}
		}
		commonDTO.setPrvList(prvList);

		for (StudentFeeSubmissionDetails feeSubmissionDetails : dao
				.getallsubmissiondetailWithFeeStatus(String
						.valueOf(admissionId))) {
			StudentFine fine = studentFineDao
					.getStudentFine(feeSubmissionDetails
							.getStudentFeeSubmissionDetailsId());
			StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
			detailsDTO.setMonth(feeSubmissionDetails.getMonth().getMonthName());
			detailsDTO.setPaidAmount(String.valueOf(feeSubmissionDetails
					.getPaidAmount()));
			detailsDTO.setFeePaidDate(df.format(feeSubmissionDetails
					.getFeePaidDate()));
			detailsDTO
					.setFeePaidStatus(feeSubmissionDetails.getFeePaidStatus());
			detailsDTO.setFineAmount(fine.getFineAmount());
			detailsDTO.setSessionId(feeSubmissionDetails.getSession()
					.getSessionId());
			if (feeSubmissionDetails.getFeePaidStatus().equalsIgnoreCase(
					"completed")) {
				monthsId.add(feeSubmissionDetails.getMonth().getMonthId());
			}
			if (feeSubmissionDetails.getFeePaidStatus().equalsIgnoreCase(
					"inprogress")) {
				inprogressMonthsId.add(feeSubmissionDetails.getMonth()
						.getMonthId());
			}
			detailsDTOs.add(detailsDTO);
		}
		commonDTO.setStudentFeeSubmissionDetailsDTOs(detailsDTOs);
		List<Month> in = monthService.findAll(Month.class);
		for (Month month : in.subList(4, 12)) {
			MonthDTO monthDTO = new MonthDTO();
			monthDTO.setMonthName(month.getMonthName());
			monthDTO.setMonthId(month.getMonthId());
			if (monthsId.contains(month.getMonthId())) {
				monthDTO.setFeePaymentStatus("COMPLETE");
			} else if (inprogressMonthsId.contains(month.getMonthId())) {
				monthDTO.setFeePaymentStatus("INPROGRESS");
			} else {
				monthDTO.setFeePaymentStatus("");
			}
			monthDTOs.add(monthDTO);
		}
		for (Month month : in.subList(0, 4)) {
			MonthDTO monthDTO = new MonthDTO();
			monthDTO.setMonthName(month.getMonthName());
			monthDTO.setMonthId(month.getMonthId());
			if (monthsId.contains(month.getMonthId())) {
				monthDTO.setFeePaymentStatus("COMPLETE");
			} else if (inprogressMonthsId.contains(month.getMonthId())) {
				monthDTO.setFeePaymentStatus("INPROGRESS");
			} else {
				monthDTO.setFeePaymentStatus("");
			}
			monthDTOs.add(monthDTO);
		}
		commonDTO.setMonthDTOs(monthDTOs);
		return commonDTO;
	}

	@Override
	public Object getStudentFeePaymentAllotment(int sessionId, int classId,
			int sectionId, int admissionNo, int monthId) throws ParseException {
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine",sessionId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		LastDate lastDate = lastDateDao.getLastDatebyMonth(monthId, sessionId);
		Date dueDate = lastDate.getLastdate();
		Date currentDate = new Date();
		int fine = 0;
		long diff = currentDate.getTime() - dueDate.getTime();
		long differDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		if (differDays > 0) {

			if (fineEntity == null) {
				fine = 0;
			} else {
				fine = (int) (differDays * fineEntity.getFineAmount());
				if (fine > fineEntity.getMaxFineAmount()) {
					fine = fineEntity.getMaxFineAmount();
				}
			}
		}
		CommonDTO commonDTO = new CommonDTO();
		commonDTO.setFineAmount(String.valueOf(fine));
		List<ClassWiseFeeDTO> classWiseFeeDTOs = new ArrayList<ClassWiseFeeDTO>();
		List<SectionWiseFeeDTO> sectionWiseFeeDTOs = new ArrayList<SectionWiseFeeDTO>();
		List<StudentWiseFeeDTO> studentWiseFeeDTOs = new ArrayList<StudentWiseFeeDTO>();
		for (ClassWiseFee classWiseFees : classWiseFeeDao.getFeeAllotement(
				sessionId, classId, monthId)) {
			if (classWiseFees != null) {
				ClassWiseFeeDTO classWiseFeeDTO = new ClassWiseFeeDTO();
				EditFeeAmount editFeeAmount = editFeeAmountDao
						.getDiscountAmountBySessionMonthCategoryId(sessionId,
								monthId, classWiseFees.getFeesCategories()
										.getFeeCategoryId(), admissionNo);
				if (editFeeAmount != null) {
					double editedAmount = classWiseFees.getFeeAmount()
							- editFeeAmount.getDiscount();
					
					classWiseFeeDTO.setFeeAmount(String.valueOf(editedAmount));
				} else {
					classWiseFeeDTO.setFeeAmount(String.valueOf(classWiseFees
							.getFeeAmount()));
				}

				classWiseFeeDTO.setFeesCategoriesName(classWiseFees
						.getFeesCategories().getFeeCategoryName());
				classWiseFeeDTOs.add(classWiseFeeDTO);

			}
		}
		for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
				.getFeeAllotement(sessionId, classId, sectionId, monthId)) {
			if (sectionWiseFee != null) {
				SectionWiseFeeDTO sectionWiseFeeDTO = new SectionWiseFeeDTO();
				EditFeeAmount editFeeAmount = editFeeAmountDao
						.getDiscountAmountBySessionMonthCategoryId(sessionId,
								monthId, sectionWiseFee.getFeesCategories()
										.getFeeCategoryId(), admissionNo);
				if (editFeeAmount != null) {
					double editedAmount = sectionWiseFee.getFeeAmount()
							- editFeeAmount.getDiscount();
					sectionWiseFeeDTO
							.setFeeAmount(String.valueOf(editedAmount));
				} else {
					sectionWiseFeeDTO.setFeeAmount(String
							.valueOf(sectionWiseFee.getFeeAmount()));
				}

				sectionWiseFeeDTO.setFeesCategoriesName(sectionWiseFee
						.getFeesCategories().getFeeCategoryName());
				sectionWiseFeeDTOs.add(sectionWiseFeeDTO);
			}
		}
		for (StudentWiseFee studentWiseFee : studentWiseFeeDao
				.getFeeAllotement(sessionId, classId, sectionId, monthId,
						String.valueOf(admissionNo))) {
			if (studentWiseFee != null) {
				StudentWiseFeeDTO studentWiseFeeDTO = new StudentWiseFeeDTO();
				EditFeeAmount editFeeAmount = editFeeAmountDao
						.getDiscountAmountBySessionMonthCategoryId(sessionId,
								monthId, studentWiseFee.getFeesCategories()
										.getFeeCategoryId(), admissionNo);
				if (editFeeAmount != null) {
					double editedAmount = studentWiseFee.getFeeAmount()
							- editFeeAmount.getDiscount();
					studentWiseFeeDTO
							.setFeeAmount(String.valueOf(editedAmount));
				} else {
					studentWiseFeeDTO.setFeeAmount(String
							.valueOf(studentWiseFee.getFeeAmount()));
				}

				studentWiseFeeDTO.setFeesCategoriesName(studentWiseFee
						.getFeesCategories().getFeeCategoryName());
				studentWiseFeeDTOs.add(studentWiseFeeDTO);
			}
		}
		commonDTO.setClassWiseFeeDTOs(classWiseFeeDTOs);
		commonDTO.setSectionWiseFeeDTOs(sectionWiseFeeDTOs);
		commonDTO.setStudentWiseFeeDTOs(studentWiseFeeDTOs);
		return commonDTO;
	}

	@Override
	public List<StudentDTO> getPaidList(int sessionId, int classId,
			int sectionId, int monthId) {
		List<Students> student = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId, sessionId);
		List<StudentDTO> dto = new ArrayList<StudentDTO>();
		for (Students stu : student) {
			double feePaidAmount = 0;
			StudentDTO stud = new StudentDTO();

			StudentFeeSubmissionDetails fee = dao.getDefaulters(sessionId,
					classId, sectionId, monthId, stu.getStudentAdmissionNo());
			if (fee != null) {
				stud.setFirstName(stu.getFirstName());
				stud.setMiddleName(stu.getMiddleName());
				stud.setLastName(stu.getLastName());
				stud.setBalanceAmount(String.valueOf(fee.getPaidAmount()));
				stud.setMobile(stu.getMobile());
				stud.setAddressLine1(stu.getAddressLine1());
				stud.setCityName(cityDao.get(stu.getCity().getCityId())
						.getCityName());
				stud.setClassName(classDao.get(stu.getClasses().getClassId())
						.getClassName());
				stud.setSectionName(sectionDao.get(
						stu.getSection().getSectionId()).getSectionName());
				stud.setSessionDuration(sessionDao.get(
						stu.getSession().getSessionId()).getSessionDuration());
				stud.setMonthName(monthDao.get(monthId).getMonthName());
				dto.add(stud);
			}
		}
		return dto;
	}

	public List<StudentDTO> getAllDefaulters() {
		
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		Session session = sessionDao.findCurrentSession();
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", session.getSessionId());
		Boolean flag = true;
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

		if (currentMonth > 3) {
			currentMonth =currentMonth-3;
		}
		 else {
			currentMonth = currentMonth +9;
		}
		for (int i = 1; i <= currentMonth; i++) {
			LastDate lastDates = lastDateDao.getLastDatebyMonth(i,
					session.getSessionId());
			if (lastDates == null) {
				StudentDTO dto = new StudentDTO();
				dto.setCategoryName("Nothing");
				lists.add(dto);
				flag = false;
				break;
			}
		}
		if (flag == true) {

			List<Students> list = studentsAdmissionDao
					.getStudentsbysession(session.getSessionId());
			for (Students students : list) {
				int classId = students.getClasses().getClassId();
				int sectionId = students.getSection().getSectionId();
				StudentFeeSubmissionDetails details = dao
						.findlastpayedmonthbystudentId(
								students.getStudentAdmissionNo(),
								session.getSessionId());

				double allotedfees = 0;
				int j = 1;
				StudentDTO dto = new StudentDTO();
				if (details != null)
					j = details.getMonth().getMonthId() + 1;
				for (int i = j; i <= currentMonth; i++) {
					LastDate lastDate = lastDateDao.getLastDatebyMonth(i,
							session.getSessionId());
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					int fine = 0;
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					if (differDays > 0) {

						if (fineEntity == null) {
							fine = 0;
						} else {
							fine = (int) (differDays * fineEntity.getFineAmount());
							if (fine > fineEntity.getMaxFineAmount()) {
								fine = fineEntity.getMaxFineAmount();
							}
						}
					}
					
					allotedfees += fine;
					if (fine > 0 || differDays > 0) {
						List<FeecategoryAmountDTO> feecategoryAmountDTOs = new ArrayList<FeecategoryAmountDTO>();
						List<ClassWiseFee> classWiseFees = classWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, i);
						for (ClassWiseFee classWiseFee : classWiseFees) {
							if (classWiseFees != null) {
								if (classWiseFees != null) {
									FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
									feecategoryAmountDTO
											.setFeeCategoryName(classWiseFee
													.getFeesCategories()
													.getFeeCategoryName());
									feecategoryAmountDTO
											.setFeeCatgoryAmount(classWiseFee
													.getFeeAmount());
									feecategoryAmountDTOs
											.add(feecategoryAmountDTO);

									EditFeeAmount editFeeAmount = editFeeAmountDao
											.getDiscountAmountBySessionMonthCategoryId(
													session.getSessionId(),
													i,
													classWiseFee
															.getFeesCategories()
															.getFeeCategoryId(),
													Integer.parseInt(students
															.getStudentAdmissionNo()));
									if (editFeeAmount != null) {
										double editedAmount = classWiseFee
												.getFeeAmount()
												- editFeeAmount.getDiscount();
										allotedfees += editedAmount;
									} else {
										allotedfees += classWiseFee
												.getFeeAmount();
									}

								}
							}
						}
						for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, sectionId, i)) {
							if (sectionWiseFee != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(sectionWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(sectionWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												session.getSessionId(),
												i,
												sectionWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(students
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = sectionWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += sectionWiseFee
											.getFeeAmount();
								}
							}

						}

						for (StudentWiseFee studentWiseFee : studentWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, sectionId, i,
										students.getStudentAdmissionNo())) {
							if (studentWiseFee != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(studentWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(studentWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												session.getSessionId(),
												i,
												studentWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(students
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = studentWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += studentWiseFee
											.getFeeAmount();
								}
							}

						}

					}
				}

				if (allotedfees > 0) {

					dto.setFirstName(students.getFirstName());
					dto.setMiddleName(students.getMiddleName());
					dto.setLastName(students.getLastName());
					dto.setFullName(students.getFirstName() + " "
							+ students.getMiddleName() + " "
							+ students.getLastName());
					dto.setAdmissionNo(students.getStudentAdmissionNo());
					dto.setStudentId(students.getStudentId());
					dto.setMobile(students.getMobile());
					dto.setClassName(students.getClasses().getClassName());
					dto.setSectionName(students.getSection().getSectionName());
					dto.setAddressLine1(students.getAddressLine1());
					dto.setBalanceAmount(String.valueOf(allotedfees));
					lists.add(dto);
				}

			}
		}
		return lists;
	}

	@Transactional
	@Override
	public List<StudentDTO> getAllDefaultersbyPageNo(int pageNo) {
		List<StudentDTO> lists = new ArrayList<StudentDTO>();

		Session session = sessionDao.findCurrentSession();
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", session.getSessionId());
		Boolean flag = true;
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

		if (currentMonth > 3) {
			currentMonth =currentMonth-3;
		}
		 else {
			currentMonth = currentMonth +9;
		}
		for (int i = 1; i <= currentMonth; i++) {
			LastDate lastDates = lastDateDao.getLastDatebyMonth(i,
					session.getSessionId());
			if (lastDates == null) {
				StudentDTO dto = new StudentDTO();
				dto.setCategoryName("Nothing");
				lists.add(dto);
				flag = false;
				break;
			}
		}
		if (flag == true) {
			List<Students> list = studentsAdmissionDao
					.getStudentsbysessionandpageno(session.getSessionId(),
							pageNo);

			for (Students students : list) {
				int classId = students.getClasses().getClassId();
				int sectionId = students.getSection().getSectionId();
				StudentFeeSubmissionDetails details = dao
						.findlastpayedmonthbystudentId(
								students.getStudentAdmissionNo(),
								session.getSessionId());
				double allotedfees = 0;
				int j = 1;
				StudentDTO dto = new StudentDTO();
				if (details != null)
					j = details.getMonth().getMonthId() + 1;
				for (int i = j; i <= currentMonth; i++) {
					LastDate lastDate = lastDateDao.getLastDatebyMonth(i,
							session.getSessionId());
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					int fine = 0;
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
				
					if (differDays > 0) {

						if (fineEntity == null) {
							fine = 0;
						} else {
							fine = (int) (differDays * fineEntity.getFineAmount());
							if (fine > fineEntity.getMaxFineAmount()) {
								fine = fineEntity.getMaxFineAmount();
							}
						}
					}
					allotedfees += fine;
					if (fine > 0 || differDays > 0) {
						List<FeecategoryAmountDTO> feecategoryAmountDTOs = new ArrayList<FeecategoryAmountDTO>();
						List<ClassWiseFee> classWiseFees = classWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, i);
						for (ClassWiseFee classWiseFee : classWiseFees) {
							if (classWiseFees != null) {
								if (classWiseFees != null) {
									FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
									feecategoryAmountDTO
											.setFeeCategoryName(classWiseFee
													.getFeesCategories()
													.getFeeCategoryName());
									feecategoryAmountDTO
											.setFeeCatgoryAmount(classWiseFee
													.getFeeAmount());
									feecategoryAmountDTOs
											.add(feecategoryAmountDTO);

									EditFeeAmount editFeeAmount = editFeeAmountDao
											.getDiscountAmountBySessionMonthCategoryId(
													session.getSessionId(),
													i,
													classWiseFee
															.getFeesCategories()
															.getFeeCategoryId(),
													Integer.parseInt(students
															.getStudentAdmissionNo()));
									if (editFeeAmount != null) {
										double editedAmount = classWiseFee
												.getFeeAmount()
												- editFeeAmount.getDiscount();
										allotedfees += editedAmount;
									} else {
										allotedfees += classWiseFee
												.getFeeAmount();
									}

								}
							}
						}
						for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, sectionId, i)) {
							if (sectionWiseFee != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(sectionWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(sectionWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												session.getSessionId(),
												i,
												sectionWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(students
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = sectionWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += sectionWiseFee
											.getFeeAmount();
								}
							}

						}

						for (StudentWiseFee studentWiseFee : studentWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, sectionId, i,
										students.getStudentAdmissionNo())) {
							if (studentWiseFee != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(studentWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(studentWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												session.getSessionId(),
												i,
												studentWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(students
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = studentWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += studentWiseFee
											.getFeeAmount();
								}
							}

						}

					}
				}

				if (allotedfees > 0) {

					dto.setFirstName(students.getFirstName());
					dto.setMiddleName(students.getMiddleName());
					dto.setLastName(students.getLastName());
					dto.setFullName(students.getFirstName() + " "
							+ students.getMiddleName() + " "
							+ students.getLastName());
					dto.setAdmissionNo(students.getStudentAdmissionNo());
					dto.setStudentId(students.getStudentId());
					dto.setMobile(students.getMobile());
					dto.setClassName(students.getClasses().getClassName());
					dto.setSectionName(students.getSection().getSectionName());
					dto.setAddressLine1(students.getAddressLine1());
					dto.setBalanceAmount(String.valueOf(allotedfees));
					lists.add(dto);
				}
			}
		}
		return lists;
	}

	@Override
	public List<StudentDTO> getClassWiseDefaulterList(int classId) {
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		Session session = sessionDao.findCurrentSession();
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", session.getSessionId());
		Boolean flag = true;
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (currentMonth > 3) {
			currentMonth =currentMonth-3;
		}
		 else {
			currentMonth = currentMonth +9;
		}
		for (int i = 1; i <= currentMonth; i++) {
			LastDate lastDates = lastDateDao.getLastDatebyMonth(i,
					session.getSessionId());
			if (lastDates == null) {
				StudentDTO dto = new StudentDTO();
				dto.setCategoryName("Nothing");
				lists.add(dto);
				flag = false;
				break;
			}
		}
		if (flag == true) {
			List<Students> list = studentsAdmissionDao
					.getStudentbyclasssession(session.getSessionId(), classId);
			for (Students students : list) {
				int sectionId = students.getSection().getSectionId();

				double allotedfees = 0;
				StudentFeeSubmissionDetails details = dao
						.findlastpayedmonthbystudentId(
								students.getStudentAdmissionNo(),
								session.getSessionId());
				int j = 1;
				StudentDTO dto = new StudentDTO();
				if (details != null)
					j = details.getMonth().getMonthId() + 1;
				for (int i = j; i <= currentMonth; i++) {
					LastDate lastDate = lastDateDao.getLastDatebyMonth(i,
							session.getSessionId());
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					int fine = 0;
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					if (differDays > 0) {

						if (fineEntity == null) {
							fine = 0;
						} else {
							fine = (int) (differDays * fineEntity.getFineAmount());
							if (fine > fineEntity.getMaxFineAmount()) {
								fine = fineEntity.getMaxFineAmount();
							}
						}
					}
					allotedfees += fine;
					if (fine > 0 || differDays > 0) {
						List<FeecategoryAmountDTO> feecategoryAmountDTOs = new ArrayList<FeecategoryAmountDTO>();
						List<ClassWiseFee> classWiseFees = classWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, i);
						for (ClassWiseFee classWiseFee : classWiseFees) {
							if (classWiseFees != null) {
								if (classWiseFees != null) {
									FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
									feecategoryAmountDTO
											.setFeeCategoryName(classWiseFee
													.getFeesCategories()
													.getFeeCategoryName());
									feecategoryAmountDTO
											.setFeeCatgoryAmount(classWiseFee
													.getFeeAmount());
									feecategoryAmountDTOs
											.add(feecategoryAmountDTO);

									EditFeeAmount editFeeAmount = editFeeAmountDao
											.getDiscountAmountBySessionMonthCategoryId(
													session.getSessionId(),
													i,
													classWiseFee
															.getFeesCategories()
															.getFeeCategoryId(),
													Integer.parseInt(students
															.getStudentAdmissionNo()));
									if (editFeeAmount != null) {
										double editedAmount = classWiseFee
												.getFeeAmount()
												- editFeeAmount.getDiscount();
										allotedfees += editedAmount;
									} else {
										allotedfees += classWiseFee
												.getFeeAmount();
									}

								}
							}
						}
						for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, sectionId, i)) {
							if (sectionWiseFee != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(sectionWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(sectionWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												session.getSessionId(),
												i,
												sectionWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(students
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = sectionWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += sectionWiseFee
											.getFeeAmount();
								}
							}

						}

						for (StudentWiseFee studentWiseFee : studentWiseFeeDao
								.getFeeAllotement(session.getSessionId(),
										classId, sectionId, i,
										students.getStudentAdmissionNo())) {
							if (studentWiseFee != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(studentWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(studentWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												session.getSessionId(),
												i,
												studentWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(students
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = studentWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += studentWiseFee
											.getFeeAmount();
								}
							}

						}

					}
				}

				if (allotedfees > 0) {

					dto.setFirstName(students.getFirstName());
					dto.setMiddleName(students.getMiddleName());
					dto.setLastName(students.getLastName());
					dto.setFullName(students.getFirstName() + " "
							+ students.getMiddleName() + " "
							+ students.getLastName());
					dto.setAdmissionNo(students.getStudentAdmissionNo());
					dto.setFatherName(students.getFatherName());
					dto.setStudentId(students.getStudentId());
					dto.setMobile(students.getMobile());
					dto.setClassName(students.getClasses().getClassName());
					dto.setSectionName(students.getSection().getSectionName());
					dto.setAddressLine1(students.getAddressLine1());
					dto.setBalanceAmount(String.valueOf(allotedfees));
					lists.add(dto);
				}

			}
		}
		return lists;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getInprogressList() {
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		List<Integer> integers = dao.getallInprogressDistinctSlipNo();
		for (Integer integer : integers) {
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = new StudentFeeSubmissionDetailsDTO();
				studentFeeSubmissionDetailsDTO
						.setStudentName(studentFeeSubmissionDetails
								.getStudents().getFirstName()
								+ " "
								+ studentFeeSubmissionDetails.getStudents()
										.getMiddleName()
								+ " "
								+ studentFeeSubmissionDetails.getStudents()
										.getLastName());
				studentFeeSubmissionDetailsDTO
						.setFatherName(studentFeeSubmissionDetails
								.getStudents().getFatherName());
				studentFeeSubmissionDetailsDTO
						.setClassName(studentFeeSubmissionDetails
								.getStudentClasses().getClassName());
				studentFeeSubmissionDetailsDTO
						.setSectionName(studentFeeSubmissionDetails
								.getSection().getSectionName());
				studentFeeSubmissionDetailsDTO
						.setChequeNo(studentFeeSubmissionDetails.getChequeNo());
				studentFeeSubmissionDetailsDTO.setNetPayableAmount(String
						.valueOf(studentFeeSubmissionDetails.getPaidAmount()));
				studentFeeSubmissionDetailsDTO
						.setBankName(studentFeeSubmissionDetails.getBankName());
				studentFeeSubmissionDetailsDTO
						.setRecieptNo(studentFeeSubmissionDetails
								.getRecieptNo());
				studentFeeSubmissionDetailsDTO
						.setMobileNo(studentFeeSubmissionDetails.getStudents()
								.getMobile());
				studentFeeSubmissionDetailsDTO
						.setFeePaidStatus(studentFeeSubmissionDetails
								.getFeePaidStatus());
				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsDTO);
			}
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public void changeFeeStatus(int slipNo, String feePaidStatus) {
		List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailList = dao
				.getallfeesubmissiondetailsBySlipNo(slipNo);
		for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailList) {
			studentFeeSubmissionDetails.setFeePaidStatus(feePaidStatus);
			dao.update(studentFeeSubmissionDetails);
		}
	}

	// tally
	@Override
	public List<StudentFeeSubmissionDetailsDTO> getStudentFeePaymentDetail(
			String studentAdmissionNo, int sessionId) {
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", sessionId);
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		List<Integer> paidMonthIds = dao.getMonthsId(sessionId,
				studentAdmissionNo);
		List<Integer> leCurrentMonth = new ArrayList<Integer>();
		List<Integer> geCurrentMonth = new ArrayList<Integer>();
		List<Integer> recieptNos = dao.getallDistinctSlipNo(sessionId,
				studentAdmissionNo);
		Calendar calendar = Calendar.getInstance();
		int monthId = 9;
		/*if (monthId > 3) {
			monthId =monthId-3;
		}
		 else {
			 monthId = monthId +9;
		}
	*/
		for (Integer integer : recieptNos) {
			Integer lastMonthId = dao.getLastMonthIdOfReciept(integer);
			leCurrentMonth.add(lastMonthId);
			
		}
		List<Month> months = monthDao.findAll(Month.class);
		Students students = studentsAdmissionDao.get(studentAdmissionNo);

		for (Month month : months) {
			if (month.getMonthId() != 13) {
				Double grossAmount = 0.0;
				List<FeesCategoriesDTO> feesCategoriesDTOs = new ArrayList<FeesCategoriesDTO>();
				StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = new StudentFeeSubmissionDetailsDTO();
				studentFeeSubmissionDetailsDTO.setMonth(month.getMonthName());
				studentFeeSubmissionDetailsDTO.setMonthId(month.getMonthId());
				// parent data
				if (paidMonthIds.contains(month.getMonthId())) {
					String paidAmount = "";
					String paidStatus = "";
					String monthNames = "";
					boolean flag = false;
					for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : dao
							.getPrevsYearFeeSubmissionDetails(sessionId,
									month.getMonthId(),
									Integer.parseInt(studentAdmissionNo))) {

						if (leCurrentMonth.contains(month.getMonthId()))
								 {
							// if (month.getMonthId() == monthId) {
							paidAmount = String
									.valueOf(studentFeeSubmissionDetails
											.getPaidAmount());
							paidStatus = studentFeeSubmissionDetails
									.getFeePaidStatus();
							studentFeeSubmissionDetailsDTO
									.setRecieptNo(studentFeeSubmissionDetails
											.getRecieptNo());
							studentFeeSubmissionDetailsDTO
									.setPaidBy(studentFeeSubmissionDetails
											.getPaidBy());
							if (studentFeeSubmissionDetails.getPaidBy().equals(
									"cheque")) {

								studentFeeSubmissionDetailsDTO
										.setBankName(studentFeeSubmissionDetails
												.getBankName());
								studentFeeSubmissionDetailsDTO
										.setChequeNo(studentFeeSubmissionDetails
												.getChequeNo());
							} else {
								studentFeeSubmissionDetailsDTO.setBankName("-");
								studentFeeSubmissionDetailsDTO.setChequeNo("-");
							}
							for (Integer slipMonthId : dao
									.getMonthIdsBySlipNo(studentFeeSubmissionDetails
											.getRecieptNo())) {
								monthNames += " "
										+ monthDao.get(slipMonthId)
												.getMonthName();
							}
							studentFeeSubmissionDetailsDTO
									.setDiscountAmount(String
											.valueOf(studentFeeSubmissionDetails
													.getDiscount()
													.getDiscountAmount()));
							flag = true;
						}
 else {
							studentFeeSubmissionDetailsDTO.setPaidAmount("-");
							studentFeeSubmissionDetailsDTO
									.setFeePaidStatus(studentFeeSubmissionDetails
											.getFeePaidStatus());
							/*
							 * studentFeeSubmissionDetailsDTO
							 * .setDiscountAmount(String
							 * .valueOf(studentFeeSubmissionDetails
							 * .getDiscount() .getDiscountAmount()));
							 */
							studentFeeSubmissionDetailsDTO
									.setDiscountAmount("0");
							studentFeeSubmissionDetailsDTO.setRecieptNo(0);
							studentFeeSubmissionDetailsDTO.setPaidBy("-");
							studentFeeSubmissionDetailsDTO.setBankName("-");
							studentFeeSubmissionDetailsDTO.setChequeNo("-");
						}
					}
					if (flag) {
						studentFeeSubmissionDetailsDTO
								.setPaidAmount(paidAmount);
						studentFeeSubmissionDetailsDTO
								.setCommonString(monthNames);
						studentFeeSubmissionDetailsDTO
								.setFeePaidStatus(paidStatus);
					}
					studentFeeSubmissionDetailsDTO.setStatus("YES");
				} else {
					studentFeeSubmissionDetailsDTO.setPaidAmount("-");
					studentFeeSubmissionDetailsDTO.setFeePaidStatus("-");
					studentFeeSubmissionDetailsDTO.setStatus("NO");
					studentFeeSubmissionDetailsDTO.setRecieptNo(0);
					studentFeeSubmissionDetailsDTO.setPaidBy("-");
					studentFeeSubmissionDetailsDTO.setBankName("-");
					studentFeeSubmissionDetailsDTO.setChequeNo("-");
					studentFeeSubmissionDetailsDTO.setDiscount("-");

				}
				// child data

				for (ClassWiseFee classWiseFee : classWiseFeeDao
						.getFeeAllotement(sessionId, students.getClasses()
								.getClassId(), month.getMonthId())) {
					if (classWiseFee != null) {
						FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
						categoriesDTO.setFeeCategoryName(classWiseFee
								.getFeesCategories().getFeeCategoryName());
						EditFeeAmount editFeeAmount = editFeeAmountDao
								.getDiscountAmountBySessionMonthCategoryId(
										sessionId, month.getMonthId(),
										classWiseFee.getFeesCategories()
												.getFeeCategoryId(), Integer
												.parseInt(studentAdmissionNo));

						if (editFeeAmount != null) {
							double editedAmount = classWiseFee.getFeeAmount()
									- editFeeAmount.getDiscount();
							categoriesDTO.setAmounts(String
									.valueOf(editedAmount));
							grossAmount += editedAmount;
						} else {
							categoriesDTO.setAmounts(String
									.valueOf(classWiseFee.getFeeAmount()));
							grossAmount += classWiseFee.getFeeAmount();
						}
						feesCategoriesDTOs.add(categoriesDTO);

					}
				}

				for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
						.getFeeAllotement(sessionId, students.getClasses()
								.getClassId(), students.getSection()
								.getSectionId(), month.getMonthId())) {
					if (sectionWiseFee != null) {
						FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
						categoriesDTO.setFeeCategoryName(sectionWiseFee
								.getFeesCategories().getFeeCategoryName());
						EditFeeAmount editFeeAmount = editFeeAmountDao
								.getDiscountAmountBySessionMonthCategoryId(
										sessionId, month.getMonthId(),
										sectionWiseFee.getFeesCategories()
												.getFeeCategoryId(), Integer
												.parseInt(studentAdmissionNo));
						if (editFeeAmount != null) {
							double editedAmount = sectionWiseFee.getFeeAmount()
									- editFeeAmount.getDiscount();
							categoriesDTO.setAmounts(String
									.valueOf(editedAmount));
							grossAmount += editedAmount;

						} else {
							categoriesDTO.setAmounts(String
									.valueOf(sectionWiseFee.getFeeAmount()));
							grossAmount += sectionWiseFee.getFeeAmount();
						}

						feesCategoriesDTOs.add(categoriesDTO);
					}
				}

				for (StudentWiseFee studentWiseFee : studentWiseFeeDao
						.getFeeAllotement(sessionId, students.getClasses()
								.getClassId(), students.getSection()
								.getSectionId(), month.getMonthId(),
								studentAdmissionNo)) {
					if (studentWiseFee != null) {
						FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
						categoriesDTO.setFeeCategoryName(studentWiseFee
								.getFeesCategories().getFeeCategoryName());
						categoriesDTO.setFeeCategoryId(studentWiseFee
								.getFeesCategories().getFeeCategoryId());
						EditFeeAmount editFeeAmount = editFeeAmountDao
								.getDiscountAmountBySessionMonthCategoryId(
										sessionId, month.getMonthId(),
										studentWiseFee.getFeesCategories()
												.getFeeCategoryId(), Integer
												.parseInt(studentAdmissionNo));
						if (editFeeAmount != null) {
							double editedAmount = studentWiseFee.getFeeAmount()
									- editFeeAmount.getDiscount();
							categoriesDTO.setAmounts(String
									.valueOf(editedAmount));
							if (studentWiseFee.getFeesCategories()
									.getFeeCategoryId() != 13) {
								grossAmount += editedAmount;
							}

						} else {
							categoriesDTO.setAmounts(String
									.valueOf(studentWiseFee.getFeeAmount()));
							if (studentWiseFee.getFeesCategories()
									.getFeeCategoryId() != 13) {
								grossAmount += studentWiseFee.getFeeAmount();
							}

						}
						feesCategoriesDTOs.add(categoriesDTO);

					}
				}
				Double lateFine = 0.0;
				LastDate lastDate;
				if ((lastDate = lastDateDao.getLastDatebyMonth(
						month.getMonthId(), sessionId)) != null) {
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					if (differDays > 0) {

						if (fineEntity == null) {
							lateFine = 0.0;
						} else {
							lateFine = (double) (differDays * fineEntity.getFineAmount());
							if (lateFine > fineEntity.getMaxFineAmount()) {
								lateFine = (double)fineEntity.getMaxFineAmount();
							}
						}
					}
				
				} else {
					lateFine = 0.0;
				}
				if (lateFine != 0) {
					FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
					categoriesDTO.setFeeCategoryName("Late Fees Fine");
					categoriesDTO.setAmounts(String.valueOf(lateFine));
					feesCategoriesDTOs.add(categoriesDTO);
				}
				studentFeeSubmissionDetailsDTO.setFineAmount(String
						.valueOf(lateFine));
				studentFeeSubmissionDetailsDTO
						.setGrossAmount(new DecimalFormat("##.##")
								.format(grossAmount));
				studentFeeSubmissionDetailsDTO
						.setFeesCategoriesDTOs(feesCategoriesDTOs);
				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsDTO);
			}
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getStudentFeePaymentDetail(
			String studentAdmissionNo) {
		List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		Session session = sessionDao.findCurrentSession();
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", session.getSessionId());
		List<Integer> paidMonthIds = dao.getMonthsId(session.getSessionId(),
				studentAdmissionNo);
		List<Integer> inprogressMonthIds = dao.getInprogressMonthsId(
				session.getSessionId(), studentAdmissionNo);
		List<Month> months = monthDao.findAll(Month.class);
		Students students = studentsAdmissionDao.get(studentAdmissionNo);
		Calendar calendar = Calendar.getInstance();
		int currentMonthId = calendar.get(Calendar.MONTH)+1;
		if (currentMonthId > 3) {
			currentMonthId =currentMonthId-3;
		}
		 else {
			 currentMonthId = currentMonthId +9;
		}

		for (Month month : months) {
			if (month.getMonthId() != 13
					&& !paidMonthIds.contains(month.getMonthId())
					&& currentMonthId >= month.getMonthId()) {
				Double grossAmount = 0.0;
				List<FeesCategoriesDTO> feesCategoriesDTOs = new ArrayList<FeesCategoriesDTO>();
				StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = new StudentFeeSubmissionDetailsDTO();
				studentFeeSubmissionDetailsDTO.setMonth(month.getMonthName());
				studentFeeSubmissionDetailsDTO.setMonthId(month.getMonthId());
				// parent data

				if (inprogressMonthIds.contains(month.getMonthId())) {
					String paidAmount = "";
					String paidStatus = "";
					String monthNames = "";
					boolean flag = false;
					for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : dao
							.getPrevsYearFeeSubmissionDetails(
									session.getSessionId(), month.getMonthId(),
									Integer.parseInt(studentAdmissionNo))) {
						Date date = studentFeeSubmissionDetails
								.getFeePaidDate();
						calendar = Calendar.getInstance();
						calendar.setTime(date);
						int monthId = calendar.get(Calendar.MONTH) + 1;
						if (monthId > 3) {
							monthId =monthId-3;
						}
						 else {
							 monthId = monthId +9;
						}
					
						if (month.getMonthId() == monthId) {
							paidAmount = String
									.valueOf(studentFeeSubmissionDetails
											.getPaidAmount());
							paidStatus = studentFeeSubmissionDetails
									.getFeePaidStatus();
							studentFeeSubmissionDetailsDTO
									.setRecieptNo(studentFeeSubmissionDetails
											.getRecieptNo());
							if (studentFeeSubmissionDetails.getPaidBy().equals(
									"cheque")) {
								studentFeeSubmissionDetailsDTO
										.setPaidBy(studentFeeSubmissionDetails
												.getPaidBy());
								studentFeeSubmissionDetailsDTO
										.setBankName(studentFeeSubmissionDetails
												.getBankName());
								studentFeeSubmissionDetailsDTO
										.setChequeNo(studentFeeSubmissionDetails
												.getChequeNo());
							} else {
								studentFeeSubmissionDetailsDTO
										.setPaidBy(studentFeeSubmissionDetails
												.getPaidBy());
								studentFeeSubmissionDetailsDTO.setBankName("-");
								studentFeeSubmissionDetailsDTO.setChequeNo("-");
							}
							for (Integer slipMonthId : dao
									.getMonthIdsBySlipNo(studentFeeSubmissionDetails
											.getRecieptNo())) {
								monthNames += " "
										+ monthDao.get(slipMonthId)
												.getMonthName();
							}
							flag = true;
						} else {
							studentFeeSubmissionDetailsDTO.setPaidAmount("-");
							studentFeeSubmissionDetailsDTO
									.setFeePaidStatus(studentFeeSubmissionDetails
											.getFeePaidStatus());
							studentFeeSubmissionDetailsDTO.setRecieptNo(0);
							studentFeeSubmissionDetailsDTO.setPaidBy("-");
							studentFeeSubmissionDetailsDTO.setBankName("-");
							studentFeeSubmissionDetailsDTO.setChequeNo("-");
						}
					}
					if (flag) {
						studentFeeSubmissionDetailsDTO
								.setPaidAmount(paidAmount);
						studentFeeSubmissionDetailsDTO
								.setCommonString(monthNames);
						studentFeeSubmissionDetailsDTO
								.setFeePaidStatus(paidStatus);
					}
					studentFeeSubmissionDetailsDTO.setStatus("YES");
				} else {
					studentFeeSubmissionDetailsDTO.setPaidAmount("-");
					studentFeeSubmissionDetailsDTO.setFeePaidStatus("-");
					studentFeeSubmissionDetailsDTO.setStatus("NO");
					studentFeeSubmissionDetailsDTO.setRecieptNo(0);
					studentFeeSubmissionDetailsDTO.setPaidBy("-");
					studentFeeSubmissionDetailsDTO.setBankName("-");
					studentFeeSubmissionDetailsDTO.setChequeNo("-");

				}

				// child data
				for (ClassWiseFee classWiseFee : classWiseFeeDao
						.getFeeAllotement(session.getSessionId(), students
								.getClasses().getClassId(), month.getMonthId())) {
					FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
					categoriesDTO.setFeeCategoryName(classWiseFee
							.getFeesCategories().getFeeCategoryName());
					categoriesDTO.setAmounts(String.valueOf(classWiseFee
							.getFeeAmount()));
					feesCategoriesDTOs.add(categoriesDTO);
					grossAmount += classWiseFee.getFeeAmount();
				}
				for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
						.getFeeAllotement(session.getSessionId(), students
								.getClasses().getClassId(), students
								.getSection().getSectionId(), month
								.getMonthId())) {
					FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
					categoriesDTO.setFeeCategoryName(sectionWiseFee
							.getFeesCategories().getFeeCategoryName());
					categoriesDTO.setAmounts(String.valueOf(sectionWiseFee
							.getFeeAmount()));
					feesCategoriesDTOs.add(categoriesDTO);
					grossAmount += sectionWiseFee.getFeeAmount();
				}
				for (StudentWiseFee studentWiseFee : studentWiseFeeDao
						.getFeeAllotement(session.getSessionId(), students
								.getClasses().getClassId(), students
								.getSection().getSectionId(), month
								.getMonthId(), studentAdmissionNo)) {
					FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
					categoriesDTO.setFeeCategoryName(studentWiseFee
							.getFeesCategories().getFeeCategoryName());
					categoriesDTO.setAmounts(String.valueOf(studentWiseFee
							.getFeeAmount()));
					feesCategoriesDTOs.add(categoriesDTO);
					grossAmount += studentWiseFee.getFeeAmount();
				}
				Double lateFine = 0.0;
				LastDate lastDate;
				if ((lastDate = lastDateDao.getLastDatebyMonth(
						month.getMonthId(), session.getSessionId())) != null) {
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					
					if (differDays > 0) {

						if (fineEntity == null) {
							lateFine = 0.0;
						} else {
							lateFine = (double) (differDays * fineEntity.getFineAmount());
							if (lateFine > fineEntity.getMaxFineAmount()) {
								lateFine = (double)fineEntity.getMaxFineAmount();
							}
						}
					}
				} else {
					lateFine = 0.0;
				}
				if (lateFine != 0) {
					FeesCategoriesDTO categoriesDTO = new FeesCategoriesDTO();
					categoriesDTO.setFeeCategoryName("Late Fees Fine");
					categoriesDTO.setAmounts(String.valueOf(lateFine));
					feesCategoriesDTOs.add(categoriesDTO);
				}
				studentFeeSubmissionDetailsDTO
						.setLastDate(new SimpleDateFormat("dd-MMM-yyyy")
								.format(lastDate.getLastdate()));
				studentFeeSubmissionDetailsDTO.setFineAmount(String
						.valueOf(lateFine));
				studentFeeSubmissionDetailsDTO
						.setGrossAmount(new DecimalFormat("##.##")
								.format(grossAmount));
				studentFeeSubmissionDetailsDTO
						.setFeesCategoriesDTOs(feesCategoriesDTOs);
				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsDTO);
			}
		}
		return studentFeeSubmissionDetailsDTOs;
	}

	@Override
	public List<FeesCategoriesDTO> getNextMonthFeedetailsbystudentId(
			String studentId) {
		int classId = 0;
		int sectionId = 0;
		int integer = 0;
		int sessionId = 0;

		List<FeesCategoriesDTO> feeslist = new ArrayList<FeesCategoriesDTO>();
		StudentFeeSubmissionDetails studentFeeSubmissionDetails = dao
				.findlastpayedmonthbystudentId(studentId, sessionDao
						.findCurrentSession().getSessionId());
		if (studentFeeSubmissionDetails == null) {
			integer = 1;

		} else {

			integer = studentFeeSubmissionDetails.getMonth().getMonthId() + 1;

		}
		if (integer == 13) {
			FeesCategoriesDTO feescatgdto = new FeesCategoriesDTO();
			feescatgdto.setFeeCategoryName("no");
			feeslist.add(feescatgdto);
		} else {
			Students student = admissionDao.get(studentId);
			sectionId = student.getSection().getSectionId();
			classId = student.getClasses().getClassId();
			sessionId = student.getSession().getSessionId();
			int feecategoryamount = 0;
			for (FeesCategories feescatg : feescategoriesDao
					.findAllByIsActiveTrue(FeesCategories.class)) {

				FeesCategoriesDTO feescatgdto = new FeesCategoriesDTO();
				feecategoryamount = 0;
				feescatgdto.setFeeCategoryName(feescatg.getFeeCategoryName());
				for (ClassWiseFee classWiseFees : classWiseFeeDao
						.getFeeAllotement(sessionId, classId, integer)) {
					if (classWiseFees != null) {
						EditFeeAmount editFeeAmount = editFeeAmountDao
								.getDiscountAmountBySessionMonthCategoryId(
										sessionId, integer, classWiseFees
												.getFeesCategories()
												.getFeeCategoryId(), Integer
												.parseInt(studentId));
						if (feescatg.getFeeCategoryName() == classWiseFees
								.getFeesCategories().getFeeCategoryName()) {
							if (editFeeAmount == null) {
								feecategoryamount += classWiseFees
										.getFeeAmount();
							} else {
								feecategoryamount += (classWiseFees
										.getFeeAmount() - editFeeAmount
										.getDiscount());
							}
						}
					}

				}
				for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
						.getFeeAllotement(sessionId, classId, sectionId,
								integer)) {
					if (sectionWiseFee != null) {
						if (feescatg.getFeeCategoryName() == sectionWiseFee
								.getFeesCategories().getFeeCategoryName()) {
							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											sessionId, integer, sectionWiseFee
													.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(studentId));
							if (editFeeAmount == null) {
								feecategoryamount += sectionWiseFee
										.getFeeAmount();
							} else {
								feecategoryamount += (sectionWiseFee
										.getFeeAmount() - editFeeAmount
										.getDiscount());
							}
						}
					}
				}
				for (StudentWiseFee studentWiseFee : studentWiseFeeDao
						.getFeeAllotement(sessionId, classId, sectionId,
								integer, studentId)) {
					if (studentWiseFee != null) {
						if (feescatg.getFeeCategoryName() == studentWiseFee
								.getFeesCategories().getFeeCategoryName()) {
							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											sessionId, integer, studentWiseFee
													.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(studentId));
							if (editFeeAmount == null) {
								feecategoryamount += studentWiseFee
										.getFeeAmount();
							} else {
								feecategoryamount += (studentWiseFee
										.getFeeAmount() - editFeeAmount
										.getDiscount());
							}
						}
					}
				}

				if (feecategoryamount == 0.0 || feecategoryamount == 0)
					feescatgdto.setAmounts("");
				else
					feescatgdto.setAmounts(String.valueOf(feecategoryamount));
				feescatgdto.setTotal(feecategoryamount);
				feescatgdto.setMonthName(monthDao.get(integer).getMonthName());
				feeslist.add(feescatgdto);

			}
		}
		return feeslist;
	}

	@Override
	public StudentFeeSubmissionDetailsDTO getPaymentStatusDetails(
			String studentId, String slipNo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		StudentFeeSubmissionDetails feeSubmissionDetails = dao
				.getPaymentStatusDetails(slipNo);
		StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
		detailsDTO.setPaidAmount(String.valueOf(feeSubmissionDetails
				.getPaidAmount()));
		detailsDTO.setDiscount(String.valueOf(feeSubmissionDetails
				.getDiscount().getDiscountAmount()));
		detailsDTO.setFineAmount(feeSubmissionDetails.getStudentFine()
				.getFineAmount());
		detailsDTO.setFeePaidDate(dateFormat.format(feeSubmissionDetails
				.getFeePaidDate()));
		detailsDTO.setPaidBy(feeSubmissionDetails.getPaidBy());

		return detailsDTO;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getTodaysCollectionsDetails(
			String stringDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (!stringDate.equals("")) {
			date = df.parse(stringDate);
		} else {
			date = df.parse(df.format(new Date()));
		}
		List<Integer> integers = dao.getallDistinctSlipNo(date);
		List<StudentFeeSubmissionDetailsDTO> feeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
		for (Integer integer : integers) {
			for (StudentFeeSubmissionDetails feeDetails : dao
					.getSingleFeeSubmissionBySlipNo(integer)) {
				StudentFeeSubmissionDetailsDTO detailsDTO = new StudentFeeSubmissionDetailsDTO();
				detailsDTO.setStudentAdmissionNo(feeDetails.getStudents()
						.getStudentAdmissionNo());
				detailsDTO.setStudentName(feeDetails.getStudents()
						.getFirstName()
						+ " "
						+ feeDetails.getStudents().getMiddleName()
						+ " "
						+ feeDetails.getStudents().getLastName());
				detailsDTO.setPaidAmount(String.valueOf(feeDetails
						.getPaidAmount()));
				detailsDTO.setFineAmount(feeDetails.getStudentFine()
						.getFineAmount());
				detailsDTO.setDiscountAmount(String.valueOf(feeDetails
						.getDiscount().getDiscountAmount()));
				detailsDTO.setlFno(feeDetails.getlFNo());
				detailsDTO.setRecieptNo(feeDetails.getRecieptNo());
				detailsDTO
						.setFeePaidDate(df.format(feeDetails.getFeePaidDate()));
				detailsDTO
						.setStudentId(feeDetails.getStudents().getStudentId());
				feeSubmissionDetailsDTOs.add(detailsDTO);
			}
		}
		return feeSubmissionDetailsDTOs;
	}

	@Override
	public CommonDTO createStudentFeeSubmissionDetailsNew(
			StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTOnew) {
		Date date = new Date();
		int monthIds = 0;
		int commonRecieptNo = 0;
		int receiptNo = 0;
		int lfNo = 0;
		StudentFeeSubmissionDetails commonReciept = dao
				.getLastCommonRecieptNo();
		if (commonReciept == null) {
			commonRecieptNo = 1;
		} else {
			commonRecieptNo = commonReciept.getCommonRecieptNo() + 1;
		}
		for (StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO : studentFeeSubmissionDetailsDTOnew
				.getFeeSubmissionDetailsDTOs()) {
			if (studentFeeSubmissionDetailsDTO.getStudentAdmissionNo() != null
					&& !studentFeeSubmissionDetailsDTO.getStudentAdmissionNo()
							.equals("")) {
				StudentFeeSubmissionDetails details = dao.getLfNo();
				if (details == null) {
					receiptNo = 50501;

				} else {
					receiptNo = details.getRecieptNo() + 1;
				}
				Students students = admissionDao
						.get(studentFeeSubmissionDetailsDTO
								.getStudentAdmissionNo());
				Fine fineEntity = fineDao.getFineByName("Late Fee Fine", students.getSession().getSessionId());
				/* lfNo = students.getLfNo(); */
				// Student detail
				StudentEntityToDTO studentEntityToDTO = StudentEntityToDTO
						.getInstance();
				List<StudentFeeSubmissionDetails> submissionDetails = new ArrayList<StudentFeeSubmissionDetails>();

				for (int monthId : studentFeeSubmissionDetailsDTO.getMonthsId()) {
					monthIds = monthId;
					StudentFeeSubmissionDetails studentFeeSubmissionDetails = new StudentFeeSubmissionDetails();
					studentFeeSubmissionDetails
							.setCommonRecieptNo(commonRecieptNo);
					if (studentFeeSubmissionDetailsDTO.getPaidBy().equals(
							"cheque")) {
						studentFeeSubmissionDetails.setPaidBy("cheque");
						studentFeeSubmissionDetails
								.setChequeNo(studentFeeSubmissionDetailsDTO
										.getChequeNo());
						studentFeeSubmissionDetails
								.setFeePaidStatus("inprogress");
						studentFeeSubmissionDetails
								.setBankName(studentFeeSubmissionDetailsDTO
										.getBankName());
					} else {
						studentFeeSubmissionDetails
								.setPaidBy(studentFeeSubmissionDetailsDTO
										.getPaidBy());
						studentFeeSubmissionDetails
								.setFeePaidStatus("completed");
					}
					studentFeeSubmissionDetails.setFeePaidDate(new Date());

					Month month = monthDao.get(monthId);
					studentFeeSubmissionDetails.setSession(students
							.getSession());
					studentFeeSubmissionDetails
							.setPaidAmountInWord(studentFeeSubmissionDetailsDTOnew
									.getPaidAmountInWord());

					studentFeeSubmissionDetails.setlFNo(lfNo);
					studentFeeSubmissionDetails.setRecieptNo(receiptNo);
					studentFeeSubmissionDetails.setStudentClasses(students
							.getClasses());
					studentFeeSubmissionDetails.setSection(students
							.getSection());
					studentFeeSubmissionDetails.setStudents(students);
					studentFeeSubmissionDetails.setMonth(month);
					studentFeeSubmissionDetails.setPaidAmount(Float
							.parseFloat(studentFeeSubmissionDetailsDTO
									.getRecentPaidAmount()));
					dao.create(studentFeeSubmissionDetails);
					submissionDetails.add(studentFeeSubmissionDetails);

					// Discount Amount Save
					Discount discountdetails = new Discount();
					discountdetails.setActive(true);
					if (studentFeeSubmissionDetailsDTO.getDiscount().equals("")) {
						discountdetails.setDiscountAmount(Double
								.parseDouble("0"));
					} else {
						discountdetails.setDiscountAmount(Double
								.parseDouble(studentFeeSubmissionDetailsDTO
										.getDiscount()));
					}
					discountdetails
							.setStudentFeeSubmissionDetails(studentFeeSubmissionDetails);
					discountdetails.setStudents(students);
					discountdetails.setCratedAt(date);
					discountDao.create(discountdetails);

					// Get Last Date and calculate the fine
					LastDate lastDate = lastDateDao.getLastDatebyMonth(monthId,
							students.getSession().getSessionId());
					Date dueDate = lastDate.getLastdate();
					Date currentDate = new Date();
					int fine = 0;
					long diff = currentDate.getTime() - dueDate.getTime();
					long differDays = TimeUnit.DAYS.convert(diff,
							TimeUnit.MILLISECONDS);
					
					if (differDays > 0) {

						if (fineEntity == null) {
							fine = 0;
						} else {
							fine = (int) (differDays * fineEntity.getFineAmount());
							if (fine > fineEntity.getMaxFineAmount()) {
								fine = fineEntity.getMaxFineAmount();
							}
						}
					}
					StudentFine studentFine = new StudentFine();
					studentFine.setCreatedAt(new Date());
					studentFine.setFineAmount(String
							.valueOf(studentFeeSubmissionDetailsDTO
									.getFineAmount()));
					studentFine
							.setStudentFeeSubmissionDetails(studentFeeSubmissionDetails);
					studentFine.setFineName("Fine");
					studentFine.setStudentClasses(students.getClasses());
					studentFine.setSection(students.getSection());
					studentFine.setStudents(students);
					studentFine.setPaid(true);
					studentFineDao.create(studentFine);

				}
				// Get due Amount and alert student that amount for next month
				if (Double.parseDouble(studentFeeSubmissionDetailsDTO
						.getDueAmount()) > 0) {
					StudentWiseFee changeStudentWiseFee = new StudentWiseFee();
					changeStudentWiseFee.setActive(true);
					changeStudentWiseFee.setFeeAmount(Double
							.parseDouble(studentFeeSubmissionDetailsDTO
									.getNetPayableAmount())
							- Double.parseDouble(studentFeeSubmissionDetailsDTO
									.getRecentPaidAmount()));
					changeStudentWiseFee.setFeesCategories(feescategoriesDao
							.get((13)));
					changeStudentWiseFee.setMonth(monthDao.get(monthIds + 1));
					changeStudentWiseFee.setSession(students.getSession());
					changeStudentWiseFee.setClasses(students.getClasses());
					changeStudentWiseFee.setSection(students.getSection());
					changeStudentWiseFee.setStudents(studentsAdmissionDao
							.get(studentFeeSubmissionDetailsDTO
									.getStudentAdmissionNo()));
					studentWiseFeeDao.create(changeStudentWiseFee);
				}
			}
		}

		return getDataForCommonReciept(commonRecieptNo);
	}

	private CommonDTO getDataForCommonReciept(int commonRecieptNo) {
		Double GrossAmount = 0.0;
		Double discountAmount = 0.0;
		Double paidAmount = 0.0;
		Double dueAmount = 0.0;
		Double fineAmount = 0.0;
		String paidDate = "";
		Set<String> set = new HashSet<String>();
		CommonDTO commonDTO = new CommonDTO();
		List<FeesCategoriesDTO> feesCategoriesDTOs = new ArrayList<FeesCategoriesDTO>();
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		StudentFeeSubmissionDetailsDTO studentFeeSubmissionDetailsDTO = new StudentFeeSubmissionDetailsDTO();
		StudentFeeSubmissionDetails studentFeeSubmissionDetails = new StudentFeeSubmissionDetails();
		int lastMonthId = 0;
		for (StudentFeeSubmissionDetails feeSubmissionDetails : dao
				.getByCommonRecieptNoAndStudentAdd(commonRecieptNo)) {
			lastMonthId = feeSubmissionDetails.getMonth().getMonthId();
			StudentFine studentFine = studentFineDao
					.getStudentFine(feeSubmissionDetails
							.getStudentFeeSubmissionDetailsId());
			if (studentFine != null) {
				fineAmount = fineAmount
						+ Double.parseDouble(studentFine.getFineAmount());
			}
		}

		for (String studentAddmissionNo : dao
				.getByCommonRecieptNo(commonRecieptNo)) {
			Students students = studentsAdmissionDao.get(studentAddmissionNo);
			StudentEntityToDTO studentEntityToDTO = StudentEntityToDTO
					.getInstance();
			studentDTOs.add(studentEntityToDTO.getConvertEntityToDTO(students));
			// get studentFeeSubmissionDetails
			studentFeeSubmissionDetails = dao.getByCommonRecieptNoStudentAddNo(
					commonRecieptNo, studentAddmissionNo);
			studentFeeSubmissionDetailsDTO = StudentFeeSubmissionDetailsEntityToDTO
					.getInstance().convertEntityToDTO(
							studentFeeSubmissionDetails);
			paidDate = new SimpleDateFormat("dd-MM-yyyy")
					.format(studentFeeSubmissionDetails.getFeePaidDate());
			paidAmount = paidAmount
					+ Double.parseDouble(String
							.valueOf(studentFeeSubmissionDetails
									.getPaidAmount()));
			StudentWiseFee stufee = studentWiseFeeDao.getDues(students
					.getSession().getSessionId(), students.getClasses()
					.getClassId(), students.getSection().getSectionId(),
					lastMonthId + 1, students.getStudentAdmissionNo());
			if (stufee != null) {
				dueAmount = dueAmount + stufee.getFeeAmount();
			}
			Discount discount = discountDao
					.getDiscountbyFeeSubId(studentFeeSubmissionDetails
							.getStudentFeeSubmissionDetailsId());
			if (discount != null) {
				discountAmount = discountAmount
						+ discount.getDiscountAmount();
			}
		}

		for (FeesCategories feescatg : feescategoriesDao
				.findAllByIsActiveTrue(FeesCategories.class)) {
			String fineType = "Fine";
			FeesCategoriesDTO feesCategoriesDTO = new FeesCategoriesDTO();
			feesCategoriesDTO.setFeeCategoryName(feescatg.getFeeCategoryName());
			double feecategoryamount = 0.0;
			for (String studentAddmissionNo : dao
					.getByCommonRecieptNo(commonRecieptNo)) {
				Students students = studentsAdmissionDao
						.get(studentAddmissionNo);

				FeesCategoriesDTO feescatgdto = new FeesCategoriesDTO();
				if (feescatg.getFeeCategoryName().equals(fineType))
					feescatgdto.setFeeCategoryName(feescatg
							.getFeeCategoryName());
				for (int monthId : dao.getByCommonRecieptNoAndStudentAddNo(
						commonRecieptNo, studentAddmissionNo)) {
					Month month = monthDao.get(monthId);
					set.add(month.getMonthName());
					for (ClassWiseFee classWiseFees : classWiseFeeDao
							.getFeeAllotement(students.getSession()
									.getSessionId(), students.getClasses()
									.getClassId(), monthId)) {
						if (classWiseFees != null) {
							if (feescatg.getFeeCategoryName() == classWiseFees
									.getFeesCategories().getFeeCategoryName()) {
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												students.getSession()
														.getSessionId(),
												monthId,
												classWiseFees
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(studentAddmissionNo));
								if (editFeeAmount != null) {
									double editedAmount = classWiseFees
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									feecategoryamount += editedAmount;
								} else {
									feecategoryamount += classWiseFees
											.getFeeAmount();
								}

							}
						}

					}
					for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
							.getFeeAllotement(students.getSession()
									.getSessionId(), students.getClasses()
									.getClassId(), students.getSection()
									.getSectionId(), monthId)) {
						if (sectionWiseFee != null) {
							if (feescatg.getFeeCategoryName() == sectionWiseFee
									.getFeesCategories().getFeeCategoryName()) {
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												students.getSession()
														.getSessionId(),
												monthId,
												sectionWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(studentAddmissionNo));
								if (editFeeAmount != null) {
									double editedAmount = sectionWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									feecategoryamount += editedAmount;
								} else {
									feecategoryamount += sectionWiseFee
											.getFeeAmount();
								}
							}
						}
					}
					for (StudentWiseFee studentWiseFee : studentWiseFeeDao
							.getFeeAllotement(students.getSession()
									.getSessionId(), students.getClasses()
									.getClassId(), students.getSection()
									.getSectionId(), monthId,
									studentAddmissionNo)) {
						if (studentWiseFee != null) {
							if (feescatg.getFeeCategoryName() == studentWiseFee
									.getFeesCategories().getFeeCategoryName()) {
								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												students.getSession()
														.getSessionId(),
												monthId,
												studentWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(studentAddmissionNo));
								if (editFeeAmount != null) {
									double editedAmount = studentWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									feecategoryamount += editedAmount;
								} else {
									feecategoryamount += studentWiseFee
											.getFeeAmount();
								}
							}
						}
					}

				}
				if (feescatg.getFeeCategoryName().equalsIgnoreCase("Fine")) {
					// Student Fine
					feecategoryamount = fineAmount;
				}
				// due for every students
				studentFeeSubmissionDetails = dao
						.getByCommonRecieptNoStudentAddNo(commonRecieptNo,
								studentAddmissionNo);

				// student Discount
				

			}
			GrossAmount = GrossAmount + feecategoryamount;
			if (feecategoryamount == 0.0 || feecategoryamount == 0)
				feesCategoriesDTO.setAmounts("");
			else
				feesCategoriesDTO.setAmounts(String.valueOf(feecategoryamount));
			feesCategoriesDTOs.add(feesCategoriesDTO);
		}

		String months = "";
		commonDTO.setStudentDTOs(studentDTOs);
		commonDTO.setFeecategoriesDTOs(feesCategoriesDTOs);
		commonDTO.setFineAmount(String.valueOf(fineAmount));
		commonDTO.setDiscount(String.valueOf(discountAmount));
		commonDTO.setPaidAmount(String.valueOf(paidAmount));
		commonDTO.setTotalFee(Float.parseFloat(String.valueOf(GrossAmount)));
		commonDTO.setDue(String.valueOf(dueAmount));
		commonDTO.setLastDate(paidDate);
		for (String monthName : set) {
			months = months + " " + monthName;
		}
		commonDTO.setMonths(months);
		commonDTO.setReceiptNo(commonRecieptNo);
		commonDTO
				.setStudentFeeSubmissionDetailsDTO(studentFeeSubmissionDetailsDTO);
		return commonDTO;
	}

	@Override
	public List<StudentFeeSubmissionDetailsDTO> getallfeesubmissiondetailsByCommonSlipNo(
			int slipNo) {
		List<Integer> integers=dao.getallfeesubmissiondetailsLfnoByCommonSlipNo(slipNo);
		return getData(integers);
	}


		@Override
		public List<StudentFeeSubmissionDetailsDTO> getBySessionId(
				Integer sessionId) {
			List<StudentFeeSubmissionDetailsDTO> studentFeeSubmissionDetailsDTOs = new ArrayList<StudentFeeSubmissionDetailsDTO>();
			List<StudentFeeSubmissionDetails> studentFeeSubmissionDetailsList = dao
					.getBySessionId(sessionId);
			StudentFeeSubmissionDetailsEntityToDTO studentFeeSubmissionDetailsEntityToDTO = StudentFeeSubmissionDetailsEntityToDTO
					.getInstance();
			for (StudentFeeSubmissionDetails studentFeeSubmissionDetails : studentFeeSubmissionDetailsList) {
				studentFeeSubmissionDetailsDTOs
						.add(studentFeeSubmissionDetailsEntityToDTO
								.convertEntityToDTO(studentFeeSubmissionDetails));
			}
			return studentFeeSubmissionDetailsDTOs;
		}
	}
