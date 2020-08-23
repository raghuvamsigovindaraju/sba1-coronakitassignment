package com.iiht.evaluation.coronokit.entity;

public class CoronaItem {
	private int pid;
	private String pname;
	private String pdesc;
	private double pcost;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
		public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public double getPcost() {
		return pcost;
	}
	public void setPcost(double pcost) {
		this.pcost = pcost;
	}
	@Override
	public String toString() {
		
		return "CoronaItem [ProductId=" + pid + ", ProductName=" + pname + ", ProductDesc=" + pdesc
				+ ", cost=" + pcost + "]";
	}
}
