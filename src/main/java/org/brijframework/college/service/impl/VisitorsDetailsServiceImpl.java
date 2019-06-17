package org.brijframework.college.service.impl;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.dao.VisitorsDetailsDao;
import org.brijframework.college.model.VisitorsDetails;
import org.brijframework.college.service.VisitorsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitorsDetailsServiceImpl extends
		CRUDServiceImpl<Integer, VisitorsDetails, VisitorsDetailsDao> implements
		VisitorsDetailsService {
	@Autowired
	public VisitorsDetailsServiceImpl(VisitorsDetailsDao dao) {
		super(dao);
	}

	@Override
	public Map<String, Object> getVisitorsDetailsByDate(String stringDate)
			throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentDate", stringDate);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (stringDate.equals("")) {
			date = sdf.parse(sdf.format(date));
		} else {
			date = sdf.parse(stringDate);
		}
		List<VisitorsDetails> visitorsDetails = dao
				.getVisitorsDetailsByDate(date);
		map.put("VisitorsDetailsList", visitorsDetails);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		map.put("yesterDayDate", sdf.format(calendar.getTime()));
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		map.put("tommarowDayDate", sdf.format(calendar.getTime()));
		return map;
	}

	@Override
	public void addVisitorsDetails(VisitorsDetails visitorsDetails) {
		Time time = new Time(Calendar.getInstance().getTimeInMillis());
		visitorsDetails.setInTime(time);
		visitorsDetails.setActive(true);
		visitorsDetails.setVisitDate(new Date());
		dao.create(visitorsDetails);
	}

	@Override
	public List<VisitorsDetails> getVisitorsDetailsByName(String visitorName) {
		return dao.getVisitorsDetailsByName(visitorName);
	}

}
