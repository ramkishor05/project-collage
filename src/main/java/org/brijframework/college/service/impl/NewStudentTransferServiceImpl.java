package org.brijframework.college.service.impl;

import java.text.SimpleDateFormat;

import org.brijframework.college.dao.NewStudentTransferDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.model.NewStudentTransfer;
import org.brijframework.college.model.Students;
import org.brijframework.college.models.dto.NewStudentTransferDTO;
import org.brijframework.college.service.NewStudentTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NewStudentTransferServiceImpl extends CRUDServiceImpl<Integer, NewStudentTransfer, NewStudentTransferDao> implements NewStudentTransferService{

	@Autowired
	public NewStudentTransferServiceImpl(NewStudentTransferDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	StudentsAdmissionDao studentAddmissionDao;
	@Override
	public void saveNewStudentTransfer(
			NewStudentTransferDTO dto) {
		NewStudentTransfer transfer=new NewStudentTransfer();
		transfer.setActive(true);
		
		transfer.setBehaviourInIX(dto.getBehaviourInIX());
		transfer.setBehaviourInVI(dto.getBehaviourInVI());
		transfer.setBehaviourInVII(dto.getBehaviourInVII());
		transfer.setBehaviourInVIII(dto.getBehaviourInVIII());
		transfer.setBehaviourInX(dto.getBehaviourInX());
		transfer.setBehaviourInXI(dto.getBehaviourInXI());
		transfer.setBehaviourInXII(dto.getBehaviourInXII());
		transfer.setDateOfEntryInIX(dto.getDateOfEntryInIX());
		transfer.setDateOfEntryInVI(dto.getDateOfEntryInVI());
		transfer.setDateOfEntryInVII(dto.getDateOfEntryInVII());
		transfer.setDateOfEntryInVIII(dto.getDateOfEntryInVIII());
		transfer.setDateOfEntryInX(dto.getDateOfEntryInX());
		transfer.setDateOfEntryInXI(dto.getDateOfEntryInXI());
		transfer.setDateOfEntryInXII(dto.getDateOfEntryInXII());
		transfer.setDateOfPromotionInIX(dto.getDateOfPromotionInIX());
		transfer.setDateOfPromotionInVI(dto.getDateOfPromotionInVI());
		transfer.setDateOfPromotionInVII(dto.getDateOfPromotionInVII());
		transfer.setDateOfPromotionInVIII(dto.getDateOfPromotionInVIII());
		transfer.setDateOfPromotionInX(dto.getDateOfPromotionInX());
		transfer.setDateOfPromotionInXI(dto.getDateOfPromotionInXI());
		transfer.setDateOfPromotionInXII(dto.getDateOfPromotionInXII());
		transfer.setDateOfTransferInIX(dto.getDateOfTransferInIX());
		transfer.setDateOfTransferInVI(dto.getDateOfTransferInVI());
		transfer.setDateOfTransferInVII(dto.getDateOfTransferInVII());
		transfer.setDateOfTransferInVIII(dto.getDateOfTransferInVIII());
		transfer.setDateOfTransferInX(dto.getDateOfTransferInX());
		transfer.setDateOfTransferInXI(dto.getDateOfTransferInXI());
		transfer.setDateOfTransferInXII(dto.getDateOfTransferInXII());
		transfer.setReasonForTransferInIX(dto.getReasonForTransferInIX());
		transfer.setReasonForTransferInVI(dto.getReasonForTransferInVI());
		transfer.setReasonForTransferInVII(dto.getReasonForTransferInVII());
		transfer.setReasonForTransferInVIII(dto.getReasonForTransferInVIII());
		transfer.setReasonForTransferInX(dto.getReasonForTransferInX());
		transfer.setReasonForTransferInXI(dto.getReasonForTransferInXI());
		transfer.setReasonForTransferInXII(dto.getReasonForTransferInXII());
		transfer.setReligion(dto.getReligion());
		transfer.setResultInIX(dto.getResultInIX());
		transfer.setResultInVI(dto.getResultInVI());
		transfer.setResultInVII(dto.getResultInVII());
		transfer.setResultInVIII(dto.getResultInVIII());
		transfer.setResultInX(dto.getResultInX());
		transfer.setResultInXI(dto.getResultInXI());
		transfer.setResultInXII(dto.getResultInXII());
		transfer.setSessionInIX(dto.getSessionInIX());
		transfer.setSessionInVI(dto.getSessionInVI());
		transfer.setSessionInVII(dto.getSessionInVII());
		transfer.setSessionInVIII(dto.getSessionInVIII());
		transfer.setSessionInX(dto.getSessionInX());
		transfer.setSessionInXI(dto.getSessionInXI());
		transfer.setSessionInXII(dto.getSessionInXII());
		transfer.setSignatureOfPrincipalInIX(dto.getSignatureOfPrincipalInIX());
		transfer.setSignatureOfPrincipalInVI(dto.getSignatureOfPrincipalInVI());
		transfer.setSignatureOfPrincipalInVII(dto.getSignatureOfPrincipalInVII());
		transfer.setSignatureOfPrincipalInVIII(dto.getSignatureOfPrincipalInVIII());
		transfer.setSignatureOfPrincipalInX(dto.getSignatureOfPrincipalInX());
		transfer.setSignatureOfPrincipalInXI(dto.getSignatureOfPrincipalInXI());
		transfer.setSignatureOfPrincipalInXII(dto.getSignatureOfPrincipalInXII());
		Students students=studentAddmissionDao.get(dto.getStudentId());
		transfer.setStudent(students);
		transfer.setFatherOccuption(dto.getFatherOccuption());
		transfer.setReligion(dto.getReligion());
		transfer.setUPDefaultDurationOfStay(dto.getUPDefaultDurationOfStay());
		transfer.setDobInWords(dto.getInWordDob());
		
		dao.create(transfer);
		
				
				
		
	}
	@Override
	public NewStudentTransferDTO getneweGeneratedTC(String studentId) {
	 
		NewStudentTransfer dto=dao.getTCForStudent(studentId);
		NewStudentTransferDTO transfer=new NewStudentTransferDTO();
		if(dto!=null){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			transfer.setActive(true);
			transfer.setFatherName(dto.getStudent().getFatherName());
			transfer.setAddress(dto.getStudent().getAddressLine1());
			transfer.setMotherName(dto.getStudent().getMotherName());
			transfer.setStudentId(dto.getStudent().getStudentAdmissionNo());
			transfer.setReligion(dto.getStudent().getReligion());
			transfer.setRegistrationNo(dto.getStudent().getStudentAdmissionNo());
			transfer.setFullName(dto.getStudent().getFirstName()+" "+ dto.getStudent().getLastName());
			transfer.setReligionCaste(dto.getReligion());
			transfer.setDob(df.format(dto.getStudent().getDateOfBirth()));
			transfer.setAdmissionDate(df.format(dto.getStudent().getAdmissionDate()));
			transfer.setLastSchoolName(dto.getStudent().getSchoolName());
			transfer.setAddress(dto.getStudent().getAddressLine1());
			transfer.setAdmissionDate(df.format(dto.getStudent().getAdmissionDate()));
            transfer.setBehaviourInIX(dto.getBehaviourInIX());
			transfer.setBehaviourInVI(dto.getBehaviourInVI());
			transfer.setBehaviourInVII(dto.getBehaviourInVII());
			transfer.setBehaviourInVIII(dto.getBehaviourInVIII());
			transfer.setBehaviourInX(dto.getBehaviourInX());
			transfer.setBehaviourInXI(dto.getBehaviourInXI());
			transfer.setBehaviourInXII(dto.getBehaviourInXII());
			transfer.setDateOfEntryInIX(dto.getDateOfEntryInIX());
			transfer.setDateOfEntryInVI(dto.getDateOfEntryInVI());
			transfer.setDateOfEntryInVII(dto.getDateOfEntryInVII());
			transfer.setDateOfEntryInVIII(dto.getDateOfEntryInVIII());
			transfer.setDateOfEntryInX(dto.getDateOfEntryInX());
			transfer.setDateOfEntryInXI(dto.getDateOfEntryInXI());
			transfer.setDateOfEntryInXII(dto.getDateOfEntryInXII());
			transfer.setDateOfPromotionInIX(dto.getDateOfPromotionInIX());
			transfer.setDateOfPromotionInVI(dto.getDateOfPromotionInVI());
			transfer.setDateOfPromotionInVII(dto.getDateOfPromotionInVII());
			transfer.setDateOfPromotionInVIII(dto.getDateOfPromotionInVIII());
			transfer.setDateOfPromotionInX(dto.getDateOfPromotionInX());
			transfer.setDateOfPromotionInXI(dto.getDateOfPromotionInXI());
			transfer.setDateOfPromotionInXII(dto.getDateOfPromotionInXII());
			transfer.setDateOfTransferInIX(dto.getDateOfTransferInIX());
			transfer.setDateOfTransferInVI(dto.getDateOfTransferInVI());
			transfer.setDateOfTransferInVII(dto.getDateOfTransferInVII());
			transfer.setDateOfTransferInVIII(dto.getDateOfTransferInVIII());
			transfer.setDateOfTransferInX(dto.getDateOfTransferInX());
			transfer.setDateOfTransferInXI(dto.getDateOfTransferInXI());
			transfer.setDateOfTransferInXII(dto.getDateOfTransferInXII());
			transfer.setReasonForTransferInIX(dto.getReasonForTransferInIX());
			transfer.setReasonForTransferInVI(dto.getReasonForTransferInVI());
			transfer.setReasonForTransferInVII(dto.getReasonForTransferInVII());
			transfer.setReasonForTransferInVIII(dto.getReasonForTransferInVIII());
			transfer.setReasonForTransferInX(dto.getReasonForTransferInX());
			transfer.setReasonForTransferInXI(dto.getReasonForTransferInXI());
			transfer.setReasonForTransferInXII(dto.getReasonForTransferInXII());
			transfer.setReligion(dto.getReligion());
			transfer.setResultInIX(dto.getResultInIX());
			transfer.setResultInVI(dto.getResultInVI());
			transfer.setResultInVII(dto.getResultInVII());
			transfer.setResultInVIII(dto.getResultInVIII());
			transfer.setResultInX(dto.getResultInX());
			transfer.setResultInXI(dto.getResultInXI());
			transfer.setResultInXII(dto.getResultInXII());
			transfer.setSessionInIX(dto.getSessionInIX());
			transfer.setSessionInVI(dto.getSessionInVI());
			transfer.setSessionInVII(dto.getSessionInVII());
			transfer.setSessionInVIII(dto.getSessionInVIII());
			transfer.setSessionInX(dto.getSessionInX());
			transfer.setSessionInXI(dto.getSessionInXI());
			transfer.setSessionInXII(dto.getSessionInXII());
			transfer.setSignatureOfPrincipalInIX(dto.getSignatureOfPrincipalInIX());
			transfer.setSignatureOfPrincipalInVI(dto.getSignatureOfPrincipalInVI());
			transfer.setSignatureOfPrincipalInVII(dto.getSignatureOfPrincipalInVII());
			transfer.setSignatureOfPrincipalInVIII(dto.getSignatureOfPrincipalInVIII());
			transfer.setSignatureOfPrincipalInX(dto.getSignatureOfPrincipalInX());
			transfer.setSignatureOfPrincipalInXI(dto.getSignatureOfPrincipalInXI());
			transfer.setSignatureOfPrincipalInXII(dto.getSignatureOfPrincipalInXII());
			transfer.setReligion(dto.getStudent().getReligion());
			transfer.setFatherOccuption(dto.getFatherOccuption());
			transfer.setUPDefaultDurationOfStay(dto.getUPDefaultDurationOfStay());
			transfer.setInWordDob(dto.getDobInWords());
			transfer.setChildImage(dto.getStudent().getPhotoFileName());
			transfer.setLogo("nida.jpg");
			
		}
		
		return transfer;
	}

}
