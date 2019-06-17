package org.brijframework.college.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.commom.convertor.AnnualExamReportDTOToEntity;
import org.brijframework.college.dao.AnnualExamGainMarksDao;
import org.brijframework.college.dao.AnnualExamReportDao;
import org.brijframework.college.dao.AnnualExamTotalGainMarksDao;
import org.brijframework.college.dao.AnnuallySubjectMarksDao;
import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.SubjectDao;
import org.brijframework.college.model.AnnualExamGainMarks;
import org.brijframework.college.model.AnnualExamReport;
import org.brijframework.college.model.AnnualExamTotalGainMarks;
import org.brijframework.college.model.AnnuallySubjectMarks;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.AnnualExamReportDTO;
import org.brijframework.college.models.dto.AnnuallySubjectMarksDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.brijframework.college.service.AnnualExamReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnualExamReportServiceImpl extends
		CRUDServiceImpl<Integer, AnnualExamReport, AnnualExamReportDao>
		implements AnnualExamReportService {
	@Autowired
	public AnnualExamReportServiceImpl(AnnualExamReportDao dao) {
		super(dao);
	}

	@Autowired
	private AnnualExamGainMarksDao annualExamGainMarksDao;
	@Autowired
	private AnnualExamTotalGainMarksDao annualExamTotalGainMarksDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private StudentsAdmissionDao studentsAdmissionDao;
	@Autowired
	private AnnuallySubjectMarksDao annuallySubjectMarksDao;
	@Autowired
	private SessionDao sessionDao;
	@Autowired
	private StudentClassesDao studentClassesDao;
	@Autowired
	private SectionDao sectionDao;

	@Override
	public void saveAnnualExamReport(AnnualExamReportDTO annualExamReportDTO) {
		createAnnualExamReport(annualExamReportDTO);

	}

	private void createAnnualExamReport(AnnualExamReportDTO annualExamReportDTO) {
		AnnualExamReportDTOToEntity annualExamReportDTOToEntity = AnnualExamReportDTOToEntity
				.getInstance();
		for (AnnualExamReportDTO annualExamReportDTONew : annualExamReportDTO
				.getAnnualExamReportDTOs()) {
			AnnualExamReport annualExamReport = dao.getAnnualExamReport(
					annualExamReportDTO.getSessionId(),
					annualExamReportDTO.getClassId(),
					annualExamReportDTO.getSectionId(),
					annualExamReportDTONew.getStudentId(),
					annualExamReportDTO.getAnnualExamType(),
					annualExamReportDTO.getExamType());

			if (annualExamReport == null) {
				annualExamReportDTO.setStudentId(annualExamReportDTONew
						.getStudentId());
				annualExamReportDTO.setMaxMarks(annualExamReportDTO
						.getMaxMarks());
				AnnualExamReport annualExamReportNew = annualExamReportDTOToEntity
						.convertEntityToDTO(annualExamReportDTO);
				dao.create(annualExamReportNew);
				saveAnnualExamGainMarks(annualExamReportNew,
						annualExamReportDTONew);
			} else {
				annualExamReport.setMaxMarks(annualExamReportDTO.getMaxMarks());
				dao.update(annualExamReport);
				saveAnnualExamGainMarks(annualExamReport,
						annualExamReportDTONew);

			}

		}

	}

	/*
	 * private void saveAnnullySubjectsMark(AnnualExamReportDTO
	 * annualExamReportDTO) { if (annualExamReportDTO.getAnnualExamReportDTOs()
	 * != null) { for (AnnuallySubjectMarksDTO annuallySubjectMarksDTO :
	 * annualExamReportDTO .getAnnuallySubjectMarksDTOs()) {
	 * AnnuallySubjectMarks annuallySubjectMarks = annuallySubjectMarksDao
	 * .getAnnuallySubjectMarks( annualExamReportDTO.getSessionId(),
	 * annualExamReportDTO.getClassId(), annualExamReportDTO.getSectionId(),
	 * annuallySubjectMarksDTO.getSubjectsId(),
	 * annualExamReportDTO.getExamType(),
	 * annualExamReportDTO.getAnnualExamType()); if (annuallySubjectMarks ==
	 * null) { annuallySubjectMarks = new AnnuallySubjectMarks();
	 * annuallySubjectMarks.setExamReportType(annualExamReportDTO
	 * .getAnnualExamType());
	 * annuallySubjectMarks.setExamType(annualExamReportDTO .getExamType());
	 * annuallySubjectMarks.setMaxMarks(String
	 * .valueOf(annuallySubjectMarksDTO.getMaxMarks()));
	 * annuallySubjectMarks.setSection(sectionDao
	 * .get(annualExamReportDTO.getSectionId()));
	 * annuallySubjectMarks.setSession(sessionDao
	 * .get(annualExamReportDTO.getSessionId()));
	 * annuallySubjectMarks.setStudentClasses(studentClassesDao
	 * .get(annualExamReportDTO.getClassId()));
	 * annuallySubjectMarks.setSubjects(subjectDao
	 * .get(annuallySubjectMarksDTO.getSubjectsId()));
	 * annuallySubjectMarksDao.create(annuallySubjectMarks); } else {
	 * annuallySubjectMarks.setMaxMarks(String
	 * .valueOf(annuallySubjectMarksDTO.getMaxMarks()));
	 * annuallySubjectMarksDao.update(annuallySubjectMarks); }
	 * 
	 * } }
	 * 
	 * }
	 */

	private void saveAnnualExamGainMarks(AnnualExamReport annualExamReport,
			AnnualExamReportDTO annualExamReportDTO) {
		if (annualExamReportDTO != null) {
			for (AnnuallySubjectMarksDTO annuallySubjectMarksDTO : annualExamReportDTO
					.getAnnuallySubjectMarksDTOs()) {
				AnnualExamGainMarks annualExamGainMarks = annualExamGainMarksDao
						.getAnnualExamGainMarks(
								annuallySubjectMarksDTO.getSubjectsId(),
								annualExamReport.getAnnualExamReportId());
				if (annualExamGainMarks == null) {
					AnnualExamGainMarks annualExamGainMarksNew = new AnnualExamGainMarks();
					annualExamGainMarksNew
							.setAnnualExamReport(annualExamReport);
					Subjects subjects = new Subjects();
					subjects.setSubjectsId(annuallySubjectMarksDTO
							.getSubjectsId());
					annualExamGainMarksNew.setSubjects(subjects);
					if (annuallySubjectMarksDTO.getGainMarks() == null) {
						annualExamGainMarksNew.setGainMarks(0.0);
					} else {
						annualExamGainMarksNew.setGainMarks(Double
								.parseDouble(annuallySubjectMarksDTO
										.getGainMarks()));
					}

					annualExamGainMarksDao.create(annualExamGainMarksNew);
				} else {
					annualExamGainMarks
							.setGainMarks(Double
									.parseDouble(annuallySubjectMarksDTO
											.getGainMarks()));
					annualExamGainMarksDao.update(annualExamGainMarks);
				}
			}
		}

	}

	@Override
	public Map<String, Object> getDataForGenerateReportCard(int sessionId,
			int classId, int sectionId, String StudentId) {
		// required variable
		Double halfYearlyTotalGain = 0.0;
		Double halfYearlyMaxTotal = 0.0;
		Double annualTotalGain = 0.0;
		Double annualGainMaxTotal = 0.0;
		// end variable
		Map<String, Object> map = new HashMap<String, Object>();
		List<Subjects> subjectList = subjectDao.getSubjectList(classId,
				sectionId, sessionId);
		List<SubjectDTO> subjectDTOs = new ArrayList<SubjectDTO>();
		AnnualExamReport getHalfUT = dao
				.getAnnualExamReport(sessionId, classId, sectionId, StudentId,
						"HALF-YEARLY", "UT+ORAL+PROJECT");
		AnnualExamReport getHalfMain = dao.getAnnualExamReport(sessionId,
				classId, sectionId, StudentId, "HALF-YEARLY", "MAIN EXAM");
		AnnualExamReport getAnnualUT = dao.getAnnualExamReport(sessionId,
				classId, sectionId, StudentId, "ANNUALLY", "UT+ORAL+PROJECT");
		AnnualExamReport getAnnualMain = dao.getAnnualExamReport(sessionId,
				classId, sectionId, StudentId, "ANNUALLY", "MAIN EXAM");
		// create first row of table
		SubjectDTO subjectDTONew = new SubjectDTO();
		subjectDTONew.setSubjectName("MAX MARKS");
		if (getHalfUT != null)
			subjectDTONew.setHalfYearlyTU(new DecimalFormat("##")
					.format(getHalfUT.getMaxMarks()));
		else {
			subjectDTONew.setHalfYearlyTU("");
		}
		if (getHalfUT != null && getHalfMain != null) {
			subjectDTONew.setHalfYearlyExam(new DecimalFormat("##")
					.format(getHalfMain.getMaxMarks()));
			Double variable = getHalfUT.getMaxMarks()
					+ getHalfMain.getMaxMarks();
			subjectDTONew.setHalfYearlyTotal(new DecimalFormat("##")
					.format(variable));
		} else {
			subjectDTONew.setHalfYearlyExam("");
			subjectDTONew.setHalfYearlyTotal("");
		}
		if (getHalfUT != null && getHalfMain != null && getAnnualUT != null)
			subjectDTONew.setAnnualYearlyTU(new DecimalFormat("##")
					.format(getAnnualUT.getMaxMarks()));
		else {
			subjectDTONew.setAnnualYearlyTU("");
		}
		if (getHalfUT != null && getHalfMain != null && getAnnualUT != null
				&& getAnnualMain != null) {
			subjectDTONew.setAnnualYearlyExam(new DecimalFormat("##")
					.format(getAnnualMain.getMaxMarks()));
			Double annualYearlyTotal = getAnnualUT.getMaxMarks()
					+ getAnnualMain.getMaxMarks();
			subjectDTONew.setAnnualYearlyTotal(new DecimalFormat("##")
					.format(annualYearlyTotal));
			Double grandTotal = getHalfUT.getMaxMarks()
					+ getHalfMain.getMaxMarks() + getAnnualUT.getMaxMarks()
					+ getAnnualMain.getMaxMarks();
			subjectDTONew.setGrandTotal(new DecimalFormat("##")
					.format(grandTotal));
		} else {
			subjectDTONew.setAnnualYearlyExam("");
			subjectDTONew.setAnnualYearlyTotal("");
			subjectDTONew.setGrandTotal("");
		}
		subjectDTOs.add(subjectDTONew);
		// end

		for (Subjects subjects : subjectList) {
			SubjectDTO subjectDTO = new SubjectDTO();
			subjectDTO.setSubjectName(subjects.getSubjectName());
			Double halfYealyGain = 0.0;
			Double annualGain = 0.0;
			// "HALF-YEARLY", "UT+ORAL+PROJECT"
			if (getHalfUT == null) {
				subjectDTO.setHalfYearlyTU("");
			} else {
				AnnualExamGainMarks halfYealyAnnualExamGainMarksUT = annualExamGainMarksDao
						.getAnnualExamGainMarks(subjects.getSubjectsId(),
								getHalfUT.getAnnualExamReportId());
				if (halfYealyAnnualExamGainMarksUT == null) {
					subjectDTO.setHalfYearlyTU("");
				} else {
					subjectDTO.setHalfYearlyTU(new DecimalFormat("##")
							.format(halfYealyAnnualExamGainMarksUT
									.getGainMarks()));
					halfYearlyTotalGain += halfYealyAnnualExamGainMarksUT
							.getGainMarks();
					halfYealyGain += halfYealyAnnualExamGainMarksUT
							.getGainMarks();
					halfYearlyMaxTotal += getHalfUT.getMaxMarks();
				}
			}
			// end
			// "HALF-YEARLY", "MAIN EXAM"
			if (getHalfUT == null || getHalfMain == null) {
				subjectDTO.setHalfYearlyExam("");
				subjectDTO.setHalfYearlyTotal("");
			} else {
				AnnualExamGainMarks halfYealyAnnualExamGainMarksMAIN = annualExamGainMarksDao
						.getAnnualExamGainMarks(subjects.getSubjectsId(),
								getHalfMain.getAnnualExamReportId());
				if (halfYealyAnnualExamGainMarksMAIN == null) {
					subjectDTO.setHalfYearlyExam("");
					subjectDTO.setHalfYearlyTotal("");
				} else {
					subjectDTO.setHalfYearlyExam(new DecimalFormat("##")
							.format(halfYealyAnnualExamGainMarksMAIN
									.getGainMarks()));
					halfYearlyTotalGain += halfYealyAnnualExamGainMarksMAIN
							.getGainMarks();
					halfYealyGain += halfYealyAnnualExamGainMarksMAIN
							.getGainMarks();
					subjectDTO.setHalfYearlyTotal(new DecimalFormat("##")
							.format(halfYealyGain));
					halfYearlyMaxTotal += getHalfMain.getMaxMarks();

				}
			}
			// end
			// "ANNUALlY", "UT+ORAL+PROJECT"
			if (getHalfUT == null || getHalfMain == null || getAnnualUT == null) {
				subjectDTO.setAnnualYearlyTU("");
			} else {
				AnnualExamGainMarks annualExamGainMarksUT = annualExamGainMarksDao
						.getAnnualExamGainMarks(subjects.getSubjectsId(),
								getAnnualUT.getAnnualExamReportId());
				if (annualExamGainMarksUT == null) {
					subjectDTO.setAnnualYearlyTU("");
				} else {
					annualGainMaxTotal += getAnnualUT.getMaxMarks();
					subjectDTO.setAnnualYearlyTU(new DecimalFormat("##")
							.format(annualExamGainMarksUT.getGainMarks()));
					annualGain += annualExamGainMarksUT.getGainMarks();
					annualTotalGain += annualExamGainMarksUT.getGainMarks();
				}
			}
			// end
			// "ANNUALlY", "MAIN EXAM"
			if (getHalfUT == null || getHalfMain == null || getAnnualUT == null
					|| getAnnualMain == null) {
				subjectDTO.setAnnualYearlyExam("");
				subjectDTO.setAnnualYearlyTotal("");
			} else {
				AnnualExamGainMarks annualExamGainMarksMAIN = annualExamGainMarksDao
						.getAnnualExamGainMarks(subjects.getSubjectsId(),
								getAnnualMain.getAnnualExamReportId());
				if (annualExamGainMarksMAIN == null) {
					subjectDTO.setAnnualYearlyExam("");
					subjectDTO.setAnnualYearlyTotal("");
				} else {
					subjectDTO.setAnnualYearlyExam(new DecimalFormat("##")
							.format(annualExamGainMarksMAIN.getGainMarks()));
					annualGain += annualExamGainMarksMAIN.getGainMarks();
					subjectDTO.setAnnualYearlyTotal(new DecimalFormat("##")
							.format(annualGain));
					annualGainMaxTotal += getAnnualMain.getMaxMarks();
					annualTotalGain += annualExamGainMarksMAIN.getGainMarks();
				}
			}
			if (getHalfUT != null && getHalfMain != null && getAnnualUT != null
					&& getAnnualMain != null) {
				Double grandTotal = halfYealyGain + annualGain;
				subjectDTO.setGrandTotal(new DecimalFormat("##")
						.format(grandTotal));
			} else {
				subjectDTO.setGrandTotal("");
			}
			subjectDTOs.add(subjectDTO);
		}
		// hear calculation total,percentage marks
		// half yearly
		List<AnnualExamTotalGainMarks> halfAnnualExamTotalGainMarkList = annualExamTotalGainMarksDao
				.getMaxAnnualExamTotalGainMarks(sessionId, classId, sectionId,
						"HALF-YEARLY");
		Double granMaxPercentage = 0.0;
		Double grand = 0.0;
		Double tot = 0.0;
		if (getHalfUT != null && getHalfMain != null) {
			map.put("halfYearlyTotal",
					""
							+ new DecimalFormat("##")
									.format(halfYearlyTotalGain)
							+ "/"
							+ new DecimalFormat("##")
									.format(halfYearlyMaxTotal) + "");
			map.put("halfYearGainPercenatge",
					new DecimalFormat("##.##")
							.format(((halfYearlyTotalGain / halfYearlyMaxTotal) * 100))
							+ "%");

			for (AnnualExamTotalGainMarks annualExamTotalGainMarks : halfAnnualExamTotalGainMarkList) {
				map.put("halfYearlyMaxGainPercentage",
						new DecimalFormat("##.##").format((annualExamTotalGainMarks
								.getTotalGainMarks() / annualExamTotalGainMarks
								.getTotalMarks()) * 100)
								+ "%");
				granMaxPercentage += Double.parseDouble(new DecimalFormat(
						"##.##").format(annualExamTotalGainMarks
						.getTotalGainMarks()
						/ annualExamTotalGainMarks.getTotalMarks()));
				grand = annualExamTotalGainMarks.getTotalGainMarks();
				tot = annualExamTotalGainMarks.getTotalMarks();
			}

		} else {
			map.put("halfYearlyTotal", " ");
			map.put("halfYearGainPercenatge", " ");

			map.put("halfYearlyMaxGainPercentage", " ");
		}
		// Annually
		List<AnnualExamTotalGainMarks> annualExamTotalGainMarkList = annualExamTotalGainMarksDao
				.getMaxAnnualExamTotalGainMarks(sessionId, classId, sectionId,
						"ANNUALLY");
		if (getHalfUT != null && getHalfMain != null && getAnnualUT != null
				&& getAnnualMain != null && annualExamTotalGainMarkList != null) {
			map.put("annuallyTotal",
					""
							+ new DecimalFormat("##").format(annualTotalGain)
							+ "/"
							+ new DecimalFormat("##")
									.format((annualGainMaxTotal)) + "");
			map.put("annuallyGainPercentage",
					new DecimalFormat("##.##")
							.format((annualTotalGain / annualGainMaxTotal) * 100)
							+ "%");

			for (AnnualExamTotalGainMarks annualExamTotalGainMarks : annualExamTotalGainMarkList) {

				map.put("annuallyMaxGainPercentage",
						new DecimalFormat("##.##").format((annualExamTotalGainMarks
								.getTotalGainMarks() / annualExamTotalGainMarks
								.getTotalMarks()) * 100)
								+ "%");
				granMaxPercentage += Double.parseDouble(new DecimalFormat(
						"##.##").format(annualExamTotalGainMarks
						.getTotalGainMarks()
						/ annualExamTotalGainMarks.getTotalMarks()));
				grand += annualExamTotalGainMarks.getTotalGainMarks();
				tot += annualExamTotalGainMarks.getTotalMarks();

			}
			// grand total
			map.put("grandTotal", "" + (halfYearlyTotalGain + annualTotalGain)
					+ "/" + (annualGainMaxTotal + halfYearlyMaxTotal) + "");
			map.put("grandGainPercentage",
					Double.parseDouble(new DecimalFormat("##.###")
							.format((annualTotalGain + halfYearlyTotalGain)
									/ (annualGainMaxTotal + halfYearlyMaxTotal)))
							* 100 + "%");
			map.put("grandMaxGainPercentage",
					Double.parseDouble(new DecimalFormat("##.###")
							.format((grand / tot) * 100)) + "%");
		} else {
			map.put("annuallyTotal", " ");
			map.put("annuallyGainPercentage", " ");
			map.put("annuallyMaxGainPercentage", " ");
			// grand total
			map.put("grandTotal", " ");
			map.put("grandGainPercentage", " ");
			map.put("grandMaxGainPercentage", " ");
		}
		map.put("SubjectDTOs", subjectDTOs);

		return map;
	}

	@Override
	public Map<String, Object> getAnnualExamReport(int sessionId, int classId,
			int sectionId, String examType, String annualExamType) {
		List<AnnualExamReportDTO> annualExamReportDTOs = new ArrayList<AnnualExamReportDTO>();
		List<Students> studentsList = studentsAdmissionDao
				.getStudentsbyClassSectionId(classId, sectionId, sessionId);
		List<Subjects> subjectsList = subjectDao.getSubjectList(classId,
				sectionId, sessionId);
		AnnualExamReportDTO annualExamReportDTOHeading = new AnnualExamReportDTO();
		annualExamReportDTOHeading.setStudentName("Student Name");
		annualExamReportDTOHeading.setFatherName("Father Name");
		List<AnnualExamReportDTO> annualExamReportDTOsHeading = new ArrayList<AnnualExamReportDTO>();
		for (Subjects subjects : subjectsList) {
			AnnualExamReportDTO annualExamReportDTOsHeadingNew = new AnnualExamReportDTO();
			annualExamReportDTOsHeadingNew.setSubjectName(subjects
					.getSubjectName());
			annualExamReportDTOsHeading.add(annualExamReportDTOsHeadingNew);
			annualExamReportDTOHeading
					.setAnnualExamReportDTOs(annualExamReportDTOsHeading);
		}
		for (Students students : studentsList) {
			AnnualExamReportDTO annualExamReportDTO = new AnnualExamReportDTO();
			annualExamReportDTO.setStudentId(students.getStudentAdmissionNo());
			annualExamReportDTO.setStudentName(students.getFirstName() + " "
					+ students.getMiddleName() + " " + students.getLastName());
			annualExamReportDTO.setFatherName(students.getFatherName());
			List<AnnualExamReportDTO> annualExamReportDTOsNew = new ArrayList<AnnualExamReportDTO>();
			for (Subjects subjects : subjectsList) {
				AnnualExamReportDTO annualExamReportDTONew = new AnnualExamReportDTO();
				AnnualExamReport annualExamReport = dao.getAnnualExamReport(
						sessionId, classId, sectionId,
						students.getStudentAdmissionNo(), annualExamType,
						examType);
				if (annualExamReport != null) {
					AnnualExamGainMarks annualExamGainMarks = annualExamGainMarksDao
							.getAnnualExamGainMarks(subjects.getSubjectsId(),
									annualExamReport.getAnnualExamReportId());
					if (annualExamGainMarks != null) {
						annualExamReportDTONew.setSubjectName(subjects
								.getSubjectName());
						annualExamReportDTONew.setSubjectId(subjects
								.getSubjectsId());
						annualExamReportDTONew.setGainMarks(annualExamGainMarks
								.getGainMarks());
					} else {
						annualExamReportDTONew.setSubjectName(subjects
								.getSubjectName());
						annualExamReportDTONew.setSubjectId(subjects
								.getSubjectsId());
						annualExamReportDTONew.setGainMarks(0.0);
					}
				} else {
					annualExamReportDTONew.setSubjectName(subjects
							.getSubjectName());
					annualExamReportDTONew.setSubjectId(subjects
							.getSubjectsId());
					annualExamReportDTONew.setGainMarks(0.0);
				}

				annualExamReportDTOsNew.add(annualExamReportDTONew);
			}
			annualExamReportDTO
					.setAnnualExamReportDTOs(annualExamReportDTOsNew);
			annualExamReportDTOs.add(annualExamReportDTO);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PARENT", annualExamReportDTOHeading);
		map.put("CHILD", annualExamReportDTOs);
		return map;
	}

	@Override
	public Double getMaxMarksforExam(int sessionId, int classId,
			int sectionId, String examType, String annualExamType) {
		AnnualExamReport annualExamReport = dao.getAnnualMaxExam(
				sessionId, classId, sectionId, annualExamType,
				examType);
		return annualExamReport.getMaxMarks();
	}
}
