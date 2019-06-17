package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annual_exam_gain_marks")
@SuppressWarnings("serial")
public class AnnualExamGainMarks extends AbstractPO<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "annual_exam_gain_marks_id")
	private int annualExamGainMarksId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "annual_exam_report_id")
	private AnnualExamReport annualExamReport;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subjects subjects;
	@Column(name = "gain_marks")
	private Double gainMarks;

	@Override
	public Integer getId() {
		return this.annualExamGainMarksId;
	}

	@Override
	public void setId(Integer id) {
		this.annualExamGainMarksId = id;
	}

	public int getAnnualExamGainMarksId() {
		return annualExamGainMarksId;
	}

	public void setAnnualExamGainMarksId(int annualExamGainMarksId) {
		this.annualExamGainMarksId = annualExamGainMarksId;
	}

	public AnnualExamReport getAnnualExamReport() {
		return annualExamReport;
	}

	public void setAnnualExamReport(AnnualExamReport annualExamReport) {
		this.annualExamReport = annualExamReport;
	}

	public Subjects getSubjects() {
		return subjects;
	}

	public void setSubjects(Subjects subjects) {
		this.subjects = subjects;
	}

	public Double getGainMarks() {
		return gainMarks;
	}

	public void setGainMarks(Double gainMarks) {
		this.gainMarks = gainMarks;
	}

}
