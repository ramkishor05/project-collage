package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.commom.convertor.SubjectDTOToEntity;
import org.brijframework.college.commom.convertor.SubjectEntityToDTO;
import org.brijframework.college.dao.MonthlyExamReportDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.SubjectDao;
import org.brijframework.college.model.MonthlyExamReport;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.SubjectDTO;
import org.brijframework.college.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("subjectService")
public class SubjectServiceImpl extends
		CRUDServiceImpl<Integer, Subjects, SubjectDao> implements
		SubjectService {

	@Autowired
	public SubjectServiceImpl(SubjectDao dao) {
		super(dao);
	}

	@Autowired
	MonthlyExamReportDao monthlyExamReportDao;
	@Autowired
	StudentsAdmissionDao studentDao;

	@Transactional
	public void addSubject(SubjectDTO subjectDTO) {
		SubjectDTOToEntity subjectDTOToEntity = new SubjectDTOToEntity();
		Subjects subjects = subjectDTOToEntity.getInstance(subjectDTO);
		dao.create(subjects);

	}

	@Override
	@Transactional
	public List<SubjectDTO> getSubjectById(int classId, int sectionId,
			int sessionId) {
		List<Subjects> list = dao.getSubjectList(classId, sectionId, sessionId);
		List<SubjectDTO> lists = new ArrayList<SubjectDTO>();

		for (Subjects subjects : list) {
			SubjectDTO dto = new SubjectDTO();
			dto.setSubjectName(subjects.getSubjectName());
			dto.setId(subjects.getSubjectsId());
			lists.add(dto);
		}

		return lists;

	}

	@Override
	@Transactional
	public SubjectDTO getSubjectById(Integer id) {
		SubjectEntityToDTO subjectEntityTodto = new SubjectEntityToDTO();
		return subjectEntityTodto.getInstance(dao.get(id));

	}

	@Override
	public void updateSubject(SubjectDTO subjectdto) {
		SubjectDTOToEntity subjectDTOToEntity = SubjectDTOToEntity
				.getInstance();
		Subjects subjects = subjectDTOToEntity.getInstance(subjectdto);
		subjects.setSubjectsId(subjectdto.getId());
		dao.update(subjects);

	}

	@Override
	public void setActiveById(int id) {
		dao.get(id).setActive(false);

	}

	@Override
	public SubjectDTO getSubjects(int classId, int sectionId,
			String subjectName, int sessionId) {
		SubjectEntityToDTO subjectEntityToDTO = SubjectEntityToDTO
				.getInstance();
		return subjectEntityToDTO.getInstance(dao.getSubjects(classId,
				sectionId, subjectName, sessionId));
	}

	@Override
	public List<SubjectDTO> getSubjectforClass(int classId, Integer sessionId) {
		List<Subjects> list = dao.getSubjectforclass(classId, sessionId);
		List<SubjectDTO> lists = new ArrayList<SubjectDTO>();

		for (Subjects subjects : list) {
			SubjectDTO dto = new SubjectDTO();
			dto.setSubjectName(subjects.getSubjectName());
			dto.setId(subjects.getSubjectsId());
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public List<SubjectDTO> getSubjectByIdforMarks(int classId, int sectionId,
			int sessionId, int monthId) {
		List<Subjects> list = dao.getSubjectList(classId, sectionId, sessionId);
		List<SubjectDTO> lists = new ArrayList<SubjectDTO>();
		for (Subjects subjects : list) {
			SubjectDTO dto = new SubjectDTO();
			MonthlyExamReport report = monthlyExamReportDao.getMaxMarks(
					classId, sessionId, sectionId, monthId,
					subjects.getSubjectsId());
			if (report == null)
				dto.setMaxMarks(0);
			else
				dto.setMaxMarks(report.getMaxMarks());
			dto.setSubjectName(subjects.getSubjectName());
			dto.setId(subjects.getSubjectsId());

			lists.add(dto);
		}

		return lists;

	}

	@Override
	public List<StudentDTO> getSubjectByIdforMarksStudent(int classId,
			int sectionId, int sessionId, int monthId) {
		List<Subjects> list = dao.getSubjectList(classId, sectionId, sessionId);
		List<StudentDTO> lists = new ArrayList<StudentDTO>();
		List<Students> studentlist = studentDao.getStudentsbyClassSectionId(
				classId, sectionId, sessionId);
		for (Students student : studentlist) {
			List<SubjectDTO> slists = new ArrayList<SubjectDTO>();
			StudentDTO sdto = new StudentDTO();
			sdto.setFullName(student.getFirstName() + " "
					+ student.getMiddleName() + " " + student.getLastName());
			sdto.setAdmissionNo(student.getStudentAdmissionNo());
			for (Subjects subjects : list) {
				SubjectDTO dto = new SubjectDTO();
				MonthlyExamReport report = monthlyExamReportDao
						.getMonthlyExamReport(sessionId, classId, sectionId,
								student.getStudentAdmissionNo(),
								subjects.getSubjectsId(), monthId);
				if (report == null) {
					dto.setGainMarks(0);
				} else
					dto.setGainMarks(report.getMarks());
				dto.setSubjectName(subjects.getSubjectName());
				dto.setId(subjects.getSubjectsId());
				slists.add(dto);
			}

			sdto.setSubjectDTO(slists);
			lists.add(sdto);
		}
		return lists;
	}
}
