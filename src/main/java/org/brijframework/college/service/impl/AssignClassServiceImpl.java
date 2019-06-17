package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.brijframework.college.dao.AssignClassDao;
import org.brijframework.college.dao.EmployeeDao;
import org.brijframework.college.dao.SectionDao;
import org.brijframework.college.dao.StudentClassesDao;
import org.brijframework.college.model.AssignClass;
import org.brijframework.college.models.dto.AssignClassDTO;
import org.brijframework.college.models.dto.StudentClassesDTO;
import org.brijframework.college.service.AssignClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("assignClassService")
public class AssignClassServiceImpl extends
		CRUDServiceImpl<Integer, AssignClass, AssignClassDao> implements
		AssignClassService {

	@Autowired
	StudentClassesDao studentClassDao;
	@Autowired
	EmployeeDao employeesDao;
	@Autowired
	SectionDao sectionDao;

	@Autowired
	public AssignClassServiceImpl(AssignClassDao dao) {
		super(dao);
	}

	@Override
	public Collection<AssignClassDTO> getAssignTeacherByClassId(int employeeId) {
		Collection<AssignClassDTO> assignClassDTOs = new ArrayList<AssignClassDTO>();
		List<AssignClass> assignclassDTOs = dao.getAssignClassById(employeeId);
		if (assignclassDTOs.isEmpty() || assignclassDTOs == null) {

		} else {
			for (AssignClass assignClass : assignclassDTOs) {
				AssignClassDTO assignClassDTO = new AssignClassDTO();
				assignClassDTO.setAssignClassId(String.valueOf(assignClass
						.getAssignClassId()));
				assignClassDTO.setClassId(String.valueOf(assignClass
						.getStudentClasses().getClassId()));
				assignClassDTO.setClassName(assignClass.getStudentClasses()
						.getClassName());
				assignClassDTO.setEmployeeId(String.valueOf(assignClass
						.getEmployees().getEmployeeId()));
				assignClassDTO.setEmployeeFullName(assignClass.getEmployees()
						.getFirstName()
						+ assignClass.getEmployees().getLastName());
				assignClassDTO.setSectionName(assignClass.getSection()
						.getSectionName());
				assignClassDTOs.add(assignClassDTO);
			}
		}
		return assignClassDTOs;
	}

	@Override
	public String checkAssignClassesForTecher(int sectionId, int classId) {
		String assignClassStatus = "";
		AssignClass assignClass = dao.checkAssignClassesForTecher(sectionId,
				classId);
		if (assignClass != null) {
			assignClassStatus = assignClass.getEmployees().getFirstName()+ " "
					+ assignClass.getEmployees().getLastName();
		} else
			assignClassStatus = "true";
		return assignClassStatus;
	}

	@Override
	public void assignClassToTeacher(AssignClassDTO assignClassDTO) {
		AssignClass assignClass = new AssignClass();
		assignClass.setEmployees(employeesDao.get(Integer
				.parseInt(assignClassDTO.getEmployeeId())));
		assignClass.setActive(true);
		assignClass.setStudentClasses(studentClassDao.get(Integer
				.parseInt(assignClassDTO.getClassId())));
		assignClass.setSection(sectionDao.get(assignClassDTO.getSectionId()));
		dao.create(assignClass);
	}

	@Override
	public Boolean deleteAssignedClassById(int classAssignId) {
		AssignClass assignClass = dao.get(classAssignId);
		assignClass.setActive(false);
		dao.update(assignClass);
		return true;
	}

	@Override
	public StudentClassesDTO getAssignClassAndSection(int employeeId) {
		StudentClassesDTO studentClassesDTO=null;
		AssignClass assignClass = dao.getAssignClassAndSection(employeeId);
		if (assignClass != null) {
			studentClassesDTO = new StudentClassesDTO();
			studentClassesDTO.setSectionId(assignClass.getSection()
					.getSectionId());
			studentClassesDTO.setSectionName(assignClass.getSection()
					.getSectionName());
			studentClassesDTO.setClassId(assignClass.getStudentClasses()
					.getClassId());
			studentClassesDTO.setClassName(assignClass.getStudentClasses()
					.getClassName());
		}
		return studentClassesDTO;
	}
}
