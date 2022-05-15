package com;

import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class BillsAPI
 */
@WebServlet("/PaymentsAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Payment itemObj = new Payment();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//(String paymentID, String accNo, String userName,String amount, String description)
		String output = itemObj.addpayment(
				request.getParameter("paymentID"),
				request.getParameter("accNo"),
				request.getParameter("userName"),
				request.getParameter("amount"),
				request.getParameter("description"));
				
				response.getWriter().write(output); 
				
//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		System.out.println(paras);
		 String output = itemObj.updatePayment(
			 paras.get("paymentID").toString(),
			 paras.get("accNo").toString(), 
			 paras.get("userName").toString(), 
			 paras.get("amount").toString(), 
			 paras.get("description").toString()
			);
		 
		 
		 response.getWriter().write(output); 
	}
	
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map paras = getParasMap(request); 
		 String output = itemObj.deletePayment(paras.get("id").toString()); 
		 response.getWriter().write(output); 
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
		 { 
			 
			 String[] p = param.split("="); 
			 map.put(p[0], p[1]); 
		 } 
			 
		 } 
			catch (Exception e) 
		 { 
		 } 
		return map; 
		}

}
