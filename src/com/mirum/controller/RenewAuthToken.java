package com.mirum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.mirum.exception.UnauthorizedException;
import com.mirum.jaxb.model.Response;
import com.mirum.jaxb.model.SignInResponse;
import com.mirum.service.CustomerService;
import com.mirum.service.CustomerServiceImpl;

/**
 * Servlet implementation class RenewAuthToken
 */
@WebServlet(description = "Generates a new token, updates the old one in the customer table and returns to the client", urlPatterns = { "/RenewAuthToken" })
public class RenewAuthToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenewAuthToken() {
        super();
        // TODO Auto-generated constructor stub
        customerService = new CustomerServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = request.getParameter("authToken");
		try {
			token = customerService.updateToken(token);
			
			response.setContentType("text/xml");
			Response xmlResponse = new SignInResponse(HttpServletResponse.SC_OK, token);
			
			JAXB.marshal(xmlResponse, response.getOutputStream());
			
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Response xmlResponse = new Response(HttpServletResponse.SC_UNAUTHORIZED);
			JAXB.marshal(xmlResponse, response.getOutputStream());
		}
	}

}
