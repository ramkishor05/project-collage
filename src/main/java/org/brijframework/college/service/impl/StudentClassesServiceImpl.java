package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.model.AssignClass;
import org.brijframework.college.model.StudentClasses;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.service.StudentClassesService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("studentClassesService")
public class StudentClassesServiceImpl extends
		CRUDServiceImpl<Integer, StudentClasses, StudentClassesDao> implements
		StudentClassesService {
	@Autowired
	public StudentClassesServiceImpl(StudentClassesDao dao) {
		super(dao);
	}

	@Override
	public StudentClassesDTO getClassById(int classId) {
		StudentClasses classes = dao.get(classId);
		StudentClassesDTO classesDTO = new StudentClassesDTO();
		classesDTO.setClassId(classes.getClassId());
		classesDTO.setClassName(classes.getClassName());
		return classesDTO;
	}

	@Override
	public List<StudentClassesDTO> getAllClass() {
		List<StudentClassesDTO> lists = new ArrayList<StudentClassesDTO>();
		List<StudentClasses> classes = dao.findAll(StudentClasses.class);
		for (StudentClasses stuclass : classes) {
			StudentClassesDTO dto = new StudentClassesDTO();
			dto.setClassId(stuclass.getClassId());
			dto.setClassName(stuclass.getClassName());
			lists.add(dto);
		}
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentClassesDTO> getAssignClassByEmpId(int employeeId) {
		List<AssignClass> assignClasses = (sessionFactory.getCurrentSession()
				.createCriteria(AssignClass.class).add(Restrictions.eq(
				"employees.employeeId", employeeId))).list();
		List<StudentClassesDTO> studentClassesDTOs = new ArrayList<StudentClassesDTO>();
		for (AssignClass assignClass : assignClasses) {
			StudentClassesDTO studentClassesDTO = new StudentClassesDTO();
			studentClassesDTO.setClassId(assignClass.getStudentClasses()
					.getClassId());
			studentClassesDTO.setClassName(assignClass.getStudentClasses()
					.getClassName());
			studentClassesDTOs.add(studentClassesDTO);
		}
		return studentClassesDTOs;
	}

	@Override
	public StudentClassesDTO getClassByName(String className) {
		StudentClasses studentClasses = dao.getClassByName(className);
		StudentClassesDTO studentClassesDTO = new StudentClassesDTO();
		if (studentClasses != null) {
			studentClassesDTO.setClassId(studentClasses.getClassId());
			studentClassesDTO.setClassName(studentClasses.getClassName());
		}
		return studentClassesDTO;
	}
}
