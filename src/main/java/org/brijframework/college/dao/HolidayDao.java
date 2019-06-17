package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.Holiday;

public interface HolidayDao extends DAO<Integer, Holiday> {
	public List<Holiday> getDataForCreateHoliday(Date fromDate, Date toDate);
	public Holiday getDataForCreateHoliday(Date date);
}
