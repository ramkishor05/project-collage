package org.brijframework.college.service.impl;

import org.brijframework.college.dao.FineDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.model.Fine;
import org.brijframework.college.models.dto.FineDTO;
import org.brijframework.college.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FineServiceImpl extends CRUDServiceImpl<Integer, Fine, FineDao>
		implements FineService {
	
	@Autowired
	SessionDao sessionDao;
	@Autowired
	public FineServiceImpl(FineDao dao) {
		super(dao);
	}

	@Override
	public FineDTO getFineByName(String Name,Integer sessionId) {
		FineDTO dto=new FineDTO();
		Fine fine=dao.getFineByName(Name,sessionId);
		if(fine!=null)
		{
		dto.setFineAmount(fine.getFineAmount());
		dto.setFineName(fine.getFineName());
		dto.setMaxFineAmount(fine.getMaxFineAmount());
		dto.setFineId(fine.getFineId());
		}
		return dto;
	}

	@Override
	public void createFine(FineDTO fine) {
		Fine fines=new Fine();
		fines.setFineAmount(fine.getFineAmount());
		fines.setFineName(fine.getFineName());
		fines.setFineAmount(fine.getFineAmount());
		fines.setMaxFineAmount(fine.getMaxFineAmount());
		fines.setSession(sessionDao.get(fine.getSessionId()));
		dao.create(fines);
		
	}

	@Override
	public void updateFine(FineDTO fine) {
		Fine fines=dao.get(fine.getFineId());
		fines.setFineAmount(fine.getFineAmount());
		fines.setFineName(fine.getFineName());
		fines.setFineAmount(fine.getFineAmount());
		fines.setMaxFineAmount(fine.getMaxFineAmount());
		fines.setSession(sessionDao.get(fine.getSessionId()));
		
	}

}
