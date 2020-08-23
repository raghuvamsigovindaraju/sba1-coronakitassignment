package com.iiht.evaluation.coronokit.model;

import java.util.List;

public class OrderSummary {
	int orderproductnumber;
	String orderproductname;
	String orderproductcost;
	String orderproductdescription;
	String orderproductquantity;
	String orderproducttotalcost;
	public int getOrderproductnumber() {
		return orderproductnumber;
	}
	public String getOrderproducttotalcost() {
		return orderproducttotalcost;
	}
	public void setOrderproducttotalcost(String orderproducttotalcost) {
		this.orderproducttotalcost = orderproducttotalcost;
	}
	public void setOrderproductnumber(int orderproductnumber) {
		this.orderproductnumber = orderproductnumber;
	}
	public String getOrderproductname() {
		return orderproductname;
	}
	public void setOrderproductname(String orderproductname) {
		this.orderproductname = orderproductname;
	}
	public String getOrderproductcost() {
		return orderproductcost;
	}
	public void setOrderproductcost(String orderproductcost) {
		this.orderproductcost = orderproductcost;
	}
	public String getOrderproductdescription() {
		return orderproductdescription;
	}
	public void setOrderproductdescription(String orderproductdescription) {
		this.orderproductdescription = orderproductdescription;
	}
	public String getOrderproductquantity() {
		return orderproductquantity;
	}
	public void setOrderproductquantity(String orderproductquantity) {
		this.orderproductquantity = orderproductquantity;
	}
	
	
}
