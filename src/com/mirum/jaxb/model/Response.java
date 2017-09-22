package com.mirum.jaxb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class Response {

	private int httpResponseCode;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}

	public Response(int httpResponseCode) {
		super();
		this.httpResponseCode = httpResponseCode;
	}

	public int getHttpResponseCode() {
		return httpResponseCode;
	}
	
	@XmlAttribute
	public void setHttpResponseCode(int httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
	}
}
