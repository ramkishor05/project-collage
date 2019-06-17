package org.brijframework.college.commom.convertor;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.StudentClassesDTO;

public class StudentClassEntityToDTO {

	public static List<StudentClassesDTO> StudentEntityToDTO(
			List<StudentClasses> list) {
		List<StudentClassesDTO> classesDTOs = new ArrayList<StudentClassesDTO>();
		for (StudentClasses studentClass : list) {
			StudentClassesDTO classesDTO = new StudentClassesDTO();
			classesDTO.setClassId(studentClass.getClassId());
			classesDTO.setClassName(studentClass.getClassName());
			classesDTOs.add(classesDTO);
		}
		return classesDTOs;
	}
}
