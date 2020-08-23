package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.exception.ProductMasterException;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.ProductMasterService;
import com.iiht.evaluation.coronokit.service.ProductMasterServiceImpl;

@WebServlet({"/newuser","/showproducts","/showkit","/addkit","/ordersummary"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterService productmasterService;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {

		productmasterService = new ProductMasterServiceImpl();
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String url = request.getServletPath();
		
		String viewName = "";
		try {
			switch (url) {
			case "/newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "/showproducts":
				viewName = showAllProducts(request, response,session);
				break;	
			case "/addkit":
				viewName = addNewItemToKit(request, response,session);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "/showkit":
				viewName = showKitDetails(request, response,session);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "/ordersummary":
				viewName = showOrderSummary(request, response,session);
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

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		List<ProductMaster> pmlist=(List<ProductMaster>) session.getAttribute("kitproducts");
		List<OrderSummary> ordersummarylist=new ArrayList<OrderSummary>();
        int totalordercost=0;
		for(ProductMaster productmaster:pmlist)
		{
			OrderSummary os=new OrderSummary();
			os.setOrderproductcost(productmaster.getCost());
			os.setOrderproductdescription(productmaster.getProductDescription());
			os.setOrderproductname(productmaster.getProductName());
			os.setOrderproductnumber(productmaster.getId());
			os.setOrderproductquantity("1");
			int cost;
			int quantity;
			int totalcost;
			
			cost=Integer.parseInt(os.getOrderproductcost());
			quantity=Integer.parseInt(os.getOrderproductquantity());
			totalcost=cost*quantity;
			totalordercost=totalordercost+totalcost;
			os.setOrderproducttotalcost(String.valueOf(totalcost));
			ordersummarylist.add(os);
			
		}
		session.setAttribute("orders", ordersummarylist);
		session.setAttribute("totalordercost", totalordercost);
		
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String view="";
		/*
		 * String[] quantity=request.getParameterValues("quantity"); for(String
		 * s:quantity) { System.out.println("quantity"+s); }
		 */
		
		List<String> ids = (List<String>) session.getAttribute("productids");
		Set<String> uniqueids=new LinkedHashSet<>(ids);
		
		try {
			List<ProductMaster> productmasterkititems=new ArrayList<ProductMaster>();
		for(String x:uniqueids)
		  {
			int id=Integer.parseInt(x);
			
				ProductMaster productmasteritemkit = productmasterService.getProduct(id);
				productmasterkititems.add(productmasteritemkit);
			
		
				session.setAttribute("kitproducts", productmasterkititems);
		view = "showkit.jsp";
		 } 
		}catch (ProductMasterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        // HttpSession session = request.getSession();
        String view="";
        
      
        view = "showproductstoadd.jsp";
		
	List<String> ids = (List<String>) session.getAttribute("productids");
		
		if(ids==null) {
			ids = new ArrayList<>();
		}
		
		String id = request.getParameter("id");
		if(id!=null) {
			ids.add(id);
		}
		
		session.setAttribute("productids", ids);
	
		return view;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException
	{
		
		String view = "";
		String user=request.getParameter("fullName");
		String email=request.getParameter("email");
		String contact=request.getParameter("contact");
		
		
		System.out.println("parameter::"+email);

	try {
		List<ProductMaster> productmasteritems = productmasterService.getAllIetms();
	
	//	request.setAttribute("products", productmasteritems);
		session.setAttribute("products", productmasteritems);
		session.setAttribute("user", user);
		session.setAttribute("email", email);
		session.setAttribute("contact", contact);
		view = "showproductstoadd.jsp";
	} catch (ProductMasterException e) {
		request.setAttribute("errMsg", e.getMessage());
		view = "errPage.jsp";
	}

	return view;
   }

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "newuser.jsp";
		return view;
	}
}