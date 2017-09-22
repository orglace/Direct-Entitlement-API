package com.mirum.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.mirum.exception.UnauthorizedException;
import com.mirum.jaxb.model.Credentials;

public class CustomerRepository {

	private DataSourceFactory factory;
	
	public CustomerRepository() {
		// TODO Auto-generated constructor stub
		this.factory = new DataSourceFactory();
	}

	public String authenticate(String user, String password) throws UnauthorizedException {
		
		String selectSQL = "SELECT * FROM customer WHERE emailAddress = ? AND password = ?";
		try {
			Connection connection = DataSourceFactory.getDataSource().getConnection();
			
			PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSQL);
			selectPreparedStatement.setString(1, user);
			selectPreparedStatement.setString(2, password);
			
			ResultSet rs = selectPreparedStatement.executeQuery();

			String authToken;
			if (rs.next()) {
				if((authToken = rs.getString("authToken")) == null) {
					authToken = updateToken(user, password);
				}
			} else {
				throw new UnauthorizedException("Bad Credentials");
			}
			
			return authToken;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Bad Credentials!";
		} 
	}
	
	public Credentials authenticateByToken(String token) throws UnauthorizedException {
		
		if (token == null) {
			throw new UnauthorizedException("Bad Credentials");
		}
		
		String selectSQL = "SELECT * FROM customer WHERE authToken = ?";
		Credentials credentials = null;
		try {
			Connection connection = DataSourceFactory.getDataSource().getConnection();
			PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSQL);
			selectPreparedStatement.setString(1, token);
			
			ResultSet rs = selectPreparedStatement.executeQuery();
			if (rs.next()) {
				credentials = new Credentials(rs.getString("emailAddress"), rs.getString("password"));
			} else {
				throw new UnauthorizedException("Bad Credentials");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return credentials;
	}
	
	public String updateToken(String user, String password) {
		
		String updateSQL = "update customer set authToken = ? where emailAddress = ? and password = ?";
		
		String newToken = generateToken(user, password);
		try {
			Connection connection = DataSourceFactory.getDataSource().getConnection();
			PreparedStatement updatePreparedStatement = connection.prepareStatement(updateSQL);
			updatePreparedStatement.setString(1, newToken);
			updatePreparedStatement.setString(2, user);
			updatePreparedStatement.setString(3, password);
			
			updatePreparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newToken;
	}
	
	public List<String> getProductsByToken(String token) throws UnauthorizedException {
		
		String selectSQL = "select e.product_id from entitlement e inner join customer c on e.customer_id = c.id where c.authToken = ?";
		
		authenticateByToken(token);
		Connection connection;
		List<String> productIds = new ArrayList<>();
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
			PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSQL);
			selectPreparedStatement.setString(1, token);
			
			ResultSet rs = selectPreparedStatement.executeQuery();
			while (rs.next()) {
				productIds.add(rs.getString("product_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return productIds;
	}
	
	public boolean verifyEntitlementByTokenAndProductId(String token, String productId) throws UnauthorizedException {
		
		String selectSQL = "select e.product_id from entitlement e inner join customer c on e.customer_id = c.id where c.authToken = ? AND e.product_id = ?";
		authenticateByToken(token);
		Connection connection;
		try {
			connection = DataSourceFactory.getDataSource().getConnection();
			PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSQL);
			selectPreparedStatement.setString(1, token);
			selectPreparedStatement.setString(2, productId);
			
			ResultSet rs = selectPreparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return false;
	}
	
	private String generateToken(String user, String password) {
		return Base64.getEncoder().encodeToString((System.currentTimeMillis() + user + password).getBytes());
	}
}
