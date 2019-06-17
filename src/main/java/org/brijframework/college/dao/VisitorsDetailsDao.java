package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.VisitorsDetails;

public interface VisitorsDetailsDao extends DAO<Integer, VisitorsDetails> {
	public List<VisitorsDetails> getVisitorsDetailsByDate(Date date);
	public List<VisitorsDetails> getVisitorsDetailsByName(String visitorName);

}
