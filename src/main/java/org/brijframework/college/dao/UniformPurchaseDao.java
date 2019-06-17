package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.UniformPurchase;

public interface UniformPurchaseDao extends DAO<Integer, UniformPurchase> {

	List<UniformPurchase> findCurrentSessionPurchases(Date frmDate);

	List<String> finshopNames();

	List<String> findUniformNames();

	List<String> findCategorybyName(String name);

	List<String> findSizebycategory(String category, String name);

	UniformPurchase getUniformPriceandId(String name, String category,
			String size);

	UniformPurchase getNewUniformPriceandId(String name, String category,
			String size, int id);

	List<UniformPurchase> findStockbyUniformName(String name, Date frmDate);

	List<UniformPurchase> findStockbyUniformCategory(String name,
			String category, Date frmDate);

	List<UniformPurchase> findStockbyUniformSize(String name, String category,
			String size, Date frmDate);

	UniformPurchase getPriceforUniform(String name, String category, String size);

	int findMaxProductNo();

	List<UniformPurchase> getUniformBoughtDetails(int stockPurchaseId);

	List<Integer> getProductPurchases(String name, String category,
			String size);

	List<UniformPurchase> getUniformStocks(String name, String category,
			String size);

	List<UniformPurchase> getSizeWisePurchases(String name, String category,
			String size, Date frmDate);

	

	



	

}
