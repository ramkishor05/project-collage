package org.brijframework.college.service;

import java.util.Collection;

import org.brijframework.college.model.AssignClass;
import org.brijframework.college.models.dto.AssignClassDTO;
import org.brijframework.college.models.dto.StudentClassesDTO;

public interface AssignClassService extends CRUDService<Integer, AssignClass> {

	Collection<AssignClassDTO> getAssignTeacherByClassId(int employeeId);

	void assignClassToTeacher(AssignClassDTO assignClassDTO);

	String checkAssignClassesForTecher(int sectionId, int classId);

	Boolean deleteAssignedClassById(int classAssignId);

	StudentClassesDTO getAssignClassAndSection(int employeeId);
}