package com.mirum.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials {

	private String emailAddress;
	private String password;
	
	public Credentials() {
		// TODO Auto-generated constructor stub
	}

	public Credentials(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	@XmlElement
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}

	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}
}
