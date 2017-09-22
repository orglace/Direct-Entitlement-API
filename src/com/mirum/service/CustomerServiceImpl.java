package com.mirum.service;

import java.util.List;

import com.mirum.exception.UnauthorizedException;
import com.mirum.jaxb.model.Credentials;
import com.mirum.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
		customerRepository = new CustomerRepository();
	}

	@Override
	public String authenticate(String user, String password) throws UnauthorizedException {
		// TODO Auto-generated method stub
		return customerRepository.authenticate(user, password);
	}

	@Override
	public String updateToken(String token) throws UnauthorizedException {
		// TODO Auto-generated method stub
		Credentials credentials = customerRepository.authenticateByToken(token);
		
		return customerRepository.updateToken(credentials.getEmailAddress(), credentials.getPassword());
	}

	@Override
	public List<String> getProductList(String token) throws UnauthorizedException {
		// TODO Auto-generated method stub
		return customerRepository.getProductsByToken(token);
	}

	@Override
	public boolean verifyEntitlement(String token, String productId) throws UnauthorizedException {
		// TODO Auto-generated method stub
		return customerRepository.verifyEntitlementByTokenAndProductId(token, productId);
	}

}
