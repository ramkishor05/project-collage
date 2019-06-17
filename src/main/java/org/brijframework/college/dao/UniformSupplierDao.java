package org.brijframework.college.dao;

import java.util.Date;
import java.util.List;

import org.brijframework.college.model.UniformSupplier;

public interface UniformSupplierDao extends DAO<Integer, UniformSupplier> {

	List<String> getSuppliersId();

	List<UniformSupplier> getDetailsbySupplier(String supplierName, Date frmDate);

	UniformSupplier getReceipt();

	UniformSupplier getStocksId(int receiptNo);

	List<UniformSupplier> getBoughtDetails(Date frmDate);

	List<UniformSupplier> getOverallByPageNo(int pageNo);

	List<UniformSupplier> findTodaysExpense(Date date);

	List<UniformSupplier> findDatewiseExpense(Date fromDate, Date toDate);

	UniformSupplier getLastpaymenttoSupplier(String supplierName,
			String supplierNo);

}
