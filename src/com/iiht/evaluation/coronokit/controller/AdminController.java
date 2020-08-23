package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.exception.ProductMasterException;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.ProductMasterService;
import com.iiht.evaluation.coronokit.service.ProductMasterServiceImpl;



@WebServlet({ "/list", "/admin","/newproduct","/addProduct","/editProduct","/saveProduct","/logout" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterService productmasterService;

	@Override
	public void init() throws ServletException {
		productmasterService = new ProductMasterServiceImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();
		String viewName = "";
		try {
			switch (url) {
			case "/admin" : 
				viewName = adminLogin(request, response);
				break;
			case "/newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "/addProduct":
				viewName = addorSaveProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "/editProduct":
				viewName = updateProduct(request, response);
				break;
			case "/saveProduct":
				viewName = addorSaveProduct(request, response);
				break;
			case "/list":
				viewName = listAllProducts(request, response);
				break;	
			case "/logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String view = "";

	try {
		List<ProductMaster> productmasteritems = productmasterService.getAllIetms();
	
		request.setAttribute("products", productmasteritems);
		view = "listproducts.jsp";
	} catch (ProductMasterException e) {
		request.setAttribute("errMsg", e.getMessage());
		view = "errPage.jsp";
	}

	return view;
   }
	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductMaster productmasteritems = new ProductMaster();
		request.setAttribute("product", productmasteritems);
		request.setAttribute("isNew", true);
		String view = "newproduct.jsp";
		return view;
	}
	private String updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		int id = Integer.parseInt(request.getParameter("id"));
			String view = "";
 
			try {
				ProductMaster productmasteritem = productmasterService.getProduct(id);
				request.setAttribute("product", productmasteritem);
				request.setAttribute("isNew", false);
				view = "newproduct.jsp";
			} catch (ProductMasterException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errPage.jsp";
			}

			return view;
		}
	
	private String addorSaveProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductMaster productmasteritem = new ProductMaster();
        System.out.println("product name"+request.getParameter("productName"));
		productmasteritem.setId(Integer.parseInt(request.getParameter("productId")));
		productmasteritem.setProductName(request.getParameter("productName"));
		productmasteritem.setCost(request.getParameter("cost"));
		productmasteritem.setProductDescription(request.getParameter("productDescription"));

		String view = "";

		try {
			if (request.getServletPath().equals("/addProduct")) {
				productmasterService.validateAndAdd(productmasteritem);
			} else if (request.getServletPath().equals("/saveProduct")) {
				productmasterService.validateAndSave(productmasteritem);
			}
			List<ProductMaster> productmasteritems = productmasterService.getAllIetms();
			
			request.setAttribute("products", productmasteritems);
			request.setAttribute("msg", "Contact Saved Successfully");
			view = "listproducts.jsp";
		} catch (ProductMasterException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}


	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	String view = "";

	try {
		List<ProductMaster> productmasteritems = productmasterService.getAllIetms();
	
		request.setAttribute("products", productmasteritems);
		view = "listproducts.jsp";
	} catch (ProductMasterException e) {
		request.setAttribute("errMsg", e.getMessage());
		view = "errPage.jsp";
	}

	return view;
	}

	
}