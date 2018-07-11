package com.rightmove.datasetup;

import com.rightmove.property.PropertyEntity;
import com.rightmove.property.PropertyType;

public class PropertyEntityBuilder {

	private long reference;
	private long price;
	private int bedrooms;
	private Integer bathrooms;
	private String number;
	private String address;
	private String region;
	private String postcode;
	private PropertyType type;

	private PropertyEntityBuilder() {

	}

	public static PropertyEntityBuilder aDefaultPropertyEntity() {
		return new PropertyEntityBuilder()
				.reference(1234L)
				.price(1_000_000L)
				.bedrooms(2)
				.bathrooms(1)
				.number("33")
				.address("Soho Square")
				.region("Soho")
				.postcode("E2  8RS")
				.type(PropertyType.DETACHED);
	}

	public PropertyEntityBuilder reference(long reference) {
		this.reference = reference;
		return this;
	}

	public PropertyEntityBuilder price(long price) {
		this.price = price;
		return this;
	}

	public PropertyEntityBuilder bedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
		return this;
	}

	public PropertyEntityBuilder bathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
		return this;
	}

	public PropertyEntityBuilder number(String number) {
		this.number = number;
		return this;
	}

	public PropertyEntityBuilder address(String address) {
		this.address = address;
		return this;
	}

	public PropertyEntityBuilder region(String region) {
		this.region = region;
		return this;
	}

	public PropertyEntityBuilder postcode(String postcode) {
		this.postcode = postcode;
		return this;
	}

	public PropertyEntityBuilder type(PropertyType type) {
		this.type = type;
		return this;
	}

	public PropertyEntity build() {
		return new PropertyEntity(reference, price, bedrooms, bathrooms, number, address, region, postcode, type);
	}
}