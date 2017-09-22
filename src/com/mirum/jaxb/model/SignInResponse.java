package com.mirum.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class SignInResponse extends Response {

	private String authToken;
	
	public SignInResponse() {
		// TODO Auto-generated constructor stub
	}

	public SignInResponse(int httpResponseCode, String authToken) {
		super(httpResponseCode);
		// TODO Auto-generated constructor stub
		this.authToken = authToken;
	}

	public String getAuthToken() {
		return authToken;
	}
	
	@XmlElement
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
