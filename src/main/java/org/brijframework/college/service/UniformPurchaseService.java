package org.brijframework.college.service;

import java.text.ParseException;
import java.util.List;

import org.brijframework.college.model.UniformPurchase;
import org.brijframework.college.models.dto.UniformPurchaseDTO;

public interface UniformPurchaseService extends CRUDService<Integer, UniformPurchase> {

	int getProductNo();

	List<UniformPurchaseDTO> getPurchaseDetails() throws ParseException;

	List<String> findShopNames();

	void savepurchasedetals(UniformPurchaseDTO uniformPurchaseDTO) throws ParseException;

	String updatepurchases(UniformPurchaseDTO uniformPurchaseDTO) throws ParseException;

		List<String> getUniformNames();

	List<String> getCategoryByName(String name);

	List<String> getSizebyCategory(String category, String name);

	UniformPurchaseDTO getPurchaseDetailsbyId(int productPurchaseId);

	String cancelPurchase(int productPurchaseId);

	List<UniformPurchaseDTO> getUniformNamesandCategory();

	UniformPurchaseDTO getUniformPriceandId(String name, String category,
			String size);

	UniformPurchaseDTO getUniformNewPriceandId(String name, String category,
			String size, int id);

	List<UniformPurchaseDTO> getUniformStockDetails() throws ParseException;

	List<UniformPurchaseDTO> getUniformStockDetailsbyname(String name) throws ParseException;

	List<UniformPurchaseDTO> getUniformStockDetailsbysize(String name,
			String category) throws ParseException;

	UniformPurchaseDTO getUniformPrice(String category, String name, String size);

	List<UniformPurchaseDTO> getBoughtDetails(int stockPurchaseId);

	int getUniformStockDetailsforeach(String name, String category, String size);

	List<UniformPurchaseDTO> getPurchasedUniformDetailsbysize(String name, String category,
			String size) throws ParseException;

	

	




}
