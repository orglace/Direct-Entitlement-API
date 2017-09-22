package com.mirum.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class VerifyEntitlementResponse extends Response {

	private boolean entitled;
	
	public VerifyEntitlementResponse() {
		// TODO Auto-generated constructor stub
	}

	public VerifyEntitlementResponse(int httpResponseCode, boolean entitled) {
		super(httpResponseCode);
		// TODO Auto-generated constructor stub
		this.entitled = entitled;
	}

	public boolean isEntitled() {
		return entitled;
	}
	
	@XmlElement
	public void setEntitled(boolean entitled) {
		this.entitled = entitled;
	}
}
