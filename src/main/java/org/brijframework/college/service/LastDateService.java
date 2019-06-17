package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.LastDate;
import org.brijframework.college.models.dto.LastDateDTO;

public interface LastDateService extends CRUDService<Integer, LastDate> {

	List<String> getLastDatecheck(int monthId);

	List<LastDateDTO> getLastDates();

	String changeLastDate(LastDateDTO lastDateDTO) throws ParseException;

	List<LastDateDTO> getLastDatesBySessionId(Integer sessionId);

	Boolean createLastDate(LastDateDTO lastDateDTO) throws ParseException;

}
