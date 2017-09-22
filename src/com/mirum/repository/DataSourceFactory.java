package com.mirum.repository;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceFactory {

	private static BasicDataSource basicDataSource;
	
	public static BasicDataSource getDataSource() {
		
		if (basicDataSource == null) {
			
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
			basicDataSource.setUrl("jdbc:mysql://localhost:3306/entitlement_api");
			basicDataSource.setUsername("root");
			basicDataSource.setPassword("root");
			
			basicDataSource.setMinIdle(5);
			basicDataSource.setMaxIdle(10);
		}
		
		return basicDataSource;
	}

}
