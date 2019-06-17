package org.brijframework.college.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "annual_exam_report")
public class AnnualExamReport extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "annual_exam_report_id")
	private int annualExamReportId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private StudentClasses studentClasses;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private Section section;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Students students;
	@Column(name = "max_marks")
	private Double maxMarks;
	@Column(name = "annual_exam_type")
	private String annualExamType;
	@Column(name = "exam_type")
	private String examType;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annualExamReport")
	private List<AnnualExamGainMarks> annualExamGainMarks;

	@Override
	public Integer getId() {
		return this.annualExamReportId;
	}

	@Override
	public void setId(Integer id) {
		this.annualExamReportId = id;
	}

	public int getAnnualExamReportId() {
		return annualExamReportId;
	}

	public void setAnnualExamReportId(int annualExamReportId) {
		this.annualExamReportId = annualExamReportId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
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

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	public Double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getAnnualExamType() {
		return annualExamType;
	}

	public void setAnnualExamType(String annualExamType) {
		this.annualExamType = annualExamType;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public List<AnnualExamGainMarks> getAnnualExamGainMarks() {
		return annualExamGainMarks;
	}

	public void setAnnualExamGainMarks(
			List<AnnualExamGainMarks> annualExamGainMarks) {
		this.annualExamGainMarks = annualExamGainMarks;
	}
}
