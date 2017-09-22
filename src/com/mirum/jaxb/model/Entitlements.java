package com.mirum.jaxb.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entitlements {
	
	@XmlElement(name="productId")
	private List<String> productIds;
	
	public Entitlements() {
		// TODO Auto-generated constructor stub
		productIds = new ArrayList<>();
	}

	public Entitlements(List<String> productIds) {
		super();
		this.productIds = productIds;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}
	
}
