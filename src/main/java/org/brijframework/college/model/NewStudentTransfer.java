package org.brijframework.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name="New_StudentTransfer")
public class NewStudentTransfer extends AbstractPO<Integer>{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transferId")
	private int transferId;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="studentId")
	private Students student;
	@Column(name="cast")
	private String religion;
	@Column(name="is_active")
	private boolean active;
	@Column(name="fatherOccuption")
	private String fatherOccuption;
	@Column(name="UPDefaultDurationOfStay")
	private String UPDefaultDurationOfStay;
	@Column(name="dobInWords")
	private String dobInWords;
	
	
	
	@Column(name="dateOfEntryInVI")
	private String dateOfEntryInVI;
	@Column(name="dateOfPromotionInVI")
	private String dateOfPromotionInVI;
	@Column(name="dateOfTransferInVI")
	private String dateOfTransferInVI;
	@Column(name="reasonForTransferInVI")
	private String reasonForTransferInVI;
	@Column(name="sessionInVI")
	private String sessionInVI;
	@Column(name="resultInVI")
	private String resultInVI;
	@Column(name="BehaviourInVI")
	private String BehaviourInVI;
	@Column(name="SignatureOfPrincipalInVI")
	private String SignatureOfPrincipalInVI;
	
	@Column(name="dateOfEntryInVII")
	private String dateOfEntryInVII;
	@Column(name="dateOfPromotionInVII")
	private String dateOfPromotionInVII;
	@Column(name="dateOfTransferInVII")
	private String dateOfTransferInVII;
	@Column(name="reasonForTransferInVII")
	private String reasonForTransferInVII;
	@Column(name="sessionInVII")
	private String sessionInVII;
	@Column(name="resultInVII")
	private String resultInVII;
	@Column(name="BehaviourInVII")
	private String BehaviourInVII;
	@Column(name="SignatureOfPrincipalInVII")
	private String SignatureOfPrincipalInVII;
	
	@Column(name="dateOfEntryInVIII")
	private String dateOfEntryInVIII;
	@Column(name="dateOfPromotionInVIII")
	private String dateOfPromotionInVIII;
	@Column(name="dateOfTransferInVIII")
	private String dateOfTransferInVIII;
	@Column(name="reasonForTransferInVIII")
	private String reasonForTransferInVIII;
	@Column(name="sessionInVIII")
	private String sessionInVIII;
	@Column(name="resultInVIII")
	private String resultInVIII;
	@Column(name="BehaviourInVIII")
	private String BehaviourInVIII;
	@Column(name="SignatureOfPrincipalInVIII")
	private String SignatureOfPrincipalInVIII;
	
	@Column(name="dateOfEntryInIX")
	private String dateOfEntryInIX;
	@Column(name="dateOfPromotionInIX")
	private String dateOfPromotionInIX;
	@Column(name="dateOfTransferInIX")
	private String dateOfTransferInIX;
	@Column(name="reasonForTransferInIX")
	private String reasonForTransferInIX;
	@Column(name="sessionInIX")
	private String sessionInIX;
	@Column(name="resultInIX")
	private String resultInIX;
	@Column(name="BehaviourInIX")
	private String BehaviourInIX;
	@Column(name="SignatureOfPrincipalInIX")
	private String SignatureOfPrincipalInIX;
	
	@Column(name="dateOfEntryInX")
	private String dateOfEntryInX;
	@Column(name="dateOfPromotionInX")
	private String dateOfPromotionInX;
	@Column(name="dateOfTransferInX")
	private String dateOfTransferInX;
	@Column(name="reasonForTransferInX")
	private String reasonForTransferInX;
	@Column(name="sessionInX")
	private String sessionInX;
	@Column(name="resultInX")
	private String resultInX;
	@Column(name="BehaviourInX")
	private String BehaviourInX;
	@Column(name="SignatureOfPrincipalInX")
	private String SignatureOfPrincipalInX;
	
	@Column(name="dateOfEntryInXI")
	private String dateOfEntryInXI;
	@Column(name="dateOfPromotionInXI")
	private String dateOfPromotionInXI;
	@Column(name="dateOfTransferInXI")
	private String dateOfTransferInXI;
	@Column(name="reasonForTransferInXI")
	private String reasonForTransferInXI;
	@Column(name="sessionInXI")
	private String sessionInXI;
	@Column(name="resultInXI")
	private String resultInXI;
	@Column(name="BehaviourInXI")
	private String BehaviourInXI;
	@Column(name="SignatureOfPrincipalInXI")
	private String SignatureOfPrincipalInXI;
	
	@Column(name="dateOfEntryInXII")
	private String dateOfEntryInXII;
	@Column(name="dateOfPromotionInXII")
	private String dateOfPromotionInXII;
	@Column(name="dateOfTransferInXII")
	private String dateOfTransferInXII;
	@Column(name="reasonForTransferInXII")
	private String reasonForTransferInXII;
	@Column(name="sessionInXII")
	private String sessionInXII;
	@Column(name="resultInXII")
	private String resultInXII;
	@Column(name="BehaviourInXII")
	private String BehaviourInXII;
	@Column(name="SignatureOfPrincipalInXII")
	private String SignatureOfPrincipalInXII;
	
	
	
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getDateOfEntryInVI() {
		return dateOfEntryInVI;
	}
	public void setDateOfEntryInVI(String dateOfEntryInVI) {
		this.dateOfEntryInVI = dateOfEntryInVI;
	}
	public String getDateOfPromotionInVI() {
		return dateOfPromotionInVI;
	}
	public void setDateOfPromotionInVI(String dateOfPromotionInVI) {
		this.dateOfPromotionInVI = dateOfPromotionInVI;
	}
	public String getDateOfTransferInVI() {
		return dateOfTransferInVI;
	}
	public void setDateOfTransferInVI(String dateOfTransferInVI) {
		this.dateOfTransferInVI = dateOfTransferInVI;
	}
	public String getReasonForTransferInVI() {
		return reasonForTransferInVI;
	}
	public void setReasonForTransferInVI(String reasonForTransferInVI) {
		this.reasonForTransferInVI = reasonForTransferInVI;
	}
	public String getSessionInVI() {
		return sessionInVI;
	}
	public void setSessionInVI(String sessionInVI) {
		this.sessionInVI = sessionInVI;
	}
	public String getResultInVI() {
		return resultInVI;
	}
	public void setResultInVI(String resultInVI) {
		this.resultInVI = resultInVI;
	}
	public String getBehaviourInVI() {
		return BehaviourInVI;
	}
	public void setBehaviourInVI(String behaviourInVI) {
		BehaviourInVI = behaviourInVI;
	}
	public String getSignatureOfPrincipalInVI() {
		return SignatureOfPrincipalInVI;
	}
	public void setSignatureOfPrincipalInVI(String signatureOfPrincipalInVI) {
		SignatureOfPrincipalInVI = signatureOfPrincipalInVI;
	}
	public String getDateOfEntryInVII() {
		return dateOfEntryInVII;
	}
	public void setDateOfEntryInVII(String dateOfEntryInVII) {
		this.dateOfEntryInVII = dateOfEntryInVII;
	}
	public String getDateOfPromotionInVII() {
		return dateOfPromotionInVII;
	}
	public void setDateOfPromotionInVII(String dateOfPromotionInVII) {
		this.dateOfPromotionInVII = dateOfPromotionInVII;
	}
	public String getDateOfTransferInVII() {
		return dateOfTransferInVII;
	}
	public void setDateOfTransferInVII(String dateOfTransferInVII) {
		this.dateOfTransferInVII = dateOfTransferInVII;
	}
	public String getReasonForTransferInVII() {
		return reasonForTransferInVII;
	}
	public void setReasonForTransferInVII(String reasonForTransferInVII) {
		this.reasonForTransferInVII = reasonForTransferInVII;
	}
	public String getSessionInVII() {
		return sessionInVII;
	}
	public void setSessionInVII(String sessionInVII) {
		this.sessionInVII = sessionInVII;
	}
	public String getResultInVII() {
		return resultInVII;
	}
	public void setResultInVII(String resultInVII) {
		this.resultInVII = resultInVII;
	}
	public String getBehaviourInVII() {
		return BehaviourInVII;
	}
	public void setBehaviourInVII(String behaviourInVII) {
		BehaviourInVII = behaviourInVII;
	}
	public String getSignatureOfPrincipalInVII() {
		return SignatureOfPrincipalInVII;
	}
	public void setSignatureOfPrincipalInVII(String signatureOfPrincipalInVII) {
		SignatureOfPrincipalInVII = signatureOfPrincipalInVII;
	}
	public String getDateOfEntryInVIII() {
		return dateOfEntryInVIII;
	}
	public void setDateOfEntryInVIII(String dateOfEntryInVIII) {
		this.dateOfEntryInVIII = dateOfEntryInVIII;
	}
	public String getDateOfPromotionInVIII() {
		return dateOfPromotionInVIII;
	}
	public void setDateOfPromotionInVIII(String dateOfPromotionInVIII) {
		this.dateOfPromotionInVIII = dateOfPromotionInVIII;
	}
	public String getDateOfTransferInVIII() {
		return dateOfTransferInVIII;
	}
	public void setDateOfTransferInVIII(String dateOfTransferInVIII) {
		this.dateOfTransferInVIII = dateOfTransferInVIII;
	}
	public String getReasonForTransferInVIII() {
		return reasonForTransferInVIII;
	}
	public void setReasonForTransferInVIII(String reasonForTransferInVIII) {
		this.reasonForTransferInVIII = reasonForTransferInVIII;
	}
	public String getSessionInVIII() {
		return sessionInVIII;
	}
	public void setSessionInVIII(String sessionInVIII) {
		this.sessionInVIII = sessionInVIII;
	}
	public String getResultInVIII() {
		return resultInVIII;
	}
	public void setResultInVIII(String resultInVIII) {
		this.resultInVIII = resultInVIII;
	}
	public String getBehaviourInVIII() {
		return BehaviourInVIII;
	}
	public void setBehaviourInVIII(String behaviourInVIII) {
		BehaviourInVIII = behaviourInVIII;
	}
	public String getSignatureOfPrincipalInVIII() {
		return SignatureOfPrincipalInVIII;
	}
	public void setSignatureOfPrincipalInVIII(String signatureOfPrincipalInVIII) {
		SignatureOfPrincipalInVIII = signatureOfPrincipalInVIII;
	}
	public String getDateOfEntryInIX() {
		return dateOfEntryInIX;
	}
	public void setDateOfEntryInIX(String dateOfEntryInIX) {
		this.dateOfEntryInIX = dateOfEntryInIX;
	}
	public String getDateOfPromotionInIX() {
		return dateOfPromotionInIX;
	}
	public void setDateOfPromotionInIX(String dateOfPromotionInIX) {
		this.dateOfPromotionInIX = dateOfPromotionInIX;
	}
	public String getDateOfTransferInIX() {
		return dateOfTransferInIX;
	}
	public void setDateOfTransferInIX(String dateOfTransferInIX) {
		this.dateOfTransferInIX = dateOfTransferInIX;
	}
	public String getReasonForTransferInIX() {
		return reasonForTransferInIX;
	}
	public void setReasonForTransferInIX(String reasonForTransferInIX) {
		this.reasonForTransferInIX = reasonForTransferInIX;
	}
	public String getSessionInIX() {
		return sessionInIX;
	}
	public void setSessionInIX(String sessionInIX) {
		this.sessionInIX = sessionInIX;
	}
	public String getResultInIX() {
		return resultInIX;
	}
	public void setResultInIX(String resultInIX) {
		this.resultInIX = resultInIX;
	}
	public String getBehaviourInIX() {
		return BehaviourInIX;
	}
	public void setBehaviourInIX(String behaviourInIX) {
		BehaviourInIX = behaviourInIX;
	}
	public String getSignatureOfPrincipalInIX() {
		return SignatureOfPrincipalInIX;
	}
	public void setSignatureOfPrincipalInIX(String signatureOfPrincipalInIX) {
		SignatureOfPrincipalInIX = signatureOfPrincipalInIX;
	}
	public String getDateOfEntryInX() {
		return dateOfEntryInX;
	}
	public void setDateOfEntryInX(String dateOfEntryInX) {
		this.dateOfEntryInX = dateOfEntryInX;
	}
	public String getDateOfPromotionInX() {
		return dateOfPromotionInX;
	}
	public void setDateOfPromotionInX(String dateOfPromotionInX) {
		this.dateOfPromotionInX = dateOfPromotionInX;
	}
	public String getDateOfTransferInX() {
		return dateOfTransferInX;
	}
	public void setDateOfTransferInX(String dateOfTransferInX) {
		this.dateOfTransferInX = dateOfTransferInX;
	}
	public String getReasonForTransferInX() {
		return reasonForTransferInX;
	}
	public void setReasonForTransferInX(String reasonForTransferInX) {
		this.reasonForTransferInX = reasonForTransferInX;
	}
	public String getSessionInX() {
		return sessionInX;
	}
	public void setSessionInX(String sessionInX) {
		this.sessionInX = sessionInX;
	}
	public String getResultInX() {
		return resultInX;
	}
	public void setResultInX(String resultInX) {
		this.resultInX = resultInX;
	}
	public String getBehaviourInX() {
		return BehaviourInX;
	}
	public void setBehaviourInX(String behaviourInX) {
		BehaviourInX = behaviourInX;
	}
	public String getSignatureOfPrincipalInX() {
		return SignatureOfPrincipalInX;
	}
	public void setSignatureOfPrincipalInX(String signatureOfPrincipalInX) {
		SignatureOfPrincipalInX = signatureOfPrincipalInX;
	}
	public String getDateOfEntryInXI() {
		return dateOfEntryInXI;
	}
	public void setDateOfEntryInXI(String dateOfEntryInXI) {
		this.dateOfEntryInXI = dateOfEntryInXI;
	}
	public String getDateOfPromotionInXI() {
		return dateOfPromotionInXI;
	}
	public void setDateOfPromotionInXI(String dateOfPromotionInXI) {
		this.dateOfPromotionInXI = dateOfPromotionInXI;
	}
	public String getDateOfTransferInXI() {
		return dateOfTransferInXI;
	}
	public void setDateOfTransferInXI(String dateOfTransferInXI) {
		this.dateOfTransferInXI = dateOfTransferInXI;
	}
	public String getReasonForTransferInXI() {
		return reasonForTransferInXI;
	}
	public void setReasonForTransferInXI(String reasonForTransferInXI) {
		this.reasonForTransferInXI = reasonForTransferInXI;
	}
	public String getSessionInXI() {
		return sessionInXI;
	}
	public void setSessionInXI(String sessionInXI) {
		this.sessionInXI = sessionInXI;
	}
	public String getResultInXI() {
		return resultInXI;
	}
	public void setResultInXI(String resultInXI) {
		this.resultInXI = resultInXI;
	}
	public String getBehaviourInXI() {
		return BehaviourInXI;
	}
	public void setBehaviourInXI(String behaviourInXI) {
		BehaviourInXI = behaviourInXI;
	}
	public String getSignatureOfPrincipalInXI() {
		return SignatureOfPrincipalInXI;
	}
	public void setSignatureOfPrincipalInXI(String signatureOfPrincipalInXI) {
		SignatureOfPrincipalInXI = signatureOfPrincipalInXI;
	}
	public String getDateOfEntryInXII() {
		return dateOfEntryInXII;
	}
	public void setDateOfEntryInXII(String dateOfEntryInXII) {
		this.dateOfEntryInXII = dateOfEntryInXII;
	}
	public String getDateOfPromotionInXII() {
		return dateOfPromotionInXII;
	}
	public void setDateOfPromotionInXII(String dateOfPromotionInXII) {
		this.dateOfPromotionInXII = dateOfPromotionInXII;
	}
	public String getDateOfTransferInXII() {
		return dateOfTransferInXII;
	}
	public void setDateOfTransferInXII(String dateOfTransferInXII) {
		this.dateOfTransferInXII = dateOfTransferInXII;
	}
	public String getReasonForTransferInXII() {
		return reasonForTransferInXII;
	}
	public void setReasonForTransferInXII(String reasonForTransferInXII) {
		this.reasonForTransferInXII = reasonForTransferInXII;
	}
	public String getSessionInXII() {
		return sessionInXII;
	}
	public void setSessionInXII(String sessionInXII) {
		this.sessionInXII = sessionInXII;
	}
	public String getResultInXII() {
		return resultInXII;
	}
	public void setResultInXII(String resultInXII) {
		this.resultInXII = resultInXII;
	}
	public String getBehaviourInXII() {
		return BehaviourInXII;
	}
	public void setBehaviourInXII(String behaviourInXII) {
		BehaviourInXII = behaviourInXII;
	}
	public String getSignatureOfPrincipalInXII() {
		return SignatureOfPrincipalInXII;
	}
	public void setSignatureOfPrincipalInXII(String signatureOfPrincipalInXII) {
		SignatureOfPrincipalInXII = signatureOfPrincipalInXII;
	}
	
	
	public String getFatherOccuption() {
		return fatherOccuption;
	}
	public void setFatherOccuption(String fatherOccuption) {
		this.fatherOccuption = fatherOccuption;
	}
	public String getUPDefaultDurationOfStay() {
		return UPDefaultDurationOfStay;
	}
	public void setUPDefaultDurationOfStay(String uPDefaultDurationOfStay) {
		UPDefaultDurationOfStay = uPDefaultDurationOfStay;
	}
	
	
	
	public String getDobInWords() {
		return dobInWords;
	}
	public void setDobInWords(String dobInWords) {
		this.dobInWords = dobInWords;
	}
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
