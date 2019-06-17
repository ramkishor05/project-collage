package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.brijframework.college.commom.convertor.StudentEntityToDTO;
import org.brijframework.college.commom.util.RandomPasswordUtil;
import org.brijframework.college.dao.CityDao;
import org.brijframework.college.dao.ClassWiseFeeDao;
import org.brijframework.college.dao.CountryDao;
import org.brijframework.college.dao.EditFeeAmountDao;
import org.brijframework.college.dao.FineDao;
import org.brijframework.college.dao.LastDateDao;
import org.brijframework.college.dao.LoginRoleDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.dao.SectionWiseFeeDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StateDao;
import org.brijframework.college.dao.StudentCategoryDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.dao.StudentTransferDao;
import org.brijframework.college.dao.StudentWiseFeeDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.UserDao;
import org.brijframework.college.dao.UserRoleDao;
import org.brijframework.college.model.ClassWiseFee;
import org.brijframework.college.model.Country;
import org.brijframework.college.model.EditFeeAmount;
import org.brijframework.college.model.Fine;
import org.brijframework.college.model.LastDate;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.SectionWiseFee;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.State;
import org.brijframework.college.model.StudentAdmission;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.StudentFeeSubmissionDetails;
import org.brijframework.college.model.StudentTransfer;
import org.brijframework.college.model.StudentWiseFee;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.User;
import org.brijframework.college.model.UserRole;
import org.brijframework.college.model.UserRolePrimaryKey;
import org.brijframework.college.model.util.PasswordEncoder;
import org.brijframework.college.models.dto.FeecategoryAmountDTO;
import org.brijframework.college.models.dto.StudentAdmissionDTO;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.service.SessionService;
import org.brijframework.college.service.StudentsAdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentsAdmissionServiceImpl extends
		CRUDServiceImpl<String, Students, StudentsAdmissionDao> implements
		StudentsAdmissionService {

	@Autowired
	public StudentsAdmissionServiceImpl(StudentsAdmissionDao dao) {
		super(dao);
	}

	@Autowired
	LoginRoleDao roleDao;
	@Autowired
	UserDao userDao;
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	SectionDao sectionDao;
	@Autowired
	StudentClassesDao classDao;
	@Autowired
	CountryDao countryDao;
	@Autowired
	StateDao stateDao;
	@Autowired
	CityDao cityDao;
	@Autowired
	StudentCategoryDao studentCategoryDao;
	@Autowired
	SessionDao sessionDao;
	@Autowired
	StudentFeeSubmissionDetailsDao studentfeedao;
	@Autowired
	StudentFeeSubmissionDetailsDao studentFeeSubmissionDetailsDao;
	@Autowired
	ClassWiseFeeDao classWiseFeeDao;
	@Autowired
	SectionWiseFeeDao sectionWiseFeeDao;
	@Autowired
	StudentWiseFeeDao studentWiseFeeDao;
	@Autowired
	MonthDao monthDao;
	@Autowired
	EditFeeAmountDao editFeeAmountDao;
	@Autowired
	SessionService sessionService;
	@Autowired
	LastDateDao lastDateDao;
	@Autowired
	FineDao fineDao;
	@Autowired
	StudentTransferDao studentTransferDao;

	@Override
	public String[] getStudentAdmissionNo() {
		Students students = dao.getAdmissionNo();

		long admno;
		long lfNo;
		if (students == null) {
			admno = 1;
			lfNo = 1;
		} else {
			admno = students.getUniqueId() + 1;

			lfNo = students.getLfNo() + 1;
		}
		Session current = sessionDao.findCurrentSession();
		String year[] = current.getSessionDuration().split("-");

		String adm = String.format("%03d", admno);
		//String id = "MPPS1/" + adm + "/" + year[0].substring(2);
		String id=String.valueOf(admno);
		String studentAdmissionNum[] = { String.valueOf(admno),
				String.valueOf(lfNo), id };

		return studentAdmissionNum;
	}

	@Override
	public int getUniqueIdNo() {
		int studentsno = dao.findAll(Students.class).size();
		int uniqueId;
		if (studentsno == 0) {
			uniqueId = 1;
		} else {
			uniqueId = studentsno + 1;
		}
		return uniqueId;
	}

	@Override
	public void registerStudentDetails(StudentDTO studentDTO)
			throws ParseException {
		String name = studentDTO.getFirstName().trim();
		String admno = studentDTO.getAdmissionNo().trim();

		String userid = name + studentDTO.getStudentId() + studentDTO.getSessionId();
		String num = RandomPasswordUtil.getRandomString();
		String password = PasswordEncoder.getEcodedPassword(num);
		User user = new User();
		user.setUsername(userid);
		user.setPassword(password);
		user.setEnabled(true);
		UserRolePrimaryKey key = new UserRolePrimaryKey();
		key.setRole(roleDao.getUserRoleByName("ROLE_STUDENT"));
		key.setUser(userDao.create(user));
		UserRole role = new UserRole();
		role.setUserRolePrimaryKey(key);
		userRoleDao.create(role);
		Students students = new Students();
		students.setActive(true);
		students.setUser(user);
		students.setLfNo(Integer.parseInt(studentDTO.getLfno()));
		students.setStudentAdmissionNo(String.valueOf(getUniqueIdNo()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
		if (studentDTO.getAdmissionDate() != null)
			students.setAdmissionDate(dateFormat.parse(studentDTO
					.getAdmissionDate()));
		students.setStudentId(studentDTO.getStudentId());
		students.setUniqueId(Integer.parseInt(admno));
		students.setFirstName(studentDTO.getFirstName());
		students.setSrno(studentDTO.getSrno());
		students.setMiddleName(studentDTO.getMiddleName());
		students.setLastName(studentDTO.getLastName());
		students.setDateOfBirth(df.parse(studentDTO.getDateOfBirth()));
		students.setAddressLine1(studentDTO.getAddressLine1());
		students.setClasses(classDao.get(studentDTO.getClassId()));
		students.setSession(sessionDao.get(studentDTO.getSessionId()));
		students.setSection(sectionDao.get(studentDTO.getSectionId()));
        students.setAdmitClassId(classDao.get(studentDTO.getClassId()));
		students.setPassword(num);
		students.setRollNo(Integer.parseInt(studentDTO.getRollno()));
		students.setCreatedAt(new Date());
		students.setEmail(studentDTO.getEmail());
		students.setFatherName(studentDTO.getFatherName());
		students.setGender(studentDTO.getGender());
		students.setPhysicallyChallenged(studentDTO.getPhysicallyChallenged());
		students.setMobile(studentDTO.getMobile());
		students.setMotherName(studentDTO.getMotherName());
		students.setNationality(studentDTO.getNationality());
		students.setGuardianName(studentDTO.getGuardianName());
		students.setPhone(studentDTO.getPhone());
		students.setPinCode(studentDTO.getPinCode());
		students.setSchoolAddress(studentDTO.getSchoolAddress());
		students.setSchoolName(studentDTO.getSchoolName());
		students.setSchoolPhone(studentDTO.getSchoolPhone());
		if (studentDTO.getCityId() > 0) {
			students.setCity(cityDao.get(studentDTO.getCityId()));

		} else
			students.setCity(null);
		if (studentDTO.getCountryId() > 0) {
			students.setCountry(countryDao.get(studentDTO.getCountryId()));
		} else
			students.setCountry(null);
		if (studentDTO.getStateId() > 0)
			students.setState(stateDao.get(studentDTO.getStateId()));
		else
			students.setState(null);

		/*
		 * if (studentDTO.getReligionName().equals(null)) { String r = "";
		 * students.setReligion(r); } else
		 */
		students.setReligion(studentDTO.getReligionName());
		if (studentDTO.getStudentCategoryId() > 0)
			students.setStudentCategory(studentCategoryDao.get(studentDTO
					.getStudentCategoryId()));
		else
			students.setStudentCategory(null);

		if (studentDTO.getPhoto().getSize() > 0) {
			students.setPhotoFileName(studentDTO.getPhoto()
					.getOriginalFilename());
		} else {
			students.setPhotoFileName("defultimage.png");
		}

		if (studentDTO.getParentphoto1().getSize() > 0) {
			students.setPhoto1FileName(studentDTO.getParentphoto1()
					.getOriginalFilename());
		} else {
			students.setPhoto1FileName("parent.jpg");
		}

		if (studentDTO.getParentphoto2().getSize() > 0) {
			students.setPhoto2FileName(studentDTO.getParentphoto2()
					.getOriginalFilename());
		} else {
			students.setPhoto2FileName("father.jpg");
		}

		if (studentDTO.getParentphoto3().getSize() > 0) {
			students.setPhoto3FileName(studentDTO.getParentphoto3()
					.getOriginalFilename());
		} else {
			students.setPhoto3FileName("kids.jpg");
		}

		dao.create(students);

	}

	@Override
	public List<StudentDTO> getStudentListById(int courseId, int branchId,
			int batchId) {
		StudentEntityToDTO entityToDTO = new StudentEntityToDTO();
		return entityToDTO.getStudentsListFromStudentEntityToDto(dao
				.getStudentListById(courseId, branchId, batchId));
	}

	public List<StudentDTO> getStudentsbyFirstname(String firstName) {
		List<Students> list = dao.getStudent(firstName);

		List<StudentDTO> lists = new ArrayList<StudentDTO>();

		for (Students students : list) {
			StudentDTO dto = new StudentDTO();
			dto.setFirstName(students.getFirstName());
			dto.setMiddleName(students.getMiddleName());
			dto.setLastName(students.getLastName());
			dto.setAdmissionNo(students.getStudentAdmissionNo());
			dto.setFatherName(students.getFatherName());
			dto.setClassName(students.getClasses().getClassName());
			dto.setSectionName(students.getSection().getSectionName());
			dto.setMotherName(students.getMotherName());
			lists.add(dto);
		}

		return lists;

	}

	public List<StudentDTO> getStudentsByClassIdAndName(int classId,
			String firstName, int sessionId) {
		List<Students> list = dao.getStudentByClassIdAndName(classId,
				firstName, sessionId);

		List<StudentDTO> lists = new ArrayList<StudentDTO>();

		for (Students students : list) {
			StudentDTO dto = new StudentDTO();
			dto.setFirstName(students.getFirstName());
			dto.setMiddleName(students.getMiddleName());
			dto.setLastName(students.getLastName());
			dto.setAdmissionNo(students.getStudentAdmissionNo());
			dto.setFatherName(students.getFatherName());
			dto.setClassName(students.getClasses().getClassName());
			dto.setSectionName(students.getSection().getSectionName());
			dto.setMotherName(students.getMotherName());
			lists.add(dto);
		}

		return lists;
	}

	@Override
	@Transactional
	public StudentDTO findStudentDetails(String id) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MMM-dd");
		Students students = dao.getActiveStudentById(id);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setAdmissionNo(id);
		/*
		 * if (students.isActive() == true) studentDTO.setActive("true"); else
		 * studentDTO.setActive("false");
		 */
		studentDTO.setFirstName(students.getFirstName());
		studentDTO.setAdmitclass(students.getAdmitClassId().getClassName());
		studentDTO.setFullName(students.getFirstName()+" "+students.getMiddleName()+" "+students.getLastName());
		studentDTO.setMiddleName(students.getMiddleName());
		studentDTO.setLastName(students.getLastName());
		studentDTO.setAddressLine1(students.getAddressLine1());
		studentDTO.setAddressLine2(students.getAddressLine2());
		studentDTO.setAdmissionDate(df.format(students.getAdmissionDate()));
		studentDTO.setSessionId(students.getSession().getSessionId());
		if (students.getCity() != null) {
			studentDTO.setCity(students.getCity().getCityName());
			studentDTO.setCityId(students.getCity().getCityId());
		}
		if (students.getCountry() != null) {
			Country country = students.getCountry();
			studentDTO.setCountryId(country.getCountryId());
			studentDTO.setCountry(country.getCountryName());
		}
		if (students.getState() != null) {
			State state = students.getState();
			studentDTO.setStateId(state.getStateId());
			studentDTO.setState(state.getStateName());
		}
		studentDTO.setSrno(students.getSrno());
		studentDTO.setSessionDuration(students.getSession()
				.getSessionDuration());
		studentDTO.setDateOfBirth(df1.format(students.getDateOfBirth()));
		studentDTO.setEmail(students.getEmail());
		studentDTO.setFatherName(students.getFatherName());
		studentDTO.setGender(students.getGender());
		studentDTO.setPhysicallyChallenged(students.getPhysicallyChallenged());
		studentDTO.setMobile(students.getMobile());
		studentDTO.setMotherName(students.getMotherName());
		studentDTO.setNationality(students.getNationality());
		studentDTO.setPhone(students.getPhone());
		studentDTO.setPinCode(String.valueOf(students.getPinCode()));
		studentDTO.setLfno(String.valueOf(students.getLfNo()));
		studentDTO.setImageName(students.getPhotoFileName());
		studentDTO.setReligionName(students.getReligion());
		studentDTO.setSectionName(students.getSection().getSectionName());

		if (students.getStudentCategory() != null) {
			studentDTO.setStudentCategoryId(students.getStudentCategory()
					.getStudentCategoriesId());
			studentDTO.setCategoryName(students.getStudentCategory()
					.getStudentCategoriesName());
		}
		studentDTO.setUpdatedAt(students.getUpdatedAt());
		StudentClasses studentClasses = students.getClasses();
		studentDTO.setClassId(studentClasses.getClassId());
		studentDTO.setClassName(studentClasses.getClassName());
		Section section = students.getSection();
		studentDTO.setSectionId(section.getSectionId());
		studentDTO.setSectionName(section.getSectionName());
		studentDTO.setRollno(String.valueOf(students.getRollNo()));
		studentDTO.setPhoto1name(students.getPhoto1FileName());
		studentDTO.setPhoto2name(students.getPhoto2FileName());
		studentDTO.setPhoto3name(students.getPhoto3FileName());
		studentDTO.setPassword(students.getPassword());
		studentDTO.setStudentId(students.getStudentId());
		studentDTO.setGuardianName(students.getGuardianName());
		studentDTO.setSchoolAddress(students.getSchoolAddress());
		studentDTO.setSchoolName(students.getSchoolName());
		studentDTO.setSchoolPhone(students.getSchoolPhone());
		return studentDTO;

	}

	public StudentDTO findInActiveStudentDetails(String id) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MMM-dd");
		Students students = dao.getInActiveStudentById(id);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setAdmissionNo(id);
		/*
		 * if (students.isActive() == true) studentDTO.setActive("true"); else
		 * studentDTO.setActive("false");
		 */
		studentDTO.setFirstName(students.getFirstName());
		studentDTO.setMiddleName(students.getMiddleName());
		studentDTO.setLastName(students.getLastName());
		studentDTO.setAddressLine1(students.getAddressLine1());
		studentDTO.setAddressLine2(students.getAddressLine2());
		studentDTO.setAdmissionDate(df.format(students.getAdmissionDate()));
		studentDTO.setSessionId(students.getSession().getSessionId());
		if (students.getCity() != null) {
			studentDTO.setCity(students.getCity().getCityName());
			studentDTO.setCityId(students.getCity().getCityId());
		}
		if (students.getCountry() != null) {
			Country country = students.getCountry();
			studentDTO.setCountryId(country.getCountryId());
			studentDTO.setCountry(country.getCountryName());
		}
		if (students.getState() != null) {
			State state = students.getState();
			studentDTO.setStateId(state.getStateId());
			studentDTO.setState(state.getStateName());
		}
		studentDTO.setSessionDuration(students.getSession()
				.getSessionDuration());
		studentDTO.setDateOfBirth(df1.format(students.getDateOfBirth()));
		studentDTO.setEmail(students.getEmail());
		studentDTO.setFatherName(students.getFatherName());
		studentDTO.setGender(students.getGender());
		studentDTO.setPhysicallyChallenged(students.getPhysicallyChallenged());
		studentDTO.setMobile(students.getMobile());
		studentDTO.setMotherName(students.getMotherName());
		studentDTO.setNationality(students.getNationality());
		studentDTO.setPhone(students.getPhone());
		studentDTO.setPinCode(String.valueOf(students.getPinCode()));
		studentDTO.setLfno(String.valueOf(students.getLfNo()));
		studentDTO.setImageName(students.getPhotoFileName());
		studentDTO.setReligionName(students.getReligion());
		studentDTO.setSectionName(students.getSection().getSectionName());
		studentDTO.setSrno(students.getSrno());
		if (students.getStudentCategory() != null) {
			studentDTO.setStudentCategoryId(students.getStudentCategory()
					.getStudentCategoriesId());
			studentDTO.setCategoryName(students.getStudentCategory()
					.getStudentCategoriesName());
		}
		studentDTO.setUpdatedAt(students.getUpdatedAt());
		StudentClasses studentClasses = students.getClasses();
		studentDTO.setClassId(studentClasses.getClassId());
		studentDTO.setClassName(studentClasses.getClassName());
		Section section = students.getSection();
		studentDTO.setSectionId(section.getSectionId());
		studentDTO.setSectionName(section.getSectionName());
		studentDTO.setRollno(String.valueOf(students.getRollNo()));
		studentDTO.setPhoto1name(students.getPhoto1FileName());
		studentDTO.setPhoto2name(students.getPhoto2FileName());
		studentDTO.setPhoto3name(students.getPhoto3FileName());
		studentDTO.setPassword(students.getPassword());
		studentDTO.setStudentId(students.getStudentId());
		studentDTO.setGuardianName(students.getGuardianName());
		studentDTO.setSchoolAddress(students.getSchoolAddress());
		studentDTO.setSchoolName(students.getSchoolName());
		studentDTO.setSchoolPhone(students.getSchoolPhone());
		return studentDTO;

	}

	@Override
	@Transactional
	public void updateStudentDetails(StudentDTO studentDTO)
			throws ParseException {
		Date date = new Date();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		Students students = dao.get(studentDTO.getAdmissionNo());
		students.setActive(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
		students.setAdmissionDate(dateFormat.parse(studentDTO
				.getAdmissionDate()));
        students.setSrno(studentDTO.getSrno());
		students.setFirstName(studentDTO.getFirstName());
		students.setMiddleName(studentDTO.getMiddleName());
		students.setLastName(studentDTO.getLastName());
		students.setDateOfBirth(df.parse(studentDTO.getDateOfBirth()));
		students.setAddressLine1(studentDTO.getAddressLine1());
		students.setSession(sessionDao.get(studentDTO.getSessionId()));
		// students.setCity(cityDao.get(studentDTO.getCityId()));
		// students.setCountry(countryDao.get(studentDTO.getCountryId()));
		students.setClasses(classDao.get(studentDTO.getClassId()));
		students.setSection(sectionDao.get(studentDTO.getSectionId()));
		students.setEmail(studentDTO.getEmail());
		students.setFatherName(studentDTO.getFatherName());
		students.setGender(studentDTO.getGender());
		students.setPhysicallyChallenged(studentDTO.getPhysicallyChallenged());
		students.setMobile(studentDTO.getMobile());
		students.setMotherName(studentDTO.getMotherName());
		students.setNationality(studentDTO.getNationality());
		students.setSchoolAddress(studentDTO.getSchoolAddress());
		students.setSchoolName(studentDTO.getSchoolName());
		students.setSchoolPhone(studentDTO.getSchoolPhone());
		students.setPhone(studentDTO.getPhone());
		students.setLfNo(Integer.parseInt(studentDTO.getLfno()));
		students.setGuardianName(studentDTO.getGuardianName());
		students.setPinCode(studentDTO.getPinCode());
		if (studentDTO.getReligionName().equals(null)) {
			String r = "";
			students.setReligion(r);
		} else
			students.setReligion(studentDTO.getReligionName());
		if (studentDTO.getStudentCategoryId() > 0)
			students.setStudentCategory(studentCategoryDao.get(studentDTO
					.getStudentCategoryId()));
		else
			students.setStudentCategory(null);
		/*
		 * students.setReligion(studentDTO.getReligionName());
		 * students.setStudentCategory(studentCategoryDao.get(studentDTO
		 * .getStudentCategoryId()));
		 */
		// students.setState(stateDao.get(studentDTO.getStateId()));
		if (studentDTO.getCityId() > 0) {
			students.setCity(cityDao.get(studentDTO.getCityId()));

		} else
			students.setCity(null);
		if (studentDTO.getCountryId() > 0) {
			students.setCountry(countryDao.get(studentDTO.getCountryId()));
		} else
			students.setCountry(null);
		if (studentDTO.getStateId() > 0)
			students.setState(stateDao.get(studentDTO.getStateId()));
		else
			students.setState(null);
		students.setUpdatedAt(date2);
		students.setRollNo(Integer.parseInt(studentDTO.getRollno()));
		if (studentDTO.getPhoto().getSize() > 0
				&& studentDTO.getPhoto() != null) {
			students.setPhotoFileName(studentDTO.getPhoto()
					.getOriginalFilename());
		} /*
		 * else { students.setPhotoFileName(studentDTO.getImageName()); }
		 */

		if (studentDTO.getParentphoto1().getSize() > 0) {
			students.setPhoto1FileName(studentDTO.getParentphoto1()
					.getOriginalFilename());
		} /*
		 * else { students.setPhoto1FileName(studentDTO.getPhoto1name()); }
		 */

		if (studentDTO.getParentphoto2().getSize() > 0) {
			students.setPhoto2FileName(studentDTO.getParentphoto2()
					.getOriginalFilename());
		} /*
		 * else { students.setPhoto2FileName(studentDTO.getPhoto2name()); }
		 */

		if (studentDTO.getParentphoto3().getSize() > 0) {
			students.setPhoto3FileName(studentDTO.getParentphoto3()
					.getOriginalFilename());
		} /*
		 * else { students.setPhoto3FileName(studentDTO.getPhoto3name()); }
		 */

	}

	@Override
	public void setActiveById(String id) {
		dao.get(id).setActive(false);
		dao.get(id).setCancellationDate(new Date());
		

	}

	public StudentDTO getStudentId(int id) {
		StudentEntityToDTO studentEntityToDTO = new StudentEntityToDTO();
		return studentEntityToDTO.getConvertEntityToDTO(dao
				.getActiveStudentById(String.valueOf(id)));
	}

	@Override
	public List<StudentDTO> getStudentandSubjectsListById(int courseId,
			int branchId, int batchId) {
		StudentDTO studentdto = new StudentDTO();
		StudentEntityToDTO entityToDTO = new StudentEntityToDTO();
		List<StudentDTO> dto = entityToDTO
				.getStudentsListFromStudentEntityToDto(dao.getStudentListById(
						courseId, branchId, batchId));
		dto.add(studentdto);
		return dto;

	}

	@Override
	public List<StudentDTO> getStudentsbyClassandSection(int classId,
			int sectionId, int sessionId) {
		List<Students> list = dao.getStudentsbyClassSectionId(classId,
				sectionId, sessionId);
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		StudentEntityToDTO studentEntityToDTO = StudentEntityToDTO
				.getInstance();
		for (Students students : list) {
			lists.add(studentEntityToDTO.getConvertEntityToDTO(students));
		}
		return lists;

	}

	@Override
	public List<StudentDTO> getStudentsbyFathername(String fatherName,
			int sessionId) {
		List<Students> list = dao.getStudentbyFatherName(fatherName, sessionId);
		List<StudentDTO> lists = new ArrayList<StudentDTO>();

		for (Students students : list) {
			StudentDTO dto = new StudentDTO();
			dto.setFirstName(students.getFirstName());
			dto.setMiddleName(students.getMiddleName());
			dto.setLastName(students.getLastName());
			dto.setAdmissionNo(students.getStudentAdmissionNo());
			dto.setFatherName(students.getFatherName());
			dto.setClassName(students.getClasses().getClassName());
			dto.setSectionName(students.getSection().getSectionName());
			dto.setMotherName(students.getMotherName());
			lists.add(dto);
		}

		return lists;

	}

	@Override
	public List<StudentDTO> getCancelledStudentsbySession(int sessionId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<Students> listcancelled = dao.getCancelledList(sessionId);
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		for (Students cancel : listcancelled) {
			StudentDTO dto = new StudentDTO();
			dto.setAdmissionNo(cancel.getStudentAdmissionNo());
			dto.setFirstName(cancel.getFirstName());
			dto.setMiddleName(cancel.getMiddleName());
			dto.setLastName(cancel.getLastName());
			dto.setFatherName(cancel.getFatherName());
			dto.setMotherName(cancel.getMotherName());
			dto.setClassName(classDao.get(cancel.getClasses().getClassId())
					.getClassName());
			dto.setSectionName(sectionDao.get(
					cancel.getSection().getSectionId()).getSectionName());
			dto.setAdmissionDate(df.format(cancel.getAdmissionDate()));
			dto.setCancellationDate(df.format(cancel.getCancellationDate()));
			if (cancel.getStudentId() == null) {
				dto.setStudentId("-");
			} else {
				dto.setStudentId(cancel.getStudentId());
			}
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<StudentDTO> getStudentsbyClassandSectionfeecheck(int classId,
			int sectionId, int sessionId) {
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine", sessionId);
		Boolean flag = true;
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

		if (currentMonth > 3) {
			currentMonth = currentMonth - 3;
		} else {
			currentMonth = currentMonth + 9;
		}

		for (int i = 1; i <= currentMonth; i++) {
			LastDate lastDates = lastDateDao.getLastDatebyMonth(i, sessionId);
			if (lastDates == null) {
				StudentDTO dto = new StudentDTO();
				dto.setCategoryName("Nothing");
				lists.add(dto);
				flag = false;
				break;
			}
		}
		if (flag == true) {
			List<Students> list = dao.getStudentsbyClassSectionId(classId,
					sectionId, sessionId);
			for (Students students : list) {
				double submittedfees = 0;
				double allotedfees = 0;
				StudentDTO dto = new StudentDTO();
				for (int i = 1; i <= currentMonth; i++) {

					LastDate lastDate = lastDateDao.getLastDatebyMonth(i,
							sessionId);
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
							fine = (int) (differDays * fineEntity
									.getFineAmount());
							if (fine > fineEntity.getMaxFineAmount()) {
								fine = fineEntity.getMaxFineAmount();
							}
						}
					}
					allotedfees += fine;
					if (fine > 0 || differDays > 0) {
						List<FeecategoryAmountDTO> feecategoryAmountDTOs = new ArrayList<FeecategoryAmountDTO>();
						List<ClassWiseFee> classWiseFees = classWiseFeeDao
								.getFeeAllotement(sessionId, classId, i);
						for (ClassWiseFee classWiseFee : classWiseFees) {
							if (classWiseFees != null) {
								FeecategoryAmountDTO feecategoryAmountDTO = new FeecategoryAmountDTO();
								feecategoryAmountDTO
										.setFeeCategoryName(classWiseFee
												.getFeesCategories()
												.getFeeCategoryName());
								feecategoryAmountDTO
										.setFeeCatgoryAmount(classWiseFee
												.getFeeAmount());
								feecategoryAmountDTOs.add(feecategoryAmountDTO);

								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												sessionId,
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
									allotedfees += classWiseFee.getFeeAmount();
								}

							}
						}
						for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
								.getFeeAllotement(sessionId, classId,
										sectionId, i)) {
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
												sessionId,
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
								.getFeeAllotement(sessionId, classId,
										sectionId, i,
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
												sessionId,
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
				List<StudentFeeSubmissionDetails> fee = studentFeeSubmissionDetailsDao
						.getfeedetailsbymonth(sessionId,
								students.getStudentAdmissionNo());
				if (fee != null) {
					Set<Integer> attributes = new HashSet<Integer>();
					List<StudentFeeSubmissionDetails> duplicates = new ArrayList<StudentFeeSubmissionDetails>();

					for (StudentFeeSubmissionDetails studentFeeSubmissionDetailsDTO : fee) {
						if (attributes.contains(studentFeeSubmissionDetailsDTO
								.getRecieptNo())) {
							duplicates.add(studentFeeSubmissionDetailsDTO);
						}
						attributes.add(studentFeeSubmissionDetailsDTO
								.getRecieptNo());
					}
					fee.removeAll(duplicates);
					for (StudentFeeSubmissionDetails sdetails : fee) {
						submittedfees += sdetails.getPaidAmount();

					}
				}
				if (allotedfees <= submittedfees) {

					dto.setDefaulterStatus(0);
				} else {
					dto.setDefaulterStatus(1);
				}
				dto.setFirstName(students.getFirstName());
				dto.setMiddleName(students.getMiddleName());
				dto.setLastName(students.getLastName());
				dto.setFullName(students.getFirstName() + " "
						+ students.getMiddleName() + " "
						+ students.getLastName());
				dto.setAdmissionNo(students.getStudentAdmissionNo());
				dto.setStudentId(students.getStudentId());
				dto.setMobile(students.getMobile());
				dto.setMotherName(students.getMotherName());
				lists.add(dto);
			}
		}
		return lists;

	}

	@Override
	public Boolean transferBatch(FeecategoryAmountDTO feecategoryAmountDTO) {
		boolean flag = false;
		for (Integer integer : feecategoryAmountDTO.getStudentId()) {
			Students std = dao.get(integer.toString());
			if (dao.checkExistStudent(std.getStudentId(),
					feecategoryAmountDTO.getToSession()) == null) {
				int sessionId = std.getSession().getSessionId();
				int classId = std.getClasses().getClassId();
				String userId = std.getFirstName().trim()
						+ std.getStudentId().trim()
						+ feecategoryAmountDTO.getToSession();
				String num = "123456";
				String password = PasswordEncoder.getEcodedPassword(num);
				User user = new User();
				user.setUsername(userId);
				user.setPassword(password);
				user.setEnabled(true);
				UserRolePrimaryKey key = new UserRolePrimaryKey();
				key.setRole(roleDao.getUserRoleByName("ROLE_STUDENT"));
				key.setUser(userDao.create(user));
				UserRole role = new UserRole();
				role.setUserRolePrimaryKey(key);
				userRoleDao.create(role);
				List<Students> stud = dao.getStudentbyclasssession(sessionId,
						classId);
				/*
				 * int lfno = 0;
				 * 
				 * if (stud.isEmpty()) { lfno = 0; } else { lfno =
				 * dao.getMaxLfno(sessionId, classId); }
				 */

				Students students = new Students();
				StudentClasses studentClasses = new StudentClasses();
				studentClasses.setClassId(feecategoryAmountDTO.getToClassId());
				Section section = new Section();
				section.setSectionId(feecategoryAmountDTO.getToSectionId());
				Session session = new Session();
				session.setSessionId(feecategoryAmountDTO.getToSession());
				students.setActive(true);
				students.setLfNo(std.getLfNo());
				students.setRollNo(std.getRollNo());
				students.setUser(user);
				students.setAdmitClassId(std.getAdmitClassId());
				students.setAddressLine1(std.getAddressLine1());
				students.setAdmissionDate(std.getAdmissionDate());
				students.setCancellationDate(std.getCancellationDate());
				students.setCity(cityDao.get(std.getCity().getCityId()));
				students.setCountry(countryDao.get(std.getCountry()
						.getCountryId()));
				students.setCreatedAt(std.getCreatedAt());
				students.setDateOfBirth(std.getDateOfBirth());
				students.setEmail(std.getEmail());
				students.setFatherName(std.getFatherName());
				students.setFirstName(std.getFirstName());
				students.setGender(std.getGender());
				students.setLastName(std.getLastName());
				students.setMiddleName(std.getMiddleName());
				students.setMobile(std.getMobile());
				students.setMotherName(std.getMotherName());
				students.setNationality(std.getNationality());
				students.setPhone(std.getPhone());
				students.setPhotoFileName(std.getPhotoFileName());
				students.setPhysicallyChallenged(std.getPhysicallyChallenged());
				students.setPinCode(std.getPinCode());
				students.setReligion(std.getReligion());
				students.setState(stateDao.get(std.getState().getStateId()));
				students.setStudentAdmissionNo(String.valueOf(getUniqueIdNo()));
				students.setPhoto1FileName(std.getPhoto1FileName());
				students.setPhoto2FileName(std.getPhoto2FileName());
				students.setPhoto3FileName(std.getPhoto3FileName());
				if (std.getStudentCategory() != null)
					students.setStudentCategory(studentCategoryDao.get(std
							.getStudentCategory().getStudentCategoriesId()));
				students.setStudentId(std.getStudentId());
				students.setPassword(num);
				students.setUpdatedAt(new Date());
				students.setClasses(studentClasses);
				students.setSection(section);
				students.setSession(session);
				students.setSrno(std.getSrno());
				dao.create(students);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<StudentDTO> getStudentsByClassId(int classId) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		for (Students student : dao.getStudentsByClassId(classId)) {
			StudentDTO dto = new StudentDTO();
			dto.setAdmissionNo(student.getStudentAdmissionNo());
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<StudentDTO> getStudentsbySessionandClass(int classId,
			int sessionId) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
		for (Students student : dao.getStudentsByClassIdandSession(classId,
				sessionId)) {
			StudentDTO dto = new StudentDTO();
			dto.setAdmissionNo(student.getStudentAdmissionNo());
			dto.setFullName(student.getFirstName() + "-"
					+ String.valueOf(student.getRollNo()));
			dto.setFirstName(student.getFirstName());
			dto.setMiddleName(student.getMiddleName());
			dto.setLastName(student.getLastName());
			dto.setRollno(String.valueOf(student.getRollNo()));
			dto.setSectionName(student.getSection().getSectionName());
			dto.setClassName(student.getClasses().getClassName());
			dto.setAddressLine1(student.getAddressLine1());
			dto.setMobile(student.getMobile());
			dto.setFatherName(student.getFatherName());
			dto.setToday(df.format(new Date()));
			dto.setMotherName(student.getMotherName());
			list.add(dto);
		}

		return list;
	}

	@Override
	public List<StudentDTO> getStudentsbySessionClasspageno(int classId,
			int sessionId, int pageno, int t) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		for (Students student : dao.getStudentsByClassIdandSessionpageno(
				classId, sessionId, pageno)) {
			StudentDTO dto = new StudentDTO();
			dto.setAdmissionNo(student.getStudentAdmissionNo());
			dto.setFirstName(student.getFirstName());
			dto.setMiddleName(student.getMiddleName());
			dto.setLastName(student.getLastName());
			dto.setRollno(String.valueOf(student.getRollNo()));
			dto.setMotherName(student.getMotherName());
			dto.setPassword(student.getPassword());
			dto.setUsername(student.getFirstName() + student.getStudentId()
					+ student.getSession().getSessionId());
			dto.setPageno(pageno + 1);
			dto.setTotalpage(t);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<StudentDTO> getlogindetailsbyname(int classId, int sessionId,
			String firstname) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		for (Students student : dao.getStudentByClassIdAndName(classId,
				firstname, sessionId)) {
			StudentDTO dto = new StudentDTO();
			dto.setAdmissionNo(student.getStudentAdmissionNo());
			dto.setFirstName(student.getFirstName());
			dto.setMiddleName(student.getMiddleName());
			dto.setLastName(student.getLastName());
			dto.setRollno(String.valueOf(student.getRollNo()));
			dto.setMotherName(student.getMotherName());
			dto.setPassword(student.getPassword());
			dto.setUsername(student.getFirstName() + student.getStudentId()
					+ student.getSession().getSessionId());
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<StudentDTO> getStudentsbySessionClassId(int classId,
			Integer sessionId) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();

		/*
		 * for (Students student :
		 * dao.getStudentsByClassIdandSession(classId,sessionId)) { StudentDTO
		 * dto = new StudentDTO();
		 * dto.setAdmissionNo(student.getStudentAdmissionNo());
		 * dto.setFullName(student
		 * .getFirstName()+"-"+String.valueOf(student.getRollNo()));
		 * dto.setFirstName(student.getFirstName());
		 * dto.setMiddleName(student.getMiddleName());
		 * dto.setLastName(student.getLastName());
		 * dto.setRollno(String.valueOf(student.getRollNo()));
		 * dto.setSectionName(student.getSection().getSectionName());
		 * dto.setClassName(student.getClasses().getClassName()); list.add(dto);
		 * }
		 */
		List<Students> list1 = dao.getStudentsByClassIdandSession(classId,
				sessionId);
		List<Students> list2 = dao.getStudentsByClassIdandSession(classId,
				sessionId);
		String firstname = "";
		for (Students student : list1) {
			for (Students student1 : list2) {
				if (student.getStudentAdmissionNo().equals(
						(student1.getStudentAdmissionNo()))) {
				} else {
					if (student.getFirstName().equals(student1.getFirstName())) {
						firstname = student1.getFirstName() + "-"
								+ student.getRollNo();

						break;
					} else {
						firstname = student.getFirstName();

					}
				}
			}
			StudentDTO dto = new StudentDTO();
			dto.setAdmissionNo(student.getStudentAdmissionNo());
			dto.setFirstName(firstname);
			dto.setMiddleName(student.getMiddleName());
			dto.setLastName(student.getLastName());
			dto.setRollno(String.valueOf(student.getRollNo()));
			dto.setSectionName(student.getSection().getSectionName());
			dto.setClassName(student.getClasses().getClassName());
			dto.setMotherName(student.getMotherName());
			dto.setFatherName(student.getFatherName());
			list.add(dto);
		}

		return list;
	}

	@Override
	public StudentDTO getStudentProfile(Integer id) {
		Students student = dao.getStudentbyuserId(id);
		return findStudentDetails(student.getStudentAdmissionNo());
	}

	@Override
	public void updatePasswordbyId(String changeId, String newpassword) {
		Students student = dao.get(changeId);
		String passwords = PasswordEncoder.getEcodedPassword(newpassword);
		User user = student.getUser();
		user.setPassword(passwords);
		student.setPassword(newpassword);
	}

	@Override
	public String getRollNo(String classId, String sectionId) {
		List<Students> students = dao.getStudentsbyClassSectionId(
				Integer.parseInt(classId), Integer.parseInt(sectionId),
				sessionService.findCurrent().getSessionId());
		String rollNo = String.valueOf(students.size() + 1);
		return rollNo;
	}

	@Override
	public StudentDTO findStudentData(String id) {
		Students student = dao.checkExistStudent(id, sessionDao
				.findCurrentSession().getSessionId());
		return findStudentDetails(student.getStudentAdmissionNo());
	}

	@Override
	public List<StudentDTO> getStudentsBySessionClassId(int classId,
			Integer sessionId) {
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		List<Students> list1 = dao.getStudentsByClassIdandSession(classId,
				sessionId);
		StudentEntityToDTO studentEntityToDTO = StudentEntityToDTO
				.getInstance();
		for (Students students : list1) {
			studentDTOs.add(studentEntityToDTO.getConvertEntityToDTO(students));
		}
		return studentDTOs;
	}

	public int getSerialNo() {
		int size = dao.findAll(StudentAdmission.class).size();
		int sNo;
		if (size == 0) {
			sNo = 1;
		} else {
			sNo = size + 1;
		}
		return sNo;
	}

	public StudentAdmissionDTO StudentAdmission(StudentAdmissionDTO admissionDTO)
			throws ParseException {
		StudentAdmission admission = new StudentAdmission();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// admission.setAdmissionNo(admissionDTO.getAdmissionNo());
		// admission.setAdmitInClass(admissionDTO.getAdmitInClass());
		if (admissionDTO.getBrotherOrSisterAge1() == null
				|| admissionDTO.getBrotherOrSisterAge1() == "") {
			admission.setBrotherOrSisterAge1("0");
		} else {
			admission.setBrotherOrSisterAge1(admissionDTO
					.getBrotherOrSisterAge1());
		}
		if (admissionDTO.getBrotherOrSisterAge2() == null
				|| admissionDTO.getBrotherOrSisterAge2() == "") {
			admission.setBrotherOrSisterAge2("0");
		} else {
			admission.setBrotherOrSisterAge2(admissionDTO
					.getBrotherOrSisterAge2());
		}
        admission.setDelete(true);
		admission.setBrotherOrSisterClass1(admissionDTO
				.getBrotherOrSisterClass1());
		admission.setBrotherOrSisterClass2(admissionDTO
				.getBrotherOrSisterClass2());
		admission.setBrotherOrSisterName1(admissionDTO
				.getBrotherOrSisterName1());
		admission.setBrotherOrSisterName2(admissionDTO
				.getBrotherOrSisterName2());
		admission.setBrotherOrSisterSchoolOrCollege1(admissionDTO
				.getBrotherOrSisterSchoolOrCollege1());
		admission.setBrotherOrSisterSchoolOrCollege2(admissionDTO
				.getBrotherOrSisterSchoolOrCollege2());
		admission.setClasses(classDao.get(admissionDTO.getClassId()));
		admission.setCountry(countryDao.get(admissionDTO.getCountryId()));
		admission.setSchoolAddress(admissionDTO.getSchoolAddress());
		admission.setSchoolName(admissionDTO.getSchoolName());
		admission.setSchoolPhone(admissionDTO.getSchoolPhone());
		admission.setSection(sectionDao.get(admissionDTO.getSectionId()));
		admission.setState(stateDao.get(admissionDTO.getStateId()));
		admission.setCity(cityDao.get(admissionDTO.getCityId()));
		if (!admissionDTO.getDateOfBirth().equals("")) {
			admission.setDateOfBirth(dateFormat.parse(admissionDTO
					.getDateOfBirth()));
		}
		admission.setFatherAnnualIncome(admissionDTO.getFatherAnnualIncome());
		admission.setFatherEducation(admissionDTO.getFatherEducation());
		admission.setFatherName(admissionDTO.getFatherName());
		admission.setFatherOccupation(admissionDTO.getFatherOccupation());
		admission.setFatherOfficeAddress(admissionDTO.getFatherOfficeAddress());
		admission.setFatherPhoneNo(admissionDTO.getFatherPhoneNo());
		/*
		 * if ((!admissionDTO.getFeeReceiptDate().equals(""))) {
		 * admission.setFeeReceiptDate(dateFormat.parse(admissionDTO
		 * .getFeeReceiptDate())); }
		 * //admission.setFeeReceiptNo(admissionDTO.getFeeReceiptNo());
		 */admission.setGender(admissionDTO.getGender());

		if (admissionDTO.getPhoto().getSize() > 0) {
			admission.setImageName(admissionDTO.getPhoto()
					.getOriginalFilename());
		} else {
			admission.setImageName("defultimage.png");
		}
		admission.setMotherAnnualIncome(admissionDTO.getMotherAnnualIncome());
		admission.setMotherEducation(admissionDTO.getMotherEducation());
		admission.setMotherName(admissionDTO.getMotherName());
		admission.setMotherOccupation(admissionDTO.getMotherOccupation());
		admission.setMotherOfficeAddress(admissionDTO.getMotherOfficeAddress());
		admission.setMotherPhoneNo(admissionDTO.getMotherPhoneNo());
		admission.setMotherTounge(admissionDTO.getMotherTounge());
		admission.setPhoneNo(admissionDTO.getPhoneNo());
		admission.setPhysicalDisability(admissionDTO.getPhysicalDisability());
		// admission.setPrincipalOrDirectorName(admissionDTO
		// .getPrincipalOrDirectorName());
		admission.setRegNo(admissionDTO.getRegNo());
		admission.setResAddress(admissionDTO.getResAddress());
		admission.setsNo(admissionDTO.getsNo());
		admission.setTransferStatus("no");
		admission.setActive(true);
		admission.setStudentName(admissionDTO.getStudentName());
		if (!admissionDTO.getWrittenOrIntervieDate().equals("")) {
			admission.setWrittenOrIntervieDate(dateFormat.parse(admissionDTO
					.getWrittenOrIntervieDate()));

		}
		dao.ceateAdmission(admission);
		return getStudentAdmissionDetails(admission.getStudentId());

	}

	public StudentAdmissionDTO getStudentAdmissionDetails(int id) {
		StudentAdmission studentAdmission = dao.getStudent(id);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		StudentAdmissionDTO admissionDTO = new StudentAdmissionDTO();

		// admissionDTO.setAdmissionNo(studentAdmission.getAdmissionNo());
		// admissionDTO.setAdmitInClass(studentAdmission.getAdmitInClass());
		admissionDTO.setSchoolAddress(studentAdmission.getSchoolAddress());
		admissionDTO.setSchoolName(studentAdmission.getSchoolName());
		admissionDTO.setSchoolPhone(studentAdmission.getSchoolPhone());
		admissionDTO.setSectionName(studentAdmission.getSection()
				.getSectionName());
		admissionDTO.setStateName(studentAdmission.getState().getStateName());
		admissionDTO.setCityName(studentAdmission.getCity().getCityName());
		admissionDTO.setBrotherOrSisterAge1(studentAdmission
				.getBrotherOrSisterAge1());
		admissionDTO.setBrotherOrSisterAge2(studentAdmission
				.getBrotherOrSisterAge2());
		admissionDTO.setBrotherOrSisterClass1(studentAdmission
				.getBrotherOrSisterClass1());
		admissionDTO.setBrotherOrSisterClass2(studentAdmission
				.getBrotherOrSisterClass2());
		admissionDTO.setBrotherOrSisterName1(studentAdmission
				.getBrotherOrSisterName1());
		admissionDTO.setBrotherOrSisterName2(studentAdmission
				.getBrotherOrSisterName2());
		admissionDTO.setBrotherOrSisterSchoolOrCollege1(studentAdmission
				.getBrotherOrSisterSchoolOrCollege1());
		admissionDTO.setBrotherOrSisterSchoolOrCollege2(studentAdmission
				.getBrotherOrSisterSchoolOrCollege2());
		admissionDTO.setClassName(studentAdmission.getClasses().getClassName());
		admissionDTO.setCountryName(studentAdmission.getCountry()
				.getCountryName());
		if (!(studentAdmission.getDateOfBirth() == null)) {
			admissionDTO.setDateOfBirth(dateFormat.format(studentAdmission
					.getDateOfBirth()));
		}
		admissionDTO.setFatherAnnualIncome(studentAdmission
				.getFatherAnnualIncome());
		admissionDTO.setFatherEducation(studentAdmission.getFatherEducation());
		admissionDTO.setFatherName(studentAdmission.getFatherName());
		admissionDTO
				.setFatherOccupation(studentAdmission.getFatherOccupation());
		admissionDTO.setFatherOfficeAddress(studentAdmission
				.getFatherOfficeAddress());
		admissionDTO.setFatherPhoneNo(studentAdmission.getFatherPhoneNo());
		/*
		 * if (!(studentAdmission.getFeeReceiptDate() == null)) {
		 * admissionDTO.setFeeReceiptDate(dateFormat.format(studentAdmission
		 * .getFeeReceiptDate())); }
		 */
		// admissionDTO.setFeeReceiptNo(studentAdmission.getFeeReceiptNo());
		admissionDTO.setMotherAnnualIncome(studentAdmission
				.getMotherAnnualIncome());
		admissionDTO.setMotherEducation(studentAdmission.getMotherEducation());
		admissionDTO.setMotherName(studentAdmission.getMotherName());
		admissionDTO
				.setMotherOccupation(studentAdmission.getMotherOccupation());
		admissionDTO.setMotherOfficeAddress(studentAdmission
				.getMotherOfficeAddress());
		admissionDTO.setMotherPhoneNo(studentAdmission.getMotherPhoneNo());
		admissionDTO.setMotherTounge(studentAdmission.getMotherTounge());
		admissionDTO.setGender(studentAdmission.getGender());
		admissionDTO.setImageName(studentAdmission.getImageName());
		admissionDTO.setPhoneNo(studentAdmission.getPhoneNo());
		admissionDTO.setPhysicalDisability(studentAdmission
				.getPhysicalDisability());
		/*
		 * admissionDTO.setPrincipalOrDirectorName(studentAdmission
		 * .getPrincipalOrDirectorName());
		 */
		admissionDTO.setRegNo(studentAdmission.getRegNo());
		admissionDTO.setsNo(studentAdmission.getsNo());
		admissionDTO.setResAddress(studentAdmission.getResAddress());
		admissionDTO.setStudentName(studentAdmission.getStudentName());
		if (!(studentAdmission.getWrittenOrIntervieDate() == null)) {
			admissionDTO.setWrittenOrIntervieDate(dateFormat
					.format(studentAdmission.getWrittenOrIntervieDate()));
		}
		return admissionDTO;
	}

	public List<StudentAdmissionDTO> getApplicationList() {
		List<StudentAdmission> list = dao.getAllApplication();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<StudentAdmissionDTO> admissionDTOs = new ArrayList<StudentAdmissionDTO>();
		for (StudentAdmission studentAdmission : list) {
			StudentAdmissionDTO admissionDTO = new StudentAdmissionDTO();
			admissionDTO.setStudentName(studentAdmission.getStudentName());
			admissionDTO.setRegNo(studentAdmission.getRegNo());
			admissionDTO.setPhoneNo(studentAdmission.getPhoneNo());
			admissionDTO.setFatherName(studentAdmission.getFatherName());
			admissionDTO.setMotherName(studentAdmission.getMotherName());
			admissionDTO.setClassName(studentAdmission.getClasses()
					.getClassName());
			if (!(studentAdmission.getDateOfBirth() == null)) {
				admissionDTO.setDateOfBirth(dateFormat.format(studentAdmission
						.getDateOfBirth()));
			}

			admissionDTO.setStudentId(studentAdmission.getStudentId());
			admissionDTOs.add(admissionDTO);
		}
		return admissionDTOs;
	}

	@Override
	public List<StudentDTO> getStudentsbyFathername(int categoryId,
			int sessionId) {
		List<Students> list = dao.getStudentbyFatherName(categoryId, sessionId);
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		for (Students students : list) {
			StudentDTO dto = new StudentDTO();
			dto.setFirstName(students.getFirstName());
			dto.setMiddleName(students.getMiddleName());
			dto.setLastName(students.getLastName());
			dto.setAdmissionNo(students.getStudentAdmissionNo());
			dto.setFatherName(students.getFatherName());
			dto.setClassName(students.getClasses().getClassName());
			dto.setSectionName(students.getSection().getSectionName());
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public List<StudentDTO> getStudentsbyCategoryId(int categoryId,
			int sessionId, int classId) {
		List<Students> list = dao.getStudentbyCategory(categoryId, sessionId,
				classId);
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		for (Students students : list) {
			StudentDTO dto = new StudentDTO();
			dto.setFirstName(students.getFirstName());
			dto.setMiddleName(students.getMiddleName());
			dto.setLastName(students.getLastName());
			dto.setAdmissionNo(students.getStudentAdmissionNo());
			dto.setFatherName(students.getFatherName());
			dto.setClassName(students.getClasses().getClassName());
			dto.setSectionName(students.getSection().getSectionName());
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public StudentDTO findStudentDetailsforPDF(String studentId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
		Students students = dao.getActiveStudentById(studentId);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setAdmissionNo(studentId);
		/*
		 * if (students.isActive() == true) studentDTO.setActive("true"); else
		 * studentDTO.setActive("false");
		 */
		studentDTO.setFirstName(students.getFirstName());
		studentDTO.setMiddleName(students.getMiddleName());
		studentDTO.setLastName(students.getLastName());
		studentDTO.setAddressLine1(students.getAddressLine1());
		studentDTO.setAddressLine2(students.getAddressLine2());
		studentDTO.setAdmissionDate(df.format(students.getAdmissionDate()));
		studentDTO.setSessionId(students.getSession().getSessionId());
		if (students.getCity() != null) {
			studentDTO.setCity(students.getCity().getCityName());
			studentDTO.setCityId(students.getCity().getCityId());
		}
		if (students.getCountry() != null) {
			Country country = students.getCountry();
			studentDTO.setCountryId(country.getCountryId());
			studentDTO.setCountry(country.getCountryName());
		}
		if (students.getState() != null) {
			State state = students.getState();
			studentDTO.setStateId(state.getStateId());
			studentDTO.setState(state.getStateName());
		}
		studentDTO.setSessionDuration(students.getSession()
				.getSessionDuration());
		studentDTO.setDateOfBirth(df1.format(students.getDateOfBirth()));
		studentDTO.setEmail(students.getEmail());
		studentDTO.setFatherName(students.getFatherName());
		studentDTO.setGender(students.getGender());
		studentDTO.setPhysicallyChallenged(students.getPhysicallyChallenged());
		studentDTO.setMobile(students.getMobile());
		studentDTO.setMotherName(students.getMotherName());
		studentDTO.setNationality(students.getNationality());
		studentDTO.setPhone(students.getPhone());
		studentDTO.setPinCode(String.valueOf(students.getPinCode()));
		studentDTO.setLfno(String.valueOf(students.getLfNo()));
		studentDTO.setImageName(students.getPhotoFileName());
		studentDTO.setReligionName(students.getReligion());
		studentDTO.setSectionName(students.getSection().getSectionName());

		if (students.getStudentCategory() != null) {
			studentDTO.setStudentCategoryId(students.getStudentCategory()
					.getStudentCategoriesId());
			studentDTO.setCategoryName(students.getStudentCategory()
					.getStudentCategoriesName());
		} else {
			studentDTO.setCategoryName("");
		}
		studentDTO.setUpdatedAt(students.getUpdatedAt());
		StudentClasses studentClasses = students.getClasses();
		studentDTO.setClassId(studentClasses.getClassId());
		studentDTO.setClassName(studentClasses.getClassName());
		Section section = students.getSection();
		studentDTO.setSectionId(section.getSectionId());
		studentDTO.setSectionName(section.getSectionName());
		studentDTO.setRollno(String.valueOf(students.getRollNo()));
		studentDTO.setPhoto1name(students.getPhoto1FileName());
		studentDTO.setPhoto2name(students.getPhoto2FileName());
		studentDTO.setPhoto3name(students.getPhoto3FileName());
		studentDTO.setPassword(students.getPassword());
		studentDTO.setStudentId(students.getStudentId());
		studentDTO.setGuardianName(students.getGuardianName());
		return studentDTO;
	}

	@Override
	public String transferStudent(int id) throws ParseException {
		StudentAdmission studentDTO = dao.getStudent(id);
		String[] uniqueNo = getStudentAdmissionNo();
		String name = studentDTO.getStudentName().trim();
		String admno = uniqueNo[0].trim();
		Session current = sessionDao.findCurrentSession();
		String userid = name + uniqueNo[2]+ current.getSessionId();
		String num = RandomPasswordUtil.getRandomString();
		String password = PasswordEncoder.getEcodedPassword(num);
		User user = new User();
		user.setUsername(userid);
		user.setPassword(password);
		user.setEnabled(true);
		UserRolePrimaryKey key = new UserRolePrimaryKey();
		key.setRole(roleDao.getUserRoleByName("ROLE_STUDENT"));
		key.setUser(userDao.create(user));
		UserRole role = new UserRole();
		role.setUserRolePrimaryKey(key);
		userRoleDao.create(role);
		Students students = new Students();
		students.setActive(true);
		students.setUser(user);
		students.setLfNo(Integer.parseInt(uniqueNo[1]));
		students.setStudentAdmissionNo(String.valueOf(getUniqueIdNo()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
		students.setAdmissionDate(new Date());
		students.setStudentId(uniqueNo[2]);
		students.setUniqueId(Integer.parseInt(admno));
		students.setFirstName(studentDTO.getStudentName());
		students.setMiddleName("");
		students.setLastName("");
		students.setDateOfBirth(df.parse(df.format(studentDTO.getDateOfBirth())));
		students.setAddressLine1(studentDTO.getResAddress());
		students.setClasses(studentDTO.getClasses());
		students.setSession(current);
		students.setSection(studentDTO.getSection());
		String roll = getRollNo(
				String.valueOf(studentDTO.getClasses().getClassId()),
				String.valueOf(studentDTO.getSection().getSectionId()));
		students.setPassword(num);
		students.setRollNo(Integer.parseInt(roll));
		students.setCreatedAt(new Date());
		students.setEmail("");
		students.setFatherName(studentDTO.getFatherName());
		students.setGender(studentDTO.getGender());
		students.setPhysicallyChallenged(studentDTO.getPhysicalDisability());
		students.setMobile(studentDTO.getPhoneNo());
		students.setMotherName(studentDTO.getMotherName());
		students.setNationality("");
		students.setGuardianName("");
		students.setPhone("");
		students.setPinCode("");
		students.setSchoolAddress(studentDTO.getSchoolAddress());
		students.setSchoolName(studentDTO.getSchoolName());
		students.setSchoolPhone(studentDTO.getSchoolPhone());

		students.setCity(studentDTO.getCity());

		students.setCountry(studentDTO.getCountry());

		students.setState(studentDTO.getState());

		students.setReligion("");

		students.setStudentCategory(null);
		students.setAdmitClassId(studentDTO.getClasses());
		students.setPhotoFileName(studentDTO.getImageName());
		students.setPhoto1FileName("parent.jpg");
		students.setPhoto2FileName("father.jpg");

		students.setPhoto3FileName("kids.jpg");

		dao.create(students);
		studentDTO.setTransferStatus("yes");
		return admno;
	}

	@Override
	public void cancelRegistration(int id) {
		StudentAdmission admission = dao.getStudent(id);
		admission.setActive(false);

	}

	@Override
	public List<StudentAdmissionDTO> getCancelledRegistration(int sessionId) {
		List<StudentAdmission> list = dao.getCancelledAllApplication();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<StudentAdmissionDTO> admissionDTOs = new ArrayList<StudentAdmissionDTO>();
		for (StudentAdmission studentAdmission : list) {
			StudentAdmissionDTO admissionDTO = new StudentAdmissionDTO();
			admissionDTO.setStudentName(studentAdmission.getStudentName());
			admissionDTO.setRegNo(studentAdmission.getRegNo());
			admissionDTO.setPhoneNo(studentAdmission.getPhoneNo());
			admissionDTO.setFatherName(studentAdmission.getFatherName());
			admissionDTO.setMotherName(studentAdmission.getMotherName());
			admissionDTO.setClassName(studentAdmission.getClasses()
					.getClassName());
			if (!(studentAdmission.getDateOfBirth() == null)) {
				admissionDTO.setDateOfBirth(dateFormat.format(studentAdmission
						.getDateOfBirth()));
			}

			admissionDTO.setStudentId(studentAdmission.getStudentId());
			admissionDTOs.add(admissionDTO);
		}
		return admissionDTOs;
	}

	@Override
	public List<StudentAdmissionDTO> getTransferredRegistration(int sessionId) {
		List<StudentAdmission> list = dao.getTransferredAllApplication();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<StudentAdmissionDTO> admissionDTOs = new ArrayList<StudentAdmissionDTO>();
		for (StudentAdmission studentAdmission : list) {
			StudentAdmissionDTO admissionDTO = new StudentAdmissionDTO();
			admissionDTO.setStudentName(studentAdmission.getStudentName());
			admissionDTO.setRegNo(studentAdmission.getRegNo());
			admissionDTO.setPhoneNo(studentAdmission.getPhoneNo());
			admissionDTO.setFatherName(studentAdmission.getFatherName());
			admissionDTO.setMotherName(studentAdmission.getMotherName());
			admissionDTO.setClassName(studentAdmission.getClasses()
					.getClassName());
			if (!(studentAdmission.getDateOfBirth() == null)) {
				admissionDTO.setDateOfBirth(dateFormat.format(studentAdmission
						.getDateOfBirth()));
			}

			admissionDTO.setStudentId(studentAdmission.getStudentId());
			admissionDTOs.add(admissionDTO);
		}
		return admissionDTOs;
	}

	@Override
	public String findStudentbalance(String studentId) {
		String result = "";
		Session current = sessionDao.findCurrentSession();
		Students student = dao.checkExistStudent(studentId,
				current.getSessionId());
		Fine fineEntity = fineDao.getFineByName("Late Fee Fine",
				current.getSessionId());
		Boolean flag = true;
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

		if (currentMonth > 3) {
			currentMonth = currentMonth - 3;
		} else {
			currentMonth = currentMonth + 9;
		}
		for (int i = 1; i <= currentMonth; i++) {
			LastDate lastDates = lastDateDao.getLastDatebyMonth(i,
					current.getSessionId());
			if (lastDates == null) {

				result = "Not Found";
				flag = false;
				break;
			}
		}
		if (flag == true) {
			int classId = student.getClasses().getClassId();
			int sectionId = student.getSection().getSectionId();
			StudentFeeSubmissionDetails details = studentFeeSubmissionDetailsDao
					.findlastpayedmonthbystudentId(
							student.getStudentAdmissionNo(),
							current.getSessionId());

			double allotedfees = 0;
			int j = 1;

			if (details != null)
				j = details.getMonth().getMonthId() + 1;
			for (int i = j; i <= currentMonth; i++) {
				LastDate lastDate = lastDateDao.getLastDatebyMonth(i,
						current.getSessionId());
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
				if (fine > 0) {

					List<ClassWiseFee> classWiseFees = classWiseFeeDao
							.getFeeAllotement(current.getSessionId(), classId,
									i);
					for (ClassWiseFee classWiseFee : classWiseFees) {
						if (classWiseFees != null) {
							if (classWiseFees != null) {

								EditFeeAmount editFeeAmount = editFeeAmountDao
										.getDiscountAmountBySessionMonthCategoryId(
												current.getSessionId(),
												i,
												classWiseFee
														.getFeesCategories()
														.getFeeCategoryId(),
												Integer.parseInt(student
														.getStudentAdmissionNo()));
								if (editFeeAmount != null) {
									double editedAmount = classWiseFee
											.getFeeAmount()
											- editFeeAmount.getDiscount();
									allotedfees += editedAmount;
								} else {
									allotedfees += classWiseFee.getFeeAmount();
								}

							}
						}
					}
					for (SectionWiseFee sectionWiseFee : sectionWiseFeeDao
							.getFeeAllotement(current.getSessionId(), classId,
									sectionId, i)) {
						if (sectionWiseFee != null) {

							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											current.getSessionId(), i,
											sectionWiseFee.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(student
													.getStudentAdmissionNo()));
							if (editFeeAmount != null) {
								double editedAmount = sectionWiseFee
										.getFeeAmount()
										- editFeeAmount.getDiscount();
								allotedfees += editedAmount;
							} else {
								allotedfees += sectionWiseFee.getFeeAmount();
							}
						}

					}

					for (StudentWiseFee studentWiseFee : studentWiseFeeDao
							.getFeeAllotement(current.getSessionId(), classId,
									sectionId, i,
									student.getStudentAdmissionNo())) {
						if (studentWiseFee != null) {

							EditFeeAmount editFeeAmount = editFeeAmountDao
									.getDiscountAmountBySessionMonthCategoryId(
											current.getSessionId(), i,
											studentWiseFee.getFeesCategories()
													.getFeeCategoryId(),
											Integer.parseInt(student
													.getStudentAdmissionNo()));
							if (editFeeAmount != null) {
								double editedAmount = studentWiseFee
										.getFeeAmount()
										- editFeeAmount.getDiscount();
								allotedfees += editedAmount;
							} else {
								allotedfees += studentWiseFee.getFeeAmount();
							}
						}

					}

				}
			}

			if (allotedfees > 0) {
				result = String.valueOf(allotedfees);
			} else {
				result = "0";
			}

		}
		return result;
	}

	@Override
	public StudentDTO findStudentPerforma(String studentId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Students student = dao.checkExistStudent(studentId, sessionDao
				.findCurrentSession().getSessionId());
		StudentDTO dto= findStudentDetails(student.getStudentAdmissionNo());
		StudentTransfer transfer=studentTransferDao.getTcfortudent(student.getStudentAdmissionNo());
		if(transfer!=null)
		{
			dto.setDateOfWithdrawl(df.format(transfer.getDateOfWithdrawl()));
			dto.setNewClass(transfer.getNewClassName());
			dto.setReligionCaste(transfer.getReligionCaste());
			
		}
		else
		{
			dto.setDateOfWithdrawl("-");
			dto.setNewClass("-");
			dto.setReligionCaste("-");
		}
		return dto;
	}

	@Override
	public void deleteRegistration(int id) {
		StudentAdmission admission = dao.getStudent(id);
		admission.setDelete(false);
		
	}

	@Override
	public void ReadmitStudent(String id) {
		dao.get(id).setActive(true);
		
	}
}
