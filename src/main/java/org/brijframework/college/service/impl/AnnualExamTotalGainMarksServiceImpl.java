package org.brijframework.college.service.impl;

import java.util.List;

import org.brijframework.college.dao.AnnualExamReportDao;
import org.brijframework.college.dao.AnnualExamTotalGainMarksDao;
import org.brijframework.college.model.AnnualExamGainMarks;
import org.brijframework.college.model.AnnualExamReport;
import org.brijframework.college.model.AnnualExamTotalGainMarks;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.AnnualExamReportDTO;
import org.brijframework.college.service.AnnualExamTotalGainMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnualExamTotalGainMarksServiceImpl
		extends
		CRUDServiceImpl<Integer, AnnualExamTotalGainMarks, AnnualExamTotalGainMarksDao>
		implements AnnualExamTotalGainMarksService {
	@Autowired
	public AnnualExamTotalGainMarksServiceImpl(AnnualExamTotalGainMarksDao dao) {
		super(dao);
	}

	@Autowired
	private AnnualExamTotalGainMarksDao annualExamTotalGainMarksDao;
	@Autowired
	private AnnualExamReportDao annualExamReportDao;

	@Override
	public void saveAnnualExamTotalGainMarks(
			AnnualExamReportDTO annualExamReportDTO) {
		Double totalMarks = 0.0;
		Double totalGain = 0.0;
		AnnualExamTotalGainMarks annualExamTotalGainMarks = annualExamTotalGainMarksDao
				.getAnnualExamTotalGainMarks(
						annualExamReportDTO.getSessionId(),
						annualExamReportDTO.getClassId(),
						annualExamReportDTO.getSectionId(),
						annualExamReportDTO.getStudentId(),
						annualExamReportDTO.getAnnualExamType());
		List<AnnualExamReport> annualExamReports = annualExamReportDao
				.getAnnualExamReport(annualExamReportDTO.getSessionId(),
						annualExamReportDTO.getClassId(),
						annualExamReportDTO.getSectionId(),
						annualExamReportDTO.getStudentId(),
						annualExamReportDTO.getAnnualExamType());
		if (!annualExamReports.isEmpty()) {
			for (AnnualExamReport annualExamReport : annualExamReports) {
				List<AnnualExamGainMarks> annualExamGainMarksList = annualExamReport
						.getAnnualExamGainMarks();
				totalMarks += annualExamReport.getMaxMarks()
						* annualExamGainMarksList.size();
				for (AnnualExamGainMarks annualExamGainMarks : annualExamGainMarksList) {
					totalGain += annualExamGainMarks.getGainMarks();
				}
			}
			if (annualExamTotalGainMarks == null) {
				AnnualExamTotalGainMarks annualExamTotalGainMarksNew = new AnnualExamTotalGainMarks();
				Session session = new Session();
				session.setSessionId(annualExamReportDTO.getSessionId());
				StudentClasses studentClasses = new StudentClasses();
				studentClasses.setClassId(annualExamReportDTO.getClassId());
				Section section = new Section();
				section.setSectionId(annualExamReportDTO.getSectionId());
				Students students = new Students();
				students.setStudentAdmissionNo(annualExamReportDTO
						.getStudentId());
				annualExamTotalGainMarksNew.setSection(section);
				annualExamTotalGainMarksNew.setSession(session);
				annualExamTotalGainMarksNew.setStudentClasses(studentClasses);
				annualExamTotalGainMarksNew.setStudents(students);
				annualExamTotalGainMarksNew.setTotalGainMarks(totalGain);
				annualExamTotalGainMarksNew.setTotalMarks(totalMarks);
				annualExamTotalGainMarksNew
						.setAnnualExamType(annualExamReportDTO
								.getAnnualExamType());
				dao.create(annualExamTotalGainMarksNew);
			} else {
				annualExamTotalGainMarks.setTotalGainMarks(totalGain);
				annualExamTotalGainMarks.setTotalMarks(totalMarks);
				dao.update(annualExamTotalGainMarks);
			}

		}
	}

}
