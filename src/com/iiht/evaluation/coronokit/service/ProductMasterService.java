package com.iiht.evaluation.coronokit.service;

import java.util.List;

import com.iiht.evaluation.coronokit.exception.ProductMasterException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public interface ProductMasterService {

	ProductMaster validateAndAdd(ProductMaster contact) throws ProductMasterException;
	ProductMaster validateAndSave(ProductMaster contact) throws ProductMasterException;
	
	boolean deleteProduct(int id) throws ProductMasterException;
	
	ProductMaster getProduct(int id) throws ProductMasterException;
	List<ProductMaster> getAllIetms() throws ProductMasterException;
}
