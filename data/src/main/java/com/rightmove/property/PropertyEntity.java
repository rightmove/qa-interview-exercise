package com.rightmove.property;

public class PropertyEntity {
	private final long reference;
	private final long price;
	private final int bedrooms;
	private final Integer bathrooms;
	private final String number;
	private final String address;
	private final String region;
	private final String postcode;
	private final String type;


	public PropertyEntity(long reference, long price, int bedrooms, Integer bathrooms, String number, String address, String region, String postcode, String type) {
		this.reference = reference;
		this.price = price;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.number = number;
		this.address = address;
		this.region = region;
		this.postcode = postcode;
		this.type = type;
	}

	public long getReference() {
		return reference;
	}

	public long getPrice() {
		return price;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public Integer getBathrooms() {
		return bathrooms;
	}

	public String getNumber() {
		return number;
	}

	public String getAddress() {
		return address;
	}

	public String getRegion() {
		return region;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getType() {
		return type;
	}
}
