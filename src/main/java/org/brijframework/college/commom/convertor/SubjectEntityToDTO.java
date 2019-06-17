package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.Subjects;
import org.brijframework.college.models.dto.SubjectDTO;

public class SubjectEntityToDTO {
	private static final SubjectEntityToDTO subjectEntityToDTO = new SubjectEntityToDTO();

	public static final SubjectEntityToDTO getInstance() {
		return subjectEntityToDTO;
	}

	public SubjectDTO getInstance(Subjects subjects) {
		SubjectDTO subjectDTO = new SubjectDTO();
		if (subjects != null) {
			if (subjects.isActive() == true)
				subjectDTO.setActive("true");
			else
				subjectDTO.setActive("false");

			subjectDTO.setSubjectName(subjects.getSubjectName());
			subjectDTO.setClassId(subjects.getStudentClasses().getClassId());
			subjectDTO
					.setClassName(subjects.getStudentClasses().getClassName());
			subjectDTO.setSectionId(subjects.getSection().getSectionId());
			subjectDTO.setSectionName(subjects.getSection().getSectionName());
			subjectDTO.setId(subjects.getSubjectsId());
			subjectDTO.setSessionId(subjects.getSession().getSessionId());
			subjectDTO.setSessionDuration(subjects.getSession().getSessionDuration());
		}
		return subjectDTO;
	}
}
