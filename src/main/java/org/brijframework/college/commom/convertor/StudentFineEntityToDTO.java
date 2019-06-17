package org.brijframework.college.commom.convertor;

import org.brijframework.college.model.StudentFine;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.StudentFineDTO;

public class StudentFineEntityToDTO {
	private static final StudentFineEntityToDTO studentFineEntityToDTO = new StudentFineEntityToDTO();

	public static final StudentFineEntityToDTO getInstance() {
		return studentFineEntityToDTO;
	}

	public StudentFineDTO convertEntityToDTO(StudentFine studentFine) {
		StudentFineDTO studentFineDTO = new StudentFineDTO();
		if (studentFine == null) {
		} else {
			Students students = studentFine.getStudents();
			studentFineDTO.setCreatedAt(studentFine.getCreatedAt().toString());
			studentFineDTO.setFineAmount(studentFine.getFineAmount());
			studentFineDTO.setFineName(studentFine.getFineName());
			studentFineDTO.setStudentAdmissionNo(students
					.getStudentAdmissionNo());
			studentFineDTO.setStudentFineId(studentFine.getStudentFineId());
			if (studentFine.isPaid() == true) {
				studentFineDTO.setPaid("yes");
			} else {
				studentFineDTO.setPaid("no");
			}
		}
		return studentFineDTO;
	}
}
