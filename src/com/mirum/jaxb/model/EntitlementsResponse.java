package com.mirum.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class EntitlementsResponse extends Response {

	private Entitlements entitlements;
	
	public EntitlementsResponse() {
		// TODO Auto-generated constructor stub
	}

	public EntitlementsResponse(int httpResponseCode, Entitlements entitlements) {
		super(httpResponseCode);
		// TODO Auto-generated constructor stub
		this.entitlements = entitlements;
	}

	public Entitlements getEntitlements() {
		return entitlements;
	}

	public void setEntitlements(Entitlements entitlements) {
		this.entitlements = entitlements;
	}
	
}
