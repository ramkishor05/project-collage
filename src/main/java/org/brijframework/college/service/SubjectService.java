package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.StudentDTO;
import org.brijframework.college.models.dto.SubjectDTO;

public interface SubjectService extends CRUDService<Integer, Subjects> {

	void addSubject(SubjectDTO subjectDTO);

	public List<SubjectDTO> getSubjectById(int classId, int sectionId, int sessionId);

	public SubjectDTO getSubjectById(Integer id);

	public void updateSubject(SubjectDTO subjectdto);

	public void setActiveById(int id);

	public SubjectDTO getSubjects(int classId, int sectionId, String subjectName, int sessionId);

	List<SubjectDTO> getSubjectforClass(int classId, Integer sessionId);

	public List<SubjectDTO> getSubjectByIdforMarks(int classId, int sectionId, int sessionId,int monthId);

	public List<StudentDTO> getSubjectByIdforMarksStudent(int classId, int sectionId,
			int sessionId, int monthId);

}
