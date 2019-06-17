package org.brijframework.college.service.impl;

import org.brijframework.college.dao.LibraraySettingsDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.LibraraySettings;
import org.brijframework.college.models.dto.LibraraySettingsDTO;
import org.brijframework.college.service.LibraraySettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("libraraySettingsService")
public class LibraraySettingsServiceImpl extends
CRUDServiceImpl<Integer, LibraraySettings,LibraraySettingsDao> implements
LibraraySettingsService{

	@Autowired
	SessionDao sessionDao;
	@Autowired
	public LibraraySettingsServiceImpl(LibraraySettingsDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LibraraySettingsDTO checkData(int sessionId) {
		LibraraySettingsDTO dto=new LibraraySettingsDTO();
		LibraraySettings LS=dao.checkData(sessionId);
		if(LS!=null){
		dto.setFineAmount(LS.getFineAmount());
		dto.setLibrarySettingId(LS.getLibrarySettingId());
		dto.setMaxBooks(LS.getMaxBooks());
		dto.setMaxDays(LS.getMaxDays());
		dto.setMaxFine(LS.getMaxFine());
		dto.setSessionId(LS.getSession().getSessionId());
		dto.setSessionName(LS.getSession().getSessionDuration());
		}
		return dto;
	}

	@Override
	public String saveLibraray(LibraraySettingsDTO libraraySettingDTO) {
		LibraraySettings LS=
				new LibraraySettings();
		LS=dao.checkData(libraraySettingDTO.getSessionId());
		String str="";
		if(LS!=null)
		{
			LS.setMaxBooks(libraraySettingDTO.getMaxBooks());
			LS.setMaxDays(libraraySettingDTO.getMaxDays());
			LS.setMaxFine(libraraySettingDTO.getMaxFine());
			LS.setFineAmount(libraraySettingDTO.getFineAmount());
			LS.setSession(sessionDao.get(libraraySettingDTO.getSessionId()));
			dao.update(LS);
			str="Library has been updated successfully.";
		}
		else
		{
			LibraraySettings LS1=
					new LibraraySettings();
			LS1.setMaxBooks(libraraySettingDTO.getMaxBooks());
			LS1.setMaxDays(libraraySettingDTO.getMaxDays());
			LS1.setMaxFine(libraraySettingDTO.getMaxFine());
			LS1.setFineAmount(libraraySettingDTO.getFineAmount());
			LS1.setSession(sessionDao.get(libraraySettingDTO.getSessionId()));
			LS1.setActive(true);
			dao.create(LS1);
			str="Library has been Created successfully.";
		}
		
		return str;
		
	}

}
