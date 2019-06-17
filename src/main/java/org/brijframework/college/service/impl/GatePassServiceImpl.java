package org.brijframework.college.service.impl;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.brijframework.college.dao.GatePassDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.GatePass;
import org.brijframework.college.models.dto.GatePassDTO;
import org.brijframework.college.service.GatePassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("gatePassService")
public class GatePassServiceImpl extends
CRUDServiceImpl<Integer, GatePass, GatePassDao> implements
GatePassService {

	@Autowired
	public GatePassServiceImpl(GatePassDao dao) {
		super(dao);
		
	}

	@Override
	public Integer findGatePassNo() {
		int no=dao.findAll(GatePass.class).size()+1;
		return no;
	}


@Autowired
	StudentsAdmissionDao studentDao;
	
	@Override
	public GatePassDTO generateGatePass(GatePassDTO gatePassDTO) {
		GatePass pass=new GatePass();
		java.util.Date date=new Date();
		java.sql.Time times=new Time(date.getTime());
		pass.setActive(true);
		pass.setLeavingDate(date);
		pass.setLeavingTime(times);
		pass.setLeavingWith(gatePassDTO.getLeavingWith());
		pass.setReason(gatePassDTO.getReason());
		pass.setStudents(studentDao.get(gatePassDTO.getAdmissionNo()));
		dao.create(pass);
		return getPDFgatePass(pass.getGatePassId());
		
	}
	
	@Override
	public GatePassDTO getPDFgatePass(Integer id) {
		GatePass pass=dao.get(id);
		GatePassDTO dto=new GatePassDTO();
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat tf=new SimpleDateFormat("h:mm a");
		dto.setLeavingDate(df.format(pass.getLeavingDate()));
		dto.setLeavingTime(tf.format(pass.getLeavingTime()));
		dto.setLeavingWith(pass.getLeavingWith());
		dto.setReason(pass.getReason());
		dto.setGatePassId(pass.getGatePassId());
		return dto;
}

	@Override
	public List<GatePassDTO> findPreviousDetailsbyId(String id) {
		List<GatePass> lists=dao.findPreviousDetailsById(id);
		List<GatePassDTO> listdto=new ArrayList<GatePassDTO>();
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat tf=new SimpleDateFormat("h:mm a");
		for(GatePass pass:lists)
		{
			GatePassDTO dto=new GatePassDTO();
			
			dto.setLeavingDate(df.format(pass.getLeavingDate()));
			dto.setLeavingTime(tf.format(pass.getLeavingTime()));
			dto.setLeavingWith(pass.getLeavingWith());
			dto.setReason(pass.getReason());
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public List<GatePassDTO> findDateWiseDetails(String dates) throws ParseException {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		List<GatePass> lists=dao.findDateWiseDetails(df.parse(dates));
		List<GatePassDTO> listdto=new ArrayList<GatePassDTO>();
	
		SimpleDateFormat tf=new SimpleDateFormat("h:mm a");
		for(GatePass pass:lists)
		{
			GatePassDTO dto=new GatePassDTO();
			
			dto.setFatherName(pass.getStudents().getFatherName());
			dto.setFirstName(pass.getStudents().getFirstName());
			dto.setMiddleName(pass.getStudents().getMiddleName());
			dto.setLastName(pass.getStudents().getLastName());
			dto.setSectionName(pass.getStudents().getSection().getSectionName());
			dto.setClassName(pass.getStudents().getClasses().getClassName());
			dto.setMobile(pass.getStudents().getMobile());
			dto.setStudentId(pass.getStudents().getStudentId());
			dto.setLeavingTime(tf.format(pass.getLeavingTime()));
			dto.setLeavingWith(pass.getLeavingWith());
			dto.setReason(pass.getReason());
			listdto.add(dto);
		}
		return listdto;
	}
	}
