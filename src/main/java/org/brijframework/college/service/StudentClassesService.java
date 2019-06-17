package org.brijframework.college.service;

import java.util.List;

import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.StudentClassesDTO;

public interface StudentClassesService extends
		CRUDService<Integer, StudentClasses> {

	List<StudentClassesDTO> getAllClass();

	StudentClassesDTO getClassById(int classId);

	public List<StudentClassesDTO> getAssignClassByEmpId(int employeeId);
	StudentClassesDTO getClassByName(String className);
		
}
