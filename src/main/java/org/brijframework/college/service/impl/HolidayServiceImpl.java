package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brijframework.college.dao.HolidayDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.Holiday;
import org.brijframework.college.models.dto.HolidayDTO;
import org.brijframework.college.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HolidayServiceImpl extends
		CRUDServiceImpl<Integer, Holiday, HolidayDao> implements HolidayService {
	@Autowired
	public HolidayServiceImpl(HolidayDao dao) {
		super(dao);
	}

	@Autowired
	private SessionDao sessionDao;

	@Override
	public Map<String, Object> getDataForCreateHoliday(int monthId, int year)
			throws ParseException {
		int MONTHID = 0;
		int YEAR = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (monthId == 14 && year == 0) {
			calendar.setTime(new Date());
			MONTHID = calendar.get(Calendar.MONTH) + 1;
			YEAR = calendar.get(Calendar.YEAR);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date firstOfDateMonth = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date lastOfDateMonth = calendar.getTime();
			List<Integer> integers = new ArrayList<Integer>();
			List<Holiday> holidays = dao.getDataForCreateHoliday(
					firstOfDateMonth, lastOfDateMonth);
			for (Holiday holiday : holidays) {
				Date holidayDate = holiday.getHolidayDate();
				calendar.setTime(holidayDate);
				integers.add(calendar.get(Calendar.DAY_OF_MONTH));
			}
			map = generateDateList(firstOfDateMonth, integers);
		} else {
			String date1 = "";
			if (monthId == 0) {
				date1 = (year - 1) + "-" + 12 + "-" + 01;
			} else if (monthId == 13) {
				date1 = (year + 1) + "-" + 1 + "-" + 01;
			} else {
				date1 = (year) + "-" + monthId + "-"
						+ 01;
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			calendar.setTime(date);
			MONTHID = calendar.get(Calendar.MONTH) + 1;
			YEAR = calendar.get(Calendar.YEAR);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date firstOfDateMonth = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date lastOfDateMonth = calendar.getTime();
			List<Integer> integers = new ArrayList<Integer>();
			List<Holiday> holidays = dao.getDataForCreateHoliday(
					firstOfDateMonth, lastOfDateMonth);
			for (Holiday holiday : holidays) {
				Date holidayDate = holiday.getHolidayDate();
				calendar.setTime(holidayDate);
				integers.add(calendar.get(Calendar.DAY_OF_MONTH));
			}
			map = generateDateList(firstOfDateMonth, integers);
		}
		map.put("YEAR", YEAR);
		map.put("MONTHID", MONTHID);
		return map;
	}

	private Map<String, Object> generateDateList(Date firstOfDateMonth,
			List<Integer> integers) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstOfDateMonth);
		int month = calendar.get(Calendar.MONTH);
		// Set month of Year
		map.put("MONTHOfYEAR",
				new SimpleDateFormat("yyyy-MMM").format(calendar.getTime()));

		List<Integer> daysList = new ArrayList<Integer>();
		List<HolidayDTO> holidayDTOs = new ArrayList<HolidayDTO>();
		// extra fields
		int days = calendar.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i < days; i++) {
			HolidayDTO holidayDTO = new HolidayDTO();
			holidayDTO.setHolidayCount("0");
			holidayDTOs.add(holidayDTO);
		}

		do {
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			daysList.add(calendar.get(Calendar.DAY_OF_MONTH));
			HolidayDTO holidayDTO = new HolidayDTO();
			holidayDTO.setHolidayDate(new SimpleDateFormat("dd-MMM-yyyy")
					.format(calendar.getTime()));
			holidayDTO.setHolidayCount(String.valueOf(calendar
					.get(Calendar.DAY_OF_MONTH)));
			if (day == Calendar.SUNDAY) {
				holidayDTO.setDescription("Sunday");
				holidayDTO.setHolidayId(0);
			} else if (integers.contains(calendar.get(Calendar.DAY_OF_MONTH))) {
				String date1 = calendar.get(calendar.YEAR) + "-" + (month + 1)
						+ "-" + calendar.get(Calendar.DAY_OF_MONTH);
				Holiday holiday = dao
						.getDataForCreateHoliday(new SimpleDateFormat(
								"yyyy-MM-dd").parse(date1));
				holidayDTO.setDescription(holiday.getDescription());
				holidayDTO.setHolidayId(holiday.getHolidayId());
			} else {
				holidayDTO.setDescription("");
				holidayDTO.setHolidayId(0);
			}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			holidayDTOs.add(holidayDTO);
		} while (calendar.get(Calendar.MONTH) == month);
		map.put("DAYININT", daysList);
		map.put("HOLIDAYSLIST", holidayDTOs);
		return map;
	}

	@Override
	public void crateHolidays(HolidayDTO holidayDTO) throws ParseException {
		Holiday holiday = new Holiday();
		holiday.setDescription(holidayDTO.getDescription());
		holiday.setHolidayDate(new SimpleDateFormat("dd-MMM-yyyy")
				.parse(holidayDTO.getHolidayDate()));
		holiday.setSession(sessionDao.findCurrentSession());
		dao.create(holiday);

	}

	@Override
	public HolidayDTO checkHolidays(String holidayDate) throws Exception {
		HolidayDTO holidayDTO = new HolidayDTO();
		Holiday holiday = dao.getDataForCreateHoliday(new SimpleDateFormat(
				"dd-MMM-yyyy").parse(holidayDate));
		if (holiday != null) {
			holidayDTO.setDescription(holiday.getDescription());
			holidayDTO.setHolidayId(holiday.getHolidayId());
		}
		return holidayDTO;
	}

	@Override
	public void editHolidays(HolidayDTO holidayDTO) throws Exception {
		Holiday holiday = dao.get(holidayDTO.getHolidayId());
		holiday.setDescription(holidayDTO.getDescription());
		dao.update(holiday);

	}

}
