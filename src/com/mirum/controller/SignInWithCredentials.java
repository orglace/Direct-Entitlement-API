package com.mirum.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.mirum.exception.UnauthorizedException;
import com.mirum.jaxb.model.Credentials;
import com.mirum.jaxb.model.Response;
import com.mirum.jaxb.model.SignInResponse;
import com.mirum.repository.CustomerRepository;
import com.mirum.service.CustomerService;
import com.mirum.service.CustomerServiceImpl;

/**
 * Servlet implementation class SignInWithCredentials
 */
@WebServlet(description = "Will authenticate the user using the passed credentials (emailAddress/password)", urlPatterns = { "/SignInWithCredentials" })
public class SignInWithCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CustomerService customerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInWithCredentials() {
        super();
        customerService = new CustomerServiceImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			
			// Create JAXBContext instance for class Credentials
			JAXBContext jc = JAXBContext.newInstance(Credentials.class);
			
			// Loading the schema for validation
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(Credentials.class.getResource("../validation/Credentials.xsd").getFile()));
			
			// Create the unmarshaller to convert the xml inputstream to java class and setting the schema for validation
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			unmarshaller.setSchema(schema);

			// Mapping the xml credentials request to java class
			Credentials credentials = (Credentials) unmarshaller.unmarshal(request.getInputStream());
			
			response.setContentType("text/xml");
			
			String authToken = customerService.authenticate(credentials.getEmailAddress(), credentials.getPassword());
			Response xmlResponse = new SignInResponse(HttpServletResponse.SC_OK, authToken);
						
			JAXB.marshal(xmlResponse, response.getOutputStream());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Bad request");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Response xmlResponse = new Response(HttpServletResponse.SC_UNAUTHORIZED);
			JAXB.marshal(xmlResponse, response.getOutputStream());
		}
		
	}

}
