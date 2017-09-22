package com.mirum.service;

import java.util.List;

import com.mirum.exception.UnauthorizedException;

public interface CustomerService {
	
	String authenticate(String user, String password) throws UnauthorizedException;
	
	String updateToken(String token) throws UnauthorizedException;
	
	List<String> getProductList(String token) throws UnauthorizedException;
	
	boolean verifyEntitlement(String token, String productId) throws UnauthorizedException;
}
