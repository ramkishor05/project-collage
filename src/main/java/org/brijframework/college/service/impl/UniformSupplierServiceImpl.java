package org.brijframework.college.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.brijframework.college.dao.AllotDressDao;
import org.brijframework.college.dao.SessionDao;
import org.brijframework.college.dao.UniformPurchaseDao;
import org.brijframework.college.dao.UniformSupplierDao;
import org.brijframework.college.model.AllotDress;
import org.brijframework.college.model.Session;
import org.brijframework.college.model.UniformPurchase;
import org.brijframework.college.model.UniformSupplier;
import org.brijframework.college.models.dto.UniformPurchaseDTO;
import org.brijframework.college.models.dto.UniformSupplierDTO;
import org.brijframework.college.service.UniformSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("uniformSupplierService")
public class UniformSupplierServiceImpl extends
		CRUDServiceImpl<Integer, UniformSupplier, UniformSupplierDao> implements
		UniformSupplierService {

	@Autowired
	SessionDao sessionDao;
	@Autowired
	AllotDressDao allotDressDao;
	@Autowired
	UniformPurchaseDao uniformPurchaseDao;

	@Autowired
	public UniformSupplierServiceImpl(UniformSupplierDao dao) {
		super(dao);

	}

	@Override
	public List<String> getSupplierNames() {
		List<String> lists = dao.getSuppliersId();
		return lists;
	}

	@Override
	public List<UniformSupplierDTO> getSupplierDetails(String supplierName)
			throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<UniformSupplier> purchases = dao.getDetailsbySupplier(
				supplierName, frmDate);
		List<UniformSupplierDTO> dtolist = new ArrayList<UniformSupplierDTO>();
		for (UniformSupplier purchase : purchases) {
			UniformSupplierDTO dto = new UniformSupplierDTO();
			dto.setGrossAmount(purchase.getGrossAmount());
			dto.setBalanceAmount(purchase.getDues());
			dto.setPaidAmount(purchase.getPaidAmount());
			dto.setDateOfPurchase(dfm.format(purchase.getDateOfPurchase()));
			dto.setStockPurchaseId(purchase.getStockPurchaseId());
			dto.setSupplierNo(purchase.getSupplierNo());
			dto.setDiscount(purchase.getDiscount());
			dto.setPaidStatus(purchase.getPaidStatus());
			dto.setPaymentMode(purchase.getPaymentMode());
			if (purchase.getPaymentMode().equals("Cheque")) {
				dto.setChequeno(String.valueOf(purchase.getChequeNo()));
				dto.setBankName(purchase.getBankName());

			} else {
				dto.setChequeno("no");
				dto.setBankName("no");
			}
			dtolist.add(dto);
		}

		return dtolist;
	}

	@Override
	public UniformSupplierDTO savePurchasedUniforms(
			UniformSupplierDTO uniformSupplierDTO) throws ParseException {
		List<AllotDress> allotedDress = allotDressDao.findAll(AllotDress.class);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int receiptNo = 0;
		UniformSupplier details = dao.getReceipt();
		if (details == null) {
			receiptNo = 50501;

		} else {
			receiptNo = details.getReceiptNo() + 1;
		}
		UniformSupplier pay = new UniformSupplier();
		pay.setActive(true);
		pay.setSupplierName(uniformSupplierDTO.getSupplierName());
		pay.setInword(uniformSupplierDTO.getInwords());
		pay.setPaidAmount(uniformSupplierDTO.getPaidAmount());
		pay.setGrossAmount(uniformSupplierDTO.getGrossAmount());
		pay.setReceiptNo(receiptNo);
		pay.setBalanceAmount(0);
		pay.setDateOfPurchase(df.parse(uniformSupplierDTO.getDateOfPurchase()));
		pay.setDiscount(uniformSupplierDTO.getGrossAmount()
				- uniformSupplierDTO.getNetPayableAmount());
		pay.setDues(uniformSupplierDTO.getNetPayableAmount()
				- uniformSupplierDTO.getPaidAmount());
		pay.setPaymentMode(uniformSupplierDTO.getPaymentMode());
		pay.setSupplierNo(uniformSupplierDTO.getSupplierNo());

		if (uniformSupplierDTO.getPaymentMode().equals("Cheque")) {
			pay.setChequeNo(Integer.parseInt(uniformSupplierDTO.getChequeno()));
			pay.setPaidStatus("Inhand");
			pay.setBankName(uniformSupplierDTO.getBankName());
		} else {

			pay.setPaidStatus("Completed");
		}
		dao.create(pay);
		for (AllotDress list : allotedDress) {

			UniformPurchase uniform = new UniformPurchase();
			uniform.setActive(true);
			uniform.setAmount(list.getAmount());
			uniform.setBoughtQuantity(list.getQuantity());
			uniform.setNetAmount(list.getNetamount());
			uniform.setRemainingQuantity(list.getQuantity());
			uniform.setShopName(uniformSupplierDTO.getSupplierName());
			uniform.setUniformSize(list.getSize());
			uniform.setUniformPrice(list.getPrice());
			uniform.setUniformName(list.getType());
			uniform.setUniformCategory(list.getCategory());
			uniform.setStocksupplier(pay);
			uniform.setDateOfPurchase(df.parse(uniformSupplierDTO
					.getDateOfPurchase()));
			uniformPurchaseDao.create(uniform);
			allotDressDao.deleteById(list.getAllotDressId());
		}
		return getBoughtReceipt(receiptNo);

	}

	@Override
	public UniformSupplierDTO getBoughtReceipt(int receiptNo) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		UniformSupplier supplier = dao.getStocksId(receiptNo);
		UniformSupplierDTO supdto = new UniformSupplierDTO();
		supdto.setBalanceAmount(supplier.getDues());
		if (supplier.getPaymentMode().equals("Cheque")) {
			supdto.setChequeno(String.valueOf(supplier.getChequeNo()));
			supdto.setBankName(supplier.getBankName());

		}
		supdto.setPaymentMode(supplier.getPaymentMode());
		supdto.setDateOfPurchase(df.format(supplier.getDateOfPurchase()));
		supdto.setDiscount(supplier.getDiscount());
		supdto.setDues(supplier.getDues());
		supdto.setGrossAmount(supplier.getGrossAmount());
		supdto.setInwords(supplier.getInword());
		supdto.setPaidAmount(supplier.getPaidAmount());
		supdto.setSupplierName(supplier.getSupplierName());
		supdto.setReceiptNo(receiptNo);
		supdto.setSupplierNo(supplier.getSupplierNo());

		List<UniformPurchase> dresslist = uniformPurchaseDao
				.getUniformBoughtDetails(supplier.getStockPurchaseId());
		List<UniformPurchaseDTO> listdto = new ArrayList<UniformPurchaseDTO>();
		for (UniformPurchase list : dresslist) {
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setAmount(list.getAmount());
			dto.setBoughtQuantity(list.getBoughtQuantity());
			dto.setNetAmount(list.getNetAmount());
			dto.setUniformCategory(list.getUniformCategory());
			dto.setUniformName(list.getUniformName());
			dto.setUniformPrice(list.getUniformPrice());
			dto.setUniformSize(list.getUniformSize());
			listdto.add(dto);
		}
		supdto.setUniformPurchase(listdto);
		return supdto;
	}

	@Override
	public List<UniformSupplierDTO> getUniformsBoughtDetails()
			throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<UniformSupplier> purchases = dao.getBoughtDetails(frmDate);
		List<UniformSupplierDTO> dtolist = new ArrayList<UniformSupplierDTO>();
		for (UniformSupplier purchase : purchases) {
			UniformSupplierDTO dto = new UniformSupplierDTO();
			dto.setGrossAmount(purchase.getGrossAmount());
			dto.setBalanceAmount(purchase.getDues());
			dto.setPaidAmount(purchase.getPaidAmount());
			dto.setDateOfPurchase(dfm.format(purchase.getDateOfPurchase()));
			dto.setStockPurchaseId(purchase.getStockPurchaseId());
			dto.setPaidStatus(purchase.getPaidStatus());
			dto.setPaymentMode(purchase.getPaymentMode());
			dto.setReceiptNo(purchase.getReceiptNo());
			dto.setSupplierName(purchase.getSupplierName());
			dto.setDiscount(purchase.getDiscount());
			dtolist.add(dto);
		}
		return dtolist;
	}

	@Override
	public void updateStatus(int id, String status) throws ParseException {
		UniformSupplier supplier = dao.getStocksId(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (status == "Completed" || status.equals("Completed"))
			supplier.setPaidStatus("Completed");
		else {
			supplier.setPaidStatus("Cancel");
			UniformSupplier last = dao.getLastpaymenttoSupplier(supplier.getSupplierName(),supplier.getSupplierNo());
			UniformSupplier sup=new UniformSupplier();
			sup.setBalanceAmount(supplier.getPaidAmount()+last.getDues());
			sup.setDues(supplier.getPaidAmount()+last.getDues());
			sup.setActive(true);
			sup.setDateOfPurchase(df.parse(df.format(new Date())));
			sup.setPaidAmount(0);
			sup.setDiscount(0);
			sup.setReceiptNo(0);
			sup.setGrossAmount(supplier.getPaidAmount()+last.getDues());
			sup.setSupplierName(supplier.getSupplierName());
			sup.setSupplierNo(supplier.getSupplierNo());
			sup.setPaidStatus("None");
			sup.setPaymentMode("Cancelled");
			dao.create(sup);
		}

	}

	@Override
	public List<UniformSupplierDTO> getUniformsBoughtDetails(String date)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfm = new SimpleDateFormat("dd-MM-yyyy");
		Date needDate = new Date();
		if (!date.equals("")) {
			needDate = df.parse(date);
		} else {
			needDate = df.parse(df.format(new Date()));
		}
		List<UniformSupplier> purchases = dao.getBoughtDetails(needDate);

		List<UniformSupplierDTO> dtolist = new ArrayList<UniformSupplierDTO>();
		for (UniformSupplier purchase : purchases) {
			UniformSupplierDTO dto = new UniformSupplierDTO();
			dto.setGrossAmount(purchase.getGrossAmount());
			dto.setBalanceAmount(purchase.getDues());
			dto.setPaidAmount(purchase.getPaidAmount());
			dto.setDateOfPurchase(dfm.format(purchase.getDateOfPurchase()));
			dto.setStockPurchaseId(purchase.getStockPurchaseId());
			dto.setPaidStatus(purchase.getPaidStatus());
			dto.setPaymentMode(purchase.getPaymentMode());
			dto.setReceiptNo(purchase.getReceiptNo());
			dto.setSupplierName(purchase.getSupplierName());
			dto.setDiscount(purchase.getDiscount());
			dtolist.add(dto);
		}
		return dtolist;
	}

	@Override
	public List<UniformSupplierDTO> findOverallByPageNo(int pageNo) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<UniformSupplierDTO> supplierDTOs = new ArrayList<UniformSupplierDTO>();
		for (UniformSupplier uniformSuppliers : dao.getOverallByPageNo(pageNo)) {
			UniformSupplierDTO supplierDTO = new UniformSupplierDTO();
			supplierDTO.setGrossAmount(uniformSuppliers.getGrossAmount());
			supplierDTO.setBalanceAmount(uniformSuppliers.getDues());
			supplierDTO.setPaidAmount(uniformSuppliers.getPaidAmount());
			supplierDTO.setDateOfPurchase(df.format(uniformSuppliers
					.getDateOfPurchase()));
			supplierDTO.setStockPurchaseId(uniformSuppliers
					.getStockPurchaseId());
			supplierDTO.setPaidStatus(uniformSuppliers.getPaidStatus());
			supplierDTO.setPaymentMode(uniformSuppliers.getPaymentMode());
			supplierDTO.setReceiptNo(uniformSuppliers.getReceiptNo());
			supplierDTO.setSupplierName(uniformSuppliers.getSupplierName());
			supplierDTO.setDiscount(uniformSuppliers.getDiscount());
			supplierDTOs.add(supplierDTO);
		}

		return supplierDTOs;
	}

	@Override
	public List<UniformSupplierDTO> findTodaysExpense() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		List<UniformSupplierDTO> supplierDTOs = new ArrayList<UniformSupplierDTO>();
		for (UniformSupplier uniformSuppliers : dao.findTodaysExpense(df
				.parse(df.format(date)))) {
			UniformSupplierDTO supplierDTO = new UniformSupplierDTO();
			supplierDTO.setGrossAmount(uniformSuppliers.getGrossAmount());
			supplierDTO.setBalanceAmount(uniformSuppliers.getDues());
			supplierDTO.setPaidAmount(uniformSuppliers.getPaidAmount());
			supplierDTO.setDateOfPurchase(df.format(uniformSuppliers
					.getDateOfPurchase()));
			supplierDTO.setStockPurchaseId(uniformSuppliers
					.getStockPurchaseId());
			supplierDTO.setPaidStatus(uniformSuppliers.getPaidStatus());
			supplierDTO.setPaymentMode(uniformSuppliers.getPaymentMode());
			supplierDTO.setReceiptNo(uniformSuppliers.getReceiptNo());
			supplierDTO.setSupplierName(uniformSuppliers.getSupplierName());
			supplierDTO.setDiscount(uniformSuppliers.getDiscount());
			supplierDTOs.add(supplierDTO);
		}
		return supplierDTOs;
	}

	@Override
	public List<UniformSupplierDTO> findDatewiseExpense(String fromDate,
			String toDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<UniformSupplierDTO> supplierDTOs = new ArrayList<UniformSupplierDTO>();
		for (UniformSupplier uniformSuppliers : dao.findDatewiseExpense(
				df.parse(fromDate), df.parse(toDate))) {
			UniformSupplierDTO supplierDTO = new UniformSupplierDTO();
			supplierDTO.setGrossAmount(uniformSuppliers.getGrossAmount());
			supplierDTO.setBalanceAmount(uniformSuppliers.getDues());
			supplierDTO.setPaidAmount(uniformSuppliers.getPaidAmount());
			supplierDTO.setDateOfPurchase(df.format(uniformSuppliers
					.getDateOfPurchase()));
			supplierDTO.setStockPurchaseId(uniformSuppliers
					.getStockPurchaseId());
			supplierDTO.setPaidStatus(uniformSuppliers.getPaidStatus());
			supplierDTO.setPaymentMode(uniformSuppliers.getPaymentMode());
			supplierDTO.setReceiptNo(uniformSuppliers.getReceiptNo());
			supplierDTO.setSupplierName(uniformSuppliers.getSupplierName());
			supplierDTO.setDiscount(uniformSuppliers.getDiscount());
			supplierDTOs.add(supplierDTO);
		}
		return supplierDTOs;
	}

}
