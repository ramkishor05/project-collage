package org.brijframework.college.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.commom.convertor.MonthlyExamReportDTOToEntity;
import org.brijframework.college.commom.convertor.MonthlyExamReportEntityToDTO;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.MonthlyExamReportDao;
import org.brijframework.college.dao.SubjectDao;
import org.brijframework.college.model.Month;
import org.brijframework.college.model.MonthlyExamReport;
import org.brijframework.college.model.Section;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.CommonDTO;
import org.brijframework.college.models.dto.MonthDTO;
import org.brijframework.college.models.dto.MonthlyExamReportDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.brijframework.college.models.dto.SubjectMarksDTO;
import org.brijframework.college.service.MonthlyExamReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MonthlyExamReportServiceImpl extends
		CRUDServiceImpl<Integer, MonthlyExamReport, MonthlyExamReportDao>
		implements MonthlyExamReportService {
	@Autowired
	public MonthlyExamReportServiceImpl(MonthlyExamReportDao dao) {
		super(dao);
	}

	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private MonthDao monthDao;

	@Override
	public MonthlyExamReportDTO getMonthlyExamReport(int sessionId,
			int classId, int sectionId, String studentId, int subjectId,
			int monthId) {
		MonthlyExamReportDTO monthlyExamReportDTO = null;
		MonthlyExamReport monthlyExamReport = dao.getMonthlyExamReport(
				sessionId, classId, sectionId, studentId, subjectId, monthId);
		MonthlyExamReportEntityToDTO monthlyExamReportEntityToDTO = MonthlyExamReportEntityToDTO
				.getInstance();
		if (monthlyExamReport != null) {
			monthlyExamReportDTO = monthlyExamReportEntityToDTO
					.convertEntityToDTO(monthlyExamReport);
		}
		return monthlyExamReportDTO;
	}

	@Override
	public void saveMonthlyExamReportData(
			MonthlyExamReportDTO monthlyExamReportDTO) {
		for (SubjectMarksDTO subjectMarksDTO : monthlyExamReportDTO
				.getSubjectMarksDTOs()) {
			if(subjectMarksDTO
					.getSubjectMarksDTOs()!=null)
			{
			for (SubjectMarksDTO subjectMarksDTONew : subjectMarksDTO
					.getSubjectMarksDTOs()) {
				
				MonthlyExamReport monthlyExamReport = dao.getMonthlyExamReport(
						monthlyExamReportDTO.getSessionId(),
						monthlyExamReportDTO.getClassId(),
						monthlyExamReportDTO.getSectionId(),
						subjectMarksDTO.getStudentId(),
						Integer.parseInt(subjectMarksDTONew.getSubjectId()),
						monthlyExamReportDTO.getMonthId());
				if (monthlyExamReport == null) {
					monthlyExamReport = new MonthlyExamReport();
					Subjects subjects = new Subjects();
					subjects.setSubjectsId(Integer.parseInt(subjectMarksDTONew
							.getSubjectId()));
					Month month = new Month();
					month.setMonthId(monthlyExamReportDTO.getMonthId());
					Section section = new Section();
					section.setSectionId(monthlyExamReportDTO.getSectionId());
					Session session = new Session();
					session.setSessionId(monthlyExamReportDTO.getSessionId());
					StudentClasses studentClasses = new StudentClasses();
					studentClasses
							.setClassId(monthlyExamReportDTO.getClassId());
					Students students = new Students();
					students.setStudentAdmissionNo(subjectMarksDTO
							.getStudentId());
					monthlyExamReport.setCreatedAt(new Date());
					monthlyExamReport.setMarks(Double
							.parseDouble(subjectMarksDTONew.getGainMarks()));
					monthlyExamReport.setMaxMarks(Double
							.parseDouble(subjectMarksDTONew.getMaxMarks()));
					monthlyExamReport.setMonth(month);
					monthlyExamReport.setSection(section);
					monthlyExamReport.setSession(session);
					monthlyExamReport.setStudentClasses(studentClasses);
					monthlyExamReport.setStudents(students);
					monthlyExamReport.setSubjects(subjects);
					monthlyExamReport.setUpdatedAt(new Date());
					dao.create(monthlyExamReport);
				} else {
					monthlyExamReport.setMarks(Double
							.parseDouble(subjectMarksDTONew.getGainMarks()));
					monthlyExamReport.setMaxMarks(Double
							.parseDouble(subjectMarksDTONew.getMaxMarks()));
					dao.update(monthlyExamReport);
				}
			}
		}
		}
	}

	@Override
	public Map<String, Object> getMonthlyExamReport(int sessionId, int classId,
			int sectionId, String studentId) {
		int maxMonthSerialNo = 0;
		// get max moth serial no to find to whom subjects marks assign
		List<MonthlyExamReport> monthlyExamReports = dao.maxMonthId(sessionId,
				classId, sectionId, studentId);
		for (MonthlyExamReport monthlyExamReport : monthlyExamReports) {
			maxMonthSerialNo = monthlyExamReport.getMonth().getSerialNo();
		}
		if (maxMonthSerialNo == 9) {
			List<MonthlyExamReport> monthlyExamReportsForJanToMarch = dao
					.getMonthIdBeetweenJanToMarch(sessionId, classId,
							sectionId, studentId);
			for (MonthlyExamReport monthlyExamReport : monthlyExamReportsForJanToMarch) {
				maxMonthSerialNo = monthlyExamReport.getMonth().getSerialNo();
			}
		}
		// end
		// result of assign subject marks for
		List<Integer> maonthSerialNos = new ArrayList<Integer>();
		for (int i = 1; i <= maxMonthSerialNo; i++) {
			maonthSerialNos.add(i);
		}
		// end
		// List month array
		List<MonthDTO> monthDTOs = new ArrayList<MonthDTO>();
		for (Integer integer : maonthSerialNos) {
			Month month = monthDao.getMonthBySerialno(integer);
			MonthDTO monthDTO = new MonthDTO();
			monthDTO.setMonthName(month.getMonthName());
			monthDTOs.add(monthDTO);
		}
		// Subjectlist
		List<SubjectDTO> subjectDTOs = new ArrayList<SubjectDTO>();
		for (Subjects subjects : subjectDao.getSubjectList(classId, sectionId,
				sessionId)) {
			SubjectDTO subjectDTO = new SubjectDTO();
			subjectDTO.setSubjectName(subjects.getSubjectName());
			List<CommonDTO> commonDTOs = new ArrayList<CommonDTO>();
			for (Integer integer : maonthSerialNos) {
				// need to remove
				Month month = monthDao.getMonthBySerialno(integer);
				// end need
				CommonDTO commonDTO = new CommonDTO();
				MonthlyExamReport monthlyExamReport = dao.getMonthlyExamReport(
						sessionId, classId, sectionId, studentId,
						subjects.getSubjectsId(), month.getMonthId());
				if (monthlyExamReport == null) {
					commonDTO.setMaxMarks("");
					commonDTO.setGainMarks("");
					commonDTOs.add(commonDTO);
				} else {
					commonDTO.setMaxMarks(new DecimalFormat("##")
							.format(monthlyExamReport.getMaxMarks()));
					commonDTO.setGainMarks(new DecimalFormat("##")
							.format(monthlyExamReport.getMarks()));
					commonDTOs.add(commonDTO);
				}
				subjectDTO.setCommonDTOs(commonDTOs);
			}
			subjectDTOs.add(subjectDTO);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MonthDTO", monthDTOs);
		map.put("SubjectDTO", subjectDTOs);
		return map;
	}

	@Override
	public List<MonthlyExamReportDTO> getMonthlyExamReport(int sessionId,
			int classId, int sectionId, String studentId, int monthId) {
		List<MonthlyExamReport> monthlyExamReports = dao.getMonthlyExamReports(
				sessionId, classId, sectionId, studentId, monthId);
		List<MonthlyExamReportDTO> monthlyExamReportDTOs = new ArrayList<MonthlyExamReportDTO>();
		MonthlyExamReportEntityToDTO monthlyExamReportEntityToDTO = MonthlyExamReportEntityToDTO
				.getInstance();
		for (MonthlyExamReport monthlyExamReport : monthlyExamReports) {
			MonthlyExamReportDTO monthlyExamReportDTO = monthlyExamReportEntityToDTO
					.convertEntityToDTO(monthlyExamReport);
			monthlyExamReportDTOs.add(monthlyExamReportDTO);
		}
		return monthlyExamReportDTOs;
	}

	@Override
	public MonthlyExamReportDTO getMonthlyExamReport(int monthlyExamReportId) {
		MonthlyExamReportEntityToDTO monthlyExamReportEntityToDTO = MonthlyExamReportEntityToDTO
				.getInstance();
		return monthlyExamReportEntityToDTO.convertEntityToDTO(dao
				.get(monthlyExamReportId));
	}

	@Override
	public void updateMonthlyExamReportData(
			MonthlyExamReportDTO monthlyExamReportDTO) {
		MonthlyExamReportDTOToEntity monthlyExamReportDTOToEntity = MonthlyExamReportDTOToEntity
				.getInstance();
		MonthlyExamReport monthlyExamReport = monthlyExamReportDTOToEntity
				.convertEntityToDTO(monthlyExamReportDTO);
		dao.update(monthlyExamReport);
	}

}
