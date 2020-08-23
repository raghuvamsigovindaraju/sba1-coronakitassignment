package com.iiht.evaluation.coronokit.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class AdminAuthenticationFilter
 */
@WebFilter("/admin")
public class AdminAuthenticationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminAuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		
		String userid=request.getParameter("loginid");
		String password=request.getParameter("password");
		if(userid.contentEquals("admin") && password.contentEquals("admin"))
		{
			chain.doFilter(request, response);
		}
		else {
			out.print("Userid/Password not valid Please Navigate to Home Page and provide valid admin Credentials");
		}
				
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
