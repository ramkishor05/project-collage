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
import org.brijframework.college.service.UniformPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("uniformPurchaseService")
public class UniformPurchaseServiceImpl extends
		CRUDServiceImpl<Integer, UniformPurchase, UniformPurchaseDao> implements
		UniformPurchaseService {
	@Autowired
	SessionDao sessionDao;
	@Autowired
	AllotDressDao allotDressDao;
	@Autowired
	UniformSupplierDao uniformSupplierDao;

	@Autowired
	public UniformPurchaseServiceImpl(UniformPurchaseDao dao) {
		super(dao);

	}

	@Override
	public int getProductNo() {
		int count = dao.findMaxProductNo() + 1;
		return count;
	}

	@Override
	public List<UniformPurchaseDTO> getPurchaseDetails() throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]),4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<UniformPurchase> purchaselist = dao
				.findCurrentSessionPurchases(frmDate);
		List<UniformPurchaseDTO> list = new ArrayList<UniformPurchaseDTO>();
		for (UniformPurchase uniform : purchaselist) {
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setAmount(uniform.getAmount());
			dto.setDateOfPurchase(df.format(uniform.getDateOfPurchase()));
			dto.setProductNo(uniform.getProductNo());
			dto.setProductPurchaseId(uniform.getProductPurchaseId());
			dto.setBoughtQuantity(uniform.getBoughtQuantity());
			dto.setShopName(uniform.getShopName());
			dto.setUniformCategory(uniform.getUniformCategory());
			dto.setUniformName(uniform.getUniformName());
			dto.setUniformSize(uniform.getUniformSize());
			dto.setUniformPrice(uniform.getUniformPrice());
			list.add(dto);

		}
		return list;
	}

	@Override
	public List<String> findShopNames() {
		
		List<String> list = dao.finshopNames();
		return list;
	}

	@Override
	public void savepurchasedetals(UniformPurchaseDTO uniformPurchaseDTO)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		UniformPurchase uniform = new UniformPurchase();
		uniform.setActive(true);
		uniform.setAmount(uniformPurchaseDTO.getAmount());
		uniform.setDateOfPurchase(df.parse(uniformPurchaseDTO
				.getDateOfPurchase()));
		uniform.setProductNo(uniformPurchaseDTO.getProductNo());
		uniform.setBoughtQuantity(uniformPurchaseDTO.getBoughtQuantity());
		uniform.setShopName(uniformPurchaseDTO.getShopName());
		uniform.setUniformCategory(uniformPurchaseDTO.getUniformCategory());
		uniform.setUniformName(uniformPurchaseDTO.getUniformName());
		uniform.setUniformSize(uniformPurchaseDTO.getUniformSize());
		uniform.setUniformPrice(uniformPurchaseDTO.getUniformPrice());
		uniform.setRemainingQuantity(uniformPurchaseDTO.getBoughtQuantity());
		dao.create(uniform);

	}

	@Override
	public String updatepurchases(UniformPurchaseDTO uniformPurchaseDTO)
			throws ParseException {
		String result = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		UniformPurchase uniform = dao.get(uniformPurchaseDTO
				.getProductPurchaseId());

		if ((uniform.getUniformCategory().equals(uniformPurchaseDTO
				.getUniformCategory()))
				&& (uniform.getUniformName().equals(uniformPurchaseDTO
						.getUniformName()))
				&& (uniform.getUniformSize().equals(uniformPurchaseDTO
						.getUniformSize()))
				&& (uniform.getUniformPrice() == uniformPurchaseDTO
						.getUniformPrice())) {
			if (uniform.getBoughtQuantity() <= uniformPurchaseDTO
					.getBoughtQuantity()) {
				uniform.setAmount(uniformPurchaseDTO.getAmount());
				uniform.setDateOfPurchase(df.parse(uniformPurchaseDTO
						.getDateOfPurchase()));
				uniform.setRemainingQuantity(uniformPurchaseDTO
						.getBoughtQuantity()
						- uniform.getBoughtQuantity()
						+ uniform.getRemainingQuantity());
				uniform.setBoughtQuantity(uniformPurchaseDTO
						.getBoughtQuantity());
				uniform.setShopName(uniformPurchaseDTO.getShopName());
				result="*Successfully Updated";
				
			} else {
				int restquantity = uniform.getRemainingQuantity();
				int returnquantity = uniform.getBoughtQuantity()
						- uniformPurchaseDTO.getBoughtQuantity();
				if (restquantity < returnquantity) {
					result = "*Stock is less";
				} else {
					uniform.setAmount(uniformPurchaseDTO.getAmount());
					uniform.setDateOfPurchase(df.parse(uniformPurchaseDTO
							.getDateOfPurchase()));
					uniform.setBoughtQuantity(uniformPurchaseDTO
							.getBoughtQuantity());
					uniform.setRemainingQuantity(uniform.getRemainingQuantity()
							- returnquantity);
					uniform.setShopName(uniformPurchaseDTO.getShopName());
					result="*Successfully Updated";
				}
			}

		} else {
			if (uniform.getRemainingQuantity() < uniform.getBoughtQuantity()) {
				result = "*Uniform has been sold out from this stock";
			} else {
				uniform.setAmount(uniformPurchaseDTO.getAmount());
				uniform.setDateOfPurchase(df.parse(uniformPurchaseDTO
						.getDateOfPurchase()));
				uniform.setBoughtQuantity(uniformPurchaseDTO
						.getBoughtQuantity());
				uniform.setShopName(uniformPurchaseDTO.getShopName());
				uniform.setUniformCategory(uniformPurchaseDTO
						.getUniformCategory());
				uniform.setUniformName(uniformPurchaseDTO.getUniformName());
				uniform.setUniformSize(uniformPurchaseDTO.getUniformSize());
				uniform.setUniformPrice(uniformPurchaseDTO.getUniformPrice());
				uniform.setRemainingQuantity(uniformPurchaseDTO
						.getBoughtQuantity());
				result = "Successfully Updated";
			}
		}
		return result;
	}

	@Override
	public List<String> getUniformNames() {
		List<String> list = dao.findUniformNames();
		return list;
	}

	@Override
	public List<String> getCategoryByName(String name) {
		List<String> list = dao.findCategorybyName(name);
		return list;
	}

	@Override
	public List<String> getSizebyCategory(String category, String name) {
		List<String> list = dao.findSizebycategory(category, name);
		return list;
	}

	@Override
	public UniformPurchaseDTO getPurchaseDetailsbyId(int productPurchaseId) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		UniformPurchase uniform = dao.get(productPurchaseId);
		UniformPurchaseDTO dto = new UniformPurchaseDTO();
		dto.setAmount(uniform.getAmount());
		dto.setDateOfPurchase(df.format(uniform.getDateOfPurchase()));
		dto.setProductNo(uniform.getProductNo());
		dto.setProductPurchaseId(uniform.getProductPurchaseId());
		dto.setBoughtQuantity(uniform.getBoughtQuantity());
		dto.setShopName(uniform.getShopName());
		dto.setUniformCategory(uniform.getUniformCategory());
		dto.setUniformName(uniform.getUniformName());
		dto.setUniformSize(uniform.getUniformSize());
		dto.setUniformPrice(uniform.getUniformPrice());
		return dto;

	}

	@Override
	public String cancelPurchase(int productPurchaseId) {
		String result = "";
		UniformPurchase uniform = dao.get(productPurchaseId);
		if (uniform.getRemainingQuantity() < uniform.getBoughtQuantity()) {
			result = "*Uniform has been sold out from this stock";
		} else {
			result = "Successfully deleted";
			uniform.setActive(false);
		}
		return result;
	}

	@Override
	public List<UniformPurchaseDTO> getUniformNamesandCategory() {
		List<String> names = dao.findUniformNames();
		List<UniformPurchaseDTO> list = new ArrayList<UniformPurchaseDTO>();
		for (String uniform : names) {
			List<String> categoryList = new ArrayList<String>();
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setUniformName(uniform);
			List<String> category1 = dao.findCategorybyName(uniform);
			for (String var : category1) {
				categoryList.add(var);
			}
			dto.setCategoryList(categoryList);
			list.add(dto);

		}
		return list;
	}

	@Override
	public UniformPurchaseDTO getUniformPriceandId(String name,
			String category, String size) {
		int remains = 0;
		UniformPurchase uniform = dao
				.getUniformPriceandId(name, category, size);
		UniformPurchaseDTO dto = new UniformPurchaseDTO();
		if (uniform == null) {

			dto.setRemainingQuantity(0);

		} else {

			List<AllotDress> allot = allotDressDao.finddetailsbyId(uniform
					.getProductPurchaseId());
			if (allot != null) {
				for (AllotDress dress : allot) {
					remains = dress.getQuantity();
				}
			}
			dto.setUniformPrice(uniform.getUniformPrice());
			dto.setProductPurchaseId(uniform.getProductPurchaseId());
			dto.setRemainingQuantity(uniform.getRemainingQuantity() - remains);
		}
		return dto;
	}

	@Override
	public UniformPurchaseDTO getUniformNewPriceandId(String name,
			String category, String size, int id) {
		UniformPurchase uniform = dao.getNewUniformPriceandId(name, category,
				size, id);
		UniformPurchaseDTO dto = new UniformPurchaseDTO();
		
		if (uniform == null) {

			dto.setRemainingQuantity(0);

		} else {
			dto.setUniformPrice(uniform.getUniformPrice());
			dto.setProductPurchaseId(uniform.getProductPurchaseId());
			dto.setRemainingQuantity(uniform.getRemainingQuantity());
		}
		return dto;
	}

	@Override
	public List<UniformPurchaseDTO> getUniformStockDetails()
			throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<String> UniformNames = dao.findUniformNames();
		List<UniformPurchaseDTO> list = new ArrayList<UniformPurchaseDTO>();
		int totalQuantity = 0;
		int remainingQuantity = 0;
		for (String name : UniformNames) {
			totalQuantity = 0;
			remainingQuantity = 0;
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setUniformName(name);

			List<UniformPurchase> Uniformstock = dao.findStockbyUniformName(
					name, frmDate);
			for (UniformPurchase stock : Uniformstock) {

				totalQuantity += stock.getBoughtQuantity();
				remainingQuantity += stock.getRemainingQuantity();
			}
			dto.setBoughtQuantity(totalQuantity);
			dto.setRemainingQuantity(remainingQuantity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<UniformPurchaseDTO> getUniformStockDetailsbyname(String name)
			throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<UniformPurchaseDTO> list = new ArrayList<UniformPurchaseDTO>();
		int totalQuantity = 0;
		int remainingQuantity = 0;
		List<String> Uniformcategory = dao.findCategorybyName(name);
		for (String category : Uniformcategory) {
			totalQuantity = 0;
			remainingQuantity = 0;
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setUniformCategory(category);
			List<UniformPurchase> Uniformstock = dao
					.findStockbyUniformCategory(name, category, frmDate);
			for (UniformPurchase stock : Uniformstock) {

				totalQuantity += stock.getBoughtQuantity();
				remainingQuantity += stock.getRemainingQuantity();
			}
			dto.setBoughtQuantity(totalQuantity);
			dto.setRemainingQuantity(remainingQuantity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<UniformPurchaseDTO> getUniformStockDetailsbysize(String name,
			String category) throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<UniformPurchaseDTO> list = new ArrayList<UniformPurchaseDTO>();
		int totalQuantity = 0;
		int remainingQuantity = 0;
		List<String> Uniformsize = dao.findSizebycategory(category, name);
		for (String size : Uniformsize) {
			totalQuantity = 0;
			remainingQuantity = 0;
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setUniformSize(size);
			List<UniformPurchase> Uniformstock = dao.findStockbyUniformSize(
					name, category, size, frmDate);
			for (UniformPurchase stock : Uniformstock) {

				totalQuantity += stock.getBoughtQuantity();
				remainingQuantity += stock.getRemainingQuantity();
			}
			dto.setBoughtQuantity(totalQuantity);
			dto.setRemainingQuantity(remainingQuantity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public UniformPurchaseDTO getUniformPrice(String category, String name,
			String size) {
		UniformPurchase uniform = dao.getPriceforUniform(name, category, size);
		UniformPurchaseDTO dto = new UniformPurchaseDTO();
		if (uniform == null) {
			dto.setUniformPrice(0);
		} else {
			dto.setUniformPrice(uniform.getUniformPrice());
		}
		return dto;
	}

	@Override
	public List<UniformPurchaseDTO> getBoughtDetails(int stockPurchaseId) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		List<UniformPurchase> purchaselist=dao.getUniformBoughtDetails(stockPurchaseId);
		UniformSupplier supply=uniformSupplierDao.get(stockPurchaseId);
		
		List<UniformPurchaseDTO> list = new ArrayList<UniformPurchaseDTO>();
		for (UniformPurchase uniform : purchaselist) {
			UniformPurchaseDTO dto = new UniformPurchaseDTO();
			dto.setAmount(uniform.getAmount());
			dto.setDateOfPurchase(df.format(uniform.getDateOfPurchase()));
		    dto.setBoughtQuantity(uniform.getBoughtQuantity());
		    dto.setNetAmount(uniform.getNetAmount());
			dto.setUniformCategory(uniform.getUniformCategory());
			dto.setUniformName(uniform.getUniformName());
			dto.setUniformSize(uniform.getUniformSize());
			dto.setUniformPrice(uniform.getUniformPrice());
			dto.setShopName(uniform.getStocksupplier().getSupplierName());
			dto.setPaymentMode(supply.getPaymentMode());
			if(supply.getPaymentMode()=="Cheque" || supply.getPaymentMode().equals("Cheque"))
			{
				dto.setBankName(supply.getBankName());
				dto.setChequeno(supply.getChequeNo());
				
			}
			list.add(dto);

		}
		return list;
	}

	@Override
	public int getUniformStockDetailsforeach(String name, String category,
			String size) {
		int remaining=0;
		List<UniformPurchase> purchase=dao.getUniformStocks(name, category, size);
		for(UniformPurchase list:purchase)
		{
			remaining+=list.getRemainingQuantity();
		}
		return remaining;
	}

	@Override
	public List<UniformPurchaseDTO> getPurchasedUniformDetailsbysize(
			String name, String category, String size) throws ParseException {
		Session current = sessionDao.findCurrentSession();
		String getYear[] = (current.getSessionDuration()).split("-");
		GregorianCalendar gc = new GregorianCalendar(
				Integer.parseInt(getYear[0]), 4 - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = df.parse(df.format(new Date(gc.getTime().getTime())));
		List<UniformPurchase> purchase=dao.getSizeWisePurchases(name,category,size,frmDate);
		List<UniformPurchaseDTO> listdto=new ArrayList<UniformPurchaseDTO>();
		for(UniformPurchase list:purchase)
		{
			UniformPurchaseDTO dto=new UniformPurchaseDTO();
			dto.setShopName(list.getShopName());
			dto.setAmount(list.getAmount());
			dto.setNetAmount(list.getNetAmount());
			dto.setDateOfPurchase(df.format(list.getDateOfPurchase()));
			dto.setBoughtQuantity(list.getBoughtQuantity());
			listdto.add(dto);
		}
		return listdto;
		
	}



}