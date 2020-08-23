package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.exception.ProductMasterException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDaoJdbcImpl implements ProductMasterDao {

	public static final String INS_CONT_QRY = "INSERT INTO Productmaster(id,productname,cost,productdesc) VALUES(?,?,?,?)";
	public static final String UPD_CONT_QRY = "UPDATE Productmaster set productname=?,cost=?,productdesc=? WHERE id=?";
	public static final String DEL_CONT_QRY = "DELETE FROM Productmaster WHERE id=?";
	public static final String GET_CONT_BY_ID_QRY = "SELECT id,productname,cost,productdesc FROM Productmaster WHERE id=?";
	public static final String GET_ALL_CONTS_QRY = "SELECT id,productname,cost,productdesc FROM Productmaster";



	@Override
	public boolean deleteById(int pId) throws ProductMasterException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_CONT_QRY);) {

			pst.setInt(1, pId);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException exp) {
			throw new ProductMasterException("Deleting Items failed!");
		}
		return isDeleted;
	}

	@Override
	public List<ProductMaster> getAll() throws ProductMasterException {
		List<ProductMaster> productmasterItems = new ArrayList<>();
		
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_ALL_CONTS_QRY);) {
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster productmasterItem = new ProductMaster();
				productmasterItem.setId(rs.getInt(1));
				productmasterItem.setProductName(rs.getString(2));
				productmasterItem.setCost(rs.getString(3));
				productmasterItem.setProductDescription((rs.getString(4)));
				
				productmasterItems.add(productmasterItem);
			}
			
			if(productmasterItems.isEmpty()) {
				productmasterItems=null;
			}

		} catch (SQLException exp) {
			throw new ProductMasterException("Feteching Items failed!");
		}
		
		return productmasterItems;
	}

	@Override
	public ProductMaster add(ProductMaster productmasterItem) throws ProductMasterException {

		if (productmasterItem != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_CONT_QRY);) {

				pst.setInt(1, productmasterItem.getId());
				pst.setString(2, productmasterItem.getProductName());
				pst.setString(3, productmasterItem.getCost());
				pst.setString(4, productmasterItem.getProductDescription());
				
				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new ProductMasterException("Saving Item failed!");
			}
		}

		return productmasterItem;
	}

	@Override
	public ProductMaster save(ProductMaster productmasterItem) throws ProductMasterException {
		if (productmasterItem != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_CONT_QRY);) {

				pst.setString(1, productmasterItem.getProductName());
				pst.setString(2, productmasterItem.getCost());
				pst.setString(3, productmasterItem.getProductDescription());
				pst.setInt(4, productmasterItem.getId());

				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new ProductMasterException("Saving Item failed!");
			}
		}

		return productmasterItem;
	}

	@Override
	public ProductMaster getById(int pId) throws ProductMasterException {
		ProductMaster productmasterItem = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_CONT_BY_ID_QRY);) {

			pst.setInt(1, pId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				productmasterItem = new ProductMaster();
				productmasterItem.setId(rs.getInt(1));
				productmasterItem.setProductName(rs.getString(2));
				productmasterItem.setCost(rs.getString(3));
				productmasterItem.setProductDescription(rs.getString(4));
		
			}

		} catch (SQLException exp) {
			throw new ProductMasterException("Feteching Item failed!");
		}

		return productmasterItem;
	}

}