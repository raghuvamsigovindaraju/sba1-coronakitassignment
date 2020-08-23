package com.iiht.evaluation.coronokit.dao;

import java.util.List;

import com.iiht.evaluation.coronokit.exception.ProductMasterException;
import com.iiht.evaluation.coronokit.model.ProductMaster;


public interface ProductMasterDao {

	ProductMaster add(ProductMaster productMasterItem) throws ProductMasterException;

	ProductMaster save(ProductMaster productMasterItem) throws ProductMasterException;

	boolean deleteById(int pId) throws ProductMasterException;

	ProductMaster getById(int pId) throws ProductMasterException;

	List<ProductMaster> getAll() throws ProductMasterException;
}
