package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.LastDateDao;
import org.brijframework.college.dao.MonthDao;
import org.brijframework.college.dao.StudentFeeSubmissionDetailsDao;
import org.brijframework.college.model.LastDate;
import org.brijframework.college.model.Month;
import org.brijframework.college.model.Session;
import org.brijframework.college.models.dto.LastDateDTO;
import org.brijframework.college.service.LastDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("lastDateService")
public class LastDateServiceImpl extends
		CRUDServiceImpl<Integer, LastDate, LastDateDao> implements
		LastDateService {
	@Autowired
	public LastDateServiceImpl(LastDateDao dao) {
		super(dao);
	}

	@Autowired
	MonthDao monthDao;
	@Autowired
	StudentFeeSubmissionDetailsDao feesubmissiondetailsdao;

	@Override
	public List<String> getLastDatecheck(int monthId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<String> check = new ArrayList<String>();
		LastDate last = dao.getLastDatebyMonth(monthId);
		Date date = new Date();
		int result = date.compareTo(last.getLastdate());
		check.add(String.valueOf(result));
		check.add(df.format(last.getLastdate()));
		return check;
	}

	@Override
	public List<LastDateDTO> getLastDates() {
		List<LastDate> lastdates = dao.findAll(LastDate.class);
		List<LastDateDTO> lastdto = new ArrayList<LastDateDTO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (LastDate last : lastdates) {
			LastDateDTO dto = new LastDateDTO();
			dto.setLastdate(df.format(last.getLastdate()));
			dto.setMonthName(monthDao.get(last.getMonth().getMonthId())
					.getMonthName());
			dto.setLastdateId(last.getLastdateId());
			dto.setMonthId(last.getMonth().getMonthId());
			dto.setSessionId(last.getSession().getSessionId());
			lastdto.add(dto);
		}
		return lastdto;
	}

	@Override
	public String changeLastDate(LastDateDTO lastDateDTO) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		LastDate last = dao.get(lastDateDTO.getLastdateId());
		int monthId=last.getMonth().getMonthId();
		int sessionId=last.getSession().getSessionId();
		String result="";
		List<Integer> list=feesubmissiondetailsdao.getReceiptformonth(monthId,sessionId,last.getLastdate());
		if(list.size()>0)
		{
		result="false";
		}
		else
		{
			last.setLastdate(df.parse(lastDateDTO.getLastdate()));
			result="true";
		}
		return result;

	}

	@Override
	public List<LastDateDTO> getLastDatesBySessionId(Integer sessionId) {
		List<LastDate> lastdates = dao.getLastDatesBySessionId(sessionId);
		List<LastDateDTO> lastdto = new ArrayList<LastDateDTO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (LastDate last : lastdates) {
			LastDateDTO dto = new LastDateDTO();
			dto.setLastdate(df.format(last.getLastdate()));
			dto.setMonthName(monthDao.get(last.getMonth().getMonthId())
					.getMonthName());
			dto.setLastdateId(last.getLastdateId());
			dto.setMonthId(last.getMonth().getMonthId());
			dto.setSessionId(last.getSession().getSessionId());
			lastdto.add(dto);
		}
		return lastdto;
	}

	@Override
	public Boolean createLastDate(LastDateDTO lastDateDTO)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if ((dao.getLastDatebyMonth(lastDateDTO.getMonthId(),
				lastDateDTO.getSessionId())) == null) {
			LastDate date = new LastDate();
			Session session = new Session();
			Month month = new Month();
			session.setSessionId(lastDateDTO.getSessionId());
			month.setMonthId(lastDateDTO.getMonthId());
			date.setSession(session);
			date.setMonth(month);
			date.setLastdate(df.parse(lastDateDTO.getLastdate()));
			dao.create(date);
			return true;
		} else {
			return false;
		}
	}

}
