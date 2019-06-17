package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.brijframework.college.dao.StudentTransferDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.StudentTransfer;
import org.brijframework.college.models.dto.StudentTransferDTO;
import org.brijframework.college.service.StudentTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("studentTransferService")
public class StudentTransferServiceImpl extends
		CRUDServiceImpl<Integer, StudentTransfer, StudentTransferDao> implements
		StudentTransferService {

	@Autowired
	public StudentTransferServiceImpl(StudentTransferDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	StudentsAdmissionDao studentDao;

	@Override
	public void saveTransferData(StudentTransferDTO studentTransferDTO)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		StudentTransfer transfer = new StudentTransfer();
		transfer.setActive(true);
		transfer.setClassInWord(studentTransferDTO.getClassInWord());
		transfer.setConduct(studentTransferDTO.getConduct());
		transfer.setDateOfWithdrawl(df.parse(studentTransferDTO.getDateOfWithdrawl()));
		transfer.setInWordDob(studentTransferDTO.getInWordDob());
		transfer.setNewClassInWord(studentTransferDTO.getNewClassInWord());
		transfer.setNewClassName(studentTransferDTO.getNewClass());
		transfer.setReligionCaste(studentTransferDTO.getReligionCaste());
		transfer.setResult(studentTransferDTO.getResult());
		System.out.println(studentTransferDTO.getStudentId());
		transfer.setStudent(studentDao.get(studentTransferDTO.getStudentId()));
		dao.create(transfer);

	}

	@Override
	public StudentTransferDTO generateTC(String studentId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		StudentTransfer transfer=dao.getTcfortudent(studentId);
		StudentTransferDTO dto=new StudentTransferDTO();
		if(transfer!=null)
		{
		dto.setAdmissionDate(df.format(transfer.getStudent().getAdmissionDate()));
		dto.setClassInWord(transfer.getClassInWord());
		dto.setClassName(transfer.getStudent().getClasses().getClassName());
		dto.setConduct(transfer.getConduct());
		dto.setDateOfWithdrawl(df.format(transfer.getDateOfWithdrawl()));
		dto.setDob(df.format(transfer.getStudent().getDateOfBirth()));
		dto.setFatherName(transfer.getStudent().getFatherName());
		dto.setFullName(transfer.getStudent().getFirstName()+" "+transfer.getStudent().getLastName());
		dto.setInWordDob(transfer.getInWordDob());
		dto.setMotherName(transfer.getStudent().getMotherName());
		dto.setNewClass(transfer.getNewClassName());
		dto.setNewClassInWord(transfer.getNewClassInWord());
		dto.setReligionCaste(transfer.getReligionCaste());
		dto.setResult(transfer.getResult());
		dto.setStudentId(transfer.getStudent().getStudentId());
		dto.setLogo("maharana.jpg");
		}
		return dto;
	}

}
