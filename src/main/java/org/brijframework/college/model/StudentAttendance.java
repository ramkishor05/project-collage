package org.brijframework.college.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "student_attendance")
public class StudentAttendance extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_attendance_id")
	private Integer studentAttendenceId;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "student_id")
	private Students students;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "class_id")
	private StudentClasses studentClasses;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "section_id")
	private Section section;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "employee_id")
	private Employees employees;
	@Column(name = "attendance_status")
	private String attendanceStatus;
	@Column(name = "date_of_attendance")
	private Date datOfAttendance;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public Integer getStudentAttendenceId() {
		return studentAttendenceId;
	}

	public void setStudentAttendenceId(Integer studentAttendenceId) {
		this.studentAttendenceId = studentAttendenceId;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	public StudentClasses getStudentClasses() {
		return studentClasses;
	}

	public void setStudentClasses(StudentClasses studentClasses) {
		this.studentClasses = studentClasses;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Date getDatOfAttendance() {
		return datOfAttendance;
	}

	public void setDatOfAttendance(Date datOfAttendance) {
		this.datOfAttendance = datOfAttendance;
	}

}
