package org.brijframework.college.service.impl;

import org.brijframework.college.dao.SchoolRegistrationDao;
import org.brijframework.college.model.SchoolRegistration;
import org.brijframework.college.models.dto.SchoolRegistrationDTO;
import org.brijframework.college.service.SchoolRegistraionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("schoolRegistrationService")
public class SchoolRegistraionServiceImpl extends
CRUDServiceImpl<Integer, SchoolRegistration, SchoolRegistrationDao> implements
SchoolRegistraionService {

	@Autowired
	public SchoolRegistraionServiceImpl(SchoolRegistrationDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String registerSchool(SchoolRegistrationDTO schoolRegistrationDTO) {
		
		String str="";
		SchoolRegistration sr=new SchoolRegistration();
		sr.setSchoolAddress(schoolRegistrationDTO.getSchoolAddress());
		sr.setSchoolLogoName(schoolRegistrationDTO.getSchoolLogoName());
		sr.setSchoolName(schoolRegistrationDTO.getSchoolName());
		sr.setContactNo(schoolRegistrationDTO.getContactNo());
		sr.setActive(true);
		dao.create(sr);
		str="School registered successfully" ;
		return str;
	}

	@Override
	public SchoolRegistrationDTO getSchoolList() {
		SchoolRegistration school=dao.getRegisterSchool();
		System.out.println(school);
		SchoolRegistrationDTO dto=null;
		if(school!=null)
		{
		dto=new SchoolRegistrationDTO();
		dto.setSchoolRegistrationId(school.getSchoolRegistrationId());
		dto.setContactNo(school.getContactNo());
		dto.setSchoolAddress(school.getSchoolAddress());
		dto.setSchoolLogoName(school.getSchoolLogoName());
		dto.setSchoolName(school.getSchoolName());
		}
		return dto;
	}

	@Override
	public String updateRegisteredSchool(
			SchoolRegistrationDTO schoolRegistrationDTO) {
		String str="";
		SchoolRegistration sr=dao.get(schoolRegistrationDTO.getSchoolRegistrationId());
		sr.setSchoolAddress(schoolRegistrationDTO.getSchoolAddress());
		sr.setSchoolLogoName(schoolRegistrationDTO.getSchoolLogoName());
		sr.setSchoolName(schoolRegistrationDTO.getSchoolName());
		sr.setContactNo(schoolRegistrationDTO.getContactNo());
		dao.update(sr);
		str="Regsitered School updated successfully" ;
		return str;
	}

	@Override
	public String deleteRegisteredSchool(
			int id) {
		String str="";
		SchoolRegistration sr=dao.get(id);
		sr.setActive(false);
		str="Regsitered School deleted successfully" ;
		return str;
	}

}
