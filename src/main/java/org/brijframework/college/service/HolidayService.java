package org.brijframework.college.service;

import java.util.Map;

import org.brijframework.college.model.Holiday;
import org.brijframework.college.models.dto.HolidayDTO;

public interface HolidayService extends CRUDService<Integer, Holiday> {
   Map<String, Object> getDataForCreateHoliday(int monthId,int year) throws Exception;
   public void crateHolidays(HolidayDTO holidayDTO)throws Exception;
   public HolidayDTO checkHolidays(String holidayDate)throws Exception;
   public void editHolidays(HolidayDTO holidayDTO)throws Exception;
   
}
