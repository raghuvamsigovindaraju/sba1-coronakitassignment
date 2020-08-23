package com.iiht.evaluation.coronokit.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDaoJdbcImpl;
import com.iiht.evaluation.coronokit.exception.ProductMasterException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterServiceImpl implements ProductMasterService {

	private ProductMasterDao productmasterDao;
	
	public ProductMasterServiceImpl() {
		productmasterDao = new ProductMasterDaoJdbcImpl();
	}
	
	private boolean isValidProductID(Integer productmasterId) {
		return productmasterId!=null && productmasterId>0; 
	}
	private boolean isValidProdname(String productname) {
		return productname!=null && (productname.length()>=3 || productname.length()<=20);
	}
	private boolean isValidProdcost(String productcost) {
		return productcost!=null && (productcost.length()>=3 || productcost.length()<=20);
	}
	private boolean isValidProddesc(String productdesc) {
		return productdesc!=null && (productdesc.length()>=3 || productdesc.length()<=20);
	}
	
	
	public boolean isValidProduct(ProductMaster productmaster) throws ProductMasterException {
		List<String> errMsgs = new ArrayList<>();
		boolean isValid=true;
		
		if(productmaster!=null) {
			if(!isValidProductID(productmaster.getId())) {
				isValid=false;
				errMsgs.add("product id can not left blank and must be a positive number, Duplicates are not allowed");
			}
			if(!isValidProdname(productmaster.getProductName())) {
				isValid=false;
				errMsgs.add("product name can not left blank and must be of 3 to 20 in length");
			}
			if(!isValidProdcost(productmaster.getCost())) {
				isValid=false;
				errMsgs.add("product cost can not left blank and must be of 3 to 20 in length");
			}
			if(!isValidProddesc(productmaster.getProductDescription())) {
				isValid=false;
				errMsgs.add("product desc can not left blank and must be of 3 to 20 in length");
			}
			
			if(!errMsgs.isEmpty()) {
				throw new ProductMasterException("Invalid Contact: "+errMsgs);
			}
		}else {
			isValid=false;
			throw new ProductMasterException("product details are not supplied");
		}
		
		return isValid;
	}
	
	@Override
	public ProductMaster validateAndAdd(ProductMaster productmaster) throws ProductMasterException {
		if(isValidProduct(productmaster)) {
			productmasterDao.add(productmaster);
		}
		return productmaster;
	}

	@Override
	public ProductMaster validateAndSave(ProductMaster productmaster) throws ProductMasterException {
		if(isValidProduct(productmaster)) {
			productmasterDao.save(productmaster);
		}
		return productmaster;
	}

	@Override
	public boolean deleteProduct(int id) throws ProductMasterException {
		return productmasterDao.deleteById(id);
	}

	@Override
	public ProductMaster getProduct(int id) throws ProductMasterException {
		return productmasterDao.getById(id);
	}

	@Override
	public List<ProductMaster> getAllIetms() throws ProductMasterException {
		return productmasterDao.getAll();
	}

}
